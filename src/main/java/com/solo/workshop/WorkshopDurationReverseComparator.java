package com.solo.workshop;

import java.util.Comparator;

/**
 * Implementation of {@link Comparator}, responsible for comparing two instances
 * of {@link Workshop} through their duration, but in the reverse order. That
 * means, the ones with longer duration will come first.
 * 
 * @author marumjr
 */
public class WorkshopDurationReverseComparator implements Comparator<Workshop> {

	@Override
	public int compare(Workshop one, Workshop another) {
		if (one.getDuration() < another.getDuration()) {
			return 1;
		} else if (one.getDuration() > another.getDuration()) {
			return -1;
		}
		return 0;
	}

}
