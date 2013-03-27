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
import javax.inject.Named;

import com.projectportal.entity.Priority;

/**
 * @author lastcow
 *
 */
@ApplicationScoped
public class PriorityProducer extends AbstractProducer {

	@Named
	@Produces
	private List<Priority> globalPriorityList;
	
	public void onPriorityListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Priority priority){
		this.reterieveAllPriorities();
	}
	
	@PostConstruct
	public void reterieveAllPriorities(){
		globalPriorityList = em.createQuery("select priority from Priority priority").getResultList();
	}
}
