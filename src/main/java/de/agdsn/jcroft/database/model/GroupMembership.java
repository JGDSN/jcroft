package de.agdsn.jcroft.database.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "group_members")
public class GroupMembership implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false, updatable = false)
    private Group group;

    @Id
    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false, updatable = false)
    private Actor actor;

    @Column(name = "is_leader", nullable = false, updatable = true)
    private boolean isLeader = false;

    @Column(name = "begins_at", nullable = true, updatable = true)
    private Date beginsAt;

    @Column(name = "ends_at", nullable = true, updatable = true)
    private Date endsAt;

    protected GroupMembership () {
        //
    }

    public GroupMembership (Group group, Actor actor) {
        Objects.requireNonNull(group);
        Objects.requireNonNull(actor);

        this.group = group;
        this.actor = actor;
    }

    public Group getGroup() {
        return group;
    }

    public Actor getActor() {
        return actor;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    public Date getBeginsAt() {
        return beginsAt;
    }

    public void setBeginsAt(Date beginsAt) {
        this.beginsAt = beginsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public boolean isMember () {
        Date current = new Date();

        if (beginsAt != null) {
            //check, if beginsAt <= current
            if (!current.after(beginsAt)) {
                return false;
            }
        }

        if (endsAt != null) {
            if (!current.before(endsAt)) {
                return false;
            }
        }

        return true;
    }

}
