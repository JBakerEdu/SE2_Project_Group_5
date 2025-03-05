package edu.westga.cs3211.hyre_defyer_project.util;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;

/**
 * The sorter that sorts the Freelancers by category
 * 
 * @author Kate Anglin
 * @version Spring 2025
 */

public class FreelancerFilter {
	/**
     * Filters a list of freelancers by selected Categories
     * 
     * @precondition freelancers != null & selectedCategories != null
     * @postcondition a new sorted list is returned, original list remains unchanged
     *
     * @param freelancers The list of freelancers to sort
     * @param selectedCategories the list of categories to show
     * @throws IllegalArgumentException if freelancers is null || selectedCategories is null
     */
    public static List<Freelancer> filterByCategories(List<Freelancer> freelancers, List<Categories> selectedCategories) {
        if (freelancers == null) {
            throw new IllegalArgumentException("Freelancers list cannot be null.");
        }
        if (selectedCategories == null) {
        	throw new IllegalArgumentException("Selected Categories list cannot be null.");
        }
        List<Freelancer> filteredFreelancers = new ArrayList<Freelancer>();
        
        for (Freelancer freelancer : freelancers) {
        	if (selectedCategories.contains(freelancer.getCategory())) {
        		filteredFreelancers.add(freelancer);
        	}
        }
        
        return filteredFreelancers;
    }
}
