package com.projectportal.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the RoleGroup database table.
 * 
 */
@Entity
@Table(name="RoleGroup")
public class RoleGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=36)
	private String groupId;

	@Column(length=1000)
	private String groupDesc;

	@Column(nullable=false, length=150)
	private String groupName;

	//bi-directional many-to-one association to Role
	@OneToMany(mappedBy="roleGroup")
	private List<Role> roles;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="roleGroup")
	private List<User> users;

	public RoleGroup() {
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupDesc() {
		return this.groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}