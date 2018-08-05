package de.agdsn.jcroft.controller;

import de.agdsn.jcroft.api.v1.token.APIv1UserTokenRepository;
import de.agdsn.jcroft.database.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class SessionController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    APIv1UserTokenRepository apIv1UserTokenRepository;

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) return "redirect:/";
        Map<String, String[]> paramMap = request.getParameterMap();

        if (paramMap.containsKey("error")) {
            model.addAttribute("error", "Invalid credentials!");
        }
        return "login";
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccess(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) return "redirect:/";

        model.addAttribute("msg", "You logged out successfully.");
        return "logout";
    }

    @GetMapping("/sessionExpired")
    public String sessionExpired(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) return "redirect:/";

        model.addAttribute("msg", "Your session expired.");
        return "logout";
    }

    @GetMapping("/sessionInvalid")
    public String sessionInvalid(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) return "redirect:/";

        model.addAttribute("msg", "Your session could not be validated.");
        return "logout";
    }
}