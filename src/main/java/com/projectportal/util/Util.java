/**
 * 
 */
package com.projectportal.util;

import com.projectportal.entity.Status;
import org.joda.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author lastcow
 *
 */
public class Util {

	public static final String ROLE_ADMIN = "Admin";
	public static final String ROLE_USER = "User";

    public static final int WBS_BASLINE = 0;
    public static final int WBS_ACTUAL = 1;

    public static final String STATUS_NEW = "New";
    public static final String STATUS_IN_PROGRESS = "In Progress";
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_FINISH = "Finish";
    public static final String STATUS_REOPEN = "Reopen";

    public static final int PREFERENCE_SEARCH_PROJECT = 0;
    public static final int PREFERENCE_SEARCH_OWNER = 1;
    public static final int PREFERENCE_SEARCH_TASK_STATUS = 2;
    public static final int PREFERENCE_SEARCH_ESTART_DATE1 = 3;
    public static final int PREFERENCE_SEARCH_ESTART_DATE2 = 4;
    public static final int PREFERENCE_SEARCH_EEND_DATE1 = 5;
    public static final int PREFERENCE_SEARCH_EEND_DATE2 = 6;
    public static final int PREFERENCE_SEARCH_ASTART_DATE1 = 7;
    public static final int PREFERENCE_SEARCH_ASTART_DATE2 = 8;
    public static final int PREFERENCE_SEARCH_AEND_DATE1 = 9;
    public static final int PREFERENCE_SEARCH_AEND_DATE2 = 10;
    public static final int PREFERENCE_TASKPANEL_SCROLLLEFT = 11;
    public static final int PREFERENCE_TASKPANEL_SCROLLTOP = 12;

    public static final int HOURS_PER_DAY = 8;

    // dependency constant
    // For ExtJs Gantt Chart
    // REF http://www.bryntum.com/docs/scheduling/#!/api/Gnt.model.Dependency
    public static final int DEPENDENCY_TYPE_START_TO_START = 0;
    public static final int DEPENDENCY_TYPE_START_TO_END = 1;
    public static final int DEPENDENCY_TYPE_END_TO_START = 2;
    public static final int DEPENDENCY_TYPE_END_TO_END = 3;
	/**
	 * MD5 string
	 * @param string
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 */
	public static String getMD5Hash(String string) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(string.getBytes());
		
		byte byteData[] = md.digest();
		
		//convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
		
	}

    /**
     * Get working hour
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getWorkingHourBetweenTwoDate(LocalDate startDate, LocalDate endDate){
        //TODO Need to add holiday
        int days = 0;

        while(!startDate.isAfter(endDate)){

            // Check for weekend
//            if(startDate.getDayOfWeek() <= DateTimeConstants.FRIDAY){
//                //TODO check for holiday
//                days ++;
//            }

            days ++ ;
            startDate = startDate.plusDays(1);
        }

        // Count one more day for math.
        return days * Util.HOURS_PER_DAY;
    }


    /**
     * Get status based on name
     * @param statusName
     * @return
     */
    public static Status getStatusByName(EntityManager em, String statusName){
        Query query = em.createQuery("SELECT status FROM Status status WHERE status.statusName = ?1");
        query.setParameter(1, statusName);
        return (Status) query.getSingleResult();
    }
}
