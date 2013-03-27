package com.projectportal.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the UserXTask database table.
 * 
 */
@Entity
@Table(name="UserXTask")
public class UserXTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserXTaskPK id;

	@Column(nullable=false)
	private float utilized;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="taskId", nullable=false, insertable=false, updatable=false)
	private Task task;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId", nullable=false, insertable=false, updatable=false)
	private User user;

	public UserXTask() {
	}

	public UserXTaskPK getId() {
		return this.id;
	}

	public void setId(UserXTaskPK id) {
		this.id = id;
	}

	public float getUtilized() {
		return this.utilized;
	}

	public void setUtilized(float utilized) {
		this.utilized = utilized;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}