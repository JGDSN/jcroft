package de.agdsn.jcroft.database.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "mac_addresses")
public class MACAddress implements Serializable {

    @Id
    @Column(name = "mac_address", nullable = false, updatable = false)
    private long macAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    protected MACAddress () {
        //
    }

    public MACAddress (User user, long macAddress) {
        Objects.requireNonNull(user);
    }

}
