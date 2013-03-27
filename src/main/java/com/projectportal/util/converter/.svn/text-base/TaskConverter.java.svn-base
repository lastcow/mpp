/**
 * 
 */
package com.projectportal.util.converter;

import com.projectportal.entity.Task;

import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Model
/**
 * @author lastcow
 *
 */
public class TaskConverter implements Converter {

	@Inject EntityManager em;
	@Inject Logger log;

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if(value.equals("0")){
			return null;
		}
		return em.find(Task.class, value);
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value instanceof Task){
			return ((Task)value).getTaskId();
		}
		
		return "0";
	}

}
