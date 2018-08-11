package de.agdsn.jcroft.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurityBeans {
    @Bean
    LoginHandler loginHandler(){
        return new LoginHandler();
    }

    @Bean
    IAuthenticationEntryPoint authenticationEntryPoint(){
        return new IAuthenticationEntryPoint();
    }

    @Bean
    IAccessDeniedHandler accessDeniedHandler(){
        return new IAccessDeniedHandler();
    }
}
