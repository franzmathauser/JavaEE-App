package de.bloodink.converters;

import java.security.NoSuchAlgorithmException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import de.bloodink.helper.PasswordHasher;

/**
 * JSF Converter to Hash a value before putting it into a managedbean value.
 * 
 * @author Franz Mathauser
 */
@FacesConverter(value = "passwordHasher")
public class PasswordHasherConverter implements Converter {

    /**
     * hasher object.
     */
    private final PasswordHasher ph = new PasswordHasher();;

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component,
            String value) {
        return getAsString(ctx, component, value);
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component,
            Object value) {
        String password = value.toString();
        String hash = null;
        try {
            hash = ph.hashPassword(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return hash;
    }

}
