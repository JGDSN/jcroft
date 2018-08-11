package de.agdsn.jcroft.controller;

import de.agdsn.jcroft.api.v1.token.APIv1UserTokenRepository;
import de.agdsn.jcroft.database.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class SessionController {

    public static final String REDIRECT_INDEX = "redirect:/";
    public static final String NO_SESSION_PAGE = "nosession";
    public static final String ERROR_PARAM = "error";

    @Autowired
    UserRepository userRepository;
    @Autowired
    APIv1UserTokenRepository apIv1UserTokenRepository;

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model, Authentication authentication, @RequestParam(required=false, defaultValue = "/p/")String from) {
        if (authentication != null && authentication.isAuthenticated()) return REDIRECT_INDEX+from;
        Map<String, String[]> paramMap = request.getParameterMap();

        if (paramMap.containsKey(ERROR_PARAM)) {
            String[] errorParts = paramMap.get(ERROR_PARAM);
            String error = "";
            if(errorParts!=null&&errorParts.length>=1){
                StringBuilder builder = new StringBuilder(errorParts[0]);
                for(int i = 1; i < errorParts.length; i++)builder.append(" ").append(errorParts[i]);
                error = builder.toString().trim();
            }
            if(error.isEmpty())error = "Invalid credentials";
            model.addAttribute(ERROR_PARAM, error);
        }
        model.addAttribute("from", from);
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

        return "invalidsession";
    }
}