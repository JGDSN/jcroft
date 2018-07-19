package de.agdsn.jcroft.hazelcast;

import de.agdsn.jcroft.JCroftConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HazelJetTest {

    @Test
    public void testStartupAndShutdown () {
        JetProvider jetProvider = new JetProvider();
        jetProvider.getCluster().shutdown();
    }
}
