package edu.westga.cs3211.hyre_defyer_project.server;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import edu.westga.cs3211.hyre_defyer_project.model.Message;
import edu.westga.cs3211.hyre_defyer_project.model.User;

/**
 * Acts as a temporary interactive server for the application
 * 
 * @author Alec Neal
 * @version Spring 2025
 */
public class ServerInterface {
	private static ArrayList<ArrayList<Message>> godMessageLog = new ArrayList<ArrayList<Message>>();
	
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
			if (messageLog.get(0).getSender().equals(sender) && messageLog.get(0).getReceiver().equals(receiver)) {
				return messageLog;
			}
			if (messageLog.get(0).getSender().equals(receiver) && messageLog.get(0).getReceiver().equals(sender)) {
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
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_LOGIN);
		request.put(Constants.REQ_USERNAME, userName);
		request.put(Constants.REQ_PASSWORD, password);
		String response = ServerCommunicator.sendRequestToServer(request);
		JSONObject jsonObject = new JSONObject(response);
		String successCode = jsonObject.getString(Constants.SUCCESS_CODE);
		if (successCode.equals(Constants.REP_SUCCESS)) {
            String bio = jsonObject.getString(Constants.REQ_BIO);
            String username = jsonObject.getString(Constants.REQ_USERNAME);
            return new User(username, bio);
		}
		return null; 
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
		JSONObject request = new JSONObject();
		request.put(Constants.REQ_TYPE, Constants.REQ_CREATE_ACCOUNT);
		request.put(Constants.REQ_USERNAME, userName);
		request.put(Constants.REQ_PASSWORD, password);
		String response = ServerCommunicator.sendRequestToServer(request);
		JSONObject jsonObject = new JSONObject(response);
		String successCode = jsonObject.getString(Constants.SUCCESS_CODE);
		return successCode.equals(Constants.REP_SUCCESS);
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
		User sender = message.getSender();
		User receiver = message.getReceiver();
		ArrayList<Message> messageLog = getMessagesBetween(sender, receiver);
		messageLog.add(message);
		if (messageLog.get(0).getMessage().equals("TEMP")) {
			getMessagesBetween(receiver, sender).remove(0);
		}
    }
	
	public static List<User> getUsers() {
		//TODO
		return null;
	}
}
