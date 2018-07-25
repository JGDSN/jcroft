package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ActorTest {

    @Test
    public void testConstructor () {
        new Actor(ActorType.USER);
        new Actor(ActorType.SERVICE);
    }

    @Test (expected = NullPointerException.class)
    public void testNullConstructor () {
        new Actor(null);
    }

    @Test
    public void testGetterAndSetter () {
        Actor actor1 = new Actor(ActorType.USER);
        Actor actor2 = new Actor(ActorType.SERVICE);

        //check for correct implementation of equals() and hashCode() method
        assertNotEquals(actor1, actor2);
        assertNotEquals(actor1.hashCode(), actor2.hashCode());

        //id wasnt set yet
        assertEquals(0, actor1.getId());
        assertEquals(0, actor2.getId());

        //check getter methods
        assertEquals(ActorType.USER, actor1.getType());
        assertEquals(true, actor1.isUser());
        assertEquals(false, actor1.isService());

        assertEquals(ActorType.SERVICE, actor2.getType());
        assertEquals(false, actor2.isUser());
        assertEquals(true, actor2.isService());
    }

}
