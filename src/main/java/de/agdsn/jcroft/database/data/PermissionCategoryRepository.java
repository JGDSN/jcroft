package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.PermissionCategory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionCategoryRepository extends PagingAndSortingRepository<PermissionCategory, Integer> {

    @Cacheable("permission-categories-findAll")
    public List<PermissionCategory> findAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    Optional<PermissionCategory> findById(int id);

}
