package de.agdsn.jcroft.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider
        implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            LDAPConnection.check(name, password);
        } catch (Exception e) {
            throw e;
        }

        return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}