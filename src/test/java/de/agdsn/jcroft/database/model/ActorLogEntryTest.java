package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import org.junit.Test;

public class ActorLogEntryTest {

    @Test
    public void testConstructor () {
        new ActorLogEntry(1, new Actor(ActorType.USER), "test");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullActorIDConstructor () {
        new ActorLogEntry(0, new Actor(ActorType.USER), "test");
    }

    @Test (expected = NullPointerException.class)
    public void testNullActorConstructor () {
        new ActorLogEntry(1, null, "test");
    }

}
