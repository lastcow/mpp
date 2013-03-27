package com.projectportal.rest;

import com.projectportal.entity.Holiday;
import com.projectportal.rest.jaxb.dto.CalendarDto;
import org.jboss.solder.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 1/7/13
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/calendar")
@RequestScoped
public class CalendarResourcesRESTService {

    @Inject
    Logger log;
    @Inject
    EntityManager em;

    @POST
    @Path("/holidaylist")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CalendarDto> getHoliday(){

        List<CalendarDto> calendarDtoList = new LinkedList<CalendarDto>();
        // Get list of holidays
        List<Holiday> holidays = em.createQuery("SELECT holiday FROM Holiday holiday order by holiday.startDate").getResultList();

        // Return with JAXB settings.
        CalendarDto calendarDto;
        for(Holiday holiday : holidays){
            calendarDto = new CalendarDto();
            calendarDto.setId(holiday.getHolidayId());
            calendarDto.setTitle(holiday.getHolidayName());
            calendarDto.setStart(holiday.getStartDate().toString());
            if(holiday.getEndDate() != null){
                calendarDto.setEnd(holiday.getEndDate().toString());
            }

            calendarDtoList.add(calendarDto);
        }

        return calendarDtoList;
    }

}
