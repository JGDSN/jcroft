package de.agdsn.jcroft.database.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "services", indexes = {
        @Index(columnList = "actor_id", name = "actor_id_idx")
})
public class ActorLogEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false, updatable = false)
    private int id;

    @Column(name = "actor_id", nullable = false, updatable = true)
    private int actorID;

    @ManyToOne(optional = false)
    private User author;

    @Column(name = "message", nullable = false, updatable = true)
    private String message;

    @Column(name = "timestamp", nullable = false, updatable = false)
    @CreationTimestamp
    private Date date;

    public ActorLogEntry (int actorID, User author, String message) {
        this.actorID = actorID;
        this.author = author;
        this.message = message;
    }

}
