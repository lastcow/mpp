/**
 * 
 */
package com.projectportal.util.converter;

import com.projectportal.entity.Project;
import org.jboss.solder.logging.Category;
import org.jboss.solder.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 * @author lastcow
 *
 */
@Named
@RequestScoped
public class ProjectConverter implements Converter {

	@Inject EntityManager em;
	@Inject @Category("Project Converter") Logger log;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return em.find(Project.class, value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(value instanceof Project){
			return ((Project)value).getProjectId();
		}
		return "0";
	}

}
