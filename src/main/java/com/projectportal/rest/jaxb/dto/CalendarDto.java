package com.projectportal.rest.jaxb.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 1/13/13
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class CalendarDto {

    private String id;
    private String start;
    private String end;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
