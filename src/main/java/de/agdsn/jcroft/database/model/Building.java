package de.agdsn.jcroft.database.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "buildings")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id", nullable = false, updatable = false)
    private int id;

    @Size(max = 45)
    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @Size(max = 45)
    @Column(name = "street", nullable = false, updatable = true)
    private String street;

    @JoinColumn(name = "default_group_id")
    @ManyToOne
    private Group defaultGroup;

    public Building (String name, String street) {
        this.name = name;
    }

}
