package com.solo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.solo.talk.Talk;

/**
 * Class containing the tests for {@link Organizer}
 * 
 * @author marumjr
 */
public class OrganizerTest extends LargeOutputTest {

	/**
	 * Integration test for {@link Organizer}
	 * <p>
	 * Checks the default case, where there are enough Talks to cover entire
	 * days.
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@Test
	public void testOrganizerDefault() throws IOException, URISyntaxException {
		List<Talk> talks = new ArrayList<Talk>();
		talks.add(createTalk("30 Minutes Talk", 30));
		talks.add(createTalk("45 Minutes Talk", 45));
		talks.add(createTalk("Lightning Talk", 5));
		talks.add(createTalk("15 Minutes Talk", 15));
		talks.add(createTalk("100 Minutes Talk", 100));
		talks.add(createTalk("65 Minutes Talk", 65));
		talks.add(createTalk("35 Minutes Talk", 35));
		talks.add(createTalk("10 Minutes Talk", 10));
		talks.add(createTalk("90 Minutes Talk", 90));
		talks.add(createTalk("75 Minutes Talk", 75));
		talks.add(createTalk("60 Minutes Talk", 60));
		talks.add(createTalk("2 Hours Talk", 120));

		Organizer organizer = new Organizer(talks);
		organizer.printTracks();

		Assert.assertEquals(readResourceFile("testOrganizerDefault_ExpectedResult.txt"), this.outContent.toString());
	}

	/**
	 * Integration test for {@link Organizer}
	 * <p>
	 * Checks the case where there are not enough talks for the day - in
	 * which case, they are all allocated to the morning session
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@Test
	public void testOrganizerJustMorning() throws IOException, URISyntaxException {
		List<Talk> talks = new ArrayList<Talk>();
		talks.add(createTalk("30 Minutes Talk", 30));
		talks.add(createTalk("45 Minutes Talk", 45));
		talks.add(createTalk("Lightning Talk", 5));
		talks.add(createTalk("60 Minutes Talk", 60));

		Organizer organizer = new Organizer(talks);
		organizer.printTracks();

		Assert.assertEquals(readResourceFile("testOrganizerJustMorning_ExpectedResult.txt"),
				this.outContent.toString());
	}

	/**
	 * Quick method for mocking a Talk
	 * 
	 * @param name
	 *            Talk's name
	 * @param duration
	 *            Talk's duration
	 * @return new instance of Talk
	 */
	private Talk createTalk(String name, int duration) {
		Talk talk = new Talk(name, duration);
		return talk;
	}

}
