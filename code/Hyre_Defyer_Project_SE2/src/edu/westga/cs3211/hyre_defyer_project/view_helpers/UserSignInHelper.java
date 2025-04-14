package edu.westga.cs3211.hyre_defyer_project.view_helpers;

import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;

/**
 * View model for SignInView
 * 
 * @author Myles Debro
 * @version Spring 2025
 */
public class UserSignInHelper {
	private static User currentUser;
	
	/**
	 * Creates new SignInViewModel object
	 */
	public UserSignInHelper() {
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
			//TEMPORARY: REMOVE LATER
			//WILL ADD FUNCTIONALITY TO ADD USERS TO DM LIST DIRECTLY, THIS IS FOR TESTING ADMIN TO USER MESSAGES
			User admin = new User("admin");
			ServerInterface.addMessageableUser(currentUser, admin);
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

}
