package edu.westga.cs3211.hyre_defyer_project.view_model;

import edu.westga.cs3211.hyre_defyer_project.model.User;

/**
 * This is the View Model for the Account Page helping with some base information
 * 
 * @author Jacob Baker
 * @version Spring 2025
 */
public class AccountPageViewModel {
	
	private static User userSelectedToView;

    /**
	 * gets the user that was last set which will allow view to know what user is needed to be viewed
	 * 
	 * @return User that is the account that will be viewed
	 */
    public static User getUserSelectedToView() {
        return userSelectedToView;
    }

    /**
     * Sets the user that will need to be viewed once able 
     * 
     * @param user the user that is being passed in
     */
    public static void setUserSelectedToView(User user) {
        userSelectedToView = user;
    }

}
