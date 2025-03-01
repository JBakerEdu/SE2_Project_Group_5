package edu.westga.cs3211.hyre_defyer_project.view_model;

import edu.westga.cs3211.hyre_defyer_project.model.User;

/**
 * View model for SignInView
 * 
 * @author Myles Debro
 * @version Spring 2025
 */
public class SignInViewModel {
	
	/**
	 * Sign in the User
	 * @param userName the username of the user
	 * @param userPassword the password associated with said username
	 * @return true if user is successfully signed in
	 * 				 false if user information doesn't match
	 */
	public boolean signIn(String userName, String userPassword) {
		if (userName.equals("hyredefyer_admin") && userPassword.equals("11111")) {
			new User(userName, userPassword);
			return true;
		}
		return false;
	}

}
