package edu.westga.cs3211.hyre_defyer_project.model;

/** Stores information within a message​
 *​
 * @author Alec Neal​
 * @version Spring 2025​
 */
public class Message {
	private String message;
	private User sender;
	
	/**
	 * Creates a message​ ​
	 * 
	 * @precondition none​
	 * @postcondition none
	 * 
	 * @param message The message
	 * @param sender  The sender​​
	 */
	public Message(String message, User sender) {
		this.message = message;
        this.sender = sender;
    }
	
	/**
	 * Gets the message
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * Gets the sender
	 * 
	 * @return the sender
	 */
	public User getSender() {
		return this.sender;
	}
}
