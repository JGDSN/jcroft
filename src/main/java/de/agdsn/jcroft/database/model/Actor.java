package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Cacheable
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

    @OneToMany (mappedBy = "actor", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupMembership> groups;

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

    public List<GroupMembership> listGroupMemberships () {
        return this.groups;
    }

    @Override
    public boolean equals(Object o) {
        //same instance
        if (this == o) return true;

        //check, if object is null
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        return id == actor.id &&
                type == actor.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

}
