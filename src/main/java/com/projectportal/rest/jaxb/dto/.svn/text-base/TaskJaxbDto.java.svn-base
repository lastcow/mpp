/**
 * 
 */
package com.projectportal.rest.jaxb.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author lastcow
 *
 */
@XmlRootElement (name = "task")
public class TaskJaxbDto {
	private String id;
	private String name;
	private String est;
	private int duration;
	private double precentCompleted;
	private String predecessorTasks;
	private List<TaskJaxbDto> subTasks;
	
	@XmlAttribute (name = "id", required = true)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement (name = "name", required = true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement (name = "est", required = true)
	public String getEst() {
		return est;
	}
	public void setEst(String est) {
		this.est = est;
	}
	
	@XmlElement (name = "duration", required = true)
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@XmlElement (name = "percentcompleted", required = true)
	public double getPrecentCompleted() {
		return precentCompleted;
	}
	public void setPrecentCompleted(double precentCompleted) {
		this.precentCompleted = precentCompleted;
	}
	
	@XmlElement (name = "predecessortasks", required = true)
	public String getPredecessorTasks() {
		return predecessorTasks;
	}
	public void setPredecessorTasks(String predecessorTasks) {
		this.predecessorTasks = predecessorTasks;
	}
	
	@XmlElementWrapper(name="childtasks", required = true)
	@XmlElement (name = "task")
	public List<TaskJaxbDto> getSubTasks() {
		return subTasks;
	}
	public void setSubTasks(List<TaskJaxbDto> subTasks) {
		this.subTasks = subTasks;
	}
	
	
	
}
