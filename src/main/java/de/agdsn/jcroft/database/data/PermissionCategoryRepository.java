package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.Permission;
import de.agdsn.jcroft.database.model.PermissionCategory;
import de.agdsn.jcroft.database.model.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionCategoryRepository extends CrudRepository<PermissionCategory, Integer> {

    @Cacheable("permission-categories-findAll")
    public List<PermissionCategory> findAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    @Cacheable("permission-categories-findById")
    Optional<PermissionCategory> findById(int id);

}
