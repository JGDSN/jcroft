package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.Property;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropertyRepository extends CrudRepository<Property, String> {

    @Cacheable("property-repository-findAll")
    public List<Property> findAll();

}
