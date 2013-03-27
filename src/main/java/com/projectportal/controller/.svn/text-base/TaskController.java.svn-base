/**
 * 
 */
package com.projectportal.controller;

import com.projectportal.data.RequestData;
import com.projectportal.dto.ResoruceUserDTO;
import com.projectportal.entity.*;
import com.projectportal.security.binding.Admin;
import com.projectportal.util.Util;
import org.jboss.seam.security.Identity;
import org.joda.time.LocalDate;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;


/**
 * @author lastcow
 *
 */
@SuppressWarnings("serial")
public @Named @ViewScoped class TaskController implements Serializable{

	@Inject private EntityManager em;
	@Inject Identity identity;
	@Inject Logger log;
	@Inject Event<Task> taskEventSrc;
    @Inject private RequestData requestData;
	
	@Named
	@Produces
	private List<Task> requestTaskList;
	
	private String projectId;
	
	private Task newTask;

    private Task editTask;
	
	@Produces
	@Named
	public Task getNewTask(){
		return this.newTask;
	}

    /** null to new task to avoid error on page
     *
     * @return
     */
    @Produces
    @Named
    public Task getEditTask() {
        return this.editTask;
    }


	/**
	 * 
	 */
	public TaskController() {
		// TODO Auto-generated constructor stub
	}
	
	public void loadSelfTasks(){
		
		// Init
		newTask = new Task();
        editTask = new Task();
		
		// Get user from DB.
		User user = em.find(User.class, identity.getUser().getId());
		requestTaskList = new ArrayList<Task>();
		
		// Loop user's tasks.
		for(UserXTask userTask : user.getUserXtasks()){
			if(userTask.getTask().getProject().getProjectId().equals(projectId)){
				requestTaskList.add(userTask.getTask());
			}
		}
	}
	
	/**
	 * Create task.
	 * @throws Exception
	 */
	@Admin
	public void creat() throws Exception{

		// Manual assign id.
		newTask.setTaskId(UUID.randomUUID().toString());

        // Priority as normal for all.
        newTask.setPriority(em.find(Priority.class, "1"));
        // Status as new
        newTask.setStatus(em.find(Status.class, "1"));

        // Start date & end estimate date.
        if(newTask.getTaskEstimatedStartDate() == null ){
            // set as project start date.
            newTask.setTaskEstimatedStartDate(em.find(Project.class, projectId).getStartDate());
        }
        if(newTask.getTaskEstimatedEndDate() == null){
            newTask.setTaskEstimatedEndDate(newTask.getTaskEstimatedStartDate());
        }
        // Task start date equals estimated date for now.
        newTask.setTaskActualStartDate(newTask.getTaskEstimatedStartDate());

        // Caculate Task Estimated End date.
        LocalDate startDate = new LocalDate(newTask.getTaskEstimatedStartDate());
        LocalDate endDate = new LocalDate(newTask.getTaskEstimatedEndDate());
        newTask.setTaskDurationHour(Util.getWorkingHourBetweenTwoDate(startDate, endDate));

		// Project related or parent task related.
		if(newTask.getParentTask() == null){
			// Assign project with.
			newTask.setProject(em.find(Project.class, projectId));
		}

        // Set related user.
        UserXTask userXTask;
        UserXTaskPK pk;
        List<UserXTask> userXTaskList = new ArrayList<UserXTask>();
        for(ResoruceUserDTO userResourceDTO : requestData.getResourceUserDTOList()){
            userXTask = new UserXTask();
            pk = new UserXTaskPK();

            pk.setTaskId(newTask.getTaskId());
            pk.setUserId(userResourceDTO.getUser().getUserId());
            userXTask.setId(pk);
            userXTask.setUser(em.find(User.class, identity.getUser().getId()));
            userXTask.setTask(newTask);
            userXTask.setUtilized(userResourceDTO.getPercentUtil());

            userXTaskList.add(userXTask);
        }

        // Do pretask (only take one here.)
        if(requestData.getPreTaskList() != null && requestData.getPreTaskList().size()>0){
            newTask.setPreTask(requestData.getPreTaskList().get(0));
        }
		newTask.setUserXtasks(userXTaskList);

        // Persistence to DB.
        em.persist(newTask);
		
		// Fire event.
		taskEventSrc.fire(newTask);
		
		// Reset
		newTask = new Task();
	}

    /**
     * update edited task.
     */
    public void update(){
        if(editTask == null) return;

        // If end date detected, task completed.
        if(editTask.getTaskActualEndDate() != null){
            editTask.setTaskPercentComplete(100f);
        }
        // Do update.
        if(editTask.getTaskPercentComplete() == 100f){
            // Set status to complete.
            editTask.setStatus(Util.getStatusByName(em, Util.STATUS_FINISH));
        }
        else if(editTask.getTaskPercentComplete() == 0f){
            editTask.setStatus(Util.getStatusByName(em, Util.STATUS_NEW));
        }else{
            editTask.setStatus(Util.getStatusByName(em, Util.STATUS_IN_PROGRESS));
        }

        em.merge(editTask);
    }

    /**
     * Dialog close and reset data.
     */
    public void closeAndReset(){

        // Reset newTask.
        this.newTask = new Task();
        // Reset resourceDtoList.
        this.requestData.setResourceUserDTOList(new ArrayList<ResoruceUserDTO>());
    }

    /**
     * Load task to modify
     */
    public void loadTask(){
        log.info("Task id: " + editTask.getTaskId());

        if(editTask == null){
            return;
        }
        // Get from DB
        editTask = em.find(Task.class, editTask.getTaskId());

        log.info("Task name: " + editTask.getTaskName());
    }

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


}
