/**
 * 
 */
package com.projectportal.data;

import com.projectportal.dto.ResoruceUserDTO;
import com.projectportal.entity.Project;
import com.projectportal.entity.Task;
import com.projectportal.entity.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author lastcow
 *
 */
@SuppressWarnings("serial")
@Named
public @ViewScoped class RequestData extends AbstractProducer implements Serializable{

	private String projectId;
	
	@Inject Logger log;
	
	@Named
	private List<SelectItem> comboTaskByProject;

    private ResoruceUserDTO resourceUserDTO;
    // Resource list (Used for display task form).
    private List<ResoruceUserDTO> resourceUserDTOList;
    // Pre task list
    private List<Task> preTaskList;

    // User id selected
    private String selectedUserId;
    // Task id selected;
    private String selectedTaskId;
    // Pretask
    private Task preTask;

    @PostConstruct
    public void postInit(){
        resourceUserDTO = new ResoruceUserDTO();
        resourceUserDTOList = new ArrayList<ResoruceUserDTO>();
        preTaskList = new ArrayList<Task>();
    }

    /**
     * Load tasks from database.
     * @return
     */
    public List<SelectItem> loadComboboxTasksByProject(){

		Project project = em.find(Project.class, projectId);
//		List<Task> taskList = project.getTasks();
//		comboTaskByProject = taskList;
		List<SelectItem> comboboxItems = new ArrayList<SelectItem>();
		for(Task task : project.getTasks()){
			buildSelectItem(comboboxItems, task, 0);
		}

		return comboboxItems;
	}

	
	/**
	 * Build task list.
	 * @param task
	 * @param n
	 * @return
	 */
	private void buildSelectItem(List<SelectItem> comboTaskByProject, Task task, int n){
		
		String prefix = "";
		for(int i = 0; i<n; i++){
			prefix += "-";
		}
		
		comboTaskByProject.add(new SelectItem(task, prefix + task.getTaskName()));
		
		// For sub tasks.
		if(task.getChildTasks() != null && task.getChildTasks().size() > 0 ){
			n++;
			for(Task subTask : task.getChildTasks()){
				buildSelectItem(comboTaskByProject, subTask, n);
			}
		}
	}

    /**
     * Get list of users
     * @return
     */
    public List<SelectItem> loadComboboxUsers(){

        List<SelectItem> comboboxItems = new ArrayList<SelectItem>();
        List<User> userList = em.createQuery("SELECT user FROM User user ORDER BY user.userName").getResultList();
        for(User user: userList){
            comboboxItems.add(new SelectItem(user, user.getUserName()));
        }

        return comboboxItems;
    }

    /**
     * Get list of projects
     * @return
     */
    public List<SelectItem> loadComboboxProjects(){
        List<SelectItem> comboboxItems = new ArrayList<SelectItem>();
        List<Project> projectList = em.createQuery("SELECT project FROM Project project ORDER BY project.projectName").getResultList();
        for(Project project : projectList){
            comboboxItems.add(new SelectItem(project, project.getProjectName()));
        }

        return comboboxItems;
    }


    /**
     * Insert resource DTO.
     */
    public void insertResourceDto(){
        if(resourceUserDTO != null){
            resourceUserDTOList.add(resourceUserDTO);
            resourceUserDTO = new ResoruceUserDTO();
        }
    }

    /**
     * Remove selected user from list.
     */
    public void removeResourceDto(){
        log.info("Removing from list: " + selectedUserId);
        if(resourceUserDTOList != null && resourceUserDTOList.size() > 0 && selectedUserId != null){
            // Build ResoruceUserDTO with userId
            User user = new User();
            user.setUserId(selectedUserId);
            ResoruceUserDTO userDto = new ResoruceUserDTO();
            userDto.setUser(user);

            log.info("Size before: " + resourceUserDTOList.size());
            resourceUserDTOList.remove(userDto);
            log.info("Size after: " + resourceUserDTOList.size());
        }
    }

    /**
     * Insert to task list.
     */
    public void insertPreTask(){
        if(preTaskList != null && preTask != null){
            preTaskList.add(preTask);
        }
    }

    /**
     * Remove pretask from list
     */
    public void removePreTask(){
        if(preTaskList != null && preTaskList.size()>0 && selectedTaskId != null){
            Task task = em.find(Task.class, selectedTaskId);
            preTaskList.remove(task);

        }
    }

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public String getProjectId(){
		return this.projectId;
	}

    public ResoruceUserDTO getResourceUserDTO() {
        return resourceUserDTO;
    }

    public void setResourceUserDTO(ResoruceUserDTO resourceUserDTO) {
        this.resourceUserDTO = resourceUserDTO;
    }

    public List<ResoruceUserDTO> getResourceUserDTOList() {
        return resourceUserDTOList;
    }

    public void setResourceUserDTOList(List<ResoruceUserDTO> resourceUserDTOList) {
        this.resourceUserDTOList = resourceUserDTOList;
    }

    public String getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(String selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public List<Task> getPreTaskList() {
        return preTaskList;
    }

    public void setPreTaskList(List<Task> preTaskList) {
        this.preTaskList = preTaskList;
    }

    public String getSelectedTaskId() {
        return selectedTaskId;
    }

    public void setSelectedTaskId(String selectedTaskId) {
        this.selectedTaskId = selectedTaskId;
    }

    public Task getPreTask() {
        return preTask;
    }

    public void setPreTask(Task preTask) {
        this.preTask = preTask;
    }
}
