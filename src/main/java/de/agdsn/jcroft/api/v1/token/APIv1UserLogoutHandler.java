package de.agdsn.jcroft.api.v1.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class APIv1UserLogoutHandler extends
        SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    APIv1UserTokenRepository apIv1UserTokenRepository;

    public APIv1UserLogoutHandler(){
        setDefaultTargetUrl("/logoutSuccess");
    }

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        //Revoke user token
        if (authentication != null && authentication.isAuthenticated()){
            apIv1UserTokenRepository.revoke(authentication.getName());
        }

        //Clear session cookie to avoid invalidation
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        super.onLogoutSuccess(request, response, authentication);
    }
}
