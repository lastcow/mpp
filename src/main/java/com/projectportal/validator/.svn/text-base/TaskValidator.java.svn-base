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
@FacesValidator("taskValidator")
public class TaskValidator extends AbstractValidator implements Validator {

    @Inject EntityManager em;
    @Inject Identity identity;
    @Inject Logger logger;

    @Inject RequestData requestData;
    @Inject InputElement<String> hiddenProjectId;
    @Inject InputElement<String> txtNewTaskName;
    @Inject InputElement<Date> txtNewTaskStartDate;
    @Inject InputElement<Date> txtNewTaskEndDate;
    @Inject InputElement<String> taNewTaskDesc;
    @Inject InputElement<Task> comboNewTaskParent;
//    @Inject InputElement<Task> comboNewTaskPred;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        for(Task preTask : requestData.getPreTaskList()){
            System.out.println("Pre task: " + preTask.getTaskName());
        }

        // Start Date can't earyly than project start date.
        if(txtNewTaskStartDate.getValue() != null ){
            // Get project
            Project project = em.find(Project.class, hiddenProjectId.getValue());
            logger.info("Project information reterieved: " + project.toString());
            if(txtNewTaskStartDate.getValue().compareTo(project.getStartDate()) <0 ){
                // Error.
                doError(txtNewTaskStartDate, "Invalid task start/end dates !", "Task start date must be after project start date");
            }

            // Check for the pretask.
            if(requestData.getPreTaskList() != null && requestData.getPreTaskList().size() > 0){
                // TODO always get first one now. (due to component limitation)
                Task preTask = requestData.getPreTaskList().get(0);
                if(preTask  != null ){
                    // Check for the date.
                    if (! (txtNewTaskStartDate.getValue().compareTo(preTask.getTaskEstimatedEndDate()) > 0)){
                        // Error.
                        doError(txtNewTaskStartDate, "Invalid task start/end dates !", "Task start date must be after pre-task end date");
                    }
                }
            }
        }

        // Date range validation
        if(txtNewTaskStartDate.getValue() != null && txtNewTaskEndDate.getValue() != null && txtNewTaskStartDate.getValue().after(txtNewTaskEndDate.getValue())){
            // Error.
            doError(txtNewTaskEndDate, "Invalid task start/end dates !", "Task end date must be after start date ");
        }

        // Parent task.
        if(comboNewTaskParent.getValue() != null ){
            // Check for start date
            if(txtNewTaskStartDate.getValue().compareTo(comboNewTaskParent.getValue().getTaskActualStartDate()) < 0 ){
                // Error
                doError(txtNewTaskStartDate, "Invalid task start/end dates !", "Task start date must be after parent's start date ");
            }
            // Check for end date
            if(txtNewTaskEndDate.getValue() != null){
                // End date should <= parent task.
                if(txtNewTaskEndDate.getValue().after(comboNewTaskParent.getValue().getTaskEstimatedEndDate())){
                    // Error
                    doError(txtNewTaskEndDate, "Invalid task start/end dates !", "Task end date must be before parent's end date ");
                }
            }
        }

        // Pre task
//        if(comboNewTaskPred.getValue() != null){
//            // Check for start date.
//            if(txtNewTaskStartDate.getValue() != null){
//                // Start date can't early than pre task's end date.
//                if(txtNewTaskStartDate.getValue().compareTo(comboNewTaskPred.getValue().getTaskEstimatedEndDate()) <= 0 ){
//                    // Error
//                    doError(txtNewTaskStartDate, "Invalid task start/end dates !", "New task start date must after pre-task end date ");
//
//                }
//            }
//        }

    }
}
