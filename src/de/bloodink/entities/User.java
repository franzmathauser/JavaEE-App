package de.bloodink.entities;

import java.io.Serializable;
import javax.persistence.*;

import de.bloodink.security.EncryptListener;
import de.bloodink.security.annotation.Encrypt;


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
	@Encrypt
	private String name;
	@Encrypt
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