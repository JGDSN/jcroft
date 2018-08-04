package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ActorLogEntryTest {

    @Test
    public void testConstructor () {
        Actor actor = new Actor();
        actor.id = 1;
        new ActorLogEntry(actor, new Actor(ActorType.USER), "test");
    }

    @Test
    public void testConstructor1 () {
        new ActorLogEntry();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullActorIDConstructor () {
        Actor actor = new Actor();
        actor.id = 0;
        new ActorLogEntry(actor, new Actor(ActorType.USER), "test");
    }

    @Test (expected = NullPointerException.class)
    public void testNullActorConstructor () {
        Actor actor = new Actor();
        actor.id = 1;
        new ActorLogEntry(actor, null, "test");
    }

    @Test (expected = NullPointerException.class)
    public void testNullMessageConstructor () {
        Actor actor = new Actor();
        actor.id = 1;
        new ActorLogEntry(actor, new Actor(ActorType.USER), null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyMessageConstructor () {
        Actor actor = new Actor();
        actor.id = 1;
        new ActorLogEntry(actor, new Actor(ActorType.USER), "");
    }

    @Test
    public void testGetter () {
        Actor actor = new Actor();
        actor.id = 1;

        Actor author = new Actor(ActorType.USER);
        author.id = 10;
        ActorLogEntry entry = new ActorLogEntry(actor, author, "test-message");

        assertEquals(0, entry.getId());
        assertEquals(1, entry.getActorID());
        assertEquals(10, entry.getAuthorId());

        assertNotNull(entry.getMessage());
        assertEquals(false, entry.getMessage().isEmpty());
        assertEquals("test-message", entry.getMessage());

        //date isnt set yet
        assertEquals(null, entry.getDate());
    }

}
