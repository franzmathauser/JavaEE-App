package de.bloodink.security;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import de.bloodink.security.annotation.Encrypt;

public class EncryptListener {
	
	@PersistenceContext 
	EntityManager em;

	@PrePersist
	@PreUpdate
	public void prePersist(Object obj) {

		for (Field field : obj.getClass().getDeclaredFields()) {
			if (field.getAnnotation(Encrypt.class) != null) {

				try {
					field.setAccessible(true);
					System.out.println("fetch value"
							+ field.get(obj).toString());

					String newValue = "XXX" + field.get(obj).toString();
					//obj.setPassword(newValue);
					field.set(obj, newValue);
					field.setAccessible(false);

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@PostLoad
	public void postLoad(Object obj){
		for (Field field : obj.getClass().getDeclaredFields()) {
			if (field.getAnnotation(Encrypt.class) != null) {

				try {
					field.setAccessible(true);
					System.out.println("fetch value"
							+ field.get(obj).toString());

					String newValue = field.get(obj).toString().substring(3);
					//obj.setPassword(newValue);
					field.set(obj, newValue);
					field.setAccessible(false);

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	

}
