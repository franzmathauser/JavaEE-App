package de.bloodink.ejbs;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import de.bloodink.MailMessage;
import de.bloodink.entities.Password;
import de.bloodink.entities.User;
import de.bloodink.helper.PasswordHasher;

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

    /**
     * Inject mailer object.
     */
    @EJB
    private MailEjb mailer;

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
     * Find DB user by name
     * 
     * @return found user or null
     */
    public User findUserByName(String name) {

        try {
            return em.createNamedQuery(User.FIND_USER_BY_NAME, User.class)
                    .setParameter("n", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Find a user by primarykey.
     * 
     * @param id
     *            user id
     * @return db user
     */
    public User findUserById(long id) {
        return em.find(User.class, id);
    }

    /**
     * Find a user by username.
     * 
     * @param username
     *            name of user
     * @return db user public User findUserByName(String username) { return
     *         em.find(User.class, username); }
     */

    /**
     * persist a user into db.
     * 
     * @param u
     *            user to persist
     */
    public void createUser(User u) {

        em.persist(u);

        String content = "new user: " + u.getName() + " => "
                + u.getPasswords().get(0).getHash() + " => salt: "
                + u.getPasswords().get(0).getSalt();

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

    public boolean login(User user) {
        boolean status = false;
        User dbUser = findUserByName(user.getName());
        System.out.println("login method: " + dbUser);
        if (dbUser != null) {
            Password pw = user.getPasswords().get(0);
            PasswordHasher ph = new PasswordHasher(dbUser.getPasswords().get(0)
                    .getSalt());

            try {
                status = ph.verifyPassword(pw.getHash(), dbUser.getPasswords()
                        .get(0).getHash());
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return status;
    }

    public boolean register(User user) {

        boolean status = false;
        User dbUser = findUserByName(user.getName());

        if (dbUser == null) {

            PasswordHasher ph = new PasswordHasher();
            try {

                Password pw = user.getPasswords().get(0);
                String hash = ph.hashPassword(pw.getHash());
                pw.setHash(hash);

                pw.setSalt(ph.getSalt());
                pw.setCreateDate(new Date());

                createUser(user);

                status = true;
            } catch (NoSuchAlgorithmException e) {

            }

        }

        return status;
    }
}
