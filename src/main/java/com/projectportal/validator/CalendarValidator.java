package com.projectportal.validator;

import org.jboss.seam.faces.validation.InputElement;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 1/18/13
 * Time: 12:57 AM
 * To change this template use File | Settings | File Templates.
 */
@FacesValidator("calendarValidator")
public class CalendarValidator implements Validator {


    @Inject
    //@InputField("startDate")
    private InputElement<Date> startDate;

    @Inject
    //@InputField("endDate")
    private InputElement<Date> endDate;

    @Inject
    private InputElement<String> desc;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if(endDate.getValue() != null && startDate.getValue().after(endDate.getValue())){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid project start/end dates !",
                    "Holiday end date must be after start date ");
            endDate.getComponent().setValid(false);
            FacesContext.getCurrentInstance().addMessage(endDate.getComponent().getClientId(), msg);
            FacesContext.getCurrentInstance().validationFailed();
        }

        // Description length.
//        if(desc.getValue() != null && desc.getValue().length() > 400 ){
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Too long for desc",
//                    "Max length for desc is 400");
//            desc.getComponent().setValid(false);
//            FacesContext.getCurrentInstance().addMessage(desc.getComponent().getClientId(), msg);
//            facesContext.getCurrentInstance().validationFailed();
//        }

    }
}
