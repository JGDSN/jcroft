package de.agdsn.jcroft.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "users", indexes = {
        @Index(columnList = "username", name = "username_idx"),
        @Index(columnList = "email", name = "email_idx")
})
public class User implements Serializable {

    private static final long serialVersionUID = 7974982498293478923L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private int id;

    @Size(max = 45)
    @Column(name = "username", unique = true, nullable = false, updatable = true)
    private String username;

    @JsonIgnore
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MACAddress> macAddresses;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected Room room;

    public User(String fname, String lname, String username, String email, Actor actor) {
        StringUtils.requireNonEmptyString(fname, "forename");
        StringUtils.requireNonEmptyString(lname, "lastname");
        StringUtils.requireNonEmptyString(username);
        StringUtils.requireNonEmptyString(email);
        Objects.requireNonNull(actor);

        this.firstName = fname;
        this.lastName = lname;
        this.username = username;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean hasRoom () {
        return this.room != null;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", email=" + email + "]";
    }

    @Override
    public boolean equals(Object o) {
        //check for same instance
        if (this == o) return true;

        //check if o is not an instance of this class
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;

        return this.hashCode() == user.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, passwordHash, registered, email, firstName, lastName, actor, unixAccounts, room);
    }

}