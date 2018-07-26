package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false, updatable = false)
    protected int id;

    @Size(max = 45)
    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    public Group (String name) {
        StringUtils.requireNonEmptyString(name, "name");
        this.name = name;
    }

    protected Group () {
        //
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
