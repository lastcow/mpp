/**
 * 
 */
package com.projectportal.exception;

import javax.inject.Inject;

import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.AuthorizationException;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;

/**
 * @author lastcow
 *
 */
@HandlesExceptions
public class ExceptionHandler {

	@Inject Messages messages;
	
	public void handleAuthorizationException(@Handles CaughtException<AuthorizationException> evt){
		messages.error("No sufficient permission.");
//		evt.handled();
	}
}
