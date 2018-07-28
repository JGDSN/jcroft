package de.agdsn.jcroft.api;

import de.agdsn.jcroft.api.v1.APIv1Handler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIBeans {
    @Bean
    APIv1Handler apiVersion1(){
        return new APIv1Handler();
    }
}
