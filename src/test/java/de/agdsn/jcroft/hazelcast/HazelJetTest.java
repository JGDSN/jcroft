package de.agdsn.jcroft.hazelcast;

import org.junit.Test;


public class HazelJetTest {

    @Test
    public void testStartupAndShutdown () {
        JetProvider jetProvider = new JetProvider();
        jetProvider.getCluster().shutdown();
    }
}
