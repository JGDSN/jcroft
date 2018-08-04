package de.agdsn.jcroft.database.model;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GroupTest {

    @Test
    public void testConstructor () {
        new Group();
    }

    @Test
    public void testConstructor1 () {
        new Group("my-group-name");
    }

    @Test (expected = NullPointerException.class)
    public void testNullConstructor () {
        new Group(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyConstructor () {
        new Group("");
    }

    @Test
    public void testGetters () {
        Group group = new Group("test");
        group.id = 10;

        assertEquals(10, group.getId());
        assertEquals("test", group.getName());
        assertNotNull(group.listMemberships());
        assertEquals(0, group.listMemberships().size());
        assertEquals(0, group.listMembers().size());

        //add membership
        GroupMembership membership = new GroupMembership();
        group.addMembership(membership);

        assertEquals(1, group.listMemberships().size());
        assertEquals(1, group.listMembers().size());

        //remove membership
        group.removeMembership(membership);
        assertEquals(0, group.listMemberships().size());
        assertEquals(0, group.listMembers().size());

        //add membership with date in future
        GroupMembership membership1 = new GroupMembership(group, new Actor());

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        membership1.setBeginsAt(c.getTime());

        group.addMembership(membership1);

        assertEquals(false, membership1.isMember());
        assertEquals(1, group.listMemberships().size());
        assertEquals(0, group.listMembers().size());
    }

    @Test
    public void testProperties () {
        Group group = new Group("test");

        assertEquals(false, group.containsProperty("test-token"));

        group.setProperty("test-token", "test");
        assertEquals(true, group.containsProperty("test-token"));
        assertEquals("test", group.getProperty("test-token"));

        //test override
        group.setProperty("test-token", "value");
        assertEquals("value", group.getProperty("test-token"));

        group.setProperty("test-token2", "test2");
        assertEquals(true, group.containsProperty("test-token"));
        assertEquals(true, group.containsProperty("test-token2"));
        assertEquals("test2", group.getProperty("test-token2"));

        //test with integer
        group.setProperty("test", 10);
        assertEquals(10, group.getPropertyInt("test"));

        //remove property
        group.removeProperty("test");
        assertEquals(false, group.containsProperty("test"));

        assertEquals("", group.getProperty("not-existent-token"));

        //check, that no other property was removed too
        assertEquals(true, group.containsProperty("test-token"));
    }

    @Test (expected = IllegalStateException.class)
    public void testGetIntNotExistentProperty () {
        Group group = new Group("test");
        group.getPropertyInt("test2");
    }

}
