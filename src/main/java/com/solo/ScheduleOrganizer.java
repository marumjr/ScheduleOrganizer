package com.solo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.solo.workshop.Workshop;

/**
 * The main class (containing the main() method).
 * <p>
 * The schedule organizer must read the input of a file, parse its content
 * searching for workshops' information, organize the schedule for those
 * workshops and present it back to the user.
 * 
 * @author marumjr
 */
public class ScheduleOrganizer {

	/**
	 * Main method.
	 * <p>
	 * It assumes the first argument in args will be a file name, which it will
	 * try to find. If it cannot, a message of <b>File not found.</b> will be
	 * shown.
	 * <p>
	 * If the file doesn't have the proper format, a message showing <b>Error
	 * while trying to read the file.</b> will emerge.
	 * <p>
	 * If everything is OK, then it will parse the file content, organize the
	 * schedule for the present workshops and print said schedule.
	 * 
	 * @param args
	 *            List of arguments. The first argument must be the name of the
	 *            file containing the workshops' names and durations, otherwise
	 *            the program will come to an end with an error message.
	 */
	public static void main(String[] args) {
		try {
			// Assuming the first argument will be a file, and ignoring anything
			// else
			String filename = args[0];
			List<Workshop> workshops = parseInputFile(filename);

			Organizer organizer = new Organizer(workshops);
			organizer.printTracks();

		} catch (FileNotFoundException e) {
			System.err.println("File not found.");

		} catch (IOException e) {
			System.err.println("Error while trying to read the file.");
			
		} catch (NumberFormatException e) {
			System.err.println("Error while trying to read the file.");
		}
	}

	/**
	 * Look for a file called filename, then parse its content in order to
	 * retrieve the information concerning the next workshops
	 * 
	 * @param filename
	 *            The name of the file
	 * @return a list of all the {@link Workshop}s
	 * @throws IOException
	 */
	protected static List<Workshop> parseInputFile(String filename) throws IOException {
		List<Workshop> workshops = new ArrayList<Workshop>();

		// Reading the parameters from the file
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader(filename));
		while ((line = br.readLine()) != null) {
			Workshop workshop = parseWorkshopInfo(line);
			workshops.add(workshop);
		}
		br.close();

		return workshops;
	}

	/**
	 * Parse a text line in search of peaces of workshop information, then
	 * construct said {@link Workshop} with the peaces of information found
	 * 
	 * @param line
	 *            Line of text to be parsed for the workshop information
	 * @return an instance of {@link Workshop}
	 */
	protected static Workshop parseWorkshopInfo(String line) {
		String trimmedLine = line.trim();
		int lastSpace = trimmedLine.lastIndexOf(" ");

		// The first part of the string describes the workshop's name
		String name = trimmedLine.substring(0, lastSpace);
		// The last peace of information in each line is the duration of the
		// event
		String duration = trimmedLine.substring(lastSpace + 1, trimmedLine.length());

		Workshop workshop = new Workshop(name, duration);
		return workshop;
	}

}
