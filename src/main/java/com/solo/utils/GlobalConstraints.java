package com.solo.utils;

/**
 * All the global constraints that might be used
 * 
 * @author marumjr
 */
public class GlobalConstraints {
	
	/** String corresponding to the lightning time of events */
	public static final String LIGHTNING_STRING = "lightning";
	
	/** Amount of minutes that a lightning event takes */
	public static final int LIGHTNING_DURATION_TIME = 5;

	/** Hour time when the morning sessions start */
	public static final int MORNING_SESSION_START_HOUR = 9;

	/** Hour time when the lunch starts */
	public static final int LUNCH_START_HOUR = 12;

	/** Hour time when the morning sessions end */
	public static final int MORNING_SESSION_END_HOUR = LUNCH_START_HOUR;

	/** Hour time when the afternoon sessions start */
	public static final int AFTERNOON_SESSION_START_HOUR = 13;

	/** Earliest hour time when the Meet Your Colleague Event starts */
	public static final int MEET_COLLEAGUES_EARLIEST_START_HOUR = 16;

	/** Latest hour time when the Meet Your Colleague Event starts */
	public static final int MEET_COLLEAGUES_LATEST_START_HOUR = 17;

}
