package edu.westga.cs3211.hyre_defyer_project.model;

import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;

/**
 * Helper class for methods that the roster will need
 * 
 * @author Myles Debro and Jacob Baker and Kate Anglin
 * @version Spring 2025
 */
public class RosterHelper {	
	/**
	 * get the roster that is static and saved here for all the UI to be able to get the same roster list
	 * 
	 * @return freelancerRoster which is the roster that is saved in the GUIHelper
	 */
	public static FreelancerRoster getFreelancerRoster() {
		return ServerInterface.getFreelancers();
	}
	
	/**
	 * Add freelancer to server
	 * 
	 * @param freelancer the freelancer added
	 * @return true if added; false otherwise
	 */
	public static boolean addFreelancerToServer(Freelancer freelancer) {
		return ServerInterface.addFreelancer(freelancer);
	}
	
	/**
	 * Edits the freelancer with the before state and becomes the after state
	 * 
	 * @param before the freelancer before
	 * @param after the freelancer after
	 */
	public static void editFreelancerToServer(Freelancer before, Freelancer after) {
		ServerInterface.removeFreelancer(before);
		ServerInterface.addFreelancer(after);
	}
	
	/**
	 * Removes the freelancer from the server
	 * 
	 * @param freelancer to be removed
	 * @return true if removed; false otherwise
	 */
	public static boolean removeFreelancerFromServer(Freelancer freelancer) {
		return ServerInterface.removeFreelancer(freelancer);
	}
}
