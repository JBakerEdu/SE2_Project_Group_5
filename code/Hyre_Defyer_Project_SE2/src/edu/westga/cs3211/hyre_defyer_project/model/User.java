package edu.westga.cs3211.hyre_defyer_project.model;

/** Handles and stores information about a user​
 *​
 * @author Alec Neal​
 * @version Spring 2025​
 */
public class User {
	
	private String userName;
	private String userBio;
	private String password;
	
	/** Creates a user​
	  *​
	  * @precondition none​
	  * @postcondition none
	  * 
	  * @param userName The users name
	  * @param password The users password​​
	  */
	public User(String userName, String password) {
			this.userName = userName; 
			this.password = password;
			this.userBio = "Bio";
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

	/**
	 * Gets the users password
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the users password
	 */
	public String getPassword() {
		return this.password;
	}
}
