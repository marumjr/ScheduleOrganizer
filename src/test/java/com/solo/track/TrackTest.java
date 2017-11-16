package com.solo.track;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import com.solo.LargeOutputTest;
import com.solo.event.TalkEvent;
import com.solo.session.AfternoonSession;
import com.solo.session.MorningSession;
import com.solo.talk.Talk;

/**
 * Class containing the tests for {@link Track}
 * 
 * @author marumjr
 */
public class TrackTest extends LargeOutputTest {

	/**
	 * Integration test for the methods within the {@link Track} class.
	 * <p>
	 * The default case contemplates when there are enough talks during the
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
		// 95 minutes of talks in the morning
		MorningSession morningSession = createMorningSession(createTalkEvent("15 Minutes Talk", 15),
				createTalkEvent("30 Minutes Talk", 30), createTalkEvent("45 Minutes Talk", 45),
				createTalkEvent("Lightning Talk", 5));

		// 220 minutes in the afternoon (from a total of 240 possible)
		AfternoonSession afternoonSession = createAfternoonSession(createTalkEvent("45 Minutes Talk", 45),
				createTalkEvent("30 Minutes Talk", 30), createTalkEvent("60 Minutes Talk", 60),
				createTalkEvent("Lightning Talk", 5), createTalkEvent("10 Minutes Talk", 10),
				createTalkEvent("70 Minutes Talk", 70));

		Track track = new Track(morningSession, afternoonSession);
		track.printTrack();

		Assert.assertEquals(readResourceFile("testTrackDefault_ExpectedResult.txt"), this.outContent.toString());
	}

	/**
	 * Integration test for the methods within the {@link Track} class.
	 * <p>
	 * This case contemplates when there are enough talks for just the
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
		MorningSession morningSession = createMorningSession(createTalkEvent("15 Minutes Talk", 15),
				createTalkEvent("30 Minutes Talk", 30), createTalkEvent("45 Minutes Talk", 45),
				createTalkEvent("Lightning Talk", 5));

		Track track = new Track(morningSession, null);
		track.printTrack();

		Assert.assertEquals(readResourceFile("testTrackJustMorning_ExpectedResult.txt"), this.outContent.toString());
	}

	/**
	 * Integration test for the methods within the {@link Track} class.
	 * <p>
	 * This case contemplates when there are enough talks for the whole
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
		MorningSession morningSession = createMorningSession(createTalkEvent("15 Minutes Talk", 15),
				createTalkEvent("30 Minutes Talk", 30), createTalkEvent("45 Minutes Talk", 45),
				createTalkEvent("Lightning Talk", 5));

		// 150 minutes total, there's still 30 minutes until Meet Your Colleagues
		AfternoonSession afternoonSession = createAfternoonSession(createTalkEvent("45 Minutes Talk", 45),
				createTalkEvent("30 Minutes Talk", 30), createTalkEvent("60 Minutes Talk", 60),
				createTalkEvent("Lightning Talk", 5), createTalkEvent("10 Minutes Talk", 10));

		Track track = new Track(morningSession, afternoonSession);
		track.printTrack();

		Assert.assertEquals(readResourceFile("testTrackShortAfternoon_ExpectedResult.txt"), this.outContent.toString());
	}

	/**
	 * Quick method for mocking an {@link MorningSession}
	 * 
	 * @param events
	 *            {@link TalkEvent} to include in your session
	 * @return new instance of an {@link MorningSession}
	 */
	private MorningSession createMorningSession(TalkEvent... events) {
		MorningSession session = new MorningSession();
		for (TalkEvent event : events) {
			session.addEvent(event);
		}

		return session;
	}

	/**
	 * Quick method for mocking an {@link AfternoonSession}
	 * 
	 * @param events
	 *            {@link TalkEvent} to include in your session
	 * @return new instance of an {@link AfternoonSession}
	 */
	private AfternoonSession createAfternoonSession(TalkEvent... events) {
		AfternoonSession session = new AfternoonSession();
		for (TalkEvent event : events) {
			session.addEvent(event);
		}

		return session;
	}

	/**
	 * Quick method for mocking a {@link TalkEvent}
	 * 
	 * @param name
	 *            The talks's name
	 * @param duration
	 *            The talk's duration
	 * @return new instance of a {@link TalkEvent}
	 */
	private TalkEvent createTalkEvent(String name, int duration) {
		Talk talk = new Talk(name, duration);
		TalkEvent talkEvent = new TalkEvent(talk);
		return talkEvent;
	}

}
