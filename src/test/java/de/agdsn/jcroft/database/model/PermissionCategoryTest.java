package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PermissionCategoryTest {

    @Test
    public void testConstructor () {
        new PermissionCategory();
    }

    @Test
    public void testConstructor1 () {
        new PermissionCategory(1, "test");
    }

    @Test
    public void testConstructor2 () {
        new PermissionCategory("test");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullIDConstructor1 () {
        new PermissionCategory(0, "test");
    }

    @Test (expected = NullPointerException.class)
    public void testNullTitleConstructor1 () {
        new PermissionCategory(1, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyTitleConstructor1 () {
        new PermissionCategory(1, "");
    }

    @Test
    public void testGetter () {
        PermissionCategory category = new PermissionCategory(20, "test");

        assertEquals(20, category.getId());
        assertEquals("test", category.getTitle());
    }

}
