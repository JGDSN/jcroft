package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.Actor;
import de.agdsn.jcroft.database.model.enums.ActorType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends CrudRepository<Actor, Integer> {

    @Cacheable("actor-findAll")
    public List<Actor> findAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    @Cacheable("actor-repository-findById")
    Optional<Actor> findById(int id);

    @Cacheable("actor-repository-findByType")
    List<Actor> findByType(ActorType type);

}
