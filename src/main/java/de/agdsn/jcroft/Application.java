package de.agdsn.jcroft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@ComponentScan
public class Application {

    public static void main(String[] args) {
        try {
            JCroftConfiguration.readConfig(new File("settings/jcroft.cfg"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        SpringApplication.run(Application.class, args);
    }

}