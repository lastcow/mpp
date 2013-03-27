/**
 * 
 */
package com.projectportal.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.projectportal.entity.Project;

/**
 * @author lastcow
 *
 */
@ApplicationScoped
public class ProjectProducer {

	@Inject EntityManager em;
	
	@Named
	@Produces
	private List<Project> globalProjectList;
	
	public void onProjectListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Project project){
		this.reterieveAllProjects();
	}
	
	@PostConstruct
	public void reterieveAllProjects(){
		globalProjectList = em.createQuery("select project from Project project").getResultList();
	}

}
