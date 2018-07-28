package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GroupTest {

    @Test
    public void testConstructor () {
        new Group();
    }

    @Test
    public void testConstructor1 () {
        new Group("my-group-name");
    }

    @Test (expected = NullPointerException.class)
    public void testNullConstructor () {
        new Group(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyConstructor () {
        new Group("");
    }

    @Test
    public void testGetters () {
        Group group = new Group("test");
        group.id = 10;

        assertEquals(10, group.getId());
        assertEquals("test", group.getName());
    }

    @Test
    public void testProperties () {
        Group group = new Group("test");

        assertEquals(false, group.containsProperty("test-token"));

        group.setProperty("test-token", "test");
        assertEquals(true, group.containsProperty("test-token"));
        assertEquals("test", group.getProperty("test-token"));

        //test override
        group.setProperty("test-token", "value");
        assertEquals("value", group.getProperty("test-token"));

        group.setProperty("test-token2", "test2");
        assertEquals(true, group.containsProperty("test-token"));
        assertEquals(true, group.containsProperty("test-token2"));
        assertEquals("test2", group.getProperty("test-token2"));

        //test with integer
        group.setProperty("test", 10);
        assertEquals(10, group.getPropertyInt("test"));
    }

}
