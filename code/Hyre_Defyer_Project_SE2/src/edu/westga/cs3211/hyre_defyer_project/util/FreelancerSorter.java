package edu.westga.cs3211.hyre_defyer_project.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;

/**
 * The sorter that sorts the Freelancers by category
 * 
 * @author Kate Anglin and Jacob
 * @version Spring 2025
 */
public class FreelancerSorter {

	/**
	 * Sorts a list of freelancers by their category.
     * 
     * @precondition freelancers != null
     * @postcondition a new sorted list is returned, original list remains unchanged
     *
     * @param freelancers The list of freelancers to sort
     * @throws IllegalArgumentException if freelancers is null
     */
	public static void sortByCategory(List<Freelancer> freelancers) {
        if (freelancers == null) {
            throw new IllegalArgumentException("Freelancers list cannot be null.");
        }
        
        Collections.sort(freelancers, Comparator.comparing(f -> f.getCategory(), String.CASE_INSENSITIVE_ORDER));
    }
}
