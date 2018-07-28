package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PropertyTest {

    @Test
    public void testConstructor () {
        new Property();
    }

    @Test
    public void testConstructor1 () {
        new Property("token", "title");
    }

    @Test (expected = NullPointerException.class)
    public void testNullTokenConstructor () {
        new Property(null, "title");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyTokenConstructor () {
        new Property("", "title");
    }

    @Test (expected = NullPointerException.class)
    public void testNullTitleConstructor () {
        new Property("token", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyTitleConstructor () {
        new Property("token", "");
    }

    @Test
    public void testGetterAndSetter () {
        Property property = new Property("token", "title");

        assertEquals("token", property.getToken());
        assertEquals("title", property.getTitle());

        property.setTitle("test");
        assertEquals("test", property.getTitle());
    }

    @Test
    public void testEquals () {
        Property property = new Property("token", "title");
        assertEquals(true, property.equals(property));
        assertEquals(false, property.equals(null));
        assertEquals(false, property.equals("token"));

        Property property1 = new Property("token", "title");
        assertEquals(true, property.equals(property1));

        assertEquals(property.hashCode(), property1.hashCode());
    }

    @Test
    public void testHashCode () {
        Property property = new Property("token", "title");
        assertEquals(true, property.hashCode() != 0);
    }

}
