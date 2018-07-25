package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.utils.IntUtils;
import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


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

    @OneToOne
    @Column(name = "actor_id", nullable = false, updatable = true)
    private Actor actor;

    public User() {
    }

    public User(String fname, String lname) {
        StringUtils.requireNonEmptyString(fname, "forename");
        StringUtils.requireNonEmptyString(lname, "lastname");

        this.firstName = fname;
        this.lastName = lname;
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

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }

}