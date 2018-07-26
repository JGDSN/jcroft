package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PermissionTest {

    @Test
    public void testConstructor () {
        new Permission();
    }

    @Test
    public void testConstructor1 () {
        new Permission("token", "test", "test");
    }

    @Test (expected = NullPointerException.class)
    public void testNullTokenConstructor () {
        new Permission(null, "test", "test");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyTokenConstructor () {
        new Permission("", "test", "test");
    }

    @Test (expected = NullPointerException.class)
    public void testNullTitleConstructor () {
        new Permission("token", null, "test");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyTitleConstructor () {
        new Permission("token", "", "test");
    }

    @Test
    public void testGetter () {
        Permission permission = new Permission("token", "test-title", "test");

        assertEquals("token", permission.getToken());
        assertEquals("test-title", permission.getTitle());
        assertEquals("test", permission.getDescription());
    }

}
