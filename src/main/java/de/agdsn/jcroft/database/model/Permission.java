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
@Table(name = "permissions")
public class Permission implements Serializable {

    @Size(max = 255)
    @Id
    @Column(name = "token", nullable = false, updatable = false, unique = true)
    private String token;

    @Column(name = "title", nullable = false, updatable = true)
    private String title;

    @Size(max = 45)
    @Column(name = "description", nullable = false, updatable = true)
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private PermissionCategory category;

    public Permission (String token, String title, String description, PermissionCategory category) {
        StringUtils.requireNonEmptyString(token, "token");
        StringUtils.requireNonEmptyString(title, "title");

        this.token = token;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    /**
     * default constructor required by spring hibernate
     */
    protected Permission () {
        //
    }

    public String getToken() {
        return token;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public PermissionCategory getCategory() {
        return category;
    }
}
