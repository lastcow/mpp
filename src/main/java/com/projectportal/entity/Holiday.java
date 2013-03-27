package com.projectportal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 1/9/13
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Holiday")
public class Holiday implements Serializable {

    @Id
    @Column(unique=true, nullable=false, length=36)
    private String holidayId;

    @Column(nullable = false, length = 250)
    private String holidayName;

    @Column(nullable=false, length=250)
    private Date startDate;

    @Column(nullable=true, length=250)
    private Date endDate;

    @Column(nullable = true, length = 400)
    private String desc;

    @Column(nullable = false, length = 1)
    private boolean fixed;


    public String getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(String holidayId) {
        this.holidayId = holidayId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date holiday) {
        this.startDate = holiday;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
}
