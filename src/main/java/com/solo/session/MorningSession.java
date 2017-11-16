package com.solo.session;

import java.util.Date;

import com.solo.utils.DateUtils;
import com.solo.utils.GlobalConstraints;

/**
 * Subclass of {@link Session}, representing the morning time.
 * 
 * @author marumjr
 */
public class MorningSession extends Session {

	@Override
	public Date getInitialTime() {
		return DateUtils.createDate(GlobalConstraints.MORNING_SESSION_START_HOUR, 0);
	}

	@Override
	public int getMaxDurationInMinutes() {
		// It takes an amount of time equals to the time between it starts and
		// lunch hour
		int durationInHours = GlobalConstraints.LUNCH_START_HOUR - GlobalConstraints.MORNING_SESSION_START_HOUR;
		int durationInMinutes = durationInHours * 60; // minutes to hours
		return durationInMinutes;
	}

}
