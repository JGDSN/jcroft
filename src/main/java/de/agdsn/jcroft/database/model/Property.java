package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "properties")
public class Property {

    @Id
    @Size(max = 45)
    @Column(name = "token", nullable = false, updatable = false, unique = true)
    private String token;

    @Size(max = 45)
    @Column(name = "title", nullable = false, updatable = true)
    private String title;

    public Property (String token, String title) {
        StringUtils.requireNonEmptyString(token, "token");
        StringUtils.requireNonEmptyString(title, "title");

        this.token = token;
        this.title = title;
    }

    protected Property () {
        //
    }

    public String getToken() {
        return token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        StringUtils.requireNonEmptyString(title, "title");
        this.title = title;
    }

}
