package com.projectportal.controller;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.Authenticator.AuthenticationStatus;
import org.picketlink.idm.impl.api.PasswordCredential;
import org.picketlink.idm.impl.api.model.SimpleUser;

import com.projectportal.entity.User;
import com.projectportal.util.Util;

@Named
@SessionScoped
public class SessionController implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6469079447821486205L;
	
	@Inject private EntityManager em;
	@Inject Identity identity;
	@Inject Credentials credentials;
	@Inject Logger log;
	
	public SessionController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Login controller.
	 * @return
	 * @throws Exception 
	 */
	public User doLogin() throws Exception{
		
		String username = credentials.getUsername();
		String password = Util.getMD5Hash(((PasswordCredential)credentials.getCredential()).getValue());
		log.info("Checking user credential: " + username + "/" + password);
		
		Query query = em.createQuery("select user from User user where user.userName = ?1 and user.password = ?2");
		query.setParameter(1,  username);
		query.setParameter(2, password);
		User user = (User) query.getSingleResult();
		
		return user;
	}
	
}
