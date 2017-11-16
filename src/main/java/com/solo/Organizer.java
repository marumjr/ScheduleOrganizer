package com.solo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.solo.event.TalkEvent;
import com.solo.session.AfternoonSession;
import com.solo.session.MorningSession;
import com.solo.session.Session;
import com.solo.session.SessionFactory;
import com.solo.talk.Talk;
import com.solo.talk.TalkDurationReverseComparator;
import com.solo.track.Track;

/**
 * Object responsible for organizing a list of {@link Talk}s in a proper
 * schedule.
 * 
 * @author marumjr
 */
public class Organizer {

	private List<Track> tracks = new ArrayList<Track>();

	/**
	 * Default constructor for {@link Organizer}.
	 * <p>
	 * One must provide it with the a list of {@link Talk}s for it to
	 * organize.
	 * 
	 * @param talks
	 *            A list of {@link Talk}s to organize
	 */
	public Organizer(List<Talk> talks) {
		Collections.sort(talks, new TalkDurationReverseComparator());
		this.organize(talks);
	}

	/**
	 * Print the content of the {@link Track}s, created during the instantiation
	 * of this object.
	 */
	public void printTracks() {
		for (int i = 0; i < this.tracks.size(); i++) {
			// Print the Track Number
			System.out.println("Track " + (i + 1) + ":");

			// Print the track itself
			Track track = this.tracks.get(i);
			track.printTrack();

			// Insert an enter space for the next track
			System.out.println("");
		}
	}

	/**
	 * Organizes the talks in Tracks.
	 * <p>
	 * It organizes the schedules using a 'Best Fit' algorithm, where a Talk
	 * is added to a Track if and only if it will leave the least amount of
	 * available time among all the available tracks.
	 * 
	 * @param talks
	 *            List of talks to organize in tracks
	 */
	private void organize(List<Talk> talks) {
		// Initialize our SessionFactory
		SessionFactory sessionFactory = new SessionFactory();

		List<Session> sessions = new ArrayList<Session>();
		for (Talk talk : talks) {
			int duration = talk.getDuration();
			int bestFitIndex = -1;
			int minDurationLeft = Integer.MAX_VALUE;

			// Look for the best fit
			for (int i = 0; i < sessions.size(); i++) {
				Session session = sessions.get(i);

				int durationLeft = session.calculateDurationUsage(duration);
				if (durationLeft >= 0 && durationLeft < minDurationLeft) {
					// Found a new best fit
					minDurationLeft = durationLeft;
					bestFitIndex = i;
				}
			}

			// Create a new Session, or retrieve the best fit session
			Session session;
			if (bestFitIndex == -1) {
				// use our SessionFactory to create the new instance
				session = sessionFactory.createNextSession();
				sessions.add(session);
			} else {
				session = sessions.get(bestFitIndex);
			}
			// Add event to the session
			TalkEvent event = new TalkEvent(talk);
			session.addEvent(event);
		}

		// Now we create the Tracks from the group of Sessions
		List<Track> tracks = new ArrayList<Track>();
		for (int i = 0; i < sessions.size(); i += 2) { // sessions come in pairs
			// First session is always a MorningSession
			MorningSession morningSession = (MorningSession) sessions.get(i);

			// There can be an Afternoon Session (or not)
			AfternoonSession afternoonSession = null;
			if (i + 1 < sessions.size()) {
				afternoonSession = (AfternoonSession) sessions.get(i + 1);
			}

			// Creating a new track for the pair of sessions
			Track track = new Track(morningSession, afternoonSession);
			tracks.add(track);
		}

		this.tracks = tracks;
	}

}
