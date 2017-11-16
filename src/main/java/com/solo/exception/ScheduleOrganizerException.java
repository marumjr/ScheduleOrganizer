package com.solo.exception;

/**
 * Exception thrown by the classes in the project
 * 
 * @author marumjr
 */
public class ScheduleOrganizerException extends Exception {

	private static final long serialVersionUID = -766642687213075448L;

	/**
	 * Constructs a new exception with the specified detail message
	 * 
	 * @param message
	 *            Message carried out by the Exception
	 */
	public ScheduleOrganizerException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified detail message, formatting
	 * the message with the arguments given
	 * 
	 * @param message
	 *            Message carried out by the Exception
	 * @param args
	 *            Arguments to be set in the message
	 */
	public ScheduleOrganizerException(String message, Object... args) {
		super(String.format(message, args));
	}

	/**
	 * Constructs a new exception with the specified detail message and cause
	 * 
	 * @param cause
	 *            The cause of the problem
	 * @param message
	 *            Message carried out by the Exception
	 */
	public ScheduleOrganizerException(Throwable cause, String message) {
		super(message, cause);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause,
	 * formatting the message with the arguments given
	 * 
	 * @param cause
	 *            The cause of the problem
	 * @param message
	 *            Message carried out by the Exception
	 * @param args
	 *            Arguments to be set in the message
	 */
	public ScheduleOrganizerException(Throwable cause, String message, Object... args) {
		super(String.format(message, args), cause);
	}

}
