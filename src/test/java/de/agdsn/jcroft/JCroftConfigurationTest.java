package de.agdsn.jcroft;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class JCroftConfigurationTest {

    @Test
    public void testConstructor () {
        new JCroftConfiguration();
    }

    @Test
    public void testReadConfig () throws IOException {
        JCroftConfiguration.readConfig();

        assertEquals(true, JCroftConfiguration.getValue("ldap_host").equals("idm0.agdsn.network") || JCroftConfiguration.getValue("ldap_host").equals("localhost"));
        assertEquals(389, JCroftConfiguration.getValueInt("ldap_port"));
    }

}
