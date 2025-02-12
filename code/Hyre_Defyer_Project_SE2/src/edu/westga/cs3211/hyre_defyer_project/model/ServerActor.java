package edu.westga.cs3211.hyre_defyer_project.model;

import java.util.ArrayList;

/**
 * Acts as a temporary interactive server for the application
 * 
 * @author Alec Neal
 * @version Spring 2025
 */
public class ServerActor {
	private ArrayList<Message> messageLog;

	/**
	 * Gets the messages between two users
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param sender   The sender
	 * @param receiver The receiver
	 * 
	 * @return the messages between the two user
	 */
	public ArrayList<Message> getMessagesBetween(User sender, User receiver) {
		return this.messageLog;
	}
	
	/**
	 * Verify login credentials
	 * @param userName the username
	 * @param password the password
	 * @return true if the credentials are valid
	 */
	public Boolean login(String userName, String password) {
		return true;
	}

	/**
	 * Sends a message
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param message  The message
	 * @param receiver The receiver
	 */
	public void sendMessage(Message message, User receiver) {
		this.messageLog.add(message);
    }

	/**
	 * Gets the users biography
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param userName the user name
	 * 
	 * @return the users biography
	 */
	public String getUserBio(String userName) {
		return "This is a bio";
	}
}
