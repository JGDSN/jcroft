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
    @JoinColumn(name = "token")
    private Permission permission;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "value", nullable = false, updatable = true)
    private PermissionValues value;

    public GroupPermission (Permission permission, Group group, PermissionValues value) {
        Objects.requireNonNull(permission);
        Objects.requireNonNull(group);
        Objects.requireNonNull(value);

        this.permission = permission;
        this.group = group;
        this.value = value;
    }

    protected GroupPermission () {
        //
    }

}
