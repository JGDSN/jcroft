package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.database.model.enums.PermissionValues;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "group_permissions")
public class GroupPermission implements Serializable {

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "token", updatable = false)
    private Permission permission;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", updatable = false)
    private Group group;

    @Column(name = "power", nullable = false, updatable = true)
    private int power;

    public GroupPermission (Permission permission, Group group, int power) {
        Objects.requireNonNull(permission);
        Objects.requireNonNull(group);

        this.permission = permission;
        this.group = group;
        this.setPower(power);
    }

    protected GroupPermission () {
        //
    }

    public Permission getPermission() {
        return permission;
    }

    public Group getGroup() {
        return group;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (power < -1) {
            throw new IllegalArgumentException("power of < -1 is not allowed (-1 means NEVER, 0 means NO and >= 1 means YES), current power: " + power + ".");
        }

        this.power = power;
    }

    public boolean isYes () {
        return this.power > 0;
    }

    public boolean isNo () {
        return this.power == 0;
    }

    public boolean isNever () {
        return this.power < 0;
    }

}
