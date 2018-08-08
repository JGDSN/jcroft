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

    public static final String REDIRECT_INDEX = "redirect:/";
    public static final String NO_SESSION_PAGE = "nosession";

    @Autowired
    UserRepository userRepository;
    @Autowired
    APIv1UserTokenRepository apIv1UserTokenRepository;

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) return REDIRECT_INDEX;
        Map<String, String[]> paramMap = request.getParameterMap();

        if (paramMap.containsKey("error")) {
            model.addAttribute("error", "Invalid credentials!");
        }
        return "login";
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccess(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) return REDIRECT_INDEX;

        model.addAttribute("msg", "You logged out successfully.");
        return NO_SESSION_PAGE;
    }

    @GetMapping("/sessionExpired")
    public String sessionExpired(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) return REDIRECT_INDEX;

        model.addAttribute("msg", "Your session expired. Please remember to log out after every visit!");
        return NO_SESSION_PAGE;
    }

    @GetMapping("/sessionInvalid")
    public String sessionInvalid(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) return REDIRECT_INDEX;
        return "redirect:/login";
    }
}