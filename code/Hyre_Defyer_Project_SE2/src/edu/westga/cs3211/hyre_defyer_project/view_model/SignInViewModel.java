package edu.westga.cs3211.hyre_defyer_project.view_model;

import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * View model for SignInView
 * 
 * @author Myles Debro
 * @version Spring 2025
 */
public class SignInViewModel {
	private static User currentUser;
	
	/**
	 * Creates new SignInViewModel object
	 */
	public SignInViewModel() {
		ServerInterface.createAccount("admin", "1234567");
	}
	
	/**
	 * Sign in the User
	 * @param userName the username of the user
	 * @param userPassword the password associated with said username
	 * @return true if user is successfully signed in
	 * 				 false if user information doesn't match
	 */
	public boolean signIn(String userName, String userPassword) {
		User user = ServerInterface.login(userName, userPassword);
		if (user == null) {
			return false;
		} else {
			currentUser = user;
			return true;
		}
	}
	
	/**
	 * Create and sign in a new account
	 * @param username the username the user set
	 * @param userpassword the password the user set
	 * @param confirmPassword the password the user re-enters
	 * @return true if the account was created
	 * 				 false if the account wasn't created due to duplicate username or if password != confirmPassword
	 */
	public boolean createAccount(String username, String userpassword, String confirmPassword) {
		if (ServerInterface.createAccount(username, userpassword)) {
			this.signIn(username, userpassword);
			return true;
		}
		return false;
	}
	
	/**
	 * Get the current user
	 * @return the current user
	 */
	public static User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Finds out if the user is signed in
	 * @return true if the user is signed in
	 * 				 false if the user isn't signed in
	 */
	public static boolean isSignedIn() {
		return currentUser != null;
	}
	
	/**
	 * Sign out a user by setting the current user to null
	 * @return true user has been set to null
	 */
	public static boolean signOut() {
		currentUser = null;
		return true;
	}
	
	/**
	 * Finds out if the input userName exists in our server
	 * @param username the input userName
	 * @return true if the user exists in the server
	 * 				 false if the user doesn't exist in the server
	 */
	public boolean userExists(String username) {
		for (User currUser : ServerActor.getUsers()) {
			if (currUser.getUserName().equals(username)) {
				return true;
			}
		}
		return false;
	}

}
