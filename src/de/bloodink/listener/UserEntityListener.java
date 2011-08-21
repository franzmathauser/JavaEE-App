package de.bloodink.listener;

import javax.persistence.PostPersist;

import de.bloodink.entities.User;

/**
 * Entity listener.
 * 
 * @author Franz Mathauser
 */
public class UserEntityListener {

    /**
     * is called if a User ist persisted into database on a insert statement.
     * 
     * @param u
     *            persisted user
     */
    @PostPersist
    public void prePersist(User u) {
        // mailer.sendMail();
    }

}
