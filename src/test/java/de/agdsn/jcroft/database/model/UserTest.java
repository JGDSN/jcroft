package de.agdsn.jcroft.database.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    @Test
    public void testConstructor () {
        new User();
    }

    @Test
    public void testConstructor1 () {
        User user = new User("Max", "Mustermann");

        assertEquals("Max", user.getFirstName());
        assertEquals("Mustermann", user.getLastName());
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
        Date registered = new Date();

        user.setId(2);
        user.setFirstName("Max");
        user.setLastName("Mustermann");
        user.setUsername("max123");
        user.setPasswordHash("my-password-hash");
        user.setRegistered(registered);

        assertEquals(2, user.getId());
        assertEquals("Max", user.getFirstName());
        assertEquals("Mustermann", user.getLastName());
        assertEquals("max123", user.getUsername());
        assertEquals("my-password-hash", user.getPasswordHash());
        assertEquals(registered, user.getRegistered());

        assertNotNull(user.toString());
    }

}
