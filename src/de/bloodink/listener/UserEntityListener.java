package de.bloodink.listener;

import javax.ejb.EJB;
import javax.persistence.PostPersist;

import de.bloodink.ejbs.MailEjb;
import de.bloodink.entities.User;

public class UserEntityListener {

    @EJB
    MailEjb mailer;

    @PostPersist
    public void prePersist(User u) {
        String content = "new user: " + u.getName() + " => " + u.getPassword();
        mailer.sendMail();
    }

}
