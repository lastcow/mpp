package com.projectportal.validator;

import org.jboss.seam.faces.validation.InputElement;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 1/22/13
 * Time: 3:53 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractValidator {

    void doError(InputElement element, String error, String errorMsg){
        // Error.
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, error, errorMsg);
        element.getComponent().setValid(false);
        FacesContext.getCurrentInstance().addMessage(element.getComponent().getClientId(), msg);
        FacesContext.getCurrentInstance().validationFailed();
    }
}
