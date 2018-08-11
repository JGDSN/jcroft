package de.agdsn.jcroft.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This class is here to provide custom error pages.
 * It also issues redirects to the login page from the ajax page, when a session is no longer valid (401 errors)
 */
@Controller
public class HttpErrorController implements ErrorController {

    public static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error(HttpServletRequest request, Model model, HttpServletResponse response) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String title = "Unknown error";
        String text = "Unknown error";
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            HttpStatus s = HttpStatus.resolve(statusCode);
            title = s.value()+" "+s.getReasonPhrase();
            Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
            if(message!=null){
                text = message.toString()+" ";
            }
            text += "If you believe that this is in error, please open a bug report!";
        }
        response.setStatus(HttpStatus.OK.value());
        model.addAttribute("title", title);
        model.addAttribute("text", text);
        return "component/error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}