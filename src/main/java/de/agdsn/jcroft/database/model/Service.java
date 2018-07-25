package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
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

    /**
     * orphanRemoval = true, because Actor should be also removed if user was removed
     */
    @OneToOne(orphanRemoval = true, optional = false)
    private Actor actor;

    public Service (String name, String token, Actor actor) {
        this.name = name;
        this.token = token;
        this.actor = actor;

        if (actor.getType() != ActorType.SERVICE) {
            throw new IllegalArgumentException("actor isn't of type SERVICE.");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Actor getActor() {
        return actor;
    }

    public int getActorId () {
        return getActor().getId();
    }

}
