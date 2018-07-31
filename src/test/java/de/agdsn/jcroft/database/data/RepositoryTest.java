package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.Actor;
import de.agdsn.jcroft.database.model.User;
import de.agdsn.jcroft.database.model.enums.ActorType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class RepositoryTest {

    @Autowired
    protected ActorRepository actorRepository;

    @Autowired
    protected UserRepository userRepository;

    @Test
    public void testAddUser () {
        //create new actor
        Actor actor = new Actor(ActorType.USER);
        assertEquals(0, actor.getId());

        //create new user
        User user = new User("Max", "Mustermann", "test", "max@mustermann.de", actor);

        actorRepository.save(actor);
        userRepository.save(user);

        //check, if actor has an id
        assertEquals(true, actor.getId() > 0);

        //find user
        Optional<User> userOptional = userRepository.findByUsername("test");
        assertEquals(true, userOptional.isPresent());
        User user1 = userOptional.get();
        assertNotNull(user1);
        assertEquals(user, user1);
        assertEquals(actor, user1.getActor());

        //get actor
        Optional<Actor> actorOptional = actorRepository.findById(user1.getActorId());
        assertEquals(true, actorOptional.isPresent());
        Actor actor1 = actorOptional.get();
        assertEquals(user.getActor(), actor1);
    }

    @Test (expected = DataIntegrityViolationException.class)
    public void testAddUserWithSameUsername () {
        //create new actor
        Actor actor = new Actor(ActorType.USER);

        //create new user
        User user = new User("Max", "Mustermann", "test", "max@mustermann.de", actor);

        actorRepository.save(actor);
        userRepository.save(user);

        //create new actor
        Actor actor1 = new Actor(ActorType.USER);

        //create new user
        User user1 = new User("Test", "Test", "test", "test@example.com", actor1);

        actorRepository.save(actor1);
        userRepository.save(user1);
    }

    @Test
    public void testRemoveUser () {
        //create new actor
        Actor actor = new Actor(ActorType.USER);

        //create new user
        User user = new User("Max", "Mustermann", "test", "max@mustermann.de", actor);

        actorRepository.save(actor);
        userRepository.save(user);

        //find user
        Optional<User> userOptional = userRepository.findByUsername("test");
        User user1 = userOptional.get();
        assertEquals(true, userOptional.isPresent());

        //remove user
        userRepository.delete(user);

        //find user again
        Optional<User> userOptional1 = userRepository.findByUsername("test");
        assertEquals(false, userOptional1.isPresent());

        //check, if actor was also remove
        Optional<Actor> actorOptional = actorRepository.findById(user1.getActorId());
        assertEquals(false, actorOptional.isPresent());
    }

    @Test
    public void testRemoveActor () {
        //create new actor
        Actor actor = new Actor(ActorType.USER);

        //create new user
        User user = new User("Max", "Mustermann", "test", "max@mustermann.de", actor);

        actorRepository.save(actor);
        userRepository.save(user);

        //find user
        Optional<User> userOptional = userRepository.findByUsername("test");
        User user1 = userOptional.get();
        assertEquals(true, userOptional.isPresent());

        //remove actor
        actorRepository.delete(actor);

        //check, if actor was remove
        Optional<Actor> actorOptional = actorRepository.findById(user1.getActorId());
        assertEquals(false, actorOptional.isPresent());

        //find user again
        Optional<User> userOptional1 = userRepository.findByUsername("test");
        assertEquals(true, userOptional1.isPresent());//TODO: set expected value to false
    }

}
