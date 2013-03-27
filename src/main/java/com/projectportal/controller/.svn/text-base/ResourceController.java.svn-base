package com.projectportal.controller;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 1/31/13
 * Time: 10:25 PM
 * To change this template use File | Settings | File Templates.
 */

import com.projectportal.entity.User;
import com.projectportal.security.binding.Admin;
import com.projectportal.util.Util;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Named @ViewScoped
public class  ResourceController implements Serializable {

    @Inject
    EntityManager em;

    private User resource;
    private List<User> resources;
    private String deleteUserId;

    @PostConstruct
    public void postInit(){
        resource = new User();
        // Get list of resources (users).
        resources = em.createQuery("SELECT user FROM User user ORDER BY user.userId").getResultList();
    }

    /**
     * Save to DB.
     */
    @Admin
    public void create(){
        // Check for null.
        if(resource == null){
            return;
        }

        // Save to db.
        resource.setUserId(UUID.randomUUID().toString());
        try {
            resource.setPassword(Util.getMD5Hash("12345"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        em.persist(resource);

        // Reset
        this.postInit();
    }

    /**
     * Update selected resource.
     */
    public void update(){
        if(resource == null){
            return;
        }

        em.merge(resource);

        this.postInit();
    }

    /**
     * Delete user
     */
    @Admin
    public void delete(){
        if(this.deleteUserId == null){
            return;
        }

        // Delete from db.
        User user = em.find(User.class, this.deleteUserId);
        em.remove(user);

        this.postInit();
    }

    /**
     * Set selected user
     * @param userId
     */
    public void setEditResource(String userId){
        resource = em.find(User.class, userId);
    }

    /**
     * Reset resource class
     */
    public void reset(){
        // Set resource => new instance.
        resource = new User();
    }

    public User getResource() {
        return resource;
    }

    public void setResource(User resource) {
        this.resource = resource;
    }

    public List<User> getResources() {
        return resources;
    }

    public void setResources(List<User> resources) {
        this.resources = resources;
    }

    public String getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }
}
