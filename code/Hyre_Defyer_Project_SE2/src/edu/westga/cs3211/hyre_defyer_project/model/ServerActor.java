package edu.westga.cs3211.hyre_defyer_project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts as a temporary interactive server for the application
 * 
 * @author Alec Neal
 * @version Spring 2025
 */
public class ServerActor {
	private static ArrayList<ArrayList<Message>> godMessageLog = new ArrayList<ArrayList<Message>>();
	private static ArrayList<User> users = new ArrayList<User>();
	
	/**
	 * Instantiates a new ServerActor
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public ServerActor() {
		
	}

	/**
	 * Gets the messages between two users
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param sender   The sender
	 * @param receiver The receiver
	 * 
	 * @return the messages between the two users, empty list if no messages
	 */
	public static ArrayList<Message> getMessagesBetween(User sender, User receiver) {
		for (ArrayList<Message> messageLog : godMessageLog) {
			if (messageLog.get(0).getUser1().equals(sender) && messageLog.get(0).getUser2().equals(receiver)) {
				return messageLog;
			}
			if (messageLog.get(0).getUser1().equals(receiver) && messageLog.get(0).getUser2().equals(sender)) {
				return messageLog;
			}
		}
		ArrayList<Message> newMessageLog = new ArrayList<Message>();
		newMessageLog.add(new Message("TEMP", sender, receiver));
		godMessageLog.add(newMessageLog);
		return newMessageLog;
	}
	
	/**
	 * Verify login credentials
	 * 
	 * @param userName the username
	 * @param password the password
	 * 
	 * @return the user object itself, null if the login credentials are invalid
	 */
	public static User login(String userName, String password) {
		for (User user : users) {
			if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	private static Boolean isDuplicateUsername(String userName) {
		for (User user : users) {
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
	public static Boolean createAccount(String userName, String password) {
		if (isDuplicateUsername(userName)) {
			return false;
		}
		return users.add(new User(userName, password));
	}

	/**
	 * Sends a message
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param message  The message
	 */
	public static void sendMessage(Message message) {
		User sender = message.getUser1();
		User receiver = message.getUser2();
		ArrayList<Message> messageLog = getMessagesBetween(sender, receiver);
		messageLog.add(message);
		if (messageLog.get(0).getMessage().equals("TEMP")) {
			getMessagesBetween(receiver, sender).remove(0);
		}
    }
	
	public static List<User> getUsers() {
		return users;
	}
}
