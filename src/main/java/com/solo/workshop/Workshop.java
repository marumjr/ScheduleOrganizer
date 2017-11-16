package com.solo.workshop;

import com.solo.utils.GlobalConstraints;

/**
 * Object that represents a Workshop, as read in an input file.
 * 
 * @author marumjr
 */
public class Workshop {

	private String name;

	private int duration;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            The workshop's name
	 * @param duration
	 *            The workshop's duration (in minutes)
	 */
	public Workshop(String name, int duration) {
		this.name = name;
		this.duration = duration;
	}

	/**
	 * Alternative constructor.
	 * <p>
	 * This one will parse the String in duration, trying to extract its
	 * information. It could be a lightning workshop (in which case, the String
	 * will be exactly that), or it'll be in a format similar to '45min'
	 * 
	 * @param name
	 *            The workshop's name
	 * @param duration
	 *            A string containing the workshop's duration
	 */
	public Workshop(String name, String duration) {
		this.name = name;

		if (GlobalConstraints.LIGHTNING_STRING.equalsIgnoreCase(duration.trim())) {
			this.duration = GlobalConstraints.LIGHTNING_DURATION_TIME;
		} else {
			String matchResult = duration.trim().replaceAll("^(\\d+)min$", "$1");
			this.duration = Integer.parseInt(matchResult);
		}
	}

	/* ******************************
	 * Getters, Setters and Overriding implementation
	 * ****************************** */

	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return String.format("%s (%d min)", this.name, this.duration);
	}

}
