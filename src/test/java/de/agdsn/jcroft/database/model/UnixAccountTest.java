package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        UnixAccount account = new UnixAccount(user);

        account.setUid(10);
        account.setGid(20);
        account.setLoginShell("/bin/bash");
        account.setHomeDir("/home/test");

        assertEquals(10, account.getUid());
        assertEquals(20, account.getGid());
        assertEquals("/bin/bash", account.getLoginShell());
        assertEquals("/home/test", account.getHomeDir());
    }

}
