package edu.westga.cs3211.hyre_defyer_project.view_model;

import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.FreelancerRoster;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;

import java.util.List;

/**
 * ViewModel for managing categories.
 * 
 * Currently, this class stores the selected category and the freelancers associated with it.
 * 
 * @author Jacob Baker and Kate Anglin
 * @version Spring 2025
 */
public class CategoryViewModel {
	
	public static String selectedCategory;

    /**
     * Sets the selected category.
     * 
     * @param category the category to be set
     */
    public void setSelectedCategory(String category) {
    	String normalizedCategory = category.trim().toUpperCase().replace(" ", "_");
        selectedCategory = normalizedCategory;
    }

    /**
     * Gets the currently selected category.
     * 
     * @return the selected category
     */
    public String getSelectedCategory() {
        return selectedCategory;
    }
    
    /**
     * Gets the freelancers with the correct category
     * 
     * @return the freelancers with the selected category
     */
    public static List<Freelancer> getFreelancers() {
    	return ServerInterface.getFreelancers().getFreelancersByCategory(selectedCategory);
    }
    
    /**
     * Gets the freelancers that matches the name and skill specified
     * 
     * @param name the name searching by
     * @param skill the skill searching by
     * @return the freelancers that match
     */
    public static List<Freelancer> getFreelancersWithNameAndSkill(String name, String skill) {
    	List<Freelancer> searched = ServerInterface.getFreelancers().getFreelancersByCategory(selectedCategory);
    	FreelancerRoster roster = new FreelancerRoster(searched);
    	List<Freelancer> result = roster.getFreelancersByNameAndSkill(name, skill);
    	return result;
    }
}
