package edu.westga.cs3211.hyre_defyer_project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Roster that will hold all Freelancers
 * 
 * @author Kate Anglin and Jacob Baker
 * @version Spring 2025
 */
public class FreelancerRoster {
	
	private static final String FREELANCER_LIST_CANNOT_BE_NULL = "Freelancer list cannot be null.";
	private static final String FREELANCER_CANNOT_BE_NULL = "Freelancer cannot be null.";
	private static final String CATEGORY_CANNOT_BE_NULL = "Category cannot be null.";
	
	private List<Freelancer> freelancers;
    
    /**
     * Constructs a FreelancerRoster with some example freelancers.
     *
     * @precondition none
     * @postcondition a roster is initialized with example freelancers
     */
    public FreelancerRoster() {
        this.freelancers = new ArrayList<>();
    }
    
    /**
     * Constructs a FreelancerRoster with a given list of freelancers.
     *
     * @precondition freelancers != null
     * @postcondition the roster is initialized with the given freelancers
     *
     * @param freelancers The list of freelancers to initialize the roster with
     * @throws IllegalArgumentException if freelancers is null
     */
    public FreelancerRoster(List<Freelancer> freelancers) {
        if (freelancers == null) {
            throw new IllegalArgumentException(FREELANCER_LIST_CANNOT_BE_NULL);
        }
        this.freelancers = new ArrayList<>(freelancers);
    }
    
    /**
     * Adds a freelancer to the roster.
     *
     * @precondition freelancer != null
     * @postcondition freelancer is added to the roster
     *
     * @param freelancer The freelancer to add
     * @throws IllegalArgumentException if freelancer is null
     */
    public void addFreelancer(Freelancer freelancer) {
        if (freelancer == null) {
            throw new IllegalArgumentException(FREELANCER_CANNOT_BE_NULL);
        }
        this.freelancers.add(freelancer);
    }
    
    /**
     * Removes a freelancer from the roster.
     *
     * @precondition freelancer != null
     * @postcondition if freelancer exists, it is removed from the roster
     *
     * @param freelancer The freelancer to remove
     * @return true if removed, false otherwise
     * @throws IllegalArgumentException if freelancer is null
     */
    public boolean removeFreelancer(Freelancer freelancer) {
        if (freelancer == null) {
            throw new IllegalArgumentException(FREELANCER_CANNOT_BE_NULL);
        }
        return this.freelancers.remove(freelancer);
    }
    
    /**
     * Retrieves a list of freelancers in a specific category.
     *
     * @precondition category != null
     * @postcondition none
     *
     * @param catagory The category to filter by
     * @return a list of freelancers in the given category
     * @throws IllegalArgumentException if category is null
     */
    public List<Freelancer> getFreelancersByCategory(String catagory) {
        if (catagory == null) {
            throw new IllegalArgumentException(CATEGORY_CANNOT_BE_NULL);
        }
        List<Freelancer> result = new ArrayList<>();
        for (Freelancer freelancer : this.freelancers) {
            if (freelancer.getCategory().equals(catagory)) {
                result.add(freelancer);
            }
        }
        return result;
    }
    
    /**
     * Retrieves a list of freelancers who have a specific skill, ignoring case and allowing partial matches.
     *
     * @precondition none
     * @postcondition none
     *
     * @param skill The skill to filter by
     * @return a list of freelancers with the given skill
     */
    public List<Freelancer> getFreelancersBySkill(String skill) {
        if ((skill == null) || (skill.isBlank())) {
        	return this.getAllFreelancers();
        }
        
        List<Freelancer> result = new ArrayList<>();
        String skillLower = skill.toLowerCase();
        
        for (Freelancer freelancer : this.freelancers) {
            for (String freelancerSkill : freelancer.getSkills()) {
                if (freelancerSkill.toLowerCase().contains(skillLower)) {
                    result.add(freelancer);
                    break;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Gets the freelancers with the specified name
     * 
     * @param name the name to search by
     * @return the freelancers matching the name
     */
    public List<Freelancer> getFreelancersByName(String name) {
        if ((name == null) || (name.isBlank())) {
        	return this.getAllFreelancers();
        }
        
        List<Freelancer> result = new ArrayList<>();
        String nameLower = name.toLowerCase();
        
        for (Freelancer freelancer : this.freelancers) {
        	if (freelancer.getUserName().toLowerCase().contains(nameLower)) {
        	    result.add(freelancer);
        	}
        }
        
        return result;
    }
    
    /**
     * Gets the freelancers with the name and skill
     * 
     * @param name the name to search by
     * @param skill the skill to search by
     * @return the freelancers matching the name and skill
     */
    public List<Freelancer> getFreelancersByNameAndSkill(String name, String skill) {
    	List<Freelancer> freelancersByName = this.getFreelancersByName(name);
    	List<Freelancer> freelancersBySkill = this.getFreelancersBySkill(skill);
        List<Freelancer> result = new ArrayList<>();
        
        for (Freelancer freelancer : freelancersByName) {
        	if (freelancersBySkill.contains(freelancer)) {
        		result.add(freelancer);
        	}
        }
        
        return result;
	}
    
    /**
     * Retrieves all freelancers in the roster.
     *
     * @precondition none
     * @postcondition none
     *
     * @return a list of all freelancers
     */
    public List<Freelancer> getAllFreelancers() {
        return this.freelancers;
    }

}
