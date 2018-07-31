package de.agdsn.jcroft.database.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "mac_addresses", indexes = {
        @Index(columnList = "user_id", name = "user_id_idx")
})
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

        if (macAddress <= 0) {
            throw new IllegalArgumentException("mac address cannot be null.");
        }

        if (macAddress > 281474976710655l) {
            throw new IllegalArgumentException("illegal mac address!");
        }

        this.user = user;
        this.macAddress = macAddress;
    }

    public MACAddress (User user, String mac) {
        this(user, Long.parseLong(mac.replace(":", ""),16));
    }

    public long getMacAddressLong() {
        return macAddress;
    }

    public User getUser() {
        return user;
    }

    public String toHex () {
        return Long.toHexString(this.macAddress);
    }

}
