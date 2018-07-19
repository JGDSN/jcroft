package de.agdsn.jcroft;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class JCroftConfigurationTest {

    @BeforeClass
    public static void beforeClass () throws IOException {
        File conf = new File("settings/jcroft.cfg");

        if (conf.exists()) {
            JCroftConfiguration.createDefaultConfiguration(conf);
        }
    }

    @Test
    public void testConstructor () {
        new JCroftConfiguration();
    }

    @Test
    public void testReadConfig () throws IOException {
        File conf = new File("settings/jcroft.cfg");
        
        JCroftConfiguration.readConfig(conf);

        assertEquals(true, JCroftConfiguration.getValue("ldap_host").equals("idm0.agdsn.network") || JCroftConfiguration.getValue("ldap_host").equals("localhost"));
        assertEquals(389, JCroftConfiguration.getValueInt("ldap_port"));
    }

}
