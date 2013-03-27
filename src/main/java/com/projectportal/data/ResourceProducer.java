/**
 * 
 */
package com.projectportal.data;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.projectportal.dto.ResourceDTO;
import com.projectportal.entity.Project;

/**
 * @author lastcow
 *
 */
@ApplicationScoped
public class ResourceProducer extends AbstractProducer{

	@Produces
	@Named
	private List<ResourceDTO> glbResourceDTOList;
	
	@PostConstruct
	public void reterieveResourcesList(){
		glbResourceDTOList = new ArrayList<ResourceDTO>();
	}
	
	/**
	 * Event listener
	 * @param project
	 */
	public void onResourceChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Project project){
		// Get list of projects again.
		this.reterieveResourcesList();
	}
}
