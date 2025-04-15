package edu.westga.cs3211.hyre_defyer_project.view_model;

import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.FreelancerRoster;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel for managing categories.
 * 
 * Currently, this class stores the selected category and the freelancers associated with it.
 * 
 * @author Jacob Baker and Kate Anglin
 * @version Spring 2025
 */
public class CategoryPageViewModel {
	
	public static String selectedCategory;
	private static ArrayList<String> selectedSkills;
	private static String selectedName;
	
    /**
     * Clears all static selection state.
     */
    public static void clearSelections() {
        selectedCategory = null;
        selectedSkills = null;
        selectedName = null;
    }

    /**
     * Sets the selected category.
     * 
     * @param category the category to be set
     */
    public static void setSelectedCategory(String category) {
    	String normalizedCategory = category.trim().toUpperCase().replace(" ", "_");
        selectedCategory = normalizedCategory;
    }

    /**
     * Gets the currently selected category.
     * 
     * @return the selected category
     */
    public static String getSelectedCategory() {
        return selectedCategory;
    }
    
    /**
     * Gets the freelancers with the correct category
     * 
     * @return the freelancers with the selected category
     */
    public static List<Freelancer> getFreelancers() {
    	return ServerInterface.getFreelancers().getFreelancersByCategory(CategoryPageViewModel.getSelectedCategory());
    }
    
    /**
     * Gets the freelancers that matches the name and skill specified
     * 
     * @param name the name searching by
     * @param skill the skill searching by
     * @return the freelancers that match
     */
    public static List<Freelancer> getFreelancersWithNameAndSkill(String name, String skill) {
    	List<Freelancer> searched = ServerInterface.getFreelancers().getFreelancersByCategory(CategoryPageViewModel.getSelectedCategory());
    	FreelancerRoster roster = new FreelancerRoster(searched);
    	List<Freelancer> result = roster.getFreelancersByNameAndSkill(name, skill);
    	return result;
    }
    
	/**
	 * gets the selected name for the category sorting
	 * 
	 * @return the selected name passed in
	 */
	public static String getSelectedName() {
		return selectedName;
	}

	/**
	 * sets the selected name for filtering
	 * 
	 * @param selectedName the name typed up sorting by
	 */
	public static void setSelectedName(String selectedName) {
		CategoryPageViewModel.selectedName = selectedName;
	}

	/**
	 * get a list of the selected skills
	 * 
	 * @return a list of skills 
	 */
	public static ArrayList<String> getSelectedSkills() {
		return selectedSkills;
	}

	/**
	 * sets the list of selected skills 
	 * 
	 * @param selectedSkills the list of skills selected in the category list
	 */
	public static void setSelectedSkills(ArrayList<String> selectedSkills) {
		CategoryPageViewModel.selectedSkills = selectedSkills;
	}
}
