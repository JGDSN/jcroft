package de.agdsn.jcroft.controller;

import de.agdsn.jcroft.api.v1.token.APIv1UserToken;
import de.agdsn.jcroft.api.v1.token.APIv1UserTokenRepository;
import de.agdsn.jcroft.database.data.ActorRepository;
import de.agdsn.jcroft.database.data.UserRepository;
import de.agdsn.jcroft.database.model.Actor;
import de.agdsn.jcroft.database.model.User;
import de.agdsn.jcroft.database.model.enums.ActorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    APIv1UserTokenRepository apIv1UserTokenRepository;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        //TODO Remove user creation on login (DANGEROUS!)
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

        model.addAttribute("username", authentication.getName());
        model.addAttribute("usercaption", "Team JCroft");
        model.addAttribute("api_token", token.getToken());
        return "index";
    }
}