package com.projectportal.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@Table(name="User")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=36)
	private String userId;

	@Column(nullable=false, length=250)
	private String email;

//	@Column(nullable=false, length=200)
//	private String firstname;
//
//	@Column(length=200)
//	private String lastname;

	@Column(nullable=false, length=100)
	private String password;

	@Column(length=100)
	private String tel;

	@Column(nullable=false, length=150)
	private String userName;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="user")
	private List<Project> projects;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role;

	//bi-directional many-to-one association to RoleGroup
	@ManyToOne
	@JoinColumn(name="groupId")
	private RoleGroup roleGroup;

	//bi-directional many-to-one association to UserXTask
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<UserXTask> userXtasks;

	//bi-directional many-to-one association to Preference
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<Preference> preferences;

	//bi-directional many-to-one association to Department
//	@ManyToOne
//	@JoinColumn(name="departmentId", nullable=false)
//	private Department department;

	public User() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public String getFirstname() {
//		return this.firstname;
//	}
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//	public String getLastname() {
//		return this.lastname;
//	}
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleGroup getRoleGroup() {
		return this.roleGroup;
	}

	public void setRoleGroup(RoleGroup roleGroup) {
		this.roleGroup = roleGroup;
	}

	public List<UserXTask> getUserXtasks() {
		return this.userXtasks;
	}

	public void setUserXtasks(List<UserXTask> userXtasks) {
		this.userXtasks = userXtasks;
	}

	public List<Preference> getPreferences() {
		return this.preferences;
	}

	public void setPreferences(List<Preference> preferences) {
		this.preferences = preferences;
	}

//	public Department getDepartment() {
//		return this.department;
//	}
//
//	public void setDepartment(Department department) {
//		this.department = department;
//	}

}