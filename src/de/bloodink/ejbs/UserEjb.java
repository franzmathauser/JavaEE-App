package de.bloodink.ejbs;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.bloodink.entities.User;

/**
 * Session Bean with businesslogic of a user entity.
 */
@Stateless
public class UserEjb {

    /**
     * Injected entityManager to perfom db actions.
     */
    @PersistenceContext
    private EntityManager em;

    @Resource(name = "jdbc/javamail")
    private Session ms;

    /**
     * Default constructor.
     */
    public UserEjb() {

    }

    /**
     * Find all DB users.
     * 
     * @return list off all users
     */
    public Collection<User> findUsers() {

        return em.createNamedQuery(User.FIND_ALL_USERS, User.class)
                .getResultList();

    }

    /**
     * Find a user by primarykey.
     * 
     * @param id
     *            username
     * @return db user
     */
    public User findUserByName(String id) {
        return em.find(User.class, id);
    }

    /**
     * persist a user into db.
     * 
     * @param u
     *            user to persist
     */
    public void createUser(User u) {

        MimeMessage msg = new MimeMessage(ms);
        String content = "new user: " + u.getName() + " => " + u.getPassword();
        try {
            msg.setFrom(new InternetAddress("franz.mathauser@googlemail.com"));
            InternetAddress[] to = { new InternetAddress("info@blood-ink.de") };
            msg.setRecipients(RecipientType.TO, to);
            msg.setSubject("New User");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html");
            System.out.println("Before send");
            javax.mail.Transport.send(msg);
            System.out.println("After send");

        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        em.persist(u);
    }

    /**
     * updates a db user.
     * 
     * @param u
     *            user to update
     */
    public void updateUser(User u) {
        em.merge(u);
    }

    /**
     * delete a user from db.
     * 
     * @param u
     *            user to be deleted
     */
    public void deleteUser(User u) {
        em.remove(em.merge(u));

    }
}
