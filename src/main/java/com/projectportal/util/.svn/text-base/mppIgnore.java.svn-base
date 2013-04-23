package com.projectportal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lastcow
 * Date: 4/17/13
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class mppIgnore {

    mppIgnore(){
        String dateStr = "Tue Apr 16 00:00:00 EDT 2013";
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            Date dateTime = format.parse(dateStr);

            System.out.println("Date: " + dateTime.toString());
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void main(String[] strs){
        new mppIgnore();
    }
}
