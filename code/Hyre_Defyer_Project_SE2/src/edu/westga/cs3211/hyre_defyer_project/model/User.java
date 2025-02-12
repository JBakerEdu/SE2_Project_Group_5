package edu.westga.cs3211.hyre_defyer_project.model;

/**
 * The User Class for our application
 * 
 * @author Kate Anglin
 */
public class User {
	
	private boolean loggedIn;

	/**
	 * Creates a new user that is not currently logged in
	 */
	public User() {
		this.setLoggedIn(false);
	}
	
	/**
	 * Creates a new User and shows if they are logged in
	 * 
	 * @param loggedIn true if logged in; false otherwise
	 */
	public User(boolean loggedIn) {
		this.setLoggedIn(loggedIn);
	}

	/**
	 * Gets if the user is logged in
	 * 
	 * @return true if logged in; false otherwise
	 */
	public boolean isLoggedIn() {
		return this.loggedIn;
	}

	/**
	 * Sets the logged in value 
	 * 
	 * @param loggedIn
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
