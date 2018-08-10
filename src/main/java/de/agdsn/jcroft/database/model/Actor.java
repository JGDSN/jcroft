package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import de.agdsn.jcroft.permission.PermissionSet;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
    private List<GroupMembership> groups = new ArrayList<>();

    @OneToOne(mappedBy = "actor", orphanRemoval = true, optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    @OneToOne(mappedBy = "actor", orphanRemoval = true, optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Service service;

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

    public User getUser() {
        if (!isUser()) {
            throw new IllegalStateException("actor isnt a user.");
        }

        return user;
    }

    public Service getService() {
        if (!isService()) {
            throw new IllegalStateException("actor isnt a service.");
        }

        return service;
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

    public Set<Group> getGroups(){
        Set<Group> pGroups = new HashSet<>();
        for(GroupMembership membership : this.listGroupMemberships()){
            pGroups.add(membership.getGroup());
        }
        //Explore all groups of this user (groups can be in groups), circles allowed
        boolean all = true;
        while(all){
            int before = pGroups.size();
            Set<Group> ng = new HashSet<>();
            for(Group g : pGroups){
                ng.add(g);
                g.listMemberships().forEach(gm->ng.add(gm.getGroup()));
            }
            pGroups = ng;
            if(pGroups.size()==before)all = false; //No new elements found anymore -> exploration finished
        }
        return pGroups;
    }

    public PermissionSet getPermissions(){
        return new PermissionSet(this);
    }

}
