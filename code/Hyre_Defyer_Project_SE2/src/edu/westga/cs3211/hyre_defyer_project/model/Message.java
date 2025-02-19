package edu.westga.cs3211.hyre_defyer_project.model;

/** Stores information within a message​
 *​
 * @author Alec Neal​
 * @version Spring 2025​
 */
public class Message {
	private String message;
	private User user1;
	private User user2;
	
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
        this.user1 = sender;
        this.user2 = receiver;
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
	public User getUser1() {
		return this.user1;
	}
	
	/**
	 * Gets one of the users
	 * 
	 * @return the sender
	 */
	public User getUser2() {
		return this.user2;
	}
}
