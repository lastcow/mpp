/**
 * 
 */
package com.projectportal.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.projectportal.dto.EmailDTO;

/**
 * @author lastcow
 *
 */
@Model
public class HelpController {

	@Inject Logger log;
	
	/**
	 * 
	 */
	public HelpController() {
	}
	
	private EmailDTO newEmail;
	private String subject;
	
	@PostConstruct
	public void postInit(){
		newEmail = new EmailDTO();
	}
	public void submitFeedback(){
		log.info("Subject with: " + newEmail.getSubject());
		subject = newEmail.getSubject();
	}
	/**
	 * @return the newEmail
	 */
	public EmailDTO getNewEmail() {
		return newEmail;
	}
	/**
	 * @param newEmail the newEmail to set
	 */
	public void setNewEmail(EmailDTO newEmail) {
		this.newEmail = newEmail;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

}
