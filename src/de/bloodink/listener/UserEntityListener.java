package de.bloodink.listener;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.persistence.PostPersist;

import de.bloodink.ejbs.MailEjb;
import de.bloodink.entities.User;

@ManagedBean
public class UserEntityListener {

    @EJB
    MailEjb mailer;

    @PostPersist
    public void prePersist(User u) {
        String content = "new user: " + u.getName() + " => " + u.getPassword();
        // mailer.sendMail();
    }

}
