package com.solo.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class with utility methods for manipulating Date in a standardized manner
 * 
 * @author marumjr
 */
public class DateUtils {

	/**
	 * Create a Date object with the specified hour and minute. Seconds and
	 * Milliseconds will be set to 0.
	 * <p>
	 * <b>All the other parameters will be set to default</b>, but it shouldn't
	 * be a problem, since for most of the application, all that matters is the
	 * Hour and Minute of a date.
	 * 
	 * @param hour
	 *            Hour (from 0 to 23) to set on the Date
	 * @param minute
	 *            Minute (from 0 to 59) to set on the Date
	 * @return the created {@link Date}
	 */
	public static Date createDate(int hour, int minute) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Add minutes to the existing Date
	 * 
	 * @param date
	 *            Date to which we want to add some minutes
	 * @param minutesToAdd
	 *            The quantity of minutes to add
	 * @return The updated date
	 */
	public static Date addMinutesToDate(Date date, int minutesToAdd) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutesToAdd);
		return cal.getTime();
	}

	/**
	 * Verifies if a certain Date is in between the specified period
	 * 
	 * @param date
	 *            Date to assert
	 * @param initPeriod
	 *            The beginning of the period
	 * @param endPeriod
	 *            The end of the period
	 * @return <code>true</code> if it date is contained by the interval,
	 *         <code>false</code> otherwise
	 */
	public static boolean isDateInPeriod(Date date, Date initPeriod, Date endPeriod) {
		Calendar actualDate = new GregorianCalendar();
		actualDate.setTime(date);
		actualDate.set(Calendar.SECOND, 0);
		actualDate.set(Calendar.MILLISECOND, 0);

		Calendar startPoint = new GregorianCalendar();
		startPoint.setTime(initPeriod);
		actualDate.set(Calendar.SECOND, 0);
		actualDate.set(Calendar.MILLISECOND, 0);

		Calendar endPoint = new GregorianCalendar();
		endPoint.setTime(endPeriod);
		actualDate.set(Calendar.SECOND, 0);
		actualDate.set(Calendar.MILLISECOND, 0);

		if ((actualDate.compareTo(startPoint) >= 0) && (actualDate.compareTo(endPoint) <= 0)) {
			return true;
		}

		return false;
	}

}
