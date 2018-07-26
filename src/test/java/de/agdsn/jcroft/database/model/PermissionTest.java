package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PermissionTest {

    @Test
    public void testConstructor () {
        new Permission();
    }

    @Test
    public void testConstructor1 () {
        new Permission("token", "test", "test", new PermissionCategory());
    }

    @Test (expected = NullPointerException.class)
    public void testNullTokenConstructor () {
        new Permission(null, "test", "test", new PermissionCategory());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyTokenConstructor () {
        new Permission("", "test", "test", new PermissionCategory());
    }

    @Test (expected = NullPointerException.class)
    public void testNullTitleConstructor () {
        new Permission("token", null, "test", new PermissionCategory());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyTitleConstructor () {
        new Permission("token", "", "test", new PermissionCategory());
    }

    @Test
    public void testGetter () {
        Permission permission = new Permission("token", "test-title", "test", new PermissionCategory());

        assertEquals("token", permission.getToken());
        assertEquals("test-title", permission.getTitle());
        assertEquals("test", permission.getDescription());
        assertNotNull(permission.getCategory());
    }

}
