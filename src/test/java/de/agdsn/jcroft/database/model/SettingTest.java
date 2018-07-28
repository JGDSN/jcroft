package de.agdsn.jcroft.database.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SettingTest {

    @Test
    public void testConstructor () {
        new Setting();
    }

    @Test
    public void testConstructor1 () {
        new Setting("my-key", "my-string-value");
    }

    @Test
    public void testConstructor2 () {
        new Setting("my-key", 1);
    }

    @Test (expected = NullPointerException.class)
    public void testNullKeyConstructor () {
        new Setting(null, "test");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyKeyConstructor () {
        new Setting("", "test");
    }

    @Test
    public void testGetterAndSetter () {
        Setting setting = new Setting("key", "value");

        assertEquals("key", setting.getKey());
        assertEquals("value", setting.getValue());

        //set new value
        setting.setValue("test");
        assertEquals("test", setting.getValue());

        //set integer value
        setting.setValue(10);
        assertEquals("10", setting.getValue());
        assertEquals(10, setting.getInt());
    }

}
