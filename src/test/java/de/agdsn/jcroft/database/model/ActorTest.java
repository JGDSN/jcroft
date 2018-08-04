package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class ActorTest {

    @Test
    public void testConstructor () {
        new Actor(ActorType.USER);
        new Actor(ActorType.SERVICE);
    }

    @Test
    public void testConstructor1 () {
        new Actor();
    }

    @Test (expected = NullPointerException.class)
    public void testNullConstructor () {
        new Actor(null);
    }

    @Test
    public void testGetterAndSetter () {
        Actor actor1 = new Actor(ActorType.USER);
        Actor actor2 = new Actor(ActorType.SERVICE);

        //should return null, but shouldnt throw an exception
        assertNull(actor1.getUser());
        assertNull(actor2.getService());

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

        assertEquals(0, actor1.listGroupMemberships().size());
    }

    @Test (expected = IllegalStateException.class)
    public void testGetServiceIfUser () {
        Actor actor1 = new Actor(ActorType.USER);
        actor1.getService();
    }

    @Test (expected = IllegalStateException.class)
    public void testGetUserIfService () {
        Actor actor2 = new Actor(ActorType.SERVICE);
        actor2.getUser();
    }

    @Test
    public void testEquals () {
        Actor actor = new Actor(ActorType.USER);
        Actor actor1 = new Actor(ActorType.USER);

        actor.id = 10;
        actor1.id = 10;

        //same instance
        assertEquals(actor, actor);

        assertNotEquals(actor, null);
        assertNotEquals(actor, "test string");

        //same values
        assertEquals(actor, actor1);

        //other type
        Actor actor2 = new Actor(ActorType.SERVICE);
        actor2.id = 10;
        assertNotEquals(actor, actor2);

        //other id
        Actor actor3 = new Actor(ActorType.USER);
        actor3.id = 20;
        assertNotEquals(actor, actor3);

        assertEquals(actor.getType(), actor1.getType());
    }

    @Test
    public void testHashCode () {
        Actor actor = new Actor(ActorType.USER);
        Actor actor1 = new Actor(ActorType.USER);

        actor.id = 10;
        actor1.id = 10;

        assertEquals(actor.hashCode(), actor1.hashCode());
    }

}
