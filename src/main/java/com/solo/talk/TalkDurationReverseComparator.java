package com.solo.talk;

import java.util.Comparator;

/**
 * Implementation of {@link Comparator}, responsible for comparing two instances
 * of {@link Talk} through their duration, but in the reverse order. That
 * means, the ones with longer duration will come first.
 * 
 * @author marumjr
 */
public class TalkDurationReverseComparator implements Comparator<Talk> {

	@Override
	public int compare(Talk one, Talk another) {
		if (one.getDuration() < another.getDuration()) {
			return 1;
		} else if (one.getDuration() > another.getDuration()) {
			return -1;
		}
		return 0;
	}

}
