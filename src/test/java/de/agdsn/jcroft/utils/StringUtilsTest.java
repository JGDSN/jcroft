package de.agdsn.jcroft.utils;

import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void testConstructor () {
        new StringUtils();
    }

    @Test
    public void testRequireNonEmptyString () {
        StringUtils.requireNonEmptyString("test");
    }

    @Test (expected = NullPointerException.class)
    public void testNullString () {
        StringUtils.requireNonEmptyString(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyString () {
        StringUtils.requireNonEmptyString("");
    }

}
