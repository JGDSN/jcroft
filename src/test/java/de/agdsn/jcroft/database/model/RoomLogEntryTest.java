package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RoomLogEntryTest {

    @Test
    public void testConstructor () {
        new RoomLogEntry();
    }

    @Test
    public void testConstructor1 () {
        new RoomLogEntry(new Room(), new Actor(ActorType.USER), "test message");
    }

    @Test
    public void testGetterAndSetter () {
        RoomLogEntry entry = new RoomLogEntry(new Room(), new Actor(ActorType.USER), "test message");

        assertEquals(0, entry.getId());
        assertNotNull(entry.getRoom());
        assertNotNull(entry.getAuthor());
        assertEquals("test message", entry.getMessage());
        assertNull(entry.getDate());//date will be set from hibernate

        //set message
        entry.setMessage("test");
        assertEquals("test", entry.getMessage());
    }

}
