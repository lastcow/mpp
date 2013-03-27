package com.projectportal.validator;

import org.jboss.seam.faces.validation.InputElement;
import org.jboss.solder.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 2/4/13
 * Time: 8:27 PM
 * To change this template use File | Settings | File Templates.
 */
@FacesValidator("userValidator")
public class UserValidator extends AbstractValidator implements Validator {

    @Inject
    EntityManager em;
    @Inject
    Logger logger;

    @Inject InputElement<String> txtResourceName;
    @Inject InputElement<String> txtResourceEmail;
    @Inject InputElement<String> validateFlag;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        boolean validate = Boolean.valueOf(validateFlag.getValue()).booleanValue();
        // User name check
        if(validate && txtResourceName.getValue() != null){
            // Check for whether resource name existed in system.
            if(em.createQuery("SELECT user FROM User user WHERE user.userName = '" + txtResourceName.getValue() + "'").getResultList().size() > 0){
                // Error
                doError(txtResourceName, "Invalid username !", "Username already taken!");
            }
        }

        // Email check
        /**
         *
         * 2013-02-23, email can be duplicated.
         */
//        if(txtResourceEmail.getValue() != null ){
//            // Check with db
//            if(em.createQuery("SELECT user FROM User user WHERE user.email ='" + txtResourceEmail.getValue() + "'").getResultList().size() > 0){
//                // Error
//                doError(txtResourceEmail, "Invalid email !", "Email already taken!");
//            }
//        }
    }
}
