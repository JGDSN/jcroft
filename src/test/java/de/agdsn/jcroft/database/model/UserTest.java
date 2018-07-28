package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserTest {

    @Test
    public void testConstructor () {
        User user = new User("Max", "Mustermann", createActor());

        assertEquals("Max", user.getFirstName());
        assertEquals("Mustermann", user.getLastName());
    }

    @Test
    public void testConstructor1 () {
        new User();
    }

    @Test (expected = NullPointerException.class)
    public void testNullFNameConstructor () {
        new User(null, "test", createActor());
    }

    @Test (expected = NullPointerException.class)
    public void testNullLNameConstructor () {
        new User("test", null, createActor());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyFNameConstructor () {
        new User("", "test", createActor());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyLNameConstructor () {
        new User("test", "", createActor());
    }

    @Test (expected = NullPointerException.class)
    public void testNullActorConstructor () {
        User user = new User("Max", "Mustermann", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testInvalideActorTypeConstructor () {
        User user = new User("Max", "Mustermann", new Actor(ActorType.SERVICE));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetNullID () {
        User user = new User("max", "mustermann", createActor());
        user.setId(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetNegativeID () {
        User user = new User("max", "mustermann", createActor());
        user.setId(-1);
    }

    @Test
    public void testGetterAndSetter () {
        User user = new User("test", "test", createActor());
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

        assertNull(user.getRoom());
        assertEquals(20, user.getActorId());

        assertNotNull(user.toString());
    }

    protected static Actor createActor () {
        Actor actor = new Actor(ActorType.USER);
        actor.id = 20;

        return actor;
    }

}
