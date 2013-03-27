/**
 * 
 */
package com.projectportal.data;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;


import com.projectportal.entity.User;
import com.projectportal.event.GlobalAction;
import com.projectportal.util.Util;

/**
 * @author lastcow
 *
 */
@SessionScoped
public class UserProducer extends AbstractProducer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7281561063779981671L;
	
	@Inject Logger log;
	
	@Produces
	@Named
	private String sessionUserGravatar;
	
	private User user;
	
	/**
	 * 
	 * @param project
	 */
	public void onLoginChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final GlobalAction globalAction){
		log.info("== Event handing...===");
	}
	
	@PostConstruct
	public void setupUser(){
		log.info("========== Getting in here ");
		String ravatarHash = null;
		if (user == null){
			log.info("========== Getting in here without user data");
			ravatarHash = "1234567890";
		}else{
			log.info("========== Getting in here with user data");
			try {
				ravatarHash = Util.getMD5Hash(user.getEmail());
			} catch (NoSuchAlgorithmException e) {
				ravatarHash = "1234567890";
			}
		}
		sessionUserGravatar = "http://www.gravatar.com/avatar/"+ravatarHash+"?s=22";
	}
}
