package de.bloodink.ejbs;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.bloodink.MailMessage;
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

    @EJB
    MailEjb mailer;

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

        em.persist(u);

        String content = "new user: " + u.getName() + " => " + u.getPassword();

        MailMessage mailMessage = new MailMessage();

        mailMessage.addRecipient("info@blood-ink.de");
        mailMessage.setContent(content);
        mailMessage.setSubject("new user: " + u.getName());
        mailer.sendAsyncMail(mailMessage);

        mailer.sendSyncMail(mailMessage);

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
