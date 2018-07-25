package de.agdsn.jcroft.controller;

import de.agdsn.jcroft.database.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("usercaption", "Team JCroft");
        return "index";
    }
}