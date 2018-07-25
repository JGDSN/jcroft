package de.agdsn.jcroft.database.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "services")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id", nullable = false, updatable = false)
    private int id;

    @Size(max = 45)
    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @Size(max = 255)
    @Column(name = "token", nullable = false, updatable = true, unique = true)
    private String token;

    /*@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "services", optional = false)
    private Actor actor;*/

    /**
     * orphanRemoval = true, because Actor should be also removed if user was removed
     */
    @OneToOne(orphanRemoval = true, optional = false)
    private Actor actor;

}
