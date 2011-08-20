package de.bloodink.ejbs;

import java.util.Collection;

import javax.ejb.Stateless;
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
