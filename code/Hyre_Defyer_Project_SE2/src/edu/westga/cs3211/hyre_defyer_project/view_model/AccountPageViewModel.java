package edu.westga.cs3211.hyre_defyer_project.view_model;

import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.view.GUIHelper;
import edu.westga.cs3211.hyre_defyer_project.view.GUIRosterHelper;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.FreelancerRoster;

/**
 * This is the ViewModel for the Account Page helping with some base information.
 * 
 * @author Jacob Baker
 * @version Spring 2025
 */
public class AccountPageViewModel {
    
    private static User userSelectedToView;

    /**
     * Gets the user that was last set, which will allow the view to know what user is needed to be viewed.
     * 
     * @return User that is the account that will be viewed
     */
    public static User getUserSelectedToView() {
        return userSelectedToView;
    }
    
    /**
     * Gets the FreelancerRoster that is being added to throughout the application.
     * 
     * @return FreelancerRoster that is the list of freelancers
     */
    public static FreelancerRoster getRoster() {
    	GUIRosterHelper helper = new GUIRosterHelper();
        return helper.getFreelancerRoster();
    }

    /**
     * Sets the user that will need to be viewed once able.
     * 
     * @param user the user that is being passed in
     */
    public static void setUserSelectedToView(User user) {
        userSelectedToView = user;
    }

    /**
     * Checks if the selected user is a freelancer by checking the FreelancerRoster.
     * 
     * @return true if the selected user is a freelancer, false otherwise
     */
    public static boolean isSelectedUserFreelancer() {
        if (userSelectedToView != null && userSelectedToView.getUserName() != null) {
        	GUIRosterHelper helper = new GUIRosterHelper();
            for (Freelancer freelancer : AccountPageViewModel.getRoster().getAllFreelancers()) {
                if (freelancer.getUserName().equals(userSelectedToView.getUserName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
