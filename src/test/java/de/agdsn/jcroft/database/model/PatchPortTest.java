package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PatchPortTest {

    @Test
    public void testConstructor () {
        new PatchPort();
    }

    @Test
    public void testConstructor1 () {
        new PatchPort(new Room());
    }

    @Test (expected = NullPointerException.class)
    public void testNullRoomConstructor () {
        new PatchPort(null);
    }

    @Test
    public void testGetterAndSetter () {
        Room room = new Room();
        PatchPort port = new PatchPort(room);

        assertEquals(0, port.getId());
        assertEquals(room, port.getRoom());

        //set new room
        Room room1 = new Room();
        port.setRoom(room1);
        assertNotEquals(room, port.getRoom());
        assertEquals(room1, port.getRoom());
    }

}
