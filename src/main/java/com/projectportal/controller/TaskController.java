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
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;
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
	@Inject Logger logger;
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

        newTask = this.adjustDatetime(newTask);

        newTask.setTaskDurationHour(this.getWorkingHourBetweenTwoDate(new LocalDate(newTask.getTaskEstimatedStartDate()), new LocalDate(newTask.getTaskEstimatedEndDate())));

        // Same duration hour for estimate and actual
        newTask.setTaskActualDurationHour(newTask.getTaskDurationHour());

        // Task start date equals estimated date for now.
        newTask.setTaskActualStartDate(newTask.getTaskEstimatedStartDate());

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
        requestData.setPreTaskList(new ArrayList<Task>());
	}

    /**
     * update edited task.
     */
    public void update(){
        if(editTask == null) return;

        int daysBetween = 0;
        DateTime editEndDate = null;

        // Set the actual start date if modified.
        if(editTask.getTaskActualStartDateTransient() != null){
            editTask.setTaskActualStartDate(editTask.getTaskActualStartDateTransient());
        }

        // Get estimated end date from DB.
        Task originalTask = em.find(Task.class, editTask.getTaskId());
        System.out.println("Original end date: " + originalTask.getTaskEstimatedEndDate());
        System.out.println("Edit end date: " + editTask.getTaskEstimatedEndDate());
        if(originalTask.getTaskEstimatedEndDate().compareTo(editTask.getTaskEstimatedEndDate()) != 0){
            DateTime originalEndDate = new DateTime(originalTask.getTaskEstimatedEndDate());
            editEndDate = new DateTime(editTask.getTaskEstimatedEndDate());
            daysBetween = Days.daysBetween(originalEndDate, editEndDate).getDays();
        }

        System.out.println("Days different: " + daysBetween);

        // Status.
        if(editTask.getTaskActualStartDate() != null){
            editTask.setStatus(Util.getStatusByName(em, Util.STATUS_IN_PROGRESS));
        }
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


        editTask = this.adjustDatetime(editTask);

        // reset hours.
        editTask.setTaskDurationHour(Util.getWorkingHourBetweenTwoDate(new LocalDate(editTask.getTaskEstimatedStartDate()), new LocalDate(editTask.getTaskEstimatedEndDate())));


        // For actual hours.
        if(editTask.getTaskActualEndDate() != null){
            // Task has completed. Calculate the hours and reset the consequence task actually start date.
            editTask.setTaskActualDurationHour(Util.getWorkingHourBetweenTwoDate(new LocalDate(editTask.getTaskActualStartDate()), new LocalDate(editTask.getTaskActualEndDate())));
        }


        em.merge(editTask);


        if(daysBetween != 0){
            // Update following tasks.
            if(originalTask.getDependentTasks() != null && originalTask.getDependentTasks().size()>0 ){
                // Modify child task date.
                for(Task childTask : originalTask.getDependentTasks()){
                    // Only end date > sequential task's estimated start date, we do modify.
                    if(editEndDate.compareTo(new DateTime(childTask.getTaskEstimatedStartDate())) >= 0 ){
                        // Get the day difference, it's the next day of previous task's estimated end date.
                        int daysToBeAdded = Days.daysBetween(new DateTime(childTask.getTaskEstimatedStartDate()), (new DateTime(editTask.getTaskEstimatedEndDate())).plusDays(1)).getDays();
                        this.doModifyEstimatedDate(childTask, daysToBeAdded);
                    }
                }
            }
        }

        DateTime editActualEndDate = new DateTime(editTask.getTaskActualEndDate());
        // Adjust the start date.
        for(Task subTask : originalTask.getDependentTasks()){

            logger.info("Before Actual Adjust : ====== " + subTask.toString());
            // Only those task not start yet can be changed.
            if(editActualEndDate.isAfter(new DateTime(subTask.getTaskActualStartDate()))){
                // Days between
                int daysToBeAdded = Days.daysBetween(new DateTime(subTask.getTaskActualStartDate()), editActualEndDate.plusDays(1)).getDays();
                this.doModifyActualDate(subTask, daysToBeAdded);
            }
        }

        if(editTask.getTaskActualEndDate() != null){
            // TODO Modify parent task's estimated end date
        }
//        editTask.setDependentTasks(originalTask.getDependentTasks());
//
//        for(Task task : editTask.getDependentTasks()){
//            System.out.println("Task: " + task.getTaskName() + " time: " + task.getTaskEstimatedStartDate()+"/"+task.getTaskEstimatedEndDate());
//        }

    }

    /**
     * Delete task.
     */
    public void delete(){
        if(editTask == null) return;

        // Get task.
        Task taskToBeDeleted = em.find(Task.class, editTask.getTaskId());

        // Deal with sub-tasks.
        List<Task> childTasks = taskToBeDeleted.getChildTasks();
        if(childTasks != null && childTasks.size() > 0){
            // All child task's project id needs to be assigned.
            Project project = this.getProjectFromTask(taskToBeDeleted);
            for(Task childTask : childTasks){
                childTask.setProject(project);
                // Merge.
                em.merge(childTask);
            }

        }

        em.remove(taskToBeDeleted);
    }

    /**
     * Get project from task
     * @param task
     * @return
     */
    private Project getProjectFromTask(Task task){
        if(task.getProject() == null){
            return this.getProjectFromTask(task.getParentTask());
        } else{
            return task.getProject();
        }
    }

    /**
     * Dialog close and reset data.
     */
    public void closeAndReset(){

        logger.info("Reset request data");

        // Reset newTask.
        this.newTask = new Task();
        // Reset editTask
        this.editTask = new Task();
        // Reset resourceDtoList.
        this.requestData.setResourceUserDTOList(new ArrayList<ResoruceUserDTO>());
    }

    /**
     * Load task to modify
     */
    public void loadTask(){

        if(editTask == null){
            return;
        }
        // Get from DB
        editTask = em.find(Task.class, editTask.getTaskId());
        if(! editTask.getStatus().getStatusName().equals(Util.STATUS_NEW)){
            // Assign actual start date to transent field.
            editTask.setTaskActualStartDateTransient(editTask.getTaskActualStartDate());
        }

        List<Task> tmpPreTaskList = new ArrayList<Task>();
        tmpPreTaskList.add(editTask.getPreTask());
        requestData.setPreTaskList(tmpPreTaskList);

        logger.info("Loaded task: " + editTask.toString());
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

    /**
     * Action for functional form call.
     */
    public void fakeAction(){
        // Do nothing.
    }

    /**
     * Update task's estimated date.
     * Only update sequential task's start/end date when end date cross start date.
     * @param task
     * @param daysDiff
     */
    private void doModifyEstimatedDate(Task task, int daysDiff){

        System.out.println("Modify task: " + task.getTaskName());
        DateTime estimatedStartDate = new DateTime(task.getTaskEstimatedStartDate());
        DateTime estimatedEndDate = new DateTime(task.getTaskEstimatedEndDate());

        // Add days diff.
        estimatedStartDate = estimatedStartDate.plusDays(daysDiff);
        estimatedEndDate = estimatedEndDate.plusDays(daysDiff);

        task.setTaskEstimatedStartDate(estimatedStartDate.toDate());
        task.setTaskEstimatedEndDate(estimatedEndDate.toDate());

        // Set actuall start date,
        if (task.getStatus().equals(Util.getStatusByName(em, Util.STATUS_NEW))){
            // Set default actualy startdate = estimated start date.
            task.setTaskActualStartDate( task.getTaskEstimatedStartDate());
        }


        System.out.println("Task: " + task.getTaskName() + " time: " + task.getTaskEstimatedStartDate()+"/"+task.getTaskEstimatedEndDate());

        // Adjust for the holiday.
        task = this.adjustDatetime(task);
        // Adjust the estimated hours
        task.setTaskDurationHour(this.getWorkingHourBetweenTwoDate(new LocalDate(task.getTaskEstimatedStartDate()), new LocalDate(task.getTaskEstimatedEndDate())));


        em.merge(task);

        // Recrusive call
        if(task.getDependentTasks() != null && task.getDependentTasks().size() > 0){
            for(Task childTask : task.getDependentTasks()){
                if(estimatedEndDate.isAfter(new DateTime(childTask.getTaskEstimatedStartDate()))){
                    doModifyEstimatedDate(childTask, Days.daysBetween(new DateTime(childTask.getTaskEstimatedStartDate()), estimatedEndDate.plusDays(1)).getDays());
                }
            }
        }
    }

    /**
     * Adjust task's actual date.
     * @param task
     * @param daysBetween
     */
    private void doModifyActualDate(Task task, int daysBetween){

        DateTime actualStartDate = new DateTime(task.getTaskActualStartDate());
        DateTime actualEndDate = new DateTime(task.getTaskActualEndDate());

        // Set the actual start date.
        task.setTaskActualStartDate(actualStartDate.plusDays(daysBetween).toDate());
        if(actualEndDate != null){
            task.setTaskActualEndDate(actualEndDate.plusDays(daysBetween).toDate());
        }

        // Adjust for the holiday.
//        task = this.adjustDatetime(task);
        // Adjust the estimated hours
        if(task.getTaskActualEndDate() != null){
            task.setTaskActualDurationHour(this.getWorkingHourBetweenTwoDate(new LocalDate(task.getTaskActualEndDate()), new LocalDate(task.getTaskActualStartDate())));
        }else{
            task.setTaskActualDurationHour(this.getWorkingHourBetweenTwoDate(new LocalDate(task.getTaskEstimatedEndDate()), new LocalDate(task.getTaskActualStartDate())));
        }

        // Percistence.
        em.merge(task);

        actualEndDate = new DateTime(task.getTaskActualEndDate());

        logger.info("========== Actual task info: " + task.toString());

        // Check for subsequence tasks.
        if(actualEndDate != null){
            for(Task subTask : task.getDependentTasks()){

                // Only those task not start yet can be changed.
                if(actualEndDate.compareTo(new DateTime(subTask.getTaskActualEndDate())) >= 0){
                    // Days between
                    int daysToBeAdded = Days.daysBetween(new DateTime(subTask.getTaskActualStartDate()), actualEndDate.plusDays(1)).getDays();

                    logger.info("=================== Days diff for actually date: " + daysToBeAdded);
                    this.doModifyActualDate(subTask, daysToBeAdded);
                }
            }
        }
    }


    /**
     * Adjust task start/end date based on the holiday
     * @param task
     * @return
     */
    private Task adjustDatetime(Task task){

        // Get list of holidays.
        List<Holiday> holidayList = em.createQuery("SELECT holiday FROM Holiday holiday ").getResultList();

        DateTime startDate = new DateTime(task.getTaskEstimatedStartDate());
        DateTime endDate = new DateTime(task.getTaskEstimatedEndDate());
        DateTime holidayStartDate = null;
        DateTime holidayEndDate = null;

        for(Holiday holiday : holidayList){
            holidayStartDate = new DateTime(holiday.getStartDate());
            if(holiday.getEndDate() != null){
                holidayEndDate = new DateTime(holiday.getEndDate());
            }else{
                holidayEndDate = holidayStartDate;
            }

            if(startDate.isAfter(holidayStartDate.plusDays(-1)) && startDate.isBefore(holidayEndDate.plusDays(1))){
                // It's holiday.
                startDate = holidayEndDate.plusDays(1);
            }
            if(endDate.isAfter(holidayStartDate.plusDays(-1)) && endDate.isBefore(holidayEndDate.plusDays(1))){
                // It's holiday.
                endDate = holidayEndDate.plusDays(1);
            }



            // Check for weekend.
            if(startDate.getDayOfWeek() == DateTimeConstants.SATURDAY  ){
                // Add up to monday.
                startDate = startDate.plusDays(2);
            }
            // Check for weekend.
            if(startDate.getDayOfWeek() == DateTimeConstants.SUNDAY  ){
                // Add up to monday.
                startDate = startDate.plusDays(1);
            }
            // Check for weekend.
            if(endDate.getDayOfWeek() == DateTimeConstants.SATURDAY  ){
                // Add up to monday.
                endDate = endDate.plusDays(2);
            }
            // Check for weekend.
            if(endDate.getDayOfWeek() == DateTimeConstants.SUNDAY  ){
                // Add up to monday.
                endDate = endDate.plusDays(1);
            }
        }

        // Reset date.
        task.setTaskEstimatedStartDate(startDate.toDate());
        task.setTaskEstimatedEndDate(endDate.toDate());

        // End date change needs change the hours.

        return task;
    }


    /**
     * Calculate the hours.
     * @param startDate
     * @param endDate
     * @return
     */
    private int getWorkingHourBetweenTwoDate(LocalDate startDate, LocalDate endDate){


        int days = Days.daysBetween(startDate.toDateMidnight(), endDate.toDateMidnight()).getDays();

        System.out.println("Start/End: " + startDate.toString() + "/" + endDate.toString() + " - days between: " + days);
        // Count one more day for math.
        return (days+1) * Util.HOURS_PER_DAY;
    }


    public TaskController() {
        LocalDate startDate = new LocalDate(2013, 4, 18);
        LocalDate endDate = new LocalDate(2013, 4, 23);

        System.out.println("Days between: " + this.getWorkingHourBetweenTwoDate(startDate, endDate));
    }

    public static  void main (String[] args){
        new TaskController();
    }


}
