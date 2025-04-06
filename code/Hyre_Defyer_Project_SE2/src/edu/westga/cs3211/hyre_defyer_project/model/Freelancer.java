package edu.westga.cs3211.hyre_defyer_project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Freelancer class stores the category selected and the skills listed.
 * 
 * @author Kate Anglin
 * @version Spring 2025
 */
public class Freelancer extends User {
	
	private static final String SKILL_CANNOT_BE_NULL = "Skill cannot be null.";
	private static final String SKILLS_CANNOT_BE_NULL = "Skills cannot be null.";
	private static final String OLD_SKILL_NOT_FOUND = "Old skill not found.";
	private static final String CATEGORIES_CANNOT_BE_NULL = "Categories cannot be null.";
	
	private List<String> categories;
    private List<String> skills;
	
    /**
     * Creates a freelancer with a username, password, biography, and categories.
     *
     * @precondition userName != null && !userName.isBlank() && userBio != null && categories != null
     * @postcondition userName, userBio, and categories are set; skills is initialized as an empty list
     *
     * @param userName The freelancer's name
     * @param userBio The freelancer's biography
     * @param categories The freelancer's categories
     * @throws IllegalArgumentException if any precondition is violated
     */
    public Freelancer(String userName, String userBio, List<String> categories) {
    	this(userName, userBio, categories, new ArrayList<String>());
    }
    
    /**
     * Creates a freelancer with a username, password, biography, category, and skills.
     *
     * @precondition userName != null && !userName.isBlank() && userBio != null && categories != null 
     * @postcondition userName, userBio, categories, and skills are set
     *
     * @param userName The freelancer's name
     * @param userBio The freelancer's biography
     * @param category The freelancer's category
     * @throws IllegalArgumentException if any precondition is violated
     */
    public Freelancer(String userName, String userBio, String category) {
    	this(userName, userBio, new ArrayList<String>());
    	this.categories.add(category);
    }
    
    /**
     * Creates a freelancer with a username, password, biography, categories, and skills.
     *
     * @precondition userName != null && !userName.isBlank() && userBio != null && categories != null 
     * @postcondition userName, userBio, categories, and skills are set
     *
     * @param userName The freelancer's name
     * @param userBio The freelancer's biography
     * @param categories The freelancer's categories
     * @param skills The freelancer's skills
     * @throws IllegalArgumentException if any precondition is violated
     */
    public Freelancer(String userName, String userBio, List<String> categories, List<String> skills) {
        super(userName, userBio);
        this.setCategories(categories);
        this.setAllSkills(skills);
    }
    
    /**
     * Creates a freelancer with a username, password, biography, category, and skills.
     *
     * @precondition userName != null && !userName.isBlank() && userBio != null && categories != null 
     * @postcondition userName, userBio, categories, and skills are set
     *
     * @param userName The freelancer's name
     * @param userBio The freelancer's biography
     * @param category The freelancer's category
     * @param skills The freelancer's skills
     * @throws IllegalArgumentException if any precondition is violated
     */
    public Freelancer(String userName, String userBio, String category, List<String> skills) {
        super(userName, userBio);
        this.categories = new ArrayList<String>();
        this.categories.add(category);
        this.setAllSkills(skills);
    }
    
    /**
     * Creates a freelancer with a username, password, biography, category, and skills.
     *
     * @precondition userName != null && !userName.isBlank() && userBio != null && categories != null 
     * @postcondition userName, userBio, categories, and skills are set
     *
     * @param userName The freelancer's name
     * @param userBio The freelancer's biography
     * @param category The freelancer's category
     * @param skills The freelancer's skills
     * @throws IllegalArgumentException if any precondition is violated
     */
    public Freelancer(String userName, String userBio, String category, String[] skills) {
        super(userName, userBio);
        this.categories = new ArrayList<String>();
        this.categories.add(category);
        this.setAllSkills(skills);
    }
    
    /**
     * Gets the freelancer's categories.
     * 
     * @precondition none
     * @postcondition none
     *
     * @return the freelancer's categories
     */
    public List<String> getCategories() {
        return this.categories;
    }
    
    /**
     * Gets the first freelancer's category.
     * 
     * @precondition none
     * @postcondition none
     *
     * @return the freelancer's category
     */
    public String getCategory() {
        return this.categories.get(0);
    }
    
    /**
     * Sets the freelancer's categories.
     *
     * @precondition categories != null
     * @postcondition this.categories is set
     *
     * @param categories The freelancer's categories
     * @throws IllegalArgumentException if category is null
     */
    public void setCategories(List<String> categories) {
        if (categories == null) {
            throw new IllegalArgumentException(CATEGORIES_CANNOT_BE_NULL);
        }
        this.categories = categories;
    }
    
    /**
     * Sets the freelancer's categories.
     *
     * @precondition categories != null
     * @postcondition this.categories is set
     *
     * @param category The freelancer's categories
     * @throws IllegalArgumentException if category is null
     */
    public void setCategory(String category) {
        if (category == null) {
            throw new IllegalArgumentException(CATEGORIES_CANNOT_BE_NULL);
        }
        this.categories.set(0, category);
    }
    
    /**
     * Gets the freelancer's skills.
     * 
     * @precondition none
     * @postcondition none
     *
     * @return a list of the freelancer's skills
     */
    public List<String> getSkills() {
        return this.skills;
    }
    
    /**
     * Sets all skills to the specified skills.
     *
     * @precondition skills != null
     * @postcondition the skill is set to the skills
     *
     * @param skills being set
     * @throws IllegalArgumentException if skills are null
     */
    public void setAllSkills(List<String> skills) {
    	if (skills == null) {
            throw new IllegalArgumentException(SKILLS_CANNOT_BE_NULL);
        }
        this.skills = skills;
    }
    
    /**
     * Sets all skills to the specified skills.
     *
     * @precondition skills != null || skill is null
     * @postcondition the skill is set to the skills
     *
     * @param skills being set
     * @throws IllegalArgumentException if skills are null
     */
    public void setAllSkills(String[] skills) {
    	this.skills = new ArrayList<String>();
    	for (int index = 0; index < skills.length; index++) {
    		 if (skills[index] == null) {
    			 throw new IllegalArgumentException(SKILL_CANNOT_BE_NULL);
    		 }
    		 this.skills.add(skills[index]);
        }
    }
    
    /**
     * Replaces an existing skill with a new skill.
     *
     * @precondition oldSkill != null && newSkill != null
     * @postcondition if oldSkill exists, it is replaced with newSkill
     *
     * @param oldSkill The skill to be replaced
     * @param newSkill The new skill to set
     * @throws IllegalArgumentException if oldSkill or newSkill is null, or if oldSkill is not found
     */
    public void replaceSkill(String oldSkill, String newSkill) {
        if (oldSkill == null || newSkill == null) {
            throw new IllegalArgumentException(SKILL_CANNOT_BE_NULL);
        }
        for (int index = 0; index < this.skills.size(); index++) {
            if (this.skills.get(index).equals(oldSkill)) {
                this.skills.set(index, newSkill); 
                return;
            }
        }
        throw new IllegalArgumentException(OLD_SKILL_NOT_FOUND);
    }
    
    /**
     * Checks if the freelancer has a specific skill.
     *
     * @precondition skill != null
     * @postcondition none
     *
     * @param skill The skill to check
     * @return true if the skill exists, false otherwise
     * @throws IllegalArgumentException if skill is null
     */
    public boolean containsSkill(String skill) {
        if (skill == null) {
            throw new IllegalArgumentException(SKILL_CANNOT_BE_NULL);
        }
        for (String currentString : this.skills) {
            if (currentString.equals(skill)) {
                return true;
            }
        }
        return false;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hash(this.skills);
		result = prime * result + Objects.hash(this.categories);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
			
		if (obj == null) {
			return false;
		}
			
		if (getClass() != obj.getClass()) {
			return false;
		}
		Freelancer other = (Freelancer) obj;
		return (this.categories.equals(other.categories)) && (this.skills.equals(other.skills)) && (other.getBio().equals(this.getBio()) && (other.getUserName().equals(this.getUserName())));
	}

	/**
	 * Sets the freelancers skill at the specific index
	 * 
	 * @param index the index
	 * @param skillText the skill
	 */
	public void setSkill(int index, String skillText) {
		if (this.skills.size() <= index) {
			this.skills.add(skillText);
			return;
		}
		this.skills.set(index, skillText);
		
	} 
    
}
