package de.agdsn.jcroft.api;

import de.agdsn.jcroft.api.v1.APIv1Handler;
import de.agdsn.jcroft.api.v1.token.APIv1UserLogoutHandler;
import de.agdsn.jcroft.api.v1.token.APIv1UserTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIBeans {
    @Bean
    APIv1Handler apiVersion1(){
        return new APIv1Handler();
    }
    @Bean
    APIv1UserTokenRepository apiVersion1UserTokenRepository(){
        return new APIv1UserTokenRepository();
    }

    @Bean
    APIv1UserLogoutHandler apiVersion1UserLogoutHandler(){
        return new APIv1UserLogoutHandler();
    }
}
