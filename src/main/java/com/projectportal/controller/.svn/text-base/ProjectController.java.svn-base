package com.projectportal.controller;

import com.projectportal.dto.ProjectDTO;
import com.projectportal.entity.Project;
import com.projectportal.entity.User;
import com.projectportal.security.binding.Admin;
import org.jboss.seam.security.Identity;
import org.jboss.solder.logging.Category;
import org.jboss.solder.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public class ProjectController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3572096930093985831L;
	
	@Inject private EntityManager em;
//	@Inject private Logger log;
	@Inject @Category("Project Controller") Logger log;
	//@Inject private FacesContext facesContext;
	@Inject private Event<Project> projectEventSrc;
	@Inject Identity identity;
	
	
	private String projectId;
	private HtmlDataTable projectDataTable;

	private Project project;
	
	private Query query;
	
	private List<Project> projectList;
	private List<ProjectDTO> projectDtoList;
	
	private String delProjectId;
	private String projectName;
	
	public ProjectController() {
		// Default constructor.
	}
	
	@PostConstruct
	public void postInit(){
	
		// Init project
		project = new Project();
		
		// Get project list.
		query = em.createQuery("select project from Project project");
		projectList = query.getResultList();
		projectDtoList = new ArrayList<ProjectDTO>();
		
		for(Project project : projectList){
			ProjectDTO projectDTO = new ProjectDTO();
			projectDTO.setProjectId(project.getProjectId());
			projectDTO.setProjectName(project.getProjectName());
			projectDTO.setProjectStatus(23.4d);
			// Add to list.
			projectDtoList.add(projectDTO);
		}
				
	}

    /**
     * Load project @ startup.
     */
	public void loadProject(){
		// Load project here based on project id.
		if(projectId == null){
			// Do nothing.
			return;
		}
		
		// Load from DB.
		project = em.find(Project.class, projectId);
	}

    /**
     * Reset project instance.
     */
    public void reset(){
        project = new Project();
    }

    /**
     * Selected project from frontend
     * @param projectId
     */
    public void setEditProject(String projectId){
        if(projectId != null && projectId.length() > 0 ){
            this.project = em.find(Project.class, projectId);
        }
    }
	
	/**
	 * Create new project.
	 * @throws Exception
	 */
	@Admin
	public void create() throws Exception{

		project.setProjectId(UUID.randomUUID().toString());
		// Set owner.
		project.setUser(em.find(User.class, identity.getUser().getId()));
		em.persist(project);

        //Event
	    projectEventSrc.fire(project);

		// Reinit.
		project = new Project();
	}

    /**
     * Update selected project.
     */
    @Admin
    public void update(){

        if(this.project != null){
            // Get project form DB and update the fields
            Project dbProject = em.find(Project.class, project.getProjectId());
            dbProject.setProjectName(project.getProjectName());
            dbProject.setStartDate(project.getStartDate());
            dbProject.setProjectDesc(project.getProjectDesc());

            em.merge(dbProject);

            // Event.
            projectEventSrc.fire(dbProject);
        }
    }
	
	/**
	 * Delete project
	 */
	@Admin
	public void delete(){
		Project project = em.find(Project.class, delProjectId);
		log.info("Deleting: " + project.getProjectName());
		em.remove(project);
		
		// Event
		projectEventSrc.fire(project);
	}
	
	
	public void createTest() {
		log.info("Test string: " + this.projectName);
	}
	
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public String getProjectId(){
		return this.projectId;
	}


	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public List<ProjectDTO> getProjectDtoList() {
		return projectDtoList;
	}

	public void setProjectDtoList(List<ProjectDTO> projectDtoList) {
		this.projectDtoList = projectDtoList;
	}

	public HtmlDataTable getProjectDataTable() {
		return projectDataTable;
	}

	public void setProjectDataTable(HtmlDataTable projectDataTable) {
		this.projectDataTable = projectDataTable;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the delProjectId
	 */
	public String getDelProjectId() {
		return delProjectId;
	}

	/**
	 * @param delProjectId the delProjectId to set
	 */
	public void setDelProjectId(String delProjectId) {
		this.delProjectId = delProjectId;
	}

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
