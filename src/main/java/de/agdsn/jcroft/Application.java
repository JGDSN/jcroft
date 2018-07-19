package de.agdsn.jcroft;

import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@ComponentScan
public class Application {

    public static JetInstance CLUSTER;
    public static final Logger MAIN_LOGGER = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        try {
            JCroftConfiguration.readConfig(new File("settings/jcroft.cfg"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        //Start HazelJet cluster
        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                CLUSTER.shutdown();
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    MAIN_LOGGER.warn("Shutdown didn't complete upon exit. Please shut down using CTRL+C");
                }
            }
        });
        CLUSTER = Jet.newJetInstance();
        //Start the spring web service
        SpringApplication.run(Application.class, args);
    }

}