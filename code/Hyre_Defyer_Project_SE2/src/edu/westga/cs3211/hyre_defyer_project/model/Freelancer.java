package edu.westga.cs3211.hyre_defyer_project.model;

/**
 * The Freelancer class stores the category selected and the skills listed.
 * 
 * @author Kate Anglin
 * @version Spring 2025
 */
public class Freelancer extends User {

	private static final int NUM_OF_SKILLS = 5;
	private static final String SKILLS_ARRAY_MUST_BE_OF_SIZE_5 = "Skills array must be of size " + NUM_OF_SKILLS + ".";
	private static final String INDEX_MUST_BE_BETWEEN_0_AND_4 = "Index must be between 0 and 4.";
	private static final String SKILL_CANNOT_BE_NULL = "Skill cannot be null.";
	private static final String OLD_SKILL_NOT_FOUND = "Old skill not found.";
	private static final String CATEGORY_CANNOT_BE_NULL = "Category cannot be null.";
	
	private Categories category;
    private String[] skills;
	
    /**
     * Creates a freelancer with a username, password, biography, and category.
     *
     * @precondition userName != null && !userName.isBlank() && userBio != null && category != null
     * @postcondition userName, userBio, and category are set; skills is initialized as an empty array of size 5
     *
     * @param userName The freelancer's name
     * @param userBio The freelancer's biography
     * @param category The freelancer's category
     * @throws IllegalArgumentException if any precondition is violated
     */
    public Freelancer(String userName, String userBio, Categories category) {
    	this(userName, userBio, category, new String[NUM_OF_SKILLS]);
    }
    
    /**
     * Creates a freelancer with a username, password, biography, category, and skills.
     *
     * @precondition userName != null && !userName.isBlank() && userBio != null && category != null && skills.length == 5
     * @postcondition userName, userBio, category, and skills are set
     *
     * @param userName The freelancer's name
     * @param userBio The freelancer's biography
     * @param category The freelancer's category
     * @param skills The freelancer's skills
     * @throws IllegalArgumentException if any precondition is violated
     */
    public Freelancer(String userName, String userBio, Categories category, String[] skills) {
        super(userName, userBio);
        this.setCategory(category);
        this.setAllSkills(skills);
    }
    
    /**
     * Gets the freelancer's category.
     * 
     * @precondition none
     * @postcondition none
     *
     * @return the freelancer's category
     */
    public Categories getCategory() {
        return this.category;
    }
    
    /**
     * Sets the freelancer's category.
     *
     * @precondition category != null
     * @postcondition this.category is set
     *
     * @param category The freelancer's category
     * @throws IllegalArgumentException if category is null
     */
    public void setCategory(Categories category) {
        if (category == null) {
            throw new IllegalArgumentException(CATEGORY_CANNOT_BE_NULL);
        }
        this.category = category;
    }
    
    /**
     * Gets the freelancer's skills.
     * 
     * @precondition none
     * @postcondition none
     *
     * @return an array of the freelancer's skills
     */
    public String[] getSkills() {
        return this.skills.clone();
    }
    
    /**
     * Sets all skills to the specified skills.
     *
     * @precondition skill != null && index >= 0 && index < 5
     * @postcondition the skill is set to the skills
     *
     * @param skills being set
     * @throws IllegalArgumentException if skill is null, or if index is out of bounds
     */
    public void setAllSkills(String[] skills) {
    	if (skills == null || skills.length != NUM_OF_SKILLS) {
            throw new IllegalArgumentException(SKILLS_ARRAY_MUST_BE_OF_SIZE_5);
        }
        this.skills = skills.clone();
    }
    
    /**
     * Sets a skill at a specific index.
     *
     * @precondition skill != null && index >= 0 && index < 5
     * @postcondition the skill is set at the given index
     *
     * @param index The index to set the skill at
     * @param skill The skill to set
     * @throws IllegalArgumentException if skill is null, or if index is out of bounds
     */
    public void setSkill(int index, String skill) {
        if (skill == null) {
            throw new IllegalArgumentException(SKILL_CANNOT_BE_NULL);
        }
        if (index < 0 || index >= NUM_OF_SKILLS) {
            throw new IllegalArgumentException(INDEX_MUST_BE_BETWEEN_0_AND_4);
        }
        this.skills[index] = skill;
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
        for (int i = 0; i < this.skills.length; i++) {
            if (this.skills[i].equals(oldSkill)) {
                this.skills[i] = newSkill;
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
	
}
