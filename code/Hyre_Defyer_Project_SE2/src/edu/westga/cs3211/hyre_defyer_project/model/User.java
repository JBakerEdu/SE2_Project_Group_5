package edu.westga.cs3211.hyre_defyer_project.model;

/** Handles and stores information about a user​
 *​
 * @author Alec Neal​
 * @version Spring 2025​
 */
public class User {
	
	private String userName;
	private String userBio;
	private ServerActor server;
	
	/** Creates a user​
	  *​
	  * @precondition none​
	  * @postcondition none
	  * 
	  * @param userName The users name
	  * @param password The users password
	  * 
	  * @throws IllegalArgumentException with wrong credentials​​
	  */
	public User(String userName, String password) {
		if (this.server.login(userName, password)) { 
			this.userName = userName; 
			this.userBio = this.server.getUserBio(userName);
		} else { 
			throw new IllegalArgumentException("Invalid login credentials"); 
		}	
	}
	
	/**
	 * Gets the users name
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the users name
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * Gets the users biography
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the users name
	 */
	public String getBio() {
		return this.userBio;
	}
}
