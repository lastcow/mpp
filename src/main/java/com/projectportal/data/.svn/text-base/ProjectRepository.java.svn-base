/**
 * 
 */
package com.projectportal.data;

import com.projectportal.dto.ProjectDTO;
import com.projectportal.entity.Project;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lastcow
 *
 */
@ApplicationScoped
public class ProjectRepository extends AbstractProducer{
	
	@Produces
	@Named
	private List<ProjectDTO> glbProjectDtoList;
	
	@Produces
	@Named
	private List<Project> glbProjectList;
	
	/**
	 * Event listener
	 * @param project
	 */
	public void onPorjectDtoListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Project project){
		// Get list of projects again.
		this.reterieveAllProjectsOrderByName();
	}
	
	@PostConstruct
	public void reterieveAllProjectsOrderByName(){
		// Get project list.
		glbProjectList  = em.createQuery("select project from Project project order by project.projectName").getResultList();
		glbProjectDtoList = new ArrayList<ProjectDTO>();
		
		for(Project project : glbProjectList){
			ProjectDTO projectDTO = new ProjectDTO();
			projectDTO.setProjectId(project.getProjectId());
			projectDTO.setProjectName(project.getProjectName());
			projectDTO.setProjectStatus(23.4d);
            projectDTO.setStartDate(project.getStartDate());
			// Add to list.
			glbProjectDtoList.add(projectDTO);
		}
	}
}
