package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "permission_categories")
public class PermissionCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false, updatable = false)
    private int id;

    @Size(max = 45)
    @Column(name = "title", unique = true, nullable = false, updatable = true)
    private String title;

    public PermissionCategory (int id, String title) {
        if (id <= 0) {
            throw new IllegalArgumentException("id has to be > 0.");
        }

        StringUtils.requireNonEmptyString(title, "title");

        this.id = id;
        this.title = title;
    }

    /**
     * default constructor required by spring hibernate
     */
    protected PermissionCategory () {
        //
    }

    public PermissionCategory (String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
