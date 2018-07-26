package de.agdsn.jcroft.database.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "profile_image_store")
public class ProfileImage implements Serializable {

    @Id
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(name = "image_blob", nullable = false)
    private byte[] data;

    public ProfileImage (User user, byte[] data) {
        setUser(user);
        setData(data);
    }

    protected ProfileImage () {
        //
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        Objects.requireNonNull(user);
        this.user = user;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        Objects.requireNonNull(data);

        if (data.length <= 0) {
            throw new IllegalArgumentException("no profile data specified (byte array length is 0).");
        }

        this.data = data;
    }

}
