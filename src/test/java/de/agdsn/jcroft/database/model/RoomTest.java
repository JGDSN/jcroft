package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {

    @Test
    public void testConstructor () {
        new Room();
    }

    @Test
    public void testConstructor1 () {
        new Room(new Building(), "0124", 2);
    }

    @Test (expected = NullPointerException.class)
    public void testNullBuildingConstructor () {
        new Room(null, "0124", 2);
    }

    @Test (expected = NullPointerException.class)
    public void testNullRoomNumberConstructor () {
        new Room(new Building(), null, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyRoomNumberConstructor () {
        new Room(new Building(), "", 2);
    }

    @Test
    public void testGetterAndSetter () {
        Building building = new Building();
        Room room = new Room(building, "012", 2);

        assertEquals(0, room.getId());
        assertNotNull(room.getBuilding());
        assertEquals(building, room.getBuilding());
        assertEquals("012", room.getRoomNumber());
        assertEquals(2, room.getFloor());
        assertEquals(true, room.isInhabitable());
        assertEquals(false, room.isInhabited());
        assertNull(room.getUser());
        assertEquals(0, room.listPatchPorts().size());

        //set values
        room.setBuilding(new Building());
        room.setRoomNumber("123");
        room.setFloor(1);
        room.setInhabitable(false);
        User user = new User();
        room.setUser(user);

        //get values
        assertNotNull(room.getBuilding());
        assertNotEquals(building, room.getBuilding());
        assertEquals("123", room.getRoomNumber());
        assertEquals(1, room.getFloor());
        assertEquals(false, room.isInhabitable());
        assertEquals(true, room.isInhabited());
        assertEquals(user, room.getUser());

        //add patch ports
        PatchPort port = new PatchPort(room);
        room.addPatchPort(port);
        room.addPatchPort(new PatchPort(room));
        assertEquals(2, room.listPatchPorts().size());

        room.removePatchPort(port);
        assertEquals(1, room.listPatchPorts().size());

        room.removeAllPatchPorts();
        assertEquals(0, room.listPatchPorts().size());
    }

}
