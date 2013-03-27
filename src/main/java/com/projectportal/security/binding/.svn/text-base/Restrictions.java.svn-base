/**
 * 
 */
package com.projectportal.security.binding;

import com.projectportal.util.Util;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

import javax.inject.Inject;
import java.util.logging.Logger;


/**
 * @author lastcow
 *
 */
public class Restrictions {

	@Inject Logger log;
	
	public @Secures @Admin boolean isAdmin(Identity identity){
		if(identity.getUser() == null){
			return false;
		}else{
			return identity.hasRole(Util.ROLE_ADMIN, "USERS", "GROUP");
		}
	}
}
