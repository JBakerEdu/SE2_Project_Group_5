package edu.westga.cs3211.hyre_defyer_project.model;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import javafx.beans.binding.ListBinding;
import javafx.beans.binding.ListExpression;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Handles and stores direct message information between two users​
 *​
 * @author Alec Neal​
 * @version Spring 2025​
 */
public class DirectMessageHandler {
	private User sender;
	private User receiver;
	private ArrayList<Message> messageLog;
	private ListProperty<User> contactList;
	private ObservableList<User> observableContactList;

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
        this.contactList = new SimpleListProperty<User>();
        this.observableContactList = FXCollections.observableArrayList(ServerInterface.getMessagableUsers(this.sender));
        this.contactList.setValue(this.observableContactList);
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
	
	/**
	 * Deletes the chat between two users
	 * @param user1 the current user
	 * @param user2 the user associated with the chat the current user wants to delete 
	 */
	public void deleteChat(User user1, User user2) {
		ServerInterface.deleteChat(user1, user2);
		this.updateMessageLog();
		this.updateContactList();
	}
	
	/** Get the List Property contact list for binding
	 * 
	 * @return list property contact list
	 */
	public ListProperty<User> getContactList() {
		return this.contactList;
	}
	
	private void updateContactList() {
		this.contactList.clear();
		this.contactList.addAll(ServerInterface.getMessagableUsers(SignInViewModel.getCurrentUser()));
	}
}
