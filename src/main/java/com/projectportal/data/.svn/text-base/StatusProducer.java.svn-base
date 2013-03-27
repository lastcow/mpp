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
import com.projectportal.entity.Status;

/**
 * @author lastcow
 *
 */
@ApplicationScoped
public class StatusProducer extends AbstractProducer {

	@Named
	@Produces
	private List<Status> globalStatusList;
	
	public void onPriorityListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Priority priority){
		this.reterieveAllStatus();
	}
	
	@PostConstruct
	public void reterieveAllStatus(){
		globalStatusList = em.createQuery("select status from Status status").getResultList();
	}
}
