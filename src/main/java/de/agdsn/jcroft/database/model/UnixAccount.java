package de.agdsn.jcroft.database.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "unix_accounts")
public class UnixAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User id;

    @Column(name = "uid", unique = true, nullable = false, updatable = true)
    private int uid;

    @Column(name = "gid", unique = false, nullable = false, updatable = true)
    private int gid;

    @Size(max = 45)
    @Column(name = "login_shell", unique = false, nullable = false, updatable = true)
    private String loginShell;

    @Size(max = 45)
    @Column(name = "home_directory", unique = false, nullable = false, updatable = true)
    private String homeDir;

    public UnixAccount (User user) {
        this.id = user;
    }

    protected UnixAccount () {
        //
    }

    public User getUser() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public int getGid() {
        return gid;
    }

    public String getLoginShell() {
        return loginShell;
    }

    public String getHomeDir() {
        return homeDir;
    }

}
