/**
 * 
 */
package com.projectportal.rest;

import com.projectportal.entity.Project;
import com.projectportal.rest.jaxb.RESTWBS;
import com.projectportal.util.Util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * @author lastcow
 *
 */
@Path("/project")
@RequestScoped
public class ProjectResourcesRESTService {
	
	@Inject Logger log;
	@Inject EntityManager em;
	@Inject private Event<Project> projectEventSrc;
	
	@DELETE
	@Path("/delete/{projectId}")
	public void deleteProject(@PathParam("projectId") String projectId) throws Exception{
		// Get project form DB.
		Project project = em.find(Project.class, projectId);
		em.remove(project);
		projectEventSrc.fire(project);
		log.info("Project: " + project.getProjectName() + " removed from DB.");
	}
	
	@GET
	@Path("/getprojectwbs/{projectId}")
	@Produces(MediaType.APPLICATION_XML)
	public RESTWBS getProjectWbs(@PathParam("projectId") String projectId){
		return new RESTWBS(em, projectId, Util.WBS_BASLINE);
	}

    @GET
    @Path("/getprojectwbs_acutal/{projectId}")
    @Produces(MediaType.APPLICATION_XML)
    public RESTWBS getProjectWbsActual(@PathParam("projectId") String projectId){
        return new RESTWBS(em, projectId, Util.WBS_ACTUAL);
    }

}
