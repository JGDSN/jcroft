package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.Permission;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends PagingAndSortingRepository<Permission, String> {

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
    Optional<Permission> findByToken(String token);

}
