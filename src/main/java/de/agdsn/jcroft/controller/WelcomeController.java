package de.agdsn.jcroft.controller;

import de.agdsn.jcroft.database.data.UserRepository;
import de.agdsn.jcroft.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        User user = new User("Moin", "Test");
        user = userRepository.save(user);
        return "index";
    }
}