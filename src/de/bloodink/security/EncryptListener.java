package de.bloodink.security;

import java.lang.reflect.Field;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import de.bloodink.security.annotation.Encrypt;

/**
 * EncryptListener is a Class which Methods are called on Annotated Callbacks.
 * This Class must be registered as EntityListener either through Annotation or
 * XML configuration in persistence.xml in order to perfom actions. The passed
 * Entity is used to find Membervariables which are market with Encrypt
 * Annotation. If a variable is found, the value is changed (encrypt or
 * decrypt).
 * 
 * @author Franz Mathauser
 */
public class EncryptListener {

    /**
     * Encryption Pattern.
     */
    public static final String ENCRYPTION_PREFIX = "XXX";

    /**
     * Das Entity wird durchsucht ob eine Membervariable eine Encrypt Annotation
     * enthält. Wenn ja, dann wird der Wert verschlüsselt.
     * 
     * @param obj
     *            Entity
     */
    @PrePersist
    @PreUpdate
    public void prePersist(Object obj) {

        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.getAnnotation(Encrypt.class) != null) {

                try {
                    field.setAccessible(true);
                    System.out.println("fetch value"
                            + field.get(obj).toString());

                    String newValue = ENCRYPTION_PREFIX
                            + field.get(obj).toString();
                    // obj.setPassword(newValue);
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

    /**
     * Das Entity wird durchsucht ob eine Membervariable eine Encrypt Annotation
     * enthält. Wenn ja, dann wird der Wert entschlüsselt.
     * 
     * @param obj
     *            Entity
     */
    @PostLoad
    public void postLoad(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.getAnnotation(Encrypt.class) != null) {

                try {
                    field.setAccessible(true);
                    System.out.println("fetch value"
                            + field.get(obj).toString());

                    String newValue = field.get(obj).toString()
                            .substring(ENCRYPTION_PREFIX.length());
                    // obj.setPassword(newValue);
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
