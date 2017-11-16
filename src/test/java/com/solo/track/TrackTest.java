package com.solo.track;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import com.solo.LargeOutputTest;
import com.solo.event.WorkshopEvent;
import com.solo.session.AfternoonSession;
import com.solo.session.MorningSession;
import com.solo.workshop.Workshop;

/**
 * Class containing the tests for {@link Track}
 * 
 * @author marumjr
 */
public class TrackTest extends LargeOutputTest {

	/**
	 * Integration test for the methods within the {@link Track} class.
	 * <p>
	 * The default case contemplates when there are enough workshops during the
	 * morning and the afternoon to cover it whole.
	 * <p>
	 * Constructs the {@link Track} and check if the output (when print) is the
	 * expected
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@Test
	public void testTrackDefault() throws IOException, URISyntaxException {
		// 95 minutes of workshops in the morning
		MorningSession morningSession = createMorningSession(createWorkshopEvent("15 Minutes Workshop", 15),
				createWorkshopEvent("30 Minutes Workshop", 30), createWorkshopEvent("45 Minutes Workshop", 45),
				createWorkshopEvent("Lightning Workshop", 5));

		// 220 minutes in the afternoon (from a total of 240 possible)
		AfternoonSession afternoonSession = createAfternoonSession(createWorkshopEvent("45 Minutes Workshop", 45),
				createWorkshopEvent("30 Minutes Workshop", 30), createWorkshopEvent("60 Minutes Workshop", 60),
				createWorkshopEvent("Lightning Workshop", 5), createWorkshopEvent("10 Minutes Workshop", 10),
				createWorkshopEvent("70 Minutes Workshop", 70));

		Track track = new Track(morningSession, afternoonSession);
		track.printTrack();

		Assert.assertEquals(readResourceFile("testTrackDefault_ExpectedResult.txt"), this.outContent.toString());
	}

	/**
	 * Integration test for the methods within the {@link Track} class.
	 * <p>
	 * This case contemplates when there are enough workshops for just the
	 * morning session
	 * <p>
	 * Constructs the {@link Track} and check if the output (when print) is the
	 * expected
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@Test
	public void testTrackJustMorning() throws IOException, URISyntaxException {
		// Session just in the morning
		MorningSession morningSession = createMorningSession(createWorkshopEvent("15 Minutes Workshop", 15),
				createWorkshopEvent("30 Minutes Workshop", 30), createWorkshopEvent("45 Minutes Workshop", 45),
				createWorkshopEvent("Lightning Workshop", 5));

		Track track = new Track(morningSession, null);
		track.printTrack();

		Assert.assertEquals(readResourceFile("testTrackJustMorning_ExpectedResult.txt"), this.outContent.toString());
	}

	/**
	 * Integration test for the methods within the {@link Track} class.
	 * <p>
	 * This case contemplates when there are enough workshops for the whole
	 * morning, but not enough to last the whole afternoon (until when the Meet
	 * Your Colleagues event takes in).
	 * <p>
	 * Constructs the {@link Track} and check if the output (when print) is the
	 * expected
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@Test
	public void testTrackShortAfternoon() throws IOException, URISyntaxException {
		MorningSession morningSession = createMorningSession(createWorkshopEvent("15 Minutes Workshop", 15),
				createWorkshopEvent("30 Minutes Workshop", 30), createWorkshopEvent("45 Minutes Workshop", 45),
				createWorkshopEvent("Lightning Workshop", 5));

		// 150 minutes total, there's still 30 minutes until Meet Your Colleagues
		AfternoonSession afternoonSession = createAfternoonSession(createWorkshopEvent("45 Minutes Workshop", 45),
				createWorkshopEvent("30 Minutes Workshop", 30), createWorkshopEvent("60 Minutes Workshop", 60),
				createWorkshopEvent("Lightning Workshop", 5), createWorkshopEvent("10 Minutes Workshop", 10));

		Track track = new Track(morningSession, afternoonSession);
		track.printTrack();

		Assert.assertEquals(readResourceFile("testTrackShortAfternoon_ExpectedResult.txt"), this.outContent.toString());
	}

	/**
	 * Quick method for mocking an {@link MorningSession}
	 * 
	 * @param events
	 *            {@link WorkshopEvent} to include in your session
	 * @return new instance of an {@link MorningSession}
	 */
	private MorningSession createMorningSession(WorkshopEvent... events) {
		MorningSession session = new MorningSession();
		for (WorkshopEvent event : events) {
			session.addEvent(event);
		}

		return session;
	}

	/**
	 * Quick method for mocking an {@link AfternoonSession}
	 * 
	 * @param events
	 *            {@link WorkshopEvent} to include in your session
	 * @return new instance of an {@link AfternoonSession}
	 */
	private AfternoonSession createAfternoonSession(WorkshopEvent... events) {
		AfternoonSession session = new AfternoonSession();
		for (WorkshopEvent event : events) {
			session.addEvent(event);
		}

		return session;
	}

	/**
	 * Quick method for mocking a {@link WorkshopEvent}
	 * 
	 * @param name
	 *            The workshops's name
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
