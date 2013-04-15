package com.projectportal.controller;

import com.projectportal.entity.Holiday;
import com.projectportal.security.binding.Admin;
import org.jboss.solder.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 1/9/13
 * Time: 11:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Model
public class CalendarController implements Serializable {

    @Inject
    EntityManager em;

    @Inject
    Logger logger;

    @Inject private Event<Holiday> holidayEventSrc;

    private String selectedHolidayId;
    private List<Holiday> holidayList;
    private Holiday newHoliday;
    private Holiday selectedHoliday;

    @PostConstruct
    public void postInit(){
        newHoliday = new Holiday();
        holidayList  = em.createQuery("SELECT holiday FROM Holiday holiday order by Holiday.startDate").getResultList();

    }



    /**
     * Create new holiday
     */
    @Admin
    public void create(){

        logger.info("Creating holiday: " + newHoliday.toString());
        newHoliday.setHolidayId(UUID.randomUUID().toString());
        em.persist(newHoliday);
        postInit();
    }

    /**
     * Update holiday
     */
    public void update(){
        if(newHoliday != null){
            em.merge(newHoliday);
        }
        // Init
        postInit();
    }

    /**
     * Delete holiday from DB
     */
    @Admin
    public void delete(){
        if(selectedHolidayId != null){
            Holiday holiday = em.find(Holiday.class, selectedHolidayId);
            em.remove(holiday);
            // Reteieve holiday list again.
            this.postInit();
            holidayEventSrc.fire(holiday);
        }
    }

    public Holiday getSelectedHoliday() {
        return selectedHoliday;
    }

    public void setSelectedHoliday(Holiday selectedHoliday) {
        this.selectedHoliday = selectedHoliday;
        this.newHoliday = selectedHoliday;
    }

    public String getSelectedHolidayId() {
        return selectedHolidayId;
    }

    public void setSelectedHolidayId(String selectedHolidayId) {
        this.selectedHolidayId = selectedHolidayId;
    }

    public List<Holiday> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<Holiday> holidayList) {
        this.holidayList = holidayList;
    }

    public Holiday getNewHoliday() {
        return newHoliday;
    }

    public void setNewHoliday(Holiday newHoliday) {
        this.newHoliday = newHoliday;
    }
}
