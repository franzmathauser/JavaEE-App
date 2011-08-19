package de.bloodink.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the user database table.
 */
@Entity
@NamedQuery(name = User.FIND_ALL_USERS, query = "SELECT u FROM User u")
public class User {

    /**
     * Name of the NamedQuery to receive all users from Database.
     */
    public static final String FIND_ALL_USERS = "User.findAllUsers";

    /**
     * Username of DB.
     */
    @Id
    private String name;

    /**
     * User Password of DB.
     */
    private String password;

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
    public final String getName() {
        return this.name;
    }

    /**
     * Set Username.
     * 
     * @param name
     *            username
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter of User Password.
     * 
     * @return passwort hash
     */
    public final String getPassword() {
        return this.password;
    }

    /**
     * Setter of User Password.
     * 
     * @param password
     *            password hash
     */
    public final void setPassword(final String password) {
        this.password = password;
    }

}
