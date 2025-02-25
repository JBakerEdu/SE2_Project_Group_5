package edu.westga.cs3211.hyre_defyer_project.model;

/** Handles and stores information about a user​
 *
 * @author Alec Neal amd Kate Anglin​
 * @version Spring 2025​
 */
public class User {
	
	private String userName;
	private String userBio;
	private String password;

	/** 
	 * Creates a user with a username, password, and biography.
	 * 
	 * @precondition userName != null && !userName.isBlank() && password != null && !password.isBlank() && userBio != null
	 * @postcondition userName, password, and userBio are set
	 * 
	 * @param userName The user's name
	 * @param password The user's password
	 * @param userBio The user's biography
	 * @throws IllegalArgumentException if userName or password is null or empty, or if userBio is null
	 */
	public User(String userName, String password, String userBio) {
		this.setUserName(userName);
		this.setPassword(password);
		this.setBio(userBio);
	}
	
	
	/** 
	 * Creates a user​
	 *​
	 * @precondition userName != null && !userName.isBlank() && password != null && !password.isBlank()
	 * @postcondition userName and password are set, userBio is initialized as an empty string
	 * 
	 * @param userName The user's name
	 * @param password The user's password​​
	 * @throws IllegalArgumentException if userName or password is null or empty
	 */
	public User(String userName, String password) {
		this(userName, password, "");
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

	 /* Sets the user's name.
	 *
	 * @precondition userName != null && !userName.isBlank()
	 * @postcondition this.userName is set
	 * 
	 * @param userName The user's name
	 * @throws IllegalArgumentException if userName is null or empty
	 */
	public void setUserName(String userName) {
		if (userName == null || userName.isBlank()) {
			throw new IllegalArgumentException("User name cannot be empty or null.");
		}
		this.userName = userName;
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

	/**
	 * Sets the user's password.
	 *
	 * @precondition password != null && !password.isBlank()
	 * @postcondition this.password is set
	 * 
	 * @param password The user's password
	 * @throws IllegalArgumentException if password is null or empty
	 */
	public void setPassword(String password) {
		if (password == null || password.isBlank()) {
			throw new IllegalArgumentException("Password cannot be empty or null.");
		}
		this.password = password;
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
	 * Sets the user's biography.
	 *
	 * @precondition bio != null
	 * @postcondition this.userBio is set
	 * 
	 * @param bio The user's biography
	 * @throws IllegalArgumentException if bio is null
	 */
	public void setBio(String bio) {
		if (bio == null) {
			throw new IllegalArgumentException("Bio cannot be null.");
		}
		this.userBio = bio;
	}
}
