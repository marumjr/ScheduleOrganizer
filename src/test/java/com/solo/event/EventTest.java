package com.solo.event;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.solo.exception.ScheduleOrganizerRuntimeException;
import com.solo.talk.Talk;
import com.solo.utils.DateUtils;

/**
 * Class containing the tests for {@link Event}
 * 
 * @author marumjr
 */
public class EventTest {

	/**
	 * Tests the constructor and all methods of {@link LunchEvent}
	 */
	@Test
	public void testLunchEvent() {
		Event lunchEvent = new LunchEvent();

		// Assert the the Setting methods don't really alter the parameters in
		// LunchEvent
		lunchEvent.setDuration(5);
		Assert.assertEquals(60, lunchEvent.getDuration());
		lunchEvent.setName("anything else");
		Assert.assertEquals("Lunch", lunchEvent.getName());
		lunchEvent.setScheduledTime(new Date());
		Assert.assertEquals(DateUtils.createDate(12, 0), lunchEvent.getScheduledTime());

		Assert.assertEquals("12:00PM Lunch", lunchEvent.toString());
	}

	/**
	 * Tests the default constructor and all methods of
	 * {@link MeetYourColleaguesEvent}.
	 */
	@Test
	public void testMeetYourColleaguesEventDefault() {
		// The default event doesn't take a parameter
		Event eventDefault = new MeetYourColleaguesEvent();

		// Assert the the Setting methods don't really alter the parameters in
		// MeetYourColleaguesEvent
		eventDefault.setDuration(5);
		Assert.assertEquals(60, eventDefault.getDuration());
		eventDefault.setName("anything else");
		Assert.assertEquals("Meet Your Colleagues Event", eventDefault.getName());

		// Tries to force a time out of the valid range for the event
		try {
			eventDefault.setScheduledTime(DateUtils.createDate(12, 0));
			Assert.fail("It shouldn't get to this point of the code.");
		} catch (ScheduleOrganizerRuntimeException e) {
			Assert.assertEquals("Unacceptable scheduled time: 12:00PM. The event 'Meet Your Colleagues Event'"
					+ " accepts scheduled times only between 04:00PM and 05:00PM.", e.getMessage());
			Assert.assertEquals("04:00PM Meet Your Colleagues Event", eventDefault.toString());
		}

		// This time, tries to set a time inside the valid range
		try {
			eventDefault.setScheduledTime(DateUtils.createDate(16, 30));
			Assert.assertEquals("04:30PM Meet Your Colleagues Event", eventDefault.toString());
		} catch (ScheduleOrganizerRuntimeException e) {
			Assert.fail("This Exception wasn't supposed to happen.");
		}
	}

	/**
	 * Tests the alternate constructor and all methods of
	 * {@link MeetYourColleaguesEvent}
	 */
	@Test
	public void testMeetYourColleaguesEventOther() {
		// This 'other' event does take a parameter...
		Event eventOther;
		try {
			// ... first, tries to force a time out of the valid range for the
			// event
			eventOther = new MeetYourColleaguesEvent(DateUtils.createDate(12, 0));
			Assert.fail("It shouldn't get to this point of the code.");
		} catch (ScheduleOrganizerRuntimeException e) {
			Assert.assertEquals("Unacceptable scheduled time: 12:00PM. The event 'Meet Your Colleagues Event'"
					+ " accepts scheduled times only between 04:00PM and 05:00PM.", e.getMessage());
		}

		// ... this time, tries to set a time inside the valid range
		try {
			eventOther = new MeetYourColleaguesEvent(DateUtils.createDate(16, 30));
			Assert.assertEquals("04:30PM Meet Your Colleagues Event", eventOther.toString());
		} catch (ScheduleOrganizerRuntimeException e) {
			Assert.fail("This Exception wasn't supposed to happen.");
		}
	}

	/**
	 * Tests the constructor and all methods of {@link TalkEvent}.
	 * <p>
	 * The normal case here states that it's not a lightning event
	 */
	@Test
	public void testTalkEventNormal() {
		// The 'normal' Talk event doesn't have lightning duration
		Talk talk = new Talk("15 Minutes Talk", 15);

		TalkEvent event = new TalkEvent(talk);
		Assert.assertEquals(15, event.getDuration());
		Assert.assertEquals("15 Minutes Talk", event.getName());

		// Check that the event has no scheduled time, as it was not assigned
		// yet
		Assert.assertEquals(null, event.getScheduledTime());
		// Now set a value to the scheduled time and check it once again
		event.setScheduledTime(DateUtils.createDate(13, 30));
		Assert.assertEquals(DateUtils.createDate(13, 30), event.getScheduledTime());

		Assert.assertEquals("01:30PM 15 Minutes Talk 15min", event.toString());

		// Here, if we change the parameters, they should REALLY take effect
		event.setDuration(5);
		Assert.assertEquals(5, event.getDuration());
		event.setName("anything else");
		Assert.assertEquals("anything else", event.getName());
	}

	/**
	 * Tests the constructor and all methods of {@link TalkEvent}.
	 * <p>
	 * In this case, it's testing a lightning event
	 */
	@Test
	public void testTalkEventLightning() {
		// The 'lightning' Talk event has a 5 minutes duration
		Talk talk = new Talk("Lightning Talk", 5);

		TalkEvent event = new TalkEvent(talk);
		event.setScheduledTime(DateUtils.createDate(13, 30));

		Assert.assertEquals(5, event.getDuration());
		Assert.assertEquals("Lightning Talk", event.getName());
		Assert.assertEquals(DateUtils.createDate(13, 30), event.getScheduledTime());

		// The only real change is in the printing format
		Assert.assertEquals("01:30PM Lightning Talk lightning", event.toString());
	}

}
