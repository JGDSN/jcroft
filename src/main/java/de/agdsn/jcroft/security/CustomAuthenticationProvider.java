package de.agdsn.jcroft.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CustomAuthenticationProvider
        implements AuthenticationProvider {

    protected Logger logger = Logger.getLogger(CustomAuthenticationProvider.class.getName());

    @Override
    public Authentication authenticate(Authentication authentication) {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            LDAPConnection.check(name, password);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Login exception", e);
            throw e;
        }

        logger.log(Level.INFO, "user logged in successfully!");

        return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}