package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProfileImageTest {

    @Test
    public void testConstructor () {
        new ProfileImage();
    }

    @Test
    public void testConstructor1 () {
        new ProfileImage(new User(), new byte[1]);
    }

    @Test (expected = NullPointerException.class)
    public void testNullUserConstructor () {
        new ProfileImage(null, new byte[1]);
    }

    @Test (expected = NullPointerException.class)
    public void testNullDataConstructor () {
        new ProfileImage(new User(), null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyDataConstructor () {
        new ProfileImage(new User(), new byte[0]);
    }

    @Test
    public void testGetterAndSetter () {
        ProfileImage image = new ProfileImage(new User(), new byte[1]);

        assertNotNull(image.getUser());
        assertNotNull(image.getData());

        assertEquals(1, image.getData().length);
    }

}
