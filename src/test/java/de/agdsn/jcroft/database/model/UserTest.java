package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    @Test
    public void testConstructor () {
        new User();
    }

    @Test (expected = NullPointerException.class)
    public void testNullFNameConstructor () {
        new User(null, "test");
    }

    @Test (expected = NullPointerException.class)
    public void testNullLNameConstructor () {
        new User("test", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyFNameConstructor () {
        new User("", "test");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyLNameConstructor () {
        new User("test", "");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetNullID () {
        User user = new User();
        user.setId(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetNegativeID () {
        User user = new User();
        user.setId(-1);
    }

    @Test
    public void testGetterAndSetter () {
        User user = new User();

        user.setId(2);
        user.setFirstName("Max");
        user.setLastName("Mustermann");

        assertEquals(2, user.getId());
        assertEquals("Max", user.getFirstName());
        assertEquals("Mustermann", user.getLastName());

        assertNotNull(user.toString());
    }

}
