package edu.westga.cs3211.hyre_defyer_project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Roster that will hold all Freelancers
 * 
 * @author Kate Anglin
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
        this.freelancers.add(new Freelancer("Alice", "pass123", "Experienced Accountant", Categories.BUSINESS_AND_FINANCE, new String[]{"Accounting", "Tax Filing", "QuickBooks", "Financial Analysis", "Excel"}));
        this.freelancers.add(new Freelancer("Bob", "secure456", "Investment Consultant", Categories.BUSINESS_AND_FINANCE, new String[]{"Investing", "Portfolio Management", "Stocks", "Bonds", "Risk Analysis"}));
        this.freelancers.add(new Freelancer("Charlie", "money789", "Financial Analyst", Categories.BUSINESS_AND_FINANCE, new String[]{"Financial Modeling", "Forecasting", "Data Analysis", "Budgeting", "Valuation"}));
        
        this.freelancers.add(new Freelancer("Dana", "design123", "Graphic Designer", Categories.DESIGN_AND_CREATIVE, new String[]{"Photoshop", "Illustrator", "Branding", "UI/UX", "Typography"}));
        this.freelancers.add(new Freelancer("Eli", "artistic456", "Illustrator", Categories.DESIGN_AND_CREATIVE, new String[]{"Digital Art", "Comics", "Concept Art", "Vector Graphics", "Sketching"}));
        this.freelancers.add(new Freelancer("Fay", "color789", "Animator", Categories.DESIGN_AND_CREATIVE, new String[]{"2D Animation", "3D Animation", "After Effects", "Storyboarding", "Motion Graphics"}));
        
        this.freelancers.add(new Freelancer("George", "dev123", "Java Developer", Categories.DEVELOPMENT_AND_IT, new String[]{"Java", "Spring", "SQL", "Git", "REST APIs"}));
        this.freelancers.add(new Freelancer("Hannah", "code456", "Web Developer", Categories.DEVELOPMENT_AND_IT, new String[]{"HTML", "CSS", "JavaScript", "React", "Node.js"}));
        this.freelancers.add(new Freelancer("Ian", "tech789", "Data Scientist", Categories.DEVELOPMENT_AND_IT, new String[]{"Python", "Machine Learning", "Pandas", "NumPy", "Deep Learning"}));
        
        this.freelancers.add(new Freelancer("Jack", "engineer123", "Mechanical Engineer", Categories.ENGINEERING_AND_SCIENCE, new String[]{"SolidWorks", "AutoCAD", "Finite Element Analysis", "Thermodynamics", "CAD Design"}));
        this.freelancers.add(new Freelancer("Karen", "science456", "Biomedical Engineer", Categories.ENGINEERING_AND_SCIENCE, new String[]{"Medical Devices", "Biomaterials", "3D Printing", "Regulatory Compliance", "Clinical Trials"}));
        this.freelancers.add(new Freelancer("Leo", "rocket789", "Aerospace Engineer", Categories.ENGINEERING_AND_SCIENCE, new String[]{"Aerodynamics", "Propulsion", "Satellite Systems", "Orbital Mechanics", "Composites"}));
        
        this.freelancers.add(new Freelancer("Mia", "sales123", "Marketing Specialist", Categories.MARKETING_AND_SALES, new String[]{"SEO", "Social Media", "Content Marketing", "Google Ads", "Email Campaigns"}));
        this.freelancers.add(new Freelancer("Noah", "brand456", "Sales Consultant", Categories.MARKETING_AND_SALES, new String[]{"B2B Sales", "CRM", "Negotiation", "Cold Calling", "Sales Funnels"}));
        this.freelancers.add(new Freelancer("Olivia", "ads789", "Digital Advertiser", Categories.MARKETING_AND_SALES, new String[]{"Facebook Ads", "PPC", "Conversion Optimization", "Market Research", "Copywriting"}));
        
        this.freelancers.add(new Freelancer("Paul", "music123", "Music Producer", Categories.MUSIC_AND_AUDIO, new String[]{"Mixing", "Mastering", "Ableton Live", "Logic Pro", "Music Composition"}));
        this.freelancers.add(new Freelancer("Quinn", "sound456", "Sound Designer", Categories.MUSIC_AND_AUDIO, new String[]{"Foley", "Game Audio", "Synthesizers", "Film Scoring", "Podcast Editing"}));
        this.freelancers.add(new Freelancer("Ryan", "record789", "Voice Actor", Categories.MUSIC_AND_AUDIO, new String[]{"Narration", "Character Voices", "Commercials", "E-Learning", "Dubbing"}));
        
        this.freelancers.add(new Freelancer("Sarah", "build123", "Carpenter", Categories.TRADES_AND_SKILLED_LABOR, new String[]{"Woodworking", "Cabinet Making", "Blueprint Reading", "Furniture Design", "Framing"}));
        this.freelancers.add(new Freelancer("Tom", "plumb456", "Plumber", Categories.TRADES_AND_SKILLED_LABOR, new String[]{"Pipe Fitting", "Drain Cleaning", "Fixture Installation", "Water Heaters", "Soldering"}));
        this.freelancers.add(new Freelancer("Uma", "electric789", "Electrician", Categories.TRADES_AND_SKILLED_LABOR, new String[]{"Wiring", "Troubleshooting", "Panel Upgrades", "Lighting Design", "Circuitry"}));
        
        this.freelancers.add(new Freelancer("Victor", "write123", "Copywriter", Categories.WRITING_AND_TRANSLATION, new String[]{"SEO Writing", "Blogging", "Technical Writing", "Ad Copy", "Editing"}));
        this.freelancers.add(new Freelancer("Wendy", "translate456", "Translator", Categories.WRITING_AND_TRANSLATION, new String[]{"Spanish", "French", "German", "Mandarin", "Localization"}));
        this.freelancers.add(new Freelancer("Xander", "novel789", "Fiction Writer", Categories.WRITING_AND_TRANSLATION, new String[]{"Creative Writing", "Screenwriting", "Storytelling", "Character Development", "Editing"}));
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
