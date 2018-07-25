package de.agdsn.jcroft.database.model.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ActorTypeTest {

    @Test
    public void testEnums () {
        assertNotNull(ActorType.USER);
        assertNotNull(ActorType.SERVICE);

        assertEquals(2, ActorType.values().length);

        assertEquals(ActorType.USER, ActorType.valueOf("USER"));
        assertEquals(ActorType.SERVICE, ActorType.valueOf("SERVICE"));
    }

}
