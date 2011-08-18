package de.bloodink.ejbs;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.bloodink.entities.User;

/**
 * Session Bean implementation class HelloBean
 */
@Stateless
public class UserEjb {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public UserEjb() {

	}
	
	public Collection<User> findUsers(){
		
		return em.createNamedQuery(User.FIND_ALL_USERS,User.class).getResultList();
		
	}

	public User findUserByName(String id) {
		return em.find(User.class, id);
	}

	public void createUser(User user) {
		em.persist(user);
	}

	public void updateUser(User user) {
		em.merge(user);
	}

	public void deleteUser(User user) {
		em.remove(em.merge(user));

	}
}
