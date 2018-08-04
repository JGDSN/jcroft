package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends PagingAndSortingRepository<Service, Integer> {

    @Cacheable("service-findAll")
    public List<Service> findAll();

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    @Cacheable("service-repository-findById")
    Optional<Service> findById(int id);

    @Cacheable("service-repository-findByToken")
    Optional<Service> findByToken(String token);

}
