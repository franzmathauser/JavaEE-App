package de.bloodink.validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;

/**
 * JSF Validator to validate a Password on form action.
 * 
 * @author Franz Mathauser
 */
@FacesValidator(value = "validatePassword")
public class ValidatePassword implements Validator {

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) {
        //

    }

}
