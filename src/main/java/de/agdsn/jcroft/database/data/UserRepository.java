package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Cacheable("findAll")
    public List<User> findAll();
}
