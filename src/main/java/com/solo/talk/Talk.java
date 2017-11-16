package com.solo.talk;

import com.solo.utils.GlobalConstraints;

/**
 * Object that represents a Talk, as read in an input file.
 * 
 * @author marumjr
 */
public class Talk {

	private String name;

	private int duration;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            The talk's name
	 * @param duration
	 *            The talk's duration (in minutes)
	 */
	public Talk(String name, int duration) {
		this.name = name;
		this.duration = duration;
	}

	/**
	 * Alternative constructor.
	 * <p>
	 * This one will parse the String in duration, trying to extract its
	 * information. It could be a lightning talk (in which case, the String
	 * will be exactly that), or it'll be in a format similar to '45min'
	 * 
	 * @param name
	 *            The talk's name
	 * @param duration
	 *            A string containing the talk's duration
	 */
	public Talk(String name, String duration) {
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
