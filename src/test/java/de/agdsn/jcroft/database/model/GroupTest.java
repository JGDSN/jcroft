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

}
