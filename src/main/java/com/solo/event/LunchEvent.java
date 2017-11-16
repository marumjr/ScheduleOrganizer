package com.solo.event;

import java.util.Date;

import com.solo.utils.DateUtils;
import com.solo.utils.GlobalConstraints;

/**
 * Subclass of {@link Event}, representing the Lunch time.
 * 
 * @author marumjr
 */
public class LunchEvent extends Event {

	private static final String EVENT_NAME = "Lunch";

	private static final int EVENT_HOUR = GlobalConstraints.LUNCH_START_HOUR;

	private static final int EVENT_DURATION = (GlobalConstraints.AFTERNOON_SESSION_START_HOUR
			- GlobalConstraints.LUNCH_START_HOUR) * 60;

	/* ******************************
	 * Getters, Setters and Overriding implementation
	 * ****************************** */

	@Override
	protected String formatToDisplay() {
		String display = this.getFormattedScheduledTime() + " " + this.getName();
		return display;
	}

	@Override
	public String getName() {
		// Fixed value
		return EVENT_NAME;
	}

	@Override
	public void setName(String name) {
		// Don't let the user subscribe the name of this event by accident
	}

	@Override
	public Date getScheduledTime() {
		// Fixed value
		return DateUtils.createDate(EVENT_HOUR, 0);
	}

	@Override
	public void setScheduledTime(Date scheduledTime) {
		// Don't let the user subscribe the time of this event by accident
	}

	@Override
	public int getDuration() {
		// Fixed value
		return EVENT_DURATION;
	}

	@Override
	public void setDuration(int duration) {
		// Don't let the user subscribe the duration of this event by accident
	}

}
