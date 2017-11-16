package com.solo.event;

import java.util.Date;

import com.solo.exception.ScheduleOrganizerRuntimeException;
import com.solo.utils.DateUtils;
import com.solo.utils.GlobalConstraints;

/**
 * Subclass of {@link Event}, representing the Meet Your Colleagues time.
 * 
 * @author marumjr
 */
public class MeetYourColleaguesEvent extends Event {

	private static final String EVENT_NAME = "Meet Your Colleagues Event";

	// This one doesn't really matter, as it's currently not in use
	private static final int EVENT_DURATION = 60;

	/**
	 * Default constructor.
	 * <p>
	 * Sets the scheduled time to the earliest possible time for the event Meet
	 * Your Colleagues.
	 */
	public MeetYourColleaguesEvent() {
		this.setScheduledTime(getEarliestTime());
	}

	/**
	 * Alternative constructor.
	 * <p>
	 * It tries to set the scheduled time to the one specified. If the scheduled
	 * time is not possible, throws a {@link ScheduleOrganizerRuntimeException}.
	 * 
	 * @param scheduledTime
	 *            The time in which we want this event to start
	 * @throws ScheduleOrganizerRuntimeException
	 *             When the scheduled time doesn't fit in between the minimum
	 *             and maximum time in which this event is allowed to start
	 */
	public MeetYourColleaguesEvent(Date scheduledTime) {
		this.setScheduledTime(scheduledTime);
	}

	/**
	 * @return the earliest possible time when this event may start
	 */
	private static final Date getEarliestTime() {
		return DateUtils.createDate(GlobalConstraints.MEET_COLLEAGUES_EARLIEST_START_HOUR, 0);
	}

	/**
	 * @return the latest possible time when this event may start
	 */
	private static final Date getLatestTime() {
		return DateUtils.createDate(GlobalConstraints.MEET_COLLEAGUES_LATEST_START_HOUR, 0);
	}

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
	public void setScheduledTime(Date scheduledTime) {
		// A scheduled time is only valid for this event if it's between the
		// minimum and maximum time for it to start
		if (DateUtils.isDateInPeriod(scheduledTime, getEarliestTime(), getLatestTime())) {
			super.setScheduledTime(scheduledTime);

		} else {
			throw new ScheduleOrganizerRuntimeException(
					"Unacceptable scheduled time: %s. The event '%s' accepts scheduled times only between %s and %s.",
					DF.format(scheduledTime), this.getName(), DF.format(getEarliestTime()), DF.format(getLatestTime()));
		}
	}

	@Override
	public int getDuration() {
		// Fixed value
		return EVENT_DURATION;
	}

	@Override
	public void setDuration(int duration) {
		// Don't let the user subscribe the name of this event by accident
	}

}
