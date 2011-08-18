package de.bloodink.controllers;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.bloodink.ejbs.UserEjb;
import de.bloodink.entities.User;
import de.bloodink.helper.PasswordHasher;

@ManagedBean
@SessionScoped
public class LoginBean {

	@EJB
	UserEjb userEjb;

	private String name;
	private String password;

	private User dbUser;

	public LoginBean() {
		new PasswordHasher();
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public String saveCredentials() {
		User user = new User();
		user.setName(name);
		user.setPassword(password);

		dbUser = userEjb.findUserByName(name);
		if (dbUser == null) {
			userEjb.createUser(user);
		} else {
			if (password.equals(dbUser.getPassword()))
				return "welcome";
			else
				return "loginerror";
		}

		return "usercreated";
	}

	public User getDbUser() {
		return dbUser;
	}

	public void setDbUser(User dbUser) {
		this.dbUser = dbUser;
	}
	
	public Collection<User> getAllUsers(){
		return userEjb.findUsers();
	}
}
