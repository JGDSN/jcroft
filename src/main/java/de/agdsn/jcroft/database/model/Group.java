package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "groups")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false, updatable = false)
    protected int id;

    @Size(max = 45)
    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @OneToMany
    private List<GroupPermission> permissions;

    @ElementCollection(targetClass=String.class)
    @JoinTable(name = "group_properties",
            joinColumns = @JoinColumn(name = "group_id"))
    @MapKeyColumn(name = "token")
    @Column(name = "value")
    private Map<String, String> properties = new HashMap<>();

    @OneToMany(mappedBy = "group")
    private List<GroupMembership> members;

    public Group (String name) {
        StringUtils.requireNonEmptyString(name, "name");
        this.name = name;
    }

    protected Group () {
        //
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setProperty (String token, String value) {
        StringUtils.requireNonEmptyString(token, "token");
        StringUtils.requireNonEmptyString(value, "value");

        this.properties.put(token, value);
    }

    public void setProperty (String token, int value) {
        StringUtils.requireNonEmptyString(token, "token");

        this.properties.put(token, "" + value);
    }

    public void removeProperty (String token) {
        this.properties.remove(token);
    }

    public String getProperty (String token) {
        if (!this.properties.containsKey(token)) {
            //token doesnt exists
            return "";
        }

        return this.properties.get(token);
    }

    public boolean containsProperty (String token) {
        return this.properties.containsKey(token);
    }

    public int getPropertyInt (String token) {
        if (!containsProperty(token)) {
            throw new IllegalStateException("property '" + token + "' doesnt exists in group '" + this.getId() + "'.");
        }

        return Integer.parseInt(getProperty(token));
    }

    public List<GroupMembership> listMemberships() {
        return members;
    }

    public void addMembership (GroupMembership membership) {
        this.members.add(membership);
    }

    public void removeMembership (GroupMembership membership) {
        this.members.remove(membership);
    }

    public List<Actor> listMembers() {
        List<Actor> actors = new ArrayList<>();

        for (GroupMembership membership : listMemberships()) {
            if (membership.isMember()) {
                actors.add(membership.getActor());
            }
        }

        return actors;
    }

}
