package com.projectportal.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the UserXTask database table.
 * 
 */
@Embeddable
public class UserXTaskPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=false, nullable=false, length=36)
	private String userId;

	@Column(unique=false, nullable=false, length=36)
	private String taskId;

	public UserXTaskPK() {
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTaskId() {
		return this.taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserXTaskPK)) {
			return false;
		}
		UserXTaskPK castOther = (UserXTaskPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.taskId.equals(castOther.taskId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.taskId.hashCode();
		
		return hash;
	}
}