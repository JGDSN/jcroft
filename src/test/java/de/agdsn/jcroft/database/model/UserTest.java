package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testConstructor () {
        User user = new User("Max", "Mustermann", "test", "max@musterdomain.de", createActor());

        assertEquals("Max", user.getFirstName());
        assertEquals("Mustermann", user.getLastName());
    }

    @Test
    public void testConstructor1 () {
        new User();
    }

    @Test (expected = NullPointerException.class)
    public void testNullFNameConstructor () {
        new User(null, "test", "test", "max@musterdomain.de", createActor());
    }

    @Test (expected = NullPointerException.class)
    public void testNullLNameConstructor () {
        new User("test", null, "test", "max@musterdomain.de", createActor());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyFNameConstructor () {
        new User("", "test", "test", "max@musterdomain.de", createActor());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyLNameConstructor () {
        new User("test", "", "test", "max@musterdomain.de", createActor());
    }

    @Test (expected = NullPointerException.class)
    public void testNullUsernameConstructor () {
        new User("test", "test", null, "max@musterdomain.de", createActor());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyUsernameConstructor () {
        new User("test", "test", "", "max@musterdomain.de", createActor());
    }

    @Test (expected = NullPointerException.class)
    public void testNullActorConstructor () {
        User user = new User("Max", "Mustermann", "test", "max@musterdomain.de", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testInvalideActorTypeConstructor () {
        User user = new User("Max", "Mustermann", "test", "max@musterdomain.de", new Actor(ActorType.SERVICE));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetNullID () {
        User user = new User("max", "mustermann", "test", "max@musterdomain.de", createActor());
        user.setId(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetNegativeID () {
        User user = new User("max", "mustermann", "test", "max@musterdomain.de", createActor());
        user.setId(-1);
    }

    @Test
    public void testGetterAndSetter () {
        User user = new User("test", "test", "test", "max@musterdomain.de", createActor());
        Date registered = new Date();

        user.setId(2);
        user.setFirstName("Max");
        user.setLastName("Mustermann");
        user.setUsername("max123");
        user.setPasswordHash("my-password-hash");
        user.setRegistered(registered);
        user.setEmail("test@example.com");

        assertEquals(2, user.getId());
        assertEquals("Max", user.getFirstName());
        assertEquals("Mustermann", user.getLastName());
        assertEquals("max123", user.getUsername());
        assertEquals("my-password-hash", user.getPasswordHash());
        assertEquals(registered, user.getRegistered());
        assertEquals("test@example.com", user.getEmail());

        assertNull(user.getRoom());
        assertEquals(false, user.hasRoom());

        user.room = new Room();
        assertEquals(true, user.hasRoom());

        assertEquals(20, user.getActorId());

        assertNotNull(user.toString());
    }

    @Test
    public void testEquals () {
        //create actor
        Actor actor = createActor();

        User user = new User("Max", "Mustermann", "test", "test@musterdomain.de", actor);
        User user1 = new User("Max", "Mustermann", "test", "test@musterdomain.de", actor);

        user.setId(10);
        user1.setId(10);

        //same instance
        assertEquals(user, user);

        //check null
        assertNotEquals(user, null);

        //check other instance
        assertNotEquals(user, "test string");

        //same values
        assertEquals(user, user1);

        user1.setEmail("test@test.de");
        assertNotEquals(user, user1);

        user1.setEmail("test@musterdomain.de");
        assertEquals(user, user1);
    }

    @Test
    public void testHashCode () {
        //create actor
        Actor actor = createActor();

        User user = new User("Max", "Mustermann", "test", "test@musterdomain.de", actor);
        User user1 = new User("Max", "Mustermann", "test", "test@musterdomain.de", actor);

        assertEquals(user.hashCode(), user1.hashCode());
    }

    protected static Actor createActor () {
        Actor actor = new Actor(ActorType.USER);
        actor.id = 20;

        return actor;
    }

}
