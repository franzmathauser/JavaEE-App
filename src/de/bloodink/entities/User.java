package de.bloodink.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import de.bloodink.security.EncryptListener;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name = User.FIND_ALL_USERS, query="SELECT u FROM User u")
@EntityListeners({EncryptListener.class})
public class User implements Serializable {
	
	public static final String FIND_ALL_USERS = "User.findAllUsers";
	
	private static final long serialVersionUID = 1L;

	@Id
	private String name;
	private String password;

    public User() {
    }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}