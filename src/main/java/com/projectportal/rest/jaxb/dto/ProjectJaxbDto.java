/**
 * 
 */
package com.projectportal.rest.jaxb.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author lastcow
 *
 */
@XmlRootElement
public class ProjectJaxbDto {

	private String id;
	private String name;
	private String startdate;
	private List<TaskJaxbDto> tasks;
	
	@XmlAttribute (name = "id", required = true)
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlAttribute (name = "name", required = true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute (name = "startdate", required = true)
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	@XmlElement (name = "task")
	public List<TaskJaxbDto> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskJaxbDto> tasks) {
		this.tasks = tasks;
	}
	
	
}
