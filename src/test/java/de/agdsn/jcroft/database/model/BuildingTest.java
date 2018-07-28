package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BuildingTest {

    @Test
    public void testConstructor () {
        new Building();
    }

    @Test
    public void testConstructor1 () {
        new Building("Building 1", "Musterstraße 22");
    }

    @Test (expected = NullPointerException.class)
    public void testNullNameConstructor () {
        new Building(null, "Musterstraße 22");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyNameConstructor () {
        new Building("", "Musterstraße 22");
    }

    @Test (expected = NullPointerException.class)
    public void testNullStreetConstructor () {
        new Building("Building 1", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyStreetConstructor () {
        new Building("Building 1", "");
    }

    @Test
    public void testGetterAndSetter () {
        Building building = new Building("Building 1", "Musterstraße 22");

        assertEquals(0, building.getId());
        assertEquals("Building 1", building.getName());
        assertEquals("Musterstraße 22", building.getStreet());
        assertEquals(null, building.getDefaultGroup());

        //set values
        building.setName("Building 2");
        building.setStreet("Musterstraße 2");

        //check, if null is allowed as argument
        building.setDefaultGroup(null);

        //set default group
        Group group = new Group();
        building.setDefaultGroup(group);

        assertEquals("Building 2", building.getName());
        assertEquals("Musterstraße 2", building.getStreet());
        assertNotNull(building.getDefaultGroup());
        assertEquals(group, building.getDefaultGroup());
    }

}
