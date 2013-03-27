/**
 * 
 */
package com.projectportal.util.converter;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.projectportal.entity.Priority;

@Named
@RequestScoped
/**
 * @author lastcow
 *
 */
public class PriorityConverter implements Converter {

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
		return  em.find(Priority.class, value);
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value instanceof Priority){
			return ((Priority)value).getPriorityId();
		}
		return "0";
	}

}
