package de.bloodink.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the user database table.
 */
@Entity
// @EntityListeners({ UserEntityListener.class })
@NamedQueries({
        @NamedQuery(name = User.FIND_ALL_USERS, query = "SELECT u FROM User u"),
        @NamedQuery(name = User.FIND_USER_BY_NAME, query = "SELECT u "
                + "FROM User u WHERE u.name = :n") })
public class User {

    /**
     * Name of the NamedQuery to receive all users from Database.
     */
    public static final String FIND_ALL_USERS = "User.findAllUsers";
    public static final String FIND_USER_BY_NAME = "User.findUserByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Username of DB.
     */
    @Column(unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "user_fk")
    private List<Password> passwords = new ArrayList<Password>();

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = true, updatable = false)
    private Date createdate;

    /**
     * Default Constructor to create a User.
     */
    public User() {
    }

    /**
     * Getter of Username.
     * 
     * @return username
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set Username.
     * 
     * @param n
     *            username
     */
    public void setName(String n) {
        this.name = n;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Password> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<Password> passwords) {
        this.passwords = passwords;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", passwords=" + passwords
                + ", createdate=" + createdate + "]";
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

}
