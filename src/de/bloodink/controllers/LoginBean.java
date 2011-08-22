package de.bloodink.controllers;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import de.bloodink.ejbs.UserEjb;
import de.bloodink.entities.Password;
import de.bloodink.entities.User;

/**
 * Login Bean is ein JSF Controller, der die Logindaten abfängt und ein User an
 * eine EJB weiterreicht.
 * 
 * @author Franz Mathauser
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    /**
     * Injected EJB, which holds business logic of User.
     */
    @EJB
    private UserEjb userEjb;

    /**
     * Username.
     */
    private String name;
    /**
     * Password.
     */
    private String password;

    /**
     * Get username of bean.
     * 
     * @return username
     */
    public String getName() {
        return name;
    }

    /**
     * Get password of bean.
     * 
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set username of bean.
     * 
     * @param n
     *            username
     */
    public void setName(String n) {
        this.name = n;
    }

    /**
     * Set password of bean.
     * 
     * @param p
     *            password
     */
    public void setPassword(String p) {

        this.password = p;
    }

    /**
     * Lädt User Entity aus der Datenbank, wenn username vorhanden. userpassword
     * = dbuserpassword => welcome userpassword != dbuserpassword => error null
     * = dbuserpassword => create
     * 
     * @return navigation action
     */
    public String login() {

        User user = new User();
        user.setName(name);
        user.getPasswords().add(new Password(password));

        // user.setPassword(password);

        if (userEjb.login(user)) {
            return "welcome";
        } else {
            return "loginerror";
        }
    }

    public String register() {
        User user = new User();
        user.setName(name);
        user.getPasswords().add(new Password(password));
        // user.setPassword(password);

        if (userEjb.register(user)) {
            // login();
            return "usercreated";
        } else {
            return "loginerror";
        }
    }

    /**
     * Receive all persistent users from database.
     * 
     * @return all users
     */
    public Collection<User> getAllUsers() {
        return userEjb.findUsers();
    }
}
