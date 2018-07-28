package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "settings")
public class Setting {

    @Id
    @Column(name = "key", unique = true, nullable = false, updatable = false)
    private String key;

    @Size(max = 600)
    @Column(name = "value", nullable = false, updatable = true)
    private String value;

    public Setting (String key, String value) {
        StringUtils.requireNonEmptyString(key, "key");

        this.key = key;
        this.value = value;
    }

    public Setting (String key, int value) {
        StringUtils.requireNonEmptyString(key, "key");

        this.key = key;
        this.value = "" + value;
    }

    protected Setting () {
        //
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = "" + value;
    }

    public int getInt () {
        return Integer.parseInt(getValue());
    }

}
