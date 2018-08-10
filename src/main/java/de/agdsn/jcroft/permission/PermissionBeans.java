package de.agdsn.jcroft.permission;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PermissionBeans {
    @Bean
    PermissionManager permissionManager(){
        return new PermissionManager();
    }
}
