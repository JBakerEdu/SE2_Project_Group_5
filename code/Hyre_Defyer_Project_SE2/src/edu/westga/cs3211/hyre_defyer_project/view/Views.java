package edu.westga.cs3211.hyre_defyer_project.view;

/**
 * Stores the different view locations
 * 
 * @author Myles Debro
 * @version Spring 2025
 */
public enum Views {
	SIGNIN("view/SignInView.fxml"),
	HOMEPAGE("view/HomePageView.fxml"),
	DMS("view/DirectMessageView.fxml"),
	ACCOUNT("view/AccountPageView.fxml");
	
	private String fileLocation;
	
	Views(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	/**
	 * Get the file location of the enum
	 * @return the file location associated with the enum
	 */
	public String location() {
		return this.fileLocation;
	}
	
}
