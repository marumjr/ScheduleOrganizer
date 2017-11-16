package com.solo.session;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.solo.event.WorkshopEvent;
import com.solo.utils.DateUtils;
import com.solo.workshop.Workshop;

/**
 * Class containing the tests for {@link Session}
 * 
 * @author marumjr
 */
public class SessionTest {

	/**
	 * Integration test for the methods within the {@link MorningSession} class.
	 * <p>
	 * Constructs the {@link MorningSession}, set some attributes and check if
	 * everything is as expected.
	 */
	@Test
	public void testMorningSession() {
		Session session = new MorningSession();

		// Checking the default parameters
		Assert.assertEquals(180, session.getMaxDurationInMinutes());
		Assert.assertEquals(DateUtils.createDate(9, 0), session.getInitialTime());

		// In this moment, there are no Events in this session...
		Assert.assertEquals(new ArrayList<WorkshopEvent>(), session.getEvents());
		// ... because of that, the available time for an event is the same as
		// the initial time
		Assert.assertEquals(DateUtils.createDate(9, 0), session.getNextEventTime());
		// ... and the minutes left for this session is equal to the total
		// amount of time
		Assert.assertEquals(180, session.getMinutesLeft());

		// If we need 15 minutes from the session time, how much time will be
		// left?
		Assert.assertEquals((180 - 15), session.calculateDurationUsage(15));

		// Configuring a workshop
		WorkshopEvent w15 = createWorkshopEvent("15 Minutes Workshop", 15);
		Assert.assertEquals(true, session.addEvent(w15));
		Assert.assertEquals(1, session.getEvents().size());
		Assert.assertEquals(DateUtils.createDate(9, 15), session.getNextEventTime());
		Assert.assertEquals(165, session.getMinutesLeft());

		// Check that a Workshop that is too long won't be configured...
		WorkshopEvent wLong = createWorkshopEvent("300 Minutes Workshop", 300);
		Assert.assertEquals(false, session.addEvent(wLong));
		// ... and that it didn't have messed around with the other parameters
		Assert.assertEquals(1, session.getEvents().size());
		Assert.assertEquals(DateUtils.createDate(9, 15), session.getNextEventTime());
		Assert.assertEquals(165, session.getMinutesLeft());

		// ... but we can still add other normal sized Workshops
		WorkshopEvent w30 = createWorkshopEvent("30 Minutes Workshop", 30);
		Assert.assertEquals(true, session.addEvent(w30));
		Assert.assertEquals(2, session.getEvents().size());
		Assert.assertEquals(DateUtils.createDate(9, 45), session.getNextEventTime());
		Assert.assertEquals(135, session.getMinutesLeft());
	}

	/**
	 * Integration test for the methods within the {@link AfternoonSession}
	 * class.
	 * <p>
	 * Constructs the {@link AfternoonSession}, set some attributes and check if
	 * everything is as expected.
	 */
	@Test
	public void testAfternoonSession() {
		Session session = new AfternoonSession();

		// Checking the default parameters
		Assert.assertEquals(240, session.getMaxDurationInMinutes());
		Assert.assertEquals(DateUtils.createDate(13, 0), session.getInitialTime());

		// In this moment, there are no Events in this session...
		Assert.assertEquals(new ArrayList<WorkshopEvent>(), session.getEvents());
		// ... because of that, the available time for an event is the same as
		// the initial time
		Assert.assertEquals(DateUtils.createDate(13, 0), session.getNextEventTime());
		// ... and the minutes left for this session is equal to the total
		// amount of time
		Assert.assertEquals(240, session.getMinutesLeft());

		// If we need 15 minutes from the session time, how much time will be
		// left?
		Assert.assertEquals((240 - 15), session.calculateDurationUsage(15));

		// Configuring a workshop
		WorkshopEvent w15 = createWorkshopEvent("15 Minutes Workshop", 15);
		Assert.assertEquals(true, session.addEvent(w15));
		Assert.assertEquals(1, session.getEvents().size());
		Assert.assertEquals(DateUtils.createDate(13, 15), session.getNextEventTime());
		Assert.assertEquals(225, session.getMinutesLeft());

		// Check that a Workshop that is too long won't be configured...
		WorkshopEvent wLong = createWorkshopEvent("300 Minutes Workshop", 300);
		Assert.assertEquals(false, session.addEvent(wLong));
		// ... and that it didn't have messed around with the other parameters
		Assert.assertEquals(1, session.getEvents().size());
		Assert.assertEquals(DateUtils.createDate(13, 15), session.getNextEventTime());
		Assert.assertEquals(225, session.getMinutesLeft());

		// ... but we can still add other normal sized Workshops
		WorkshopEvent w30 = createWorkshopEvent("30 Minutes Workshop", 30);
		Assert.assertEquals(true, session.addEvent(w30));
		Assert.assertEquals(2, session.getEvents().size());
		Assert.assertEquals(DateUtils.createDate(13, 45), session.getNextEventTime());
		Assert.assertEquals(195, session.getMinutesLeft());
	}

	/**
	 * Tests if the {@link SessionFactory} class is creating the right instances
	 * of a {@link Session}
	 */
	@Test
	public void testSessionFactory() {
		SessionFactory sessionFactory = new SessionFactory();

		// The first instance the SessionFactory should provide to us is a
		// MorningSession...
		Assert.assertEquals(MorningSession.class, sessionFactory.createNextSession().getClass());
		// ... and then an AfternoonSession...
		Assert.assertEquals(AfternoonSession.class, sessionFactory.createNextSession().getClass());
		// ... and then a MorningSession again...
		Assert.assertEquals(MorningSession.class, sessionFactory.createNextSession().getClass());
		// ... and yet another AfternoonSession
		Assert.assertEquals(AfternoonSession.class, sessionFactory.createNextSession().getClass());
	}

	/**
	 * Quick method for mocking a {@link WorkshopEvent}
	 * 
	 * @param name
	 *            The workshop's name
	 * @param duration
	 *            The workshop's duration
	 * @return new instance of a {@link WorkshopEvent}
	 */
	private WorkshopEvent createWorkshopEvent(String name, int duration) {
		Workshop workshop = new Workshop(name, duration);
		WorkshopEvent workshopEvent = new WorkshopEvent(workshop);
		return workshopEvent;
	}

}
