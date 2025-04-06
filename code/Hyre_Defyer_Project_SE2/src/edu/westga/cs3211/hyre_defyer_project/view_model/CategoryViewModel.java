package edu.westga.cs3211.hyre_defyer_project.view_model;

import edu.westga.cs3211.hyre_defyer_project.view.GUIRosterHelper;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel for managing categories.
 * 
 * Currently, this class stores the selected category and the freelancers associated with it.
 * 
 * @author Jacob Baker
 * @version Spring 2025
 */
public class CategoryViewModel {
	
	public static String selectedCategory;
	
	private GUIRosterHelper helper = new GUIRosterHelper();
	private List<Freelancer> freelancers;
	
    /**
     * Initializes the CategoryViewModel with an empty list of freelancers.
     */
    public CategoryViewModel() {
        this.freelancers = new ArrayList<>();
    }

    /**
     * The GUI Roster Helper
     * 
     * @return the helper
     */
    public GUIRosterHelper getHelper() {
		return this.helper;
	}

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
     * Adds a freelancer to the current category.
     * 
     * @param freelancer the freelancer to be added
     */
    public void addPersonToCategory(Freelancer freelancer) {
        if (freelancer.getCategory() == selectedCategory) {
        	this.freelancers.add(freelancer);
        } else {
            throw new IllegalArgumentException(freelancer.getUserName() + " does not belong to this category.");
        }
    }
}
