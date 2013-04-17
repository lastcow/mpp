/**
 * 
 */
package com.projectportal.rest.jaxb;

import com.projectportal.entity.Project;
import com.projectportal.entity.Task;
import com.projectportal.rest.jaxb.dto.ProjectJaxbDto;
import com.projectportal.rest.jaxb.dto.TaskJaxbDto;
import com.projectportal.util.Util;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lastcow
 *
 */
@XmlRootElement (name="projects")
public class RESTWBS {
	
	private List<ProjectJaxbDto> projectDtoList;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,M,d");
    private int wbsType;

	public RESTWBS(){}
	
	/**
	 * Init
	 * @param em
	 * @param projectId
	 */
	public RESTWBS(EntityManager em, String projectId, int wbsType){

        this.wbsType = wbsType;
		projectDtoList = new ArrayList<ProjectJaxbDto>();
		Project project = em.find(Project.class, projectId);
		
		if( project != null){
			ProjectJaxbDto projectDto = new ProjectJaxbDto();
			projectDto.setId(project.getProjectId());
			projectDto.setName(project.getProjectName());
			projectDto.setStartdate(new StringBuilder(dateFormat.format(project.getStartDate())).toString());
		
		
			List<TaskJaxbDto> tasks = new LinkedList<TaskJaxbDto>();
			for(Task task : project.getTasks()){
				tasks.add(this.buildTaskList(task));
			}
			
			projectDto.setTasks(tasks);
			projectDtoList.add(projectDto);
		}
		
	}
	
	/**
	 * Recrusive task to build task chain.
	 * @param task
	 * @return
	 */
	private TaskJaxbDto buildTaskList(Task task){
		
		TaskJaxbDto taskDto = new TaskJaxbDto();
		taskDto.setName(task.getTaskName());
		taskDto.setId(task.getTaskId());
        if(wbsType == Util.WBS_BASLINE){
		    taskDto.setEst(new StringBuilder(dateFormat.format(task.getTaskEstimatedStartDate())).toString());
            taskDto.setDuration(task.getTaskDurationHour());
        }else if(wbsType == Util.WBS_ACTUAL){
            taskDto.setEst(new StringBuilder(dateFormat.format(task.getTaskActualStartDate())).toString());
            taskDto.setPrecentCompleted(task.getTaskPercentComplete());
            taskDto.setDuration(task.getTaskActualDurationHour());
        }
		taskDto.setPredecessorTasks(task.getPreTask() == null ? "" : task.getPreTask().getTaskId());

		
		if(task.getChildTasks() != null && task.getChildTasks().size() > 0){
			List<TaskJaxbDto> tasks = new ArrayList<TaskJaxbDto> ();
			taskDto.setSubTasks(tasks);
			// Call recrusive method.
			for(Task subTask : task.getChildTasks()){
				tasks.add(this.buildTaskList(subTask));
			}
		}
		
		return taskDto;
	}

	@XmlElement (name="project")
	public List<ProjectJaxbDto> getProjectDto() {
		return projectDtoList;
	}

	public void setProjectDto(List<ProjectJaxbDto> projectDto) {
		this.projectDtoList = projectDtoList;
	}
	
}

