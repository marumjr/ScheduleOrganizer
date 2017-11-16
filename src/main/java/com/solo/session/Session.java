package com.solo.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.solo.event.TalkEvent;
import com.solo.utils.DateUtils;

/**
 * Abstract class representing a session of a working day, which can contain
 * {@link TalkEvent}s.
 * 
 * @author marumjr
 */
public abstract class Session {

	private List<TalkEvent> events = new ArrayList<TalkEvent>();

	private Date nextEventTime = this.getInitialTime();

	private int minutesLeft = this.getMaxDurationInMinutes();

	/**
	 * @return the max duration time, in minutes, that this session takes
	 */
	public abstract int getMaxDurationInMinutes();

	/**
	 * @return an instance of {@link Date} with the default initial time for
	 *         this session
	 */
	public abstract Date getInitialTime();

	/**
	 * Calculate the duration usage, in minutes, of the time we still left
	 * 
	 * @param duration
	 *            Duration time, in minutes, we'd like to check
	 * @return The time we have left in this session after we discount the
	 *         duration
	 */
	public int calculateDurationUsage(int duration) {
		return this.minutesLeft - duration;
	}

	/**
	 * Adds an TalkEvent to the session, if there's still enough room for
	 * it. Otherwise, just answer with a <code>false</code>, doing nothing else.
	 * 
	 * @param event
	 *            TalkEvent we want to add to this session
	 * @return <code>true</code> if the Event could be added, <code>false</code>
	 *         otherwise
	 */
	public boolean addEvent(TalkEvent event) {
		boolean inserted = false;

		int eventDuration = event.getDuration();
		// If there's still room for the event...
		if (eventDuration <= this.minutesLeft) {
			// ... sets the scheduled time of the event with the next available
			// time...
			event.setScheduledTime(this.nextEventTime);
			// ... and add it to the events of this session
			this.events.add(event);

			// We now have less room for other events
			this.minutesLeft -= eventDuration;
			// We prepare our schedule for the next event
			this.nextEventTime = DateUtils.addMinutesToDate(this.nextEventTime, eventDuration);

			inserted = true;
		}

		return inserted;
	}

	/* ******************************
	 * Getters, Setters and Overriding implementation
	 * ****************************** */

	public int getMinutesLeft() {
		return this.minutesLeft;
	}

	public Date getNextEventTime() {
		return this.nextEventTime;
	}

	public List<TalkEvent> getEvents() {
		return this.events;
	}

}
