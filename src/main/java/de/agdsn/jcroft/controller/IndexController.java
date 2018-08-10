package de.agdsn.jcroft.controller;

import de.agdsn.jcroft.Application;
import de.agdsn.jcroft.api.v1.token.APIv1UserToken;
import de.agdsn.jcroft.api.v1.token.APIv1UserTokenRepository;
import de.agdsn.jcroft.database.data.ActorRepository;
import de.agdsn.jcroft.database.data.UserRepository;
import de.agdsn.jcroft.database.model.Actor;
import de.agdsn.jcroft.database.model.User;
import de.agdsn.jcroft.database.model.enums.ActorType;
import de.agdsn.jcroft.permission.PermissionSet;
import de.agdsn.jcroft.ui.menu.MenuStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    APIv1UserTokenRepository apIv1UserTokenRepository;
    @Autowired
    MenuStructure menuStructure;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String index() {
        return "redirect:/p/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/p/**")
    public String page(Model model, Authentication authentication, HttpServletRequest request) {
        //TODO Remove user creation on login (DANGEROUS!)
        //Fetch User & API Token
        Optional<User> ou = userRepository.findByUsername(authentication.getName());
        User user;
        if(ou.isPresent()) {
            user = ou.get();
        }else{
            Actor actor = new Actor(ActorType.USER);
            actor = actorRepository.save(actor);
            user = new User("Test", "Gandalf", authentication.getName(), "le.trol@web.xxx", actor);
            user = userRepository.save(user);
        }
        APIv1UserToken token = apIv1UserTokenRepository.lookupByUser(authentication.getName());
        if(token==null)token = apIv1UserTokenRepository.issue(authentication.getName(), user.getId());

        String path = request.getRequestURI().substring("/p".length());
        if(path.isEmpty())path = "/";
        if(!path.endsWith("/"))path = path + "/";

        //Calculate version message
        String version = "<b>Version</b> "+Application.version+" <b>Build</b> "+Application.build;

        //Calculate permissions
        PermissionSet permissionSet = user.getActor().getPermissions();

        //Build menu structure (depending on permissions)
        String menu = menuStructure.toHTML(permissionSet);


        model.addAttribute("username", authentication.getName());
        model.addAttribute("usercaption", "Team JCroft");
        model.addAttribute("api_token", token.getToken());
        model.addAttribute("init_path", path);
        model.addAttribute("version", version);
        model.addAttribute("menu", menu);
        return "index";
    }
}