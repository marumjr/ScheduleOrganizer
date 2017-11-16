package com.solo.session;

import java.util.Date;

import com.solo.utils.DateUtils;
import com.solo.utils.GlobalConstraints;

/**
 * Subclass of {@link Session}, representing the afternoon time.
 * 
 * @author marumjr
 */
public class AfternoonSession extends Session {

	@Override
	public Date getInitialTime() {
		return DateUtils.createDate(GlobalConstraints.AFTERNOON_SESSION_START_HOUR, 0);
	}

	@Override
	public int getMaxDurationInMinutes() {
		// It takes an amount of time equals to when it starts until the latest
		// possible time for the Meet Your Colleagues Event
		int durationInHours = GlobalConstraints.MEET_COLLEAGUES_LATEST_START_HOUR
				- GlobalConstraints.AFTERNOON_SESSION_START_HOUR;
		int durationInMinutes = durationInHours * 60; // minutes to hours
		return durationInMinutes;
	}

}
