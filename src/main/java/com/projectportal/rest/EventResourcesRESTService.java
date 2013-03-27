package com.projectportal.rest;

import com.projectportal.entity.User;
import com.projectportal.entity.UserXTask;
import com.projectportal.rest.jaxb.RESTEvents;
import org.jboss.seam.security.Identity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 2/8/13
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/event")
@RequestScoped
public class EventResourcesRESTService {

    @Inject
    EntityManager em;
    @Inject
    Identity identity;

    @GET
    @Path("/getevents/{flat}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RESTEvents> getProjectWbs(@PathParam("flat") String flag){
        List<RESTEvents> eventsList = new ArrayList<RESTEvents>();

        User user = em.find(User.class, identity.getUser().getId());
        RESTEvents restEvents = null;

        if(user != null){
            for(UserXTask userTask : user.getUserXtasks()){
                restEvents = new RESTEvents();
                restEvents.setDate(userTask.getTask().getTaskEstimatedStartDate().getTime());
                restEvents.setTitle(userTask.getTask().getTaskName());
                restEvents.setDescription(userTask.getTask().getTaskDesc());
                restEvents.setType("Task");
                restEvents.setUrl("http://www.google.com");

                eventsList.add(restEvents);
            }
        }

        return eventsList;
    }
}
