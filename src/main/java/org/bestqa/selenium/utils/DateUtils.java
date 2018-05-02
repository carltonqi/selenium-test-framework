package org.bestqa.selenium.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Related class of time and date
 *
 */
public class DateUtils {
	/**
	 * Standard time format yyyy-MM-dd
	 */
    public static final SimpleDateFormat STD_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
	
    /**
     * Get current time
     */
    public static Calendar getNow() {
        return Calendar.getInstance();
    }
    
    /**
     * Get yesterday time
     */
    public static Calendar getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal;
    }

    /**
     * Get tomorrow time 
     */
    public static Calendar getTomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, +1);
        return cal;
    }
    
    /**
     * Get the time before 'day' day time
     * @param day
     */
    public static Calendar getDateBefore(int day) {
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DAY_OF_MONTH, -day);
    	return cal;
    }
    
    /**
     * The time after 'day' day time
     * @param day
     */
    public static Calendar getDateAfter(int day) {
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DAY_OF_MONTH, day);
    	return cal;
    }
    
    /**
     * Get Current hour
     */
    public static Integer getCurrentHour() {
        return getNow().get(Calendar.HOUR_OF_DAY);
    }
    
    /**
     * Get previous hour
     */
    public static Integer getPreviousHour() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -1);
        return cal.get(Calendar.HOUR_OF_DAY);
    }
    
    /**
     * Get next hour
     */
    public static Integer getNextHour() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, +1);
        return cal.get(Calendar.HOUR_OF_DAY);
    }
}
