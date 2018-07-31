package de.agdsn.jcroft.database.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

public class MACAddress {

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
