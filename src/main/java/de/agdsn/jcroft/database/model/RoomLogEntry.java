package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "room_logs", indexes = {
        @Index(columnList = "room_id", name = "room_id_idx"),
        @Index(columnList = "author_id_id", name = "author_id_idx")
})
public class RoomLogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false, updatable = false)
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false, updatable = false)
    private Room room;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false, updatable = true)
    private Actor author;

    @Size(max = 255)
    @Column(name = "message", nullable = false, updatable = true)
    private String message;

    @Column(name = "created", nullable = false, updatable = false)
    @CreationTimestamp
    private Date date;

    /**
    * private constructor for hibernate
    */
    protected RoomLogEntry () {
        //
    }

    public RoomLogEntry (Room room, Actor author, String message) {
        Objects.requireNonNull(room);
        Objects.requireNonNull(author);
        StringUtils.requireNonEmptyString(message, "message");

        this.room = room;
        this.author = author;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public Actor getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

}
