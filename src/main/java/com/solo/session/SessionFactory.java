package com.solo.session;

/**
 * Factory for {@link Session}s.
 * <p>
 * The first instance is always going to be a <b>{@link MorningSession}</b>, but
 * from then on, the sessions should come always in pairs. As such, every time a
 * new instance for a session is invoked to this class, it will create the
 * remaining pair.
 * 
 * @author marumjr
 */
public class SessionFactory {

	private Session nextSession = new MorningSession();

	/**
	 * Constructor, which initializes the first instance to return as the
	 * {@link MorningSession}.
	 */
	public SessionFactory() {
		this.nextSession = new MorningSession();
	}

	/**
	 * Instantiates a new {@link Session} according to the following rule:
	 * <ul>
	 * <li>The first use of this method will return a
	 * {@link MorningSession};</li>
	 * <li>It will then check to which class belonged the last instance and it
	 * will create the other one.</li>
	 * </ul>
	 * 
	 * @return an instance of a {@link Session}
	 */
	public Session createNextSession() {
		// This is the one we'll return
		Session currentSession = nextSession;

		// Now we instantiate the next one, alternating between the two possible
		// session types
		if (MorningSession.class.isAssignableFrom(this.nextSession.getClass())) {
			this.nextSession = new AfternoonSession();
		} else {
			this.nextSession = new MorningSession();
		}

		return currentSession;
	}

}
