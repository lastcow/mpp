/**
 * 
 */
package com.projectportal.security;

import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.picketlink.idm.api.User;
import org.picketlink.idm.impl.api.model.SimpleUser;
import org.jboss.seam.international.status.Messages;

import com.projectportal.controller.SessionController;
import com.projectportal.entity.Role;
import com.projectportal.event.GlobalAction;

/**
 * @author lastcow
 *
 */
public class AuthenticatorImpl extends BaseAuthenticator implements
		Authenticator {

	@Inject private EntityManager em;
	@Inject Identity identity;
	@Inject Credentials credentials;
	@Inject Logger log;
	@Inject private Event<GlobalAction> authActionEventSrc;
	@Inject Messages messages;
	
	@Inject SessionController sessionController;
	
	
	@Override
	public void authenticate() {
		try {
			com.projectportal.entity.User portalUser = sessionController.doLogin();
			if(portalUser != null){
				// Login
				User simpleUser = new SimpleUser(portalUser.getUserId());
				this.setStatus(AuthenticationStatus.SUCCESS);
				this.setUser(simpleUser);
				
				// We only have 'USERS' group now.
				
				// User may in one role.
				if(portalUser.getRole() != null){
					identity.addRole(portalUser.getRole().getRoleName(), "USERS", "GROUP");
				}
				
				// User may in group.
				if(portalUser.getRoleGroup() != null){
					for(Role role : portalUser.getRoleGroup().getRoles()){
						identity.addRole(role.getRoleName(), "USERS", "GROUP");
					}
				}
				
				
			}else{
				// Login fail
				this.setStatus(AuthenticationStatus.FAILURE);
			}
		} catch (Exception e) {
			this.setStatus(AuthenticationStatus.FAILURE);
			log.warning(e.getLocalizedMessage());
		}
	}
	
	@Override
	public void postAuthenticate(){
		log.info("User: " + this.getUser().getId() + " logs in.");
		authActionEventSrc.fire(new GlobalAction());
		log.info("Event fired");
	}

}
