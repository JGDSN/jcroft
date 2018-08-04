package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    @Cacheable("user-findAll")
    public List<User> findAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    //@Cacheable("user-repository-findById")
    Optional<User> findById(int id);

    Optional<User> findByUsername(String username);

}
