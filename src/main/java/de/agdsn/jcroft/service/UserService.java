package de.agdsn.jcroft.service;

import de.agdsn.jcroft.database.data.ActorRepository;
import de.agdsn.jcroft.database.data.UserRepository;
import de.agdsn.jcroft.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {

    @Autowired
    protected ActorRepository actorRepository;

    @Autowired
    protected UserRepository userRepository;

    //logger
    protected static final Logger logger = Logger.getLogger(UserService.class.getName());

    /**
    * private constructor, because you should use @AutoWired instead of creating a new instance
    */
    protected UserService () {
        //
    }

    /**
    * create new user
    */
    @Transactional
    public void createUser () {
        //
    }

    /**
    * remove user by username
     *
     * @param username username
    */
    @Transactional
    public void removeUser (String username) {
        //first, get user
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (!userOptional.isPresent()) {
            logger.log(Level.FINE, "It's not neccessary to remove user {0}, because user doesnt exists.", username);

            //user doesnt exists
            return;
        }

        //delete user and actor from database
        userRepository.delete(userOptional.get());

        logger.log(Level.INFO, "removed user '{0}'", username);
    }

    /**
     * remove user by userID
     *
     * @param userID id of user
     */
    @Transactional
    public void removeUserById (int userID) {
        //first, get user
        Optional<User> userOptional = userRepository.findById(userID);

        if (!userOptional.isPresent()) {
            logger.log(Level.FINE, "It's not neccessary to remove user with id {0}, because user doesnt exists.", userID);

            //user doesnt exists
            return;
        }

        //delete user and actor from database
        userRepository.delete(userOptional.get());

        logger.log(Level.INFO, "removed user '{0}' with id " + userID + "", userOptional.get().getUsername());
    }

}
