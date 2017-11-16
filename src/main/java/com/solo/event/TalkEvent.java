package com.solo.event;

import com.solo.talk.Talk;
import com.solo.utils.GlobalConstraints;

/**
 * Subclass of {@link Event}, representing a scheduled workshop.
 * 
 * @author marumjr
 */
public class TalkEvent extends Event {

	/**
	 * Default constructor.
	 * <p>
	 * It builds this object according to the content of a {@link Talk}.
	 * 
	 * @param workshop
	 *            A {@link Talk} containing informations to build this
	 *            object
	 */
	public TalkEvent(Talk workshop) {
		this.name = workshop.getName();
		this.duration = workshop.getDuration();
	}

	/**
	 * Format the duration to be display, since it can be lightning or a number,
	 * in which case it's shown in this similar format: '45min'
	 * 
	 * @return the formatted duration
	 */
	private String getFormattedDuration() {
		if (getDuration() == GlobalConstraints.LIGHTNING_DURATION_TIME) {
			return GlobalConstraints.LIGHTNING_STRING;
		}

		return this.getDuration() + "min";
	}

	@Override
	protected String formatToDisplay() {
		String display = this.getFormattedScheduledTime() + " " + this.getName() + " " + this.getFormattedDuration();
		return display;
	}

}
