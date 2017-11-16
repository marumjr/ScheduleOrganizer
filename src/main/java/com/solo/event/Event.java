package com.solo.event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Abstract class representing an event during the day, like a talk or the
 * lunch time.
 * 
 * @author marumjr
 */
public abstract class Event {

	/** The default {@link DateFormat} to format the dates displayed by events */
	protected static final DateFormat DF = new SimpleDateFormat("hh:mma");

	protected String name;

	protected Date scheduledTime;

	protected int duration;

	/**
	 * Formats the object in a String to be displayed to the user
	 * <p>
	 * Each type of event has its own form of being displayed
	 * 
	 * @return String already formatted
	 */
	protected abstract String formatToDisplay();

	/**
	 * @return the scheduled time, which is a date, formatted to the hh:mmAM/PM
	 *         pattern
	 */
	protected String getFormattedScheduledTime() {
		return DF.format(this.getScheduledTime());
	}

	/* ******************************
	 * Getters, Setters and Overriding implementation
	 * ****************************** */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return this.formatToDisplay();
	}

}
