package de.agdsn.jcroft;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class JCroftConfigurationTest {

    @Before
    public void before() throws IOException {
        File conf = new File("settings/jcroft1.cfg");

        if (conf.exists()) {
            conf.delete();
        }

        JCroftConfiguration.createDefaultConfiguration(conf);

        //delete junit files
        File conf1 = new File("settings/jcroft.junit.cfg");

        if (conf1.exists()) {
            conf1.delete();
        }
    }

    @After
    public void after() {
        new File("settings/jcroft.junit1.cfg").delete();
        new File("settings/jcroft1.cfg").delete();
    }

    @Test
    public void testConstructor() {
        new JCroftConfiguration();
    }

    @Test (expected = NullPointerException.class)
    public void testGetNullKey () {
        JCroftConfiguration.getValue(null);
    }

    @Test
    public void testContains () {
        assertEquals(false, JCroftConfiguration.contains("not-existent-key"));
    }

    @Test (expected = IllegalStateException.class)
    public void testGetNotExistentConfigKey () {
        JCroftConfiguration.getValue("not-existent-key");
    }

    @Test
    public void testReadConfig() throws IOException {
        File conf = new File("settings/jcroft1.cfg");
        JCroftConfiguration.readConfig(conf);

        assertEquals(true, JCroftConfiguration.getValue("ldap_host").equals("idm0.agdsn.network") || JCroftConfiguration.getValue("ldap_host").equals("localhost"));
        assertEquals(389, JCroftConfiguration.getValueInt("ldap_port"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadConfig1() throws IOException {
        File conf = new File("settings/jcroft.junit.cfg");
        JCroftConfiguration.readConfig(conf);
    }

    @Test(expected = IllegalStateException.class)
    public void testReadConfig2() throws IOException {
        File conf = new File("src/test/resources/jcroft2.cfg");
        JCroftConfiguration.readConfig(conf);
    }

}
