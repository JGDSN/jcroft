package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "actors")
public class Actor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", nullable = false, updatable = false)
    protected int id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false, updatable = false)
    private ActorType type;

    /**
    * create a new actor
    */
    public Actor (ActorType type) {
        Objects.requireNonNull(type);
        this.type = type;
    }

    /**
     * default constructor required by spring hibernate
     */
    protected Actor () {
        //
    }

    public int getId() {
        return id;
    }

    public ActorType getType() {
        return type;
    }

    public boolean isUser () {
        return type == ActorType.USER;
    }

    public boolean isService () {
        return type == ActorType.SERVICE;
    }

}
