package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.utils.IntUtils;
import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 7974982498293478923L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(String fname, String lname) {
        StringUtils.requireNonEmptyString(fname, "forename");
        StringUtils.requireNonEmptyString(lname, "lastname");

        //TODO: check for specical characters

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

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}