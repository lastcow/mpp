package com.projectportal.validator;


import org.jboss.seam.faces.validation.InputElement;

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
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
@FacesValidator("projectValidator")
public class ProjectValidator implements Validator {


    @Inject
    private InputElement<String> txtNewProjectName;

    @Inject
    private InputElement<Date> txtNewProjectStartDate;

    @Inject
    private InputElement<String> txtProjectDesc;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        // All webpage validation.
    }
}
