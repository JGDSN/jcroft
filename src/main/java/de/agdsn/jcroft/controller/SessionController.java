package de.agdsn.jcroft.controller;

import de.agdsn.jcroft.database.data.UserRepository;
import de.agdsn.jcroft.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class SessionController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model, Authentication authentication) {
        if(authentication!=null&&authentication.isAuthenticated())return "redirect:/";
        Map<String, String[]> paramMap = request.getParameterMap();

        if (paramMap.containsKey("error")) {
            model.addAttribute("error", "Invalid credentials!");
        }
        return "login";
    }
}