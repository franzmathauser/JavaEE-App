package de.bloodink.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the user database table.
 */
@Entity
// @EntityListeners({ UserEntityListener.class })
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

    /**
     * Getter of User Password.
     * 
     * @return passwort hash
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter of User Password.
     * 
     * @param p
     *            password hash
     */
    public void setPassword(String p) {
        this.password = p;
    }

}
