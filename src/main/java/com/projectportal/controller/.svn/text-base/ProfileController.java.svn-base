package com.projectportal.controller;

import com.projectportal.entity.User;
import org.jboss.seam.security.Identity;
import org.jboss.solder.logging.Category;
import org.jboss.solder.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@Model
public class ProfileController {

	@Inject @Category("PROFILE CONTROLLER") Logger log;
	@Inject EntityManager em;
	@Inject Identity identity;
	
	private User currentUser;
	
	public ProfileController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void initController(){
		// Load user data.
		currentUser = em.find(User.class, identity.getUser().getId());
	}

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	

}
