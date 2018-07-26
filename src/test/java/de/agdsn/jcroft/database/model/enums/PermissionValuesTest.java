package de.agdsn.jcroft.database.model.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PermissionValuesTest {

    @Test
    public void testLength () {
        assertEquals(3, PermissionValues.values().length);
    }

    @Test
    public void testValueOf () {
        assertEquals(PermissionValues.YES, PermissionValues.valueOf("YES"));
        assertEquals(PermissionValues.NO, PermissionValues.valueOf("NO"));
        assertEquals(PermissionValues.NEVER, PermissionValues.valueOf("NEVER"));
    }

}
