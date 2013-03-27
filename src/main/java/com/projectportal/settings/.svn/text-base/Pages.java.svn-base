package com.projectportal.settings;

import org.jboss.seam.faces.rewrite.FacesRedirect;
import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.security.RestrictAtPhase;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;

import com.projectportal.security.binding.Admin;

import org.jboss.seam.faces.event.PhaseIdType;
import org.jboss.seam.security.annotations.LoggedIn;


@ViewConfig
public interface Pages {
	
	static enum Pages0{
//		@FacesRedirect
//		@ViewPattern("/modules/*")
//		@LoginView("/projectportal.xhtml")
//		@AccessDeniedView("/projectportal.xhtml")
//		@RestrictAtPhase(PhaseIdType.RESTORE_VIEW)
//        @Admin
//        ADMIN;
        
        @FacesRedirect
        @RestrictAtPhase({PhaseIdType.RESTORE_VIEW, PhaseIdType.INVOKE_APPLICATION})
		@ViewPattern("/modules/*")
		@LoginView("/projectportal.xhtml")
		@AccessDeniedView("/projectportal.xhtml")
		@LoggedIn
		ALL;
	}
}
