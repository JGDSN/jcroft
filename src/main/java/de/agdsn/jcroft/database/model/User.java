package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.ActorType;
import de.agdsn.jcroft.utils.IntUtils;
import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 7974982498293478923L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private int id;

    @Size(max = 45)
    @Column(name = "username", unique = true, nullable = false, updatable = true)
    private String username;

    @Size(max = 255)
    @Column(name = "password_hash", nullable = true, updatable = true)
    private String passwordHash;

    @Column(name = "registered", nullable = false, updatable = false)
    @CreationTimestamp
    private Date registered;

    @Size(max = 255)
    @Column(name = "email", nullable = false, updatable = true)
    private String email;

    @Size(max = 45)
    @Column(name = "first_name", nullable = false, updatable = true)
    private String firstName;

    @Size(max = 45)
    @Column(name = "surname", nullable = false, updatable = true)
    private String lastName;

    /**
     * orphanRemoval = true, because Actor should be also removed if user was removed
     */
    @OneToOne(orphanRemoval = true, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Column(name = "user_id")
    private List<UnixAccount> unixAccounts;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Room room;

    public User(String fname, String lname, Actor actor) {
        StringUtils.requireNonEmptyString(fname, "forename");
        StringUtils.requireNonEmptyString(lname, "lastname");
        Objects.requireNonNull(actor);

        this.firstName = fname;
        this.lastName = lname;
        this.actor = actor;

        if (actor.getType() != ActorType.USER) {
            throw new IllegalArgumentException("actor type isn't of type USER.");
        }
    }

    /**
     * default constructor required by spring hibernate
     */
    protected User () {
        //
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        IntUtils.requireNumberGreaterNull(id, "id");
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        StringUtils.requireNonEmptyString(firstName, "firstName");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        StringUtils.requireNonEmptyString(firstName, "lastName");
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        StringUtils.requireNonEmptyString(username);
        this.username = username;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getRegistered() {
        return this.registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Actor getActor () {
        return this.actor;
    }

    public int getActorId () {
        return getActor().getId();
    }

    public Room getRoom() {
        return this.room;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }

}