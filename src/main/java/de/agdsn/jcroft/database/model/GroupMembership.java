package de.agdsn.jcroft.database.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "group_members")
public class GroupMembership {

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Column(name = "is_leader", nullable = false, updatable = true)
    private boolean isLeader = false;

    @Column(name = "begins_at", nullable = true, updatable = true)
    private Date beginsAt;

    @Column(name = "ends_at", nullable = true, updatable = true)
    private Date endsAt;

}
