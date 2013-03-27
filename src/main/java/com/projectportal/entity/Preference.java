package com.projectportal.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Preference database table.
 * 
 */
@Entity
@Table(name="Preference")
public class Preference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int preferenceId;

	@Column(nullable=true, length=500)
	private String value;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId", nullable=false)
	private User user;

	public Preference() {
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Preference that = (Preference) o;

        if (preferenceId != that.preferenceId) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = preferenceId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    public int getPreferenceId() {
		return this.preferenceId;
	}

	public void setPreferenceId(int preferenceId) {
		this.preferenceId = preferenceId;
	}


	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}