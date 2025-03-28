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
	private static final String SKILL_CANNOT_BE_BLANK = "Skill cannot be blank.";
	private static final String SKILL_CANNOT_BE_NULL = "Skill cannot be null.";
	
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
     * @param category The category to filter by
     * @return a list of freelancers in the given category
     * @throws IllegalArgumentException if category is null
     */
    public List<Freelancer> getFreelancersByCategory(Categories category) {
        if (category == null) {
            throw new IllegalArgumentException(CATEGORY_CANNOT_BE_NULL);
        }
        List<Freelancer> result = new ArrayList<>();
        for (Freelancer freelancer : this.freelancers) {
            if (freelancer.getCategory().equals(category)) {
                result.add(freelancer);
            }
        }
        return result;
    }
    
    /**
     * Retrieves a list of freelancers who have a specific skill, ignoring case and allowing partial matches.
     *
     * @precondition skill != null && !skill.isBlank()
     * @postcondition none
     *
     * @param skill The skill to filter by
     * @return a list of freelancers with the given skill
     * @throws IllegalArgumentException if skill is null
     */
    public List<Freelancer> getFreelancersBySkill(String skill) {
        if (skill == null) {
            throw new IllegalArgumentException(SKILL_CANNOT_BE_NULL);
        } else if (skill.isBlank()) {
        	throw new IllegalArgumentException(SKILL_CANNOT_BE_BLANK);
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
