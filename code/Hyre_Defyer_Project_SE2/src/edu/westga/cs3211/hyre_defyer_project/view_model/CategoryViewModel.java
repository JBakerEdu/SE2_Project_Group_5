package edu.westga.cs3211.hyre_defyer_project.view_model;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
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
    
    public static Categories selectedCategory;
    private List<Freelancer> freelancers;

    /**
     * Initializes the CategoryViewModel with an empty list of freelancers.
     */
    public CategoryViewModel() {
        this.freelancers = new ArrayList<>();
    }

    /**
     * Sets the selected category.
     * 
     * @param category the category to be set
     */
    public void setSelectedCategory(Categories category) {
        selectedCategory = category;
    }

    /**
     * Gets the currently selected category.
     * 
     * @return the selected category
     */
    public Categories getSelectedCategory() {
        return selectedCategory;
    }

    /**
     * Adds a freelancer to the current category.
     * 
     * @param freelancer the freelancer to be added
     */
    public void addPersonToCategory(Freelancer freelancer) {
        if (freelancer.getCategory() == selectedCategory) {
            freelancers.add(freelancer);
        } else {
            throw new IllegalArgumentException(freelancer.getUserName() + " does not belong to this category.");
        }
    }


    /**
     * Gets the list of freelancers in the current category.
     * 
     * @return the list of freelancers in the category
     */
    public List<Freelancer> getFreelancers() {
        return this.freelancers;
    }
}
