package de.agdsn.jcroft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@ComponentScan
public class Application {

    public static void main(String[] args) {
        try {
            JCroftConfiguration.readConfig();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        SpringApplication.run(Application.class, args);
    }

}