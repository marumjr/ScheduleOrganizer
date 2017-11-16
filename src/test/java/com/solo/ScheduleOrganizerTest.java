package com.solo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.solo.talk.Talk;

/**
 * Class containing the tests for {@link ScheduleOrganizer}
 * 
 * @author marumjr
 */
public class ScheduleOrganizerTest extends LargeOutputTest {

	/**
	 * Check the {@link ScheduleOrganizer#parseTalkInfo(String)}
	 * <p>
	 * Check the common cases, the lightning special case and some unusual
	 * cases, like a talk with long name or duration
	 */
	@Test
	public void testParseTalkInfo() {
		// Common cases
		Talk talk = ScheduleOrganizer.parseTalkInfo("65 Minutes Talk 65min");
		Assert.assertEquals("65 Minutes Talk", talk.getName());
		Assert.assertEquals(65, talk.getDuration());

		talk = ScheduleOrganizer.parseTalkInfo("5 Minutes Talk 5min");
		Assert.assertEquals("5 Minutes Talk", talk.getName());
		Assert.assertEquals(5, talk.getDuration());

		// Handling lightning special case
		talk = ScheduleOrganizer.parseTalkInfo("Lightning Talk lightning");
		Assert.assertEquals("Lightning Talk", talk.getName());
		Assert.assertEquals(5, talk.getDuration());

		// Checking if it matters to have a long name
		talk = ScheduleOrganizer.parseTalkInfo(
				"Talk with a really long name just to see if it's working under extreme conditions and whatnot 40min");
		Assert.assertEquals(
				"Talk with a really long name just to see if it's working under extreme conditions and whatnot",
				talk.getName());
		Assert.assertEquals(40, talk.getDuration());

		// Checking if matters have a long duration
		talk = ScheduleOrganizer.parseTalkInfo("Talk with a long duration 30000min");
		Assert.assertEquals("Talk with a long duration", talk.getName());
		Assert.assertEquals(30000, talk.getDuration());
	}

	/**
	 * 
	 * Check the {@link ScheduleOrganizer#parseInputFile(String)}
	 * <p>
	 * It loads <b>defaultInput.txt</b> into the parseInputFile(), which returns
	 * a list of Talks. It then iterates over each Talk to see if it was
	 * correctly parsed.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@Test
	public void testParseInputFile() throws FileNotFoundException, IOException, URISyntaxException {
		File inputFile = retrieveResourceFile("defaultInput.txt");

		List<Talk> talks = ScheduleOrganizer.parseInputFile(inputFile.getAbsolutePath());
		Iterator<Talk> iterator = talks.iterator();

		// Check every possible parsed line
		assertSameName(iterator, "Create better mocks for Spring Boot", 65);
		assertSameName(iterator, "More Java for people", 40);
		assertSameName(iterator, "Fun with Kotlin", 30);
		assertSameName(iterator, "Managing dependencies with Maven", 45);
		assertSameName(iterator, "Better error handling in Java", 45);
		assertSameName(iterator, "Scala from JEE guys", 5);
		assertSameName(iterator, "Slack for old people", 60);
		assertSameName(iterator, "Finance Domain explained", 45);
		assertSameName(iterator, "Healthier lunch in Berlin", 30);
		assertSameName(iterator, "Scope Future", 30);
		assertSameName(iterator, "Better work in Teams", 45);
		assertSameName(iterator, "Best Spring Features", 60);
		assertSameName(iterator, "Advance Spring Boot", 60);
		assertSameName(iterator, "Why Clojure Matters", 45);
		assertSameName(iterator, "Living in Berlin", 30);
		assertSameName(iterator, "Working with Azure", 30);
		assertSameName(iterator, "Maintain Java Code", 60);
		assertSameName(iterator, "Better Way of reading Books", 30);
		assertSameName(iterator, "What you need to know about ExtJS", 30);

		// Check that there are no more elements to check
		Assert.assertFalse(iterator.hasNext());
	}

	/**
	 * Check the {@link ScheduleOrganizer#main(String[])} for a standard input
	 * file.
	 * <p>
	 * It loads <b>defaultInput.txt</b> into the main() method and asserts the
	 * system output to an expected output
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void testMainDefaultInput() throws URISyntaxException, IOException {
		File inputFile = retrieveResourceFile("defaultInput.txt");

		ScheduleOrganizer.main(new String[] { inputFile.getAbsolutePath() });
		Assert.assertEquals(readResourceFile("testMainDefaultInput_ExpectedResult.txt"), this.outContent.toString());
	}

	/**
	 * Check the {@link ScheduleOrganizer#main(String[])} for a large input
	 * file.
	 * <p>
	 * It loads <b>largeInput.txt</b> into the main() method and asserts the
	 * system output to an expected output
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void testMainLargeInput() throws URISyntaxException, IOException {
		File inputFile = retrieveResourceFile("largeInput.txt");

		ScheduleOrganizer.main(new String[] { inputFile.getAbsolutePath() });
		Assert.assertEquals(readResourceFile("testMainLargeInput_ExpectedResult.txt"), this.outContent.toString());
	}

	/**
	 * Check the {@link ScheduleOrganizer#main(String[])} for a non-existent
	 * file.
	 * <p>
	 * It compares the error output to the expected result.
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void testMainNonexistentFile() throws URISyntaxException, IOException {
		ScheduleOrganizer.main(new String[] { "nonexistent.txt" });
		Assert.assertEquals("File not found.\n", this.errContent.toString());
	}

	/**
	 * Check the {@link ScheduleOrganizer#main(String[])} for a non-existent
	 * file.
	 * <p>
	 * It compares the error output to the expected result.
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void testMainMalformedInputFiles() throws URISyntaxException, IOException {
		File inputFile = retrieveResourceFile("malformedInput_spaceInDuration.txt");
		ScheduleOrganizer.main(new String[] { inputFile.getAbsolutePath() });
		Assert.assertEquals("Error while trying to read the file.\n", this.errContent.toString());

		inputFile = retrieveResourceFile("malformedInput_typoInLightning.txt");
		ScheduleOrganizer.main(new String[] { inputFile.getAbsolutePath() });
		// This time there're two error messages
		Assert.assertEquals("Error while trying to read the file.\n"
				+ "Error while trying to read the file.\n", this.errContent.toString());
	}

	/**
	 * Assert if the next Talk in iterator has the expected name and
	 * duration
	 * 
	 * @param iterator
	 *            Iterator with the Talks
	 * @param expectedName
	 *            Expected name which to compare
	 * @param expectedDuration
	 *            Expected duration which to compare
	 */
	private void assertSameName(Iterator<Talk> iterator, String expectedName, int expectedDuration) {
		Talk nextTalk = iterator.next();
		Assert.assertEquals(expectedName, nextTalk.getName());
		Assert.assertEquals(expectedDuration, nextTalk.getDuration());
	}

}
