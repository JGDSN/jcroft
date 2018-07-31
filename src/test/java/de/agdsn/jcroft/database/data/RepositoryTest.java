package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.Actor;
import de.agdsn.jcroft.database.model.User;
import de.agdsn.jcroft.database.model.enums.ActorType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

        //create new user
        User user = new User("Max", "Mustermann", "test", "max@mustermann.de", actor);

        actorRepository.save(actor);
        userRepository.save(user);

        //find user
        Optional<User> userOptional = userRepository.findByUsername("test");
        assertEquals(true, userOptional.isPresent());
        User user1 = userOptional.get();
        assertNotNull(user1);
        assertEquals(user, user1);
        assertEquals(actor, user1.getActor());
    }

}
