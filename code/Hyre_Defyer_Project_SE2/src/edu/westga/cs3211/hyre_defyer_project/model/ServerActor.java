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
	private ArrayList<User> users;

	/**
	 * Gets the messages between two users
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param sender   The sender
	 * @param receiver The receiver
	 * 
	 * @return the messages between the two users
	 */
	public ArrayList<Message> getMessagesBetween(User sender, User receiver) {
		return this.messageLog;
	}
	
	/**
	 * Verify login credentials
	 * 
	 * @param userName the username
	 * @param password the password
	 * 
	 * @return the user object itself, null if the login credentials are invalid
	 */
	public User login(String userName, String password) {
		for (User user : this.users) {
			if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	private Boolean isDuplicateUsername(String userName) {
		for (User user : this.users) {
			if (user.getUserName().equals(userName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Creates an account
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param userName the username
	 * @param password the password
	 * 
	 * @return true if the account was created, false if duplicate username
     */
	public Boolean createAccount(String userName, String password) {
		if (this.isDuplicateUsername(userName)) {
			return false;
		}
		return this.users.add(new User(userName, password));
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
}
