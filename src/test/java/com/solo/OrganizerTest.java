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
	 * Checks the default case, where there are enough Workshops to cover entire
	 * days.
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@Test
	public void testOrganizerDefault() throws IOException, URISyntaxException {
		List<Talk> workshops = new ArrayList<Talk>();
		workshops.add(createWorkshop("30 Minutes Workshop", 30));
		workshops.add(createWorkshop("45 Minutes Workshop", 45));
		workshops.add(createWorkshop("Lightning Workshop", 5));
		workshops.add(createWorkshop("15 Minutes Workshop", 15));
		workshops.add(createWorkshop("100 Minutes Workshop", 100));
		workshops.add(createWorkshop("65 Minutes Workshop", 65));
		workshops.add(createWorkshop("35 Minutes Workshop", 35));
		workshops.add(createWorkshop("10 Minutes Workshop", 10));
		workshops.add(createWorkshop("90 Minutes Workshop", 90));
		workshops.add(createWorkshop("75 Minutes Workshop", 75));
		workshops.add(createWorkshop("60 Minutes Workshop", 60));
		workshops.add(createWorkshop("2 Hours Workshop", 120));

		Organizer organizer = new Organizer(workshops);
		organizer.printTracks();

		Assert.assertEquals(readResourceFile("testOrganizerDefault_ExpectedResult.txt"), this.outContent.toString());
	}

	/**
	 * Integration test for {@link Organizer}
	 * <p>
	 * Checks the case where there are not enough workshops for the day - in
	 * which case, they are all allocated to the morning session
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@Test
	public void testOrganizerJustMorning() throws IOException, URISyntaxException {
		List<Talk> workshops = new ArrayList<Talk>();
		workshops.add(createWorkshop("30 Minutes Workshop", 30));
		workshops.add(createWorkshop("45 Minutes Workshop", 45));
		workshops.add(createWorkshop("Lightning Workshop", 5));
		workshops.add(createWorkshop("60 Minutes Workshop", 60));

		Organizer organizer = new Organizer(workshops);
		organizer.printTracks();

		Assert.assertEquals(readResourceFile("testOrganizerJustMorning_ExpectedResult.txt"),
				this.outContent.toString());
	}

	/**
	 * Quick method for mocking a Workshop
	 * 
	 * @param name
	 *            Workshop's name
	 * @param duration
	 *            Workshop's duration
	 * @return new instance of Workshop
	 */
	private Talk createWorkshop(String name, int duration) {
		Talk workshop = new Talk(name, duration);
		return workshop;
	}

}
