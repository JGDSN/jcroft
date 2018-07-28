package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "kontingent_types")
public class KontingentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kontingent_type_id", nullable = false, updatable = false)
    private int typeId;

    @Size(max = 45)
    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    public KontingentType (String name) {
        StringUtils.requireNonEmptyString(name, "name");
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
