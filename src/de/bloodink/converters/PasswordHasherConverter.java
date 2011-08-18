package de.bloodink.converters;

import java.security.NoSuchAlgorithmException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import de.bloodink.helper.PasswordHasher;

@FacesConverter(value="passwordHasher")
public class PasswordHasherConverter implements Converter {
	
	PasswordHasher ph = new PasswordHasher();;
		
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		return getAsString(ctx,component,(Object)value);
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
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
