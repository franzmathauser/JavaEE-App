package de.bloodink.controllers;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.bloodink.ejbs.UserEjb;
import de.bloodink.entities.User;
import de.bloodink.helper.PasswordHasher;

/**
 * Login Bean is ein JSF Controller, der die Logindaten abfängt und ein User an
 * eine EJB weiterreicht.
 * 
 * @author Franz Mathauser
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    /**
     * Injected EJB, which holds business logic of User.
     */
    @EJB
    UserEjb userEjb;

    /**
     * Username.
     */
    private String name;
    /**
     * Password.
     */
    private String password;

    /**
     * User received vom Database with same name as loginname.
     */
    private User dbUser;

    /**
     * Default Constructror for POJO.
     */
    public LoginBean() {
        new PasswordHasher();
    }

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
     * @param name
     *            username
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set password of bean.
     * 
     * @param password
     *            password
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * Lädt User Entity aus der Datenbank, wenn username vorhanden. userpassword
     * = dbuserpassword => welcome userpassword != dbuserpassword => error null
     * = dbuserpassword => create
     * 
     * @return navigation action
     */
    public String saveCredentials() {
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        dbUser = userEjb.findUserByName(name);
        if (dbUser == null) {
            userEjb.createUser(user);
        } else {
            if (password.equals(dbUser.getPassword()))
                return "welcome";
            else
                return "loginerror";
        }

        return "usercreated";
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
