package com.solo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;

/**
 * Super class for those tests where the Output is too large, so we need to
 * extract the expected results from a file.
 * <p>
 * This class does also reconfigures the standard output to a field called
 * <b>outContent</b>, so that one can easily access the system output
 * 
 * @author marumjr
 */
public abstract class LargeOutputTest {

	/** The content of the standard system output */
	protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	/** The content of the standard error output */
	protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	/**
	 * Method called before every test
	 * <p>
	 * It sets the standard system output to the <b>outContent</b> field
	 */
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(this.outContent));
		System.setErr(new PrintStream(this.errContent));
	}

	/**
	 * Method called after every test.
	 * <p>
	 * It sets the standard system output back to its initial state
	 */
	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

	/**
	 * Retrieve the resource file
	 * 
	 * @param filename
	 *            Name of the resource file to retrieve
	 * @return The resource file
	 * @throws URISyntaxException
	 */
	protected File retrieveResourceFile(String filename) throws URISyntaxException {
		String pkgName = this.getClass().getPackage().getName().replace(".", "/") + "/";
		URI uri = this.getClass().getClassLoader().getResource(pkgName + filename).toURI();
		File file = new File(uri);

		return file;
	}

	/**
	 * Reads all the content of a resource file into a single String
	 * 
	 * @param filename
	 *            Name of the file to read
	 * @return String with all the content of said file
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	protected String readResourceFile(String filename) throws IOException, URISyntaxException {
		File file = retrieveResourceFile(filename);
		byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

		return new String(encoded);
	}

}
