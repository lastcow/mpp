package com.projectportal.validator;

import com.projectportal.data.RequestData;
import com.projectportal.dto.ResoruceUserDTO;
import com.projectportal.entity.User;
import org.jboss.seam.faces.validation.InputElement;
import org.jboss.solder.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 1/22/13
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
@FacesValidator("resourceValidator")
public class ResourceValidator extends AbstractValidator implements Validator {

    @Inject
    RequestData requestData;
    @Inject
    InputElement<User> comboResource;
    @Inject
    Logger logger;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if(comboResource.getValue() != null ){
            //if(requestData.getResourceUserDTOList().contains(comboResource.getValue())){
            for(ResoruceUserDTO resoruceUserDTO : requestData.getResourceUserDTOList()){
                if(comboResource.getValue().getUserId().equals(resoruceUserDTO.getUser().getUserId())){
                    doError(comboResource, "User already in this task", "User already assigned.");
                    break;
                }
            }
            //}
        }
    }
}
