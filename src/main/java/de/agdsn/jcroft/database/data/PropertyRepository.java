package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.Property;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PropertyRepository extends PagingAndSortingRepository<Property, String> {

    @Cacheable("property-repository-findAll")
    public List<Property> findAll();

}
