package de.agdsn.jcroft.database.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "permission_categories")
public class PermissionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false, updatable = false)
    private int id;

    @Size(max = 45)
    @Column(name = "title", unique = true, nullable = false, updatable = true)
    private String title;

    public PermissionCategory (int id, String title) {
        this.id = id;
        this.title = title;
    }

    public PermissionCategory (String title) {
        this.title = title;
    }

}
