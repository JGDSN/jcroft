package de.agdsn.jcroft;

import de.agdsn.jcroft.hazelcast.JetProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@ComponentScan
public class Application {

    @Autowired
    private JetProvider jetProvider;

    public static final Logger MAIN_LOGGER = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        try {
            JCroftConfiguration.readConfig(new File("settings/jcroft.cfg"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        //Start the spring web service
        SpringApplication.run(Application.class, args);
    }

    public JetProvider getJetProvider() {
        return jetProvider;
    }
}