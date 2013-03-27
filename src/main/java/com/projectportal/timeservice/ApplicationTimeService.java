/**
 * 
 */
package com.projectportal.timeservice;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 * @author lastcow
 *
 */
@Singleton
public class ApplicationTimeService {

	@Schedule(hour = "22")
	public void TimerTest(){
		System.out.println("Hour reached.");
	}
}
