package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ActorLogEntryTest {

    @Test
    public void testConstructor () {
        new ActorLogEntry(1, new Actor(ActorType.USER), "test");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullActorIDConstructor () {
        new ActorLogEntry(0, new Actor(ActorType.USER), "test");
    }

    @Test (expected = NullPointerException.class)
    public void testNullActorConstructor () {
        new ActorLogEntry(1, null, "test");
    }

    @Test (expected = NullPointerException.class)
    public void testNullMessageConstructor () {
        new ActorLogEntry(1, new Actor(ActorType.USER), null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyMessageConstructor () {
        new ActorLogEntry(1, new Actor(ActorType.USER), "");
    }

    @Test
    public void testGetter () {
        Actor author = new Actor(ActorType.USER);
        author.id = 10;
        ActorLogEntry entry = new ActorLogEntry(1, author, "test-message");

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
