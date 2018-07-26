package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.PermissionValues;
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
        new GroupPermission(new Permission(), new Group(), PermissionValues.YES);
    }

    @Test (expected = NullPointerException.class)
    public void testNullPermissionConstructor () {
        new GroupPermission(null, new Group(), PermissionValues.YES);
    }

    @Test (expected = NullPointerException.class)
    public void testNullGroupConstructor () {
        new GroupPermission(new Permission(), null, PermissionValues.YES);
    }

    @Test (expected = NullPointerException.class)
    public void testNullPermissionValueConstructor () {
        new GroupPermission(new Permission(), new Group(), null);
    }

    @Test
    public void testGetterAndSetter () {
        GroupPermission permission = new GroupPermission(new Permission(), new Group(), PermissionValues.YES);

        assertNotNull(permission.getPermission());
        assertNotNull(permission.getGroup());
        assertNotNull(permission.getValue());

        assertEquals(PermissionValues.YES, permission.getValue());
    }

}
