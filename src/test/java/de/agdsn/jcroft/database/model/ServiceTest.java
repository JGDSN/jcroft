package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceTest {

    @Test
    public void testConstructor () {
        //create new actor first
        Actor actor = new Actor(ActorType.SERVICE);
        new Service("test", "token", actor);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructor1 () {
        //create new actor first
        Actor actor = new Actor(ActorType.USER);
        new Service("test", "test", actor);
    }

    @Test
    public void testConstructor2 () {
        new Service();
    }

    @Test
    public void testGetterAndSetter () {
        Actor actor = new Actor(ActorType.SERVICE);
        actor.id = 20;
        Service service = new Service("test-name", "test-token", actor);
        service.id = 10;

        assertEquals(10, service.getId());
        assertEquals("test-name", service.getName());
        assertEquals("test-token", service.getToken());
        assertEquals(ActorType.SERVICE, service.getActor().getType());
        assertEquals(20, service.getActorId());

        //set token
        service.setToken("test-token2");
        assertEquals("test-token2", service.getToken());

        service.setName("max");
        assertEquals("max", service.getName());
    }

}
