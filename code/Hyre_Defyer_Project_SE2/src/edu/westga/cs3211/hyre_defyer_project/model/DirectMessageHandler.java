package edu.westga.cs3211.hyre_defyer_project.model;

import java.util.ArrayList;

import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;

/** Handles and stores direct message information between two users​
 *​
 * @author Alec Neal​
 * @version Spring 2025​
 */
public class DirectMessageHandler {
	private User sender;
	private User receiver;
	private ArrayList<Message> messageLog;

	/** Creates a user with ​
	  *​
	  * @precondition none​
	  * @postcondition none
	  * 
	  * @param sender The sender
	  * @param receiver The receiver​​
	  */
	public DirectMessageHandler(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.updateMessageLog();
    }

	private void updateMessageLog() {
		ArrayList<Message> fullMessageLog = new ArrayList<Message>(ServerInterface.getMessagesBetween(this.sender, this.receiver));
        this.messageLog = fullMessageLog;
    }
	
	/** Sends a direct message to the receiver​
	  *​
	  * @precondition none​
	  * @postcondition this.fullMessageLog.size()@prev == this.fullMessageLog.size() - 1
	  * 
	  * @param message the message to send​​
	  */
	public void sendMessage(Message message) {
		ServerInterface.sendMessage(message);
		this.updateMessageLog();
	}
	
	/**
	 * Gets the Full Message Log
	 *
	 * @precondition none
	 * @return the full message log
	 */
	public ArrayList<Message> getFullMessageLog() {
		this.updateMessageLog();
		return this.messageLog;
	}
}
