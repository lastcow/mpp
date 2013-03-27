package com.projectportal.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Priority database table.
 * 
 */
@Entity
@Table(name="Priority")
public class Priority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=36)
	private String priorityId;

	@Column(length=1000)
	private String priorityDesc;

	@Column(nullable=false, length=100)
	private String priorityName;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="priority")
	private List<Task> tasks;

	public Priority() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((priorityId == null) ? 0 : priorityId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Priority other = (Priority) obj;
		if (priorityId == null) {
			if (other.priorityId != null)
				return false;
		} else if (!priorityId.equals(other.priorityId))
			return false;
		return true;
	}

	public String getPriorityId() {
		return this.priorityId;
	}

	public void setPriorityId(String priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriorityDesc() {
		return this.priorityDesc;
	}

	public void setPriorityDesc(String priorityDesc) {
		this.priorityDesc = priorityDesc;
	}

	public String getPriorityName() {
		return this.priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}