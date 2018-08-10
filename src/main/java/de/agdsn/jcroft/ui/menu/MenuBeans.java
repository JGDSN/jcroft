package de.agdsn.jcroft.ui.menu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MenuBeans {
    @Bean
    MenuStructure menuStructure(){
        return new MenuStructure();
    }
}
