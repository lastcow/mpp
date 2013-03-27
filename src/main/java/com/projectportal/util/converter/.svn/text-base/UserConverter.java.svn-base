package com.projectportal.util.converter;

import com.projectportal.entity.User;

import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 1/20/13
 * Time: 12:05 AM
 * To change this template use File | Settings | File Templates.
 */
@Model
public class UserConverter implements Converter {

    @Inject
    EntityManager em;
    @Inject
    Logger log;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

        if(value != null){
            return em.find(User.class, value);
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {

        if(value instanceof User){
            return ((User)value).getUserId();
        }

        return null;
    }
}
