/**
 * 
 */
package com.projectportal.bean;

import com.projectportal.dto.TaskSearchPreferenceDto;
import com.projectportal.entity.*;
import com.projectportal.util.Util;
import org.jboss.seam.security.Identity;
import org.jboss.solder.logging.Logger;
import org.openfaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author lastcow
 *
 */
@Named
@ViewScoped
public class TaskTreeTableBean implements Serializable {

    private Project project;
    private User user;
    private Status status;
    private TaskSearchPreferenceDto searchPreference;

	@Inject EntityManager em;
    @Inject
    Identity identity;
    @Inject
    Logger logger;
	private List<Task> taskList;

    /**
     * Load saved preference.
     */
    @PostConstruct
    public void loadCreterial(){
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        searchPreference = new TaskSearchPreferenceDto();
        User loginUser = em.find(User.class, identity.getUser().getId());
        for(Preference preference : loginUser.getPreferences()){
            if(preference.getValue() != null){
                if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_PROJECT){
                    project = em.find(Project.class, preference.getValue());
                }
                if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_OWNER){
                    user = em.find(User.class, preference.getValue());
                }
                if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_TASK_STATUS){
                    status = em.find(Status.class, preference.getValue());
                }
                if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_ESTART_DATE1){
                    try {
                        searchPreference.setEstimatedStartDate1(format.parse(preference.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_ESTART_DATE2){
                    try {
                        searchPreference.setEstimatedStartDate2(format.parse(preference.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_EEND_DATE1){
                    try {
                        searchPreference.setEstimatedEndDate1(format.parse(preference.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_EEND_DATE2){
                    try {
                        searchPreference.setEstimatedEndDate2((format.parse(preference.getValue())));
                    } catch (ParseException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_ASTART_DATE1){
                    try {
                        searchPreference.setActualStartDate1(format.parse(preference.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_ASTART_DATE2){
                    try {
                        searchPreference.setActualStartDate2(format.parse(preference.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_AEND_DATE1){
                    try {
                        searchPreference.setActualEndDate1(format.parse(preference.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_AEND_DATE2){
                    try {
                        searchPreference.setActualEndDate2(format.parse(preference.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }

            }
        }
    }

    /**
     * Save preference.
     */
	public void loadTasks(){
        // Get login user.
        User loginUser = em.find(User.class, identity.getUser().getId());
        List<Preference> preferenceList = loginUser.getPreferences();

        for(Preference preference : preferenceList){
            // Search cretail.
            if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_PROJECT){
                if(project != null){
                    preference.setValue(project.getProjectId());
                }else{
                    preference.setValue(null);
                }
                em.merge(preference);
            }
            if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_OWNER){
                if(user != null){
                    preference.setValue(user.getUserId());
                }else{
                    preference.setValue(null);
                }
                em.merge(preference);
            }
            if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_TASK_STATUS){
                if(status != null){
                    preference.setValue(status.getStatusId());
                }else{
                    preference.setValue(null);
                }
                em.merge(preference);
            }
            if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_ESTART_DATE1){
                if(searchPreference.getEstimatedStartDate1() != null){
                    preference.setValue(String.valueOf(searchPreference.getEstimatedStartDate1()));
                }else{
                    preference.setValue(null);
                }
                em.merge(preference);
            }
            if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_ESTART_DATE2){
                if(searchPreference.getEstimatedStartDate2() != null){
                    preference.setValue(String.valueOf(searchPreference.getEstimatedStartDate2()));
                }else{
                    preference.setValue(null);
                }
                em.merge(preference);
            }
            if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_EEND_DATE1){
                if(searchPreference.getEstimatedEndDate1() != null){
                    preference.setValue(String.valueOf(searchPreference.getEstimatedEndDate1()));
                }else{
                    preference.setValue(null);
                }
                em.merge(preference);
            }
            if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_EEND_DATE2){
                if(searchPreference.getEstimatedEndDate2() != null){
                    preference.setValue(String.valueOf(searchPreference.getEstimatedEndDate2()));
                }else{
                    preference.setValue(null);
                }
                em.merge(preference);
            }
            if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_ASTART_DATE1){
                if(searchPreference.getActualStartDate1() != null){
                    preference.setValue(String.valueOf(searchPreference.getActualStartDate1()));
                }else{
                    preference.setValue(null);
                }
                em.merge(preference);
            }
            if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_ASTART_DATE2){
                if(searchPreference.getActualStartDate2() != null){
                    preference.setValue(String.valueOf(searchPreference.getActualStartDate2()));
                }else{
                    preference.setValue(null);
                }
                em.merge(preference);
            }
            if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_AEND_DATE1){
                if(searchPreference.getActualEndDate1() != null){
                    preference.setValue(String.valueOf(searchPreference.getActualEndDate1()));
                }else{
                    preference.setValue(null);
                }
                em.merge(preference);
            }
            if(preference.getPreferenceId() == Util.PREFERENCE_SEARCH_AEND_DATE2){
                if(searchPreference.getActualEndDate2() != null){
                    preference.setValue(String.valueOf(searchPreference.getActualEndDate2()));
                }else{
                    preference.setValue(null);
                }
                em.merge(preference);
            }
        }
	}

    /**
     * Get all task nodes.
     * @return
     */
	public List<Task> getNodeChildren() {
        Task task = getTask();
        if(task != null){
            return task.getChildTasks();
        }else{
            String query = "select task from Task task ";
            if(user != null){
                query += "JOIN task.userXtasks ut ";
            }
            // putting where.
            query += "where task.project IS NOT NULL ";
            if(user != null){
                query += "AND ut.user.userId = :userId ";
            }
            if(project != null){
                query += "AND task.project.id = :projectId ";
            }
            if(status != null){
                query += "AND task.status.statusId = :statusId ";
            }

            // Estimated Start Date
            if(searchPreference.getEstimatedStartDate1() != null && searchPreference.getEstimatedStartDate2() != null){
                query += "AND task.taskEstimatedStartDate BETWEEN :estimatedStartDate1 AND :estimatedStartDate2 ";
            }
            // Estimated End Date
            if(searchPreference.getEstimatedEndDate1() != null && searchPreference.getEstimatedEndDate2() != null){
                query += "AND task.taskEstimatedEndDate BETWEEN :estimatedEndDate1 AND :estimatedEndDate2 ";
            }
            // Actual Start Date
            if(searchPreference.getActualStartDate1() != null && searchPreference.getActualStartDate2() != null){
                query += "AND task.taskActualStartDate BETWEEN :actualStartDate1 AND :actualStartDate2 ";
            }
            // Actual End Date
            if(searchPreference.getActualEndDate1() != null && searchPreference.getActualEndDate2() != null){
                query += "AND task.taskActualEndDate BETWEEN :actualEndDate1 AND :actualEndDate2 ";
            }

            Query sqlQuery = em.createQuery(query);

            if(user != null){
                sqlQuery.setParameter("userId", user.getUserId());
            }
            if(project != null){
                sqlQuery.setParameter("projectId", project.getProjectId());
            }
            if(status != null){
                sqlQuery.setParameter("statusId", status.getStatusId());
            }
            if(searchPreference.getEstimatedStartDate1() != null && searchPreference.getEstimatedStartDate2() != null){
                sqlQuery.setParameter("estimatedStartDate1", searchPreference.getEstimatedStartDate1(), TemporalType.DATE);
                sqlQuery.setParameter("estimatedStartDate2", searchPreference.getEstimatedStartDate2(), TemporalType.DATE);
            }
            if(searchPreference.getEstimatedEndDate1() != null && searchPreference.getEstimatedEndDate2() != null){
                sqlQuery.setParameter("estimatedEndDate1", searchPreference.getEstimatedEndDate1(), TemporalType.DATE);
                sqlQuery.setParameter("estimatedEndDate2", searchPreference.getEstimatedEndDate2(), TemporalType.DATE);
            }
            if(searchPreference.getActualStartDate1() != null && searchPreference.getActualStartDate2() != null){
                sqlQuery.setParameter("actualStartDate1", searchPreference.getActualStartDate1(), TemporalType.DATE);
                sqlQuery.setParameter("actualStartDate2", searchPreference.getActualStartDate2(), TemporalType.DATE);
            }
            if(searchPreference.getActualEndDate1() != null && searchPreference.getActualEndDate2() != null){
                sqlQuery.setParameter("actualEndDate1", searchPreference.getActualEndDate1(), TemporalType.DATE);
                sqlQuery.setParameter("actualEndDate2", searchPreference.getActualEndDate2(), TemporalType.DATE);
            }

            taskList = sqlQuery.getResultList();
            return taskList;
        }
    }

    /**
     * Get children
     * @return
     */
	public boolean getNodeHasChildren() {
        Task task = getTask();
        if(task.getChildTasks() == null || task.getChildTasks().size() < 1){
        	return false;
        }
        return true;
    }

    /**
     * Match the value on JSF page.
     * @return
     */
	private Task getTask() {
        return Faces.var("task", Task.class);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TaskSearchPreferenceDto getSearchPreference() {
        return searchPreference;
    }

    public void setSearchPreference(TaskSearchPreferenceDto searchPreference) {
        this.searchPreference = searchPreference;
    }
}
