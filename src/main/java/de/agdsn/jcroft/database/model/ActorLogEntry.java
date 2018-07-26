package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "actor_logs", indexes = {
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
    private Actor author;

    @Column(name = "message", nullable = false, updatable = true)
    private String message;

    @Column(name = "timestamp", nullable = false, updatable = false)
    @CreationTimestamp
    private Date date;

    public ActorLogEntry (int actorID, Actor author, String message) {
        Objects.requireNonNull(author);
        StringUtils.requireNonEmptyString(message, "message");

        if (actorID <= 0) {
            throw new IllegalArgumentException("actorID has to be >= 0.");
        }

        this.actorID = actorID;
        this.author = author;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public int getActorID() {
        return actorID;
    }

    public Actor getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

}
