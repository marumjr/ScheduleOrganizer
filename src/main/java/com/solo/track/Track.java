package com.solo.track;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.solo.event.Event;
import com.solo.event.LunchEvent;
import com.solo.event.MeetYourColleaguesEvent;
import com.solo.event.TalkEvent;
import com.solo.exception.ScheduleOrganizerRuntimeException;
import com.solo.session.AfternoonSession;
import com.solo.session.MorningSession;

/**
 * Object that represents a Track (a working day of events).
 * 
 * @author marumjr
 */
public class Track {

	private List<Event> events = new ArrayList<Event>();;

	/**
	 * Default constructor.
	 * <p>
	 * It builds the Track according the the session of events that occur in the
	 * morning and in the afternoon.
	 * 
	 * @param morningSession
	 *            A {@link MorningSession}, which are the events that will
	 *            happen in the morning
	 * @param afternoonSession
	 *            An {@link AfternoonSession}, which are the events that will
	 *            happen in the afternoon
	 */
	public Track(MorningSession morningSession, AfternoonSession afternoonSession) {
		this.buildEvents(morningSession, afternoonSession);
	}

	/**
	 * Print the content of the {@link Track}, which is the content of all
	 * events that are included there.
	 */
	public void printTrack() {
		for (Event event : this.events) {
			System.out.println(event);
		}
	}

	/**
	 * Build the events, according to the sessions.
	 * <p>
	 * First, it will insert all the events from the {@link MorningSession},
	 * then the {@link LunchEvent}. Then the events in the
	 * {@link AfternoonSession} (if it exists). Finally, the
	 * {@link MeetYourColleaguesEvent}.
	 * 
	 * @param morningSession
	 *            The {@link MorningSession}, containing all the
	 *            {@link TalkEvent} that are going to happen in the morning
	 * @param afternoonSession
	 *            The {@link AfternoonSession}, containing all the
	 *            {@link TalkEvent} that are going to happen in the
	 *            afternoon
	 */
	private void buildEvents(MorningSession morningSession, AfternoonSession afternoonSession) {
		// First, add all the morning WorkshopEvents...
		for (Event event : morningSession.getEvents()) {
			this.events.add(event);
		}

		// ... we can then add the LunchEvent...
		this.events.add(new LunchEvent());

		if (afternoonSession != null) {
			// ... if there're events in the afternoon, we'll add that too
			for (Event event : afternoonSession.getEvents()) {
				this.events.add(event);
			}

			// And try to add the MeetYourColleaguesEvent...
			try {
				// ... either after the last afternoon's workshop...
				Date nextEventTime = afternoonSession.getNextEventTime();
				MeetYourColleaguesEvent meetYourColleaguesEvent = new MeetYourColleaguesEvent(nextEventTime);
				this.events.add(meetYourColleaguesEvent);

			} catch (ScheduleOrganizerRuntimeException e) {
				// ... or at the default time (16PM)
				MeetYourColleaguesEvent meetYourColleaguesEvent = new MeetYourColleaguesEvent();
				this.events.add(meetYourColleaguesEvent);
			}

		} else {
			// ... if there's no events in the afternoon, then just add the
			// MeetYourColleaguesEvent
			MeetYourColleaguesEvent meetYourColleaguesEvent = new MeetYourColleaguesEvent();
			this.events.add(meetYourColleaguesEvent);
		}
	}

}
