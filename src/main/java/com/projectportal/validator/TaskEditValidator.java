package com.projectportal.validator;

import com.projectportal.data.RequestData;
import com.projectportal.entity.Project;
import com.projectportal.entity.Task;
import org.jboss.seam.faces.validation.InputElement;
import org.jboss.seam.security.Identity;
import org.jboss.solder.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 1/21/13
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
@FacesValidator("taskEditValidator")
public class TaskEditValidator extends AbstractValidator implements Validator {

    @Inject EntityManager em;
    @Inject Identity identity;
    @Inject Logger logger;

    @Inject RequestData requestData;
    @Inject InputElement<String> txtEditTaskName;
    @Inject InputElement<Integer> txtEditTaskPercentage;
    @Inject InputElement<Date> txtEditTaskStartDate;
    @Inject InputElement<String> editTaskId;


    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        logger.info("Start task edit form validation");
        Task task = em.find(Task.class, editTaskId.getValue());
        Project project = task.getProject();
        Task preTask = task.getPreTask();

        // Start Date can't earyly than project start date.
        if(txtEditTaskStartDate.getValue() != null ){

            if(txtEditTaskStartDate.getValue().compareTo(project.getStartDate()) <0 ){
                // Error.
                doError(txtEditTaskStartDate, "Invalid task start/end dates !", "Task start date must be after project start date");
            }

            // Check for the pretask.
            if(preTask  != null ){
                // Check for the date.
                if (! (txtEditTaskStartDate.getValue().compareTo(preTask.getTaskEstimatedEndDate()) > 0)){
                    // Error.
                    doError(txtEditTaskStartDate, "Invalid task start/end dates !", "Task start date must be after pre-task end date");
                }
            }
        }

    }
}
