package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SwitchTest {

    @Test
    public void testConstructor () {
        new Switch();
    }

    @Test
    public void testConstructor1 () {
        new Switch("switch-1", "10.0.0.1");
    }

    @Test (expected = NullPointerException.class)
    public void testNullNameConstructor () {
        new Switch(null, "10.0.0.1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyNameConstructor () {
        new Switch("", "10.0.0.1");
    }

    @Test (expected = NullPointerException.class)
    public void testNullIPConstructor () {
        new Switch("switch-1", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyIPConstructor () {
        new Switch("switch-1", "");
    }

    @Test
    public void testGetterAndSetter () {
        Switch switch1 = new Switch("switch-1", "10.0.0.2");

        assertEquals(0, switch1.getId());
        assertEquals("switch-1", switch1.getName());
        assertEquals("10.0.0.2", switch1.getManagementIP());

        switch1.setName("test");
        switch1.setManagementIP("10.0.0.1");

        assertEquals("test", switch1.getName());
        assertEquals("10.0.0.1", switch1.getManagementIP());
    }

}
