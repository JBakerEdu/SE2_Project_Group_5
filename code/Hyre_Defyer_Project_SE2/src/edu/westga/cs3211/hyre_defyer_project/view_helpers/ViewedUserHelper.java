package edu.westga.cs3211.hyre_defyer_project.view_helpers;

import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.model.RosterHelper;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.FreelancerRoster;

/**
 * This is the ViewModel for the Account Page helping with some base information.
 * 
 * @author Jacob Baker and Kate Anglin
 * @version Spring 2025
 */
public class ViewedUserHelper {
    
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
        return RosterHelper.getFreelancerRoster();
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
        if (userSelectedToView != null) {
            for (Freelancer freelancer : ViewedUserHelper.getRoster().getAllFreelancers()) {
                if (freelancer.getUserName().equals(userSelectedToView.getUserName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
