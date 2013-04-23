package com.projectportal.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Task database table.
 * 
 */
@Entity
@Table(name="Task")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=36)
	private String taskId;

    @Temporal(TemporalType.TIMESTAMP)
	private Date taskActualEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date taskActualStartDate;

	@Column(length=1000)
	private String taskDesc;

	private int taskDurationHour;

    private int taskActualDurationHour;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date taskEstimatedEndDate;


	@Column(nullable=false)
	private Date taskEstimatedStartDate;

	@Column(nullable=false, length=200)
	private String taskName;

	@Column(nullable=false)
	private float taskPercentComplete;

    @Transient
    private Date taskActualStartDateTransient;

	//bi-directional many-to-one association to Priority
	@ManyToOne
	@JoinColumn(name="priorityId", nullable=false)
	private Priority priority;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="projectId")
	private Project project;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="statusId", nullable=false)
	private Status status;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="parentTaskId")
	private Task parentTask;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="parentTask")
	private List<Task> childTasks;

    //bi-directional many-to-one association to Task
    @ManyToOne
    @JoinColumn(name="preTaskId")
    private Task preTask;

    //bi-directional many-to-one association to Task
    @OneToMany(mappedBy="preTask")
    private List<Task> dependentTasks;

	//bi-directional many-to-one association to UserXTask
	@OneToMany(mappedBy="task", cascade = CascadeType.ALL)
	private List<UserXTask> userXtasks;

	public Task() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
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
		Task other = (Task) obj;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		return true;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Date getTaskActualEndDate() {
		return this.taskActualEndDate;
	}

	public void setTaskActualEndDate(Date taskActualEndDate) {
		this.taskActualEndDate = taskActualEndDate;
	}

	public Date getTaskActualStartDate() {
		return this.taskActualStartDate;
	}

	public void setTaskActualStartDate(Date taskActualStartDate) {
		this.taskActualStartDate = taskActualStartDate;
	}

	public String getTaskDesc() {
		return this.taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public int getTaskDurationHour() {
		return this.taskDurationHour;
	}

	public void setTaskDurationHour(int taskDurationHour) {
		this.taskDurationHour = taskDurationHour;
	}

	public Date getTaskEstimatedEndDate() {
		return this.taskEstimatedEndDate;
	}

	public void setTaskEstimatedEndDate(Date taskEstimatedEndDate) {
		this.taskEstimatedEndDate = taskEstimatedEndDate;
	}

	public Date getTaskEstimatedStartDate() {
		return this.taskEstimatedStartDate;
	}

	public void setTaskEstimatedStartDate(Date taskEstimatedStartDate) {
		this.taskEstimatedStartDate = taskEstimatedStartDate;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public float getTaskPercentComplete() {
		return this.taskPercentComplete;
	}

	public void setTaskPercentComplete(float taskPercentComplete) {
		this.taskPercentComplete = taskPercentComplete;
	}

	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Task getParentTask() {
		return this.parentTask;
	}

	public void setParentTask(Task parentTask) {
		this.parentTask = parentTask;
	}

	public List<Task> getChildTasks() {
		return this.childTasks;
	}

	public void setChildTasks(List<Task> childTasks) {
		this.childTasks = childTasks;
	}

	public Task getPreTask() {
		return this.preTask;
	}

	public void setPreTask(Task preTask) {
		this.preTask = preTask;
	}

	public List<Task> getDependentTasks() {
		return this.dependentTasks;
	}

	public void setDependentTasks(List<Task> dependentTasks) {
		this.dependentTasks = dependentTasks;
	}

	public List<UserXTask> getUserXtasks() {
		return this.userXtasks;
	}

	public void setUserXtasks(List<UserXTask> userXtasks) {
		this.userXtasks = userXtasks;
	}

    public int getTaskActualDurationHour() {
        return taskActualDurationHour;
    }

    public void setTaskActualDurationHour(int taskActualDurationHour) {
        this.taskActualDurationHour = taskActualDurationHour;
    }

    public Date getTaskActualStartDateTransient() {
        return taskActualStartDateTransient;
    }

    public void setTaskActualStartDateTransient(Date taskActualStartDateTransient) {
        this.taskActualStartDateTransient = taskActualStartDateTransient;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", taskActualEndDate=" + taskActualEndDate +
                ", taskActualStartDate=" + taskActualStartDate +
                ", taskDesc='" + taskDesc + '\'' +
                ", taskDurationHour=" + taskDurationHour +
                ", taskEstimatedEndDate=" + taskEstimatedEndDate +
                ", taskEstimatedStartDate=" + taskEstimatedStartDate +
                ", taskName='" + taskName + '\'' +
                ", taskPercentComplete=" + taskPercentComplete +
                ", priority=" + priority +
                '}';
    }
}