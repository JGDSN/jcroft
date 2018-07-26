package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.Permission;
import de.agdsn.jcroft.database.model.PermissionCategory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionCategoryRepository extends CrudRepository<PermissionCategory, Integer> {

    @Cacheable("permission-categories-findAll")
    public List<PermissionCategory> findAll();

}
