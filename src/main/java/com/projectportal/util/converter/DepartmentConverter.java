package com.projectportal.util.converter;

import com.projectportal.entity.Department;

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
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 2/3/13
 * Time: 7:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class DepartmentConverter implements Converter {

    @Inject
    EntityManager em;
    @Inject
    Logger log;


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return em.find(Department.class, s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o instanceof Department){
            return ((Department)o).getDepartmentId();
        }
        return null;
    }
}
