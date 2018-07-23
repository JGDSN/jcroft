package de.agdsn.jcroft.utils;

import org.junit.Test;

public class IntUtilsTest {

    @Test
    public void testConstructor () {
        new IntUtils();
    }

    @Test
    public void testRequireNumberGreaterNull () {
        IntUtils.requireNumberGreaterNull(1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRequireNumberGreaterNull1 () {
        IntUtils.requireNumberGreaterNull(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNegatitveNumber () {
        IntUtils.requireNumberGreaterNull(-1);
    }

}
