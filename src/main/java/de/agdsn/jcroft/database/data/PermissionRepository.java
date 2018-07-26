package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.Actor;
import de.agdsn.jcroft.database.model.Permission;
import de.agdsn.jcroft.database.model.enums.ActorType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends CrudRepository<Permission, String> {

    @Cacheable("permissions-findAll")
    public List<Permission> findAll();

    /**
     * Retrieves an permission by its token.
     *
     * @param token must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    @Cacheable("permission-repository-findByToken")
    Optional<Actor> findByToken(String token);

}
