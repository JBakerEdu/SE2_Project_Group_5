package edu.westga.cs3211.hyre_defyer_project.model;

/** Stores information within a message​
 *​
 * @author Alec Neal​
 * @version Spring 2025​
 */
public class Message {
	private String message;
	private User sender;
	private User receiver;
	
	/**
	 * Creates a message​ ​
	 * 
	 * @precondition none​
	 * @postcondition none
	 * 
	 * @param message The message
	 * @param sender  The sender
	 * @param receiver  The receiver​​
	 */
	public Message(String message, User sender, User receiver) {
		this.message = message;
        this.sender = sender;
        this.receiver = receiver;
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
	 * Gets one of the users
	 * 
	 * @return the sender
	 */
	public User getSender() {
		return this.sender;
	}
	
	/**
	 * Gets one of the users
	 * 
	 * @return the sender
	 */
	public User getReceiver() {
		return this.receiver;
	}
	
	@Override
	public String toString() {
		return this.sender + ": " + this.message;
	}
}
