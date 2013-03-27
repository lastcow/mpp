/**
 * 
 */
package com.projectportal.util.converter;

import com.projectportal.entity.Status;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Named
@RequestScoped
/**
 * @author lastcow
 *
 */
public class StatusConverter implements Converter {

	@Inject EntityManager em;
	@Inject Logger log;

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return em.find(Status.class, value);
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value instanceof Status){
			return ((Status)value).getStatusId();
		}
		
		return "0";
	}

}
