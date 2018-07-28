package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GroupPermissionTest {

    @Test
    public void testConstructor () {
        new GroupPermission();
    }

    @Test
    public void testConstructor1 () {
        new GroupPermission(new Permission(), new Group(), 1);
    }

    @Test (expected = NullPointerException.class)
    public void testNullPermissionConstructor () {
        new GroupPermission(null, new Group(), 1);
    }

    @Test (expected = NullPointerException.class)
    public void testNullGroupConstructor () {
        new GroupPermission(new Permission(), null, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testInvalidePermissionValueConstructor () {
        new GroupPermission(new Permission(), new Group(), -2);
    }

    @Test
    public void testGetterAndSetter () {
        GroupPermission permission = new GroupPermission(new Permission(), new Group(), 1);

        assertNotNull(permission.getPermission());
        assertNotNull(permission.getGroup());
        assertNotNull(permission.getPower());

        assertEquals(1, permission.getPower());

        permission.setPower(0);
        assertEquals(0, permission.getPower());
        assertEquals(false, permission.isYes());
        assertEquals(true, permission.isNo());
        assertEquals(false, permission.isNever());

        permission.setPower(1);
        assertEquals(1, permission.getPower());
        assertEquals(false, permission.isYes());
        assertEquals(false, permission.isNo());
        assertEquals(true, permission.isNever());

        permission.setPower(1);
        assertEquals(1, permission.getPower());
        assertEquals(true, permission.isYes());
        assertEquals(false, permission.isNo());
        assertEquals(false, permission.isNever());
    }

}
