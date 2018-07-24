package de.agdsn.jcroft.controller;

import de.agdsn.jcroft.database.data.UserRepository;
import de.agdsn.jcroft.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String index() {
        return "index";
    }
}