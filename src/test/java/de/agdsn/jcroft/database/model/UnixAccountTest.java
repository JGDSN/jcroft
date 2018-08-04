package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UnixAccountTest {

    @Test
    public void testConstructor () {
        new UnixAccount();
    }

    @Test
    public void testConstructor1 () {
        User user = new User();
        new UnixAccount(user);
    }

    @Test (expected = NullPointerException.class)
    public void testNullUserConstructor () {
        new UnixAccount(null);
    }

    @Test (expected = IllegalStateException.class)
    public void testAlreadyExistentUserAttribute () {
        User user = new User();
        UnixAccount account = new UnixAccount(user);

        //set new user
        account.setUser(new User());
    }

    @Test
    public void testGetterAndSetter () {
        User user = new User();
        UnixAccount account = new UnixAccount();
        assertEquals(0, account.getId());

        account.setUid(10);
        account.setGid(20);
        account.setLoginShell("/bin/bash");
        account.setHomeDir("/home/test");
        account.setUser(user);

        assertEquals(10, account.getUid());
        assertEquals(20, account.getGid());
        assertEquals("/bin/bash", account.getLoginShell());
        assertEquals("/home/test", account.getHomeDir());
        assertEquals(user, account.getUser());
    }

    @Test
    public void testEquals () {
        UnixAccount account = new UnixAccount();
        UnixAccount account1 = new UnixAccount();

        account.id = 10;
        account1.id = 10;

        //same instance
        assertEquals(account, account);

        assertNotEquals(account, null);
        assertNotEquals(account, "test string");

        //same values
        assertEquals(account, account1);

        //other id
        UnixAccount account2 = new UnixAccount();
        account2.id = 20;
        assertNotEquals(account, account2);

        assertEquals(account.getLoginShell(), account1.getLoginShell());
    }

    @Test
    public void testHashCode () {
        UnixAccount account = new UnixAccount();
        UnixAccount account1 = new UnixAccount();

        account.id = 10;
        account1.id = 10;

        assertEquals(account.hashCode(), account1.hashCode());
    }

}
