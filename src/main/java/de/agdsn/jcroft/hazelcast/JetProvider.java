package de.agdsn.jcroft.hazelcast;

import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import de.agdsn.jcroft.Application;
import org.springframework.stereotype.Component;

@Component
public class JetProvider {
    private JetInstance cluster;

    public JetProvider(){
        cluster = buildJetProvider();
    }

    private JetInstance buildJetProvider() {
        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                cluster.shutdown();
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    Application.MAIN_LOGGER.warn("Shutdown didn't complete upon exit. Please shut down using CTRL+C");
                    Thread.currentThread().interrupt();
                }
            }
        });
        return Jet.newJetInstance();
    }

    public JetInstance getCluster(){
        return cluster;
    }
}
