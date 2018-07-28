package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "groups")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false, updatable = false)
    protected int id;

    @Size(max = 45)
    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @OneToMany
    private List<GroupPermission> permissions;

    @ElementCollection(targetClass=String.class)
    @JoinTable(name = "group_properties",
            joinColumns = @JoinColumn(name = "group_id"))
    @MapKeyColumn(name = "token")
    @Column(name = "value")
    private Map<String, String> properties = new HashMap<>();

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
