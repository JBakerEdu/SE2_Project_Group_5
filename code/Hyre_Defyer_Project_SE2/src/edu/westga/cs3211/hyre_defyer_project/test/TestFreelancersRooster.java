package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.FreelancerRoster;

class TestFreelancersRooster {

private FreelancerRoster roster;
    
    @BeforeEach
    public void setUp() {
        roster = new FreelancerRoster();
        roster.addFreelancer(new Freelancer("Alice1", "Experienced Accountant", Categories.BUSINESS_AND_FINANCE, new String[]{"Accounting", "Tax Filing", "QuickBooks", "Financial Analysis", "Excel"}));
        roster.addFreelancer(new Freelancer("Bob1", "Investment Consultant", Categories.BUSINESS_AND_FINANCE, new String[]{"Investing", "Portfolio Management", "Stocks", "Bonds", "Risk Analysis"}));
        roster.addFreelancer(new Freelancer("Charlie1", "Financial Analyst", Categories.BUSINESS_AND_FINANCE, new String[]{"Financial Modeling", "Forecasting", "Data Analysis", "Budgeting", "Valuation"}));

        roster.addFreelancer(new Freelancer("Alice2", "Experienced Accountant", Categories.BUSINESS_AND_FINANCE, new String[]{"Accounting", "Tax Filing", "QuickBooks", "Financial Analysis", "Excel"}));
        roster.addFreelancer(new Freelancer("Bob2", "Investment Consultant", Categories.BUSINESS_AND_FINANCE, new String[]{"Investing", "Portfolio Management", "Stocks", "Bonds", "Risk Analysis"}));
        roster.addFreelancer(new Freelancer("Charlie2", "Financial Analyst", Categories.BUSINESS_AND_FINANCE, new String[]{"Financial Modeling", "Forecasting", "Data Analysis", "Budgeting", "Valuation"}));

        roster.addFreelancer(new Freelancer("Alice3", "Experienced Accountant", Categories.BUSINESS_AND_FINANCE, new String[]{"Accounting", "Tax Filing", "QuickBooks", "Financial Analysis", "Excel"}));
        roster.addFreelancer(new Freelancer("Bob3", "Investment Consultant", Categories.BUSINESS_AND_FINANCE, new String[]{"Investing", "Portfolio Management", "Stocks", "Bonds", "Risk Analysis"}));
        roster.addFreelancer(new Freelancer("Charlie3", "Financial Analyst", Categories.BUSINESS_AND_FINANCE, new String[]{"Financial Modeling", "Forecasting", "Data Analysis", "Budgeting", "Valuation"}));

        roster.addFreelancer(new Freelancer("Alice4", "Experienced Accountant", Categories.BUSINESS_AND_FINANCE, new String[]{"Accounting", "Tax Filing", "QuickBooks", "Financial Analysis", "Excel"}));
        roster.addFreelancer(new Freelancer("Bob4", "Investment Consultant", Categories.BUSINESS_AND_FINANCE, new String[]{"Investing", "Portfolio Management", "Stocks", "Bonds", "Risk Analysis"}));
        roster.addFreelancer(new Freelancer("Charlie4", "Financial Analyst", Categories.BUSINESS_AND_FINANCE, new String[]{"Financial Modeling", "Forecasting", "Data Analysis", "Budgeting", "Valuation"}));

        roster.addFreelancer(new Freelancer("Alice5", "Experienced Accountant", Categories.BUSINESS_AND_FINANCE, new String[]{"Accounting", "Tax Filing", "QuickBooks", "Financial Analysis", "Excel"}));
        roster.addFreelancer(new Freelancer("Bob5", "Investment Consultant", Categories.BUSINESS_AND_FINANCE, new String[]{"Investing", "Portfolio Management", "Stocks", "Bonds", "Risk Analysis"}));
        roster.addFreelancer(new Freelancer("Charlie5", "Financial Analyst", Categories.BUSINESS_AND_FINANCE, new String[]{"Financial Modeling", "Forecasting", "Data Analysis", "Budgeting", "Valuation"}));

        roster.addFreelancer(new Freelancer("Alice6", "Experienced Accountant", Categories.BUSINESS_AND_FINANCE, new String[]{"Accounting", "Tax Filing", "QuickBooks", "Financial Analysis", "Excel"}));
        roster.addFreelancer(new Freelancer("Bob6", "Investment Consultant", Categories.BUSINESS_AND_FINANCE, new String[]{"Investing", "Portfolio Management", "Stocks", "Bonds", "Risk Analysis"}));
        roster.addFreelancer(new Freelancer("Charlie6", "Financial Analyst", Categories.BUSINESS_AND_FINANCE, new String[]{"Financial Modeling", "Forecasting", "Data Analysis", "Budgeting", "Valuation"}));

        roster.addFreelancer(new Freelancer("Alice7", "Experienced Accountant", Categories.BUSINESS_AND_FINANCE, new String[]{"Accounting", "Tax Filing", "QuickBooks", "Financial Analysis", "Excel"}));
        roster.addFreelancer(new Freelancer("Bob7", "Investment Consultant", Categories.BUSINESS_AND_FINANCE, new String[]{"Investing", "Portfolio Management", "Stocks", "Bonds", "Risk Analysis"}));
        roster.addFreelancer(new Freelancer("Charlie7", "Financial Analyst", Categories.BUSINESS_AND_FINANCE, new String[]{"Financial Modeling", "Forecasting", "Data Analysis", "Budgeting", "Valuation"}));

        roster.addFreelancer(new Freelancer("Alice8", "Experienced Accountant", Categories.BUSINESS_AND_FINANCE, new String[]{"Accounting", "Tax Filing", "QuickBooks", "Financial Analysis", "Excel"}));
        roster.addFreelancer(new Freelancer("Bob8", "Investment Consultant", Categories.BUSINESS_AND_FINANCE, new String[]{"Investing", "Portfolio Management", "Stocks", "Bonds", "Risk Analysis"}));
        roster.addFreelancer(new Freelancer("Charlie8", "Financial Analyst", Categories.BUSINESS_AND_FINANCE, new String[]{"Financial Modeling", "Forecasting", "Data Analysis", "Budgeting", "Valuation"}));

        roster.addFreelancer(new Freelancer("Alice9", "Experienced Accountant", Categories.BUSINESS_AND_FINANCE, new String[]{"Accounting", "Tax Filing", "QuickBooks", "Financial Analysis", "Excel"}));
        roster.addFreelancer(new Freelancer("Bob9", "Investment Consultant", Categories.BUSINESS_AND_FINANCE, new String[]{"Investing", "Portfolio Management", "Stocks", "Bonds", "Risk Analysis"}));
        roster.addFreelancer(new Freelancer("Charlie9", "Financial Analyst", Categories.BUSINESS_AND_FINANCE, new String[]{"Financial Modeling", "Forecasting", "Data Analysis", "Budgeting", "Valuation"}));

        roster.addFreelancer(new Freelancer("Alice10", "Experienced Accountant", Categories.BUSINESS_AND_FINANCE, new String[]{"Accounting", "Tax Filing", "QuickBooks", "Financial Analysis", "Excel"}));
        roster.addFreelancer(new Freelancer("Bob10", "Investment Consultant", Categories.BUSINESS_AND_FINANCE, new String[]{"Investing", "Portfolio Management", "Stocks", "Bonds", "Risk Analysis"}));
        roster.addFreelancer(new Freelancer("Charlie10", "Financial Analyst", Categories.BUSINESS_AND_FINANCE, new String[]{"Financial Modeling", "Forecasting", "Data Analysis", "Budgeting", "Valuation"}));
        
        roster.addFreelancer(new Freelancer("Dana", "Graphic Designer", Categories.DESIGN_AND_CREATIVE, new String[]{"Photoshop", "Illustrator", "Branding", "UI/UX", "Typography"}));
        roster.addFreelancer(new Freelancer("Eli", "Illustrator", Categories.DESIGN_AND_CREATIVE, new String[]{"Digital Art", "Comics", "Concept Art", "Vector Graphics", "Sketching"}));
        roster.addFreelancer(new Freelancer("Fay", "Animator", Categories.DESIGN_AND_CREATIVE, new String[]{"2D Animation", "3D Animation", "After Effects", "Storyboarding", "Motion Graphics"}));
        
        roster.addFreelancer(new Freelancer("George", "Java Developer", Categories.DEVELOPMENT_AND_IT, new String[]{"Java", "Spring", "SQL", "Git", "REST APIs"}));
        roster.addFreelancer(new Freelancer("Hannah", "Web Developer", Categories.DEVELOPMENT_AND_IT, new String[]{"HTML", "CSS", "JavaScript", "React", "Node.js"}));
        roster.addFreelancer(new Freelancer("Ian", "Data Scientist", Categories.DEVELOPMENT_AND_IT, new String[]{"Python", "Machine Learning", "Pandas", "NumPy", "Deep Learning"}));
        
        roster.addFreelancer(new Freelancer("Jack", "Mechanical Engineer", Categories.ENGINEERING_AND_SCIENCE, new String[]{"SolidWorks", "AutoCAD", "Finite Element Analysis", "Thermodynamics", "CAD Design"}));
        roster.addFreelancer(new Freelancer("Karen", "Biomedical Engineer", Categories.ENGINEERING_AND_SCIENCE, new String[]{"Medical Devices", "Biomaterials", "3D Printing", "Regulatory Compliance", "Clinical Trials"}));
        roster.addFreelancer(new Freelancer("Leo", "Aerospace Engineer", Categories.ENGINEERING_AND_SCIENCE, new String[]{"Aerodynamics", "Propulsion", "Satellite Systems", "Orbital Mechanics", "Composites"}));
        
        roster.addFreelancer(new Freelancer("Mia", "Marketing Specialist", Categories.MARKETING_AND_SALES, new String[]{"SEO", "Social Media", "Content Marketing", "Google Ads", "Email Campaigns"}));
        roster.addFreelancer(new Freelancer("Noah", "Sales Consultant", Categories.MARKETING_AND_SALES, new String[]{"B2B Sales", "CRM", "Negotiation", "Cold Calling", "Sales Funnels"}));
        roster.addFreelancer(new Freelancer("Olivia", "Digital Advertiser", Categories.MARKETING_AND_SALES, new String[]{"Facebook Ads", "PPC", "Conversion Optimization", "Market Research", "Copywriting"}));
        
        roster.addFreelancer(new Freelancer("Paul", "Music Producer", Categories.MUSIC_AND_AUDIO, new String[]{"Mixing", "Mastering", "Ableton Live", "Logic Pro", "Music Composition"}));
        roster.addFreelancer(new Freelancer("Quinn", "Sound Designer", Categories.MUSIC_AND_AUDIO, new String[]{"Foley", "Game Audio", "Synthesizers", "Film Scoring", "Podcast Editing"}));
        roster.addFreelancer(new Freelancer("Ryan", "Voice Actor", Categories.MUSIC_AND_AUDIO, new String[]{"Narration", "Character Voices", "Commercials", "E-Learning", "Dubbing"}));
        
        roster.addFreelancer(new Freelancer("Sarah", "Carpenter", Categories.TRADES_AND_SKILLED_LABOR, new String[]{"Woodworking", "Cabinet Making", "Blueprint Reading", "Furniture Design", "Framing"}));
        roster.addFreelancer(new Freelancer("Tom", "Plumber", Categories.TRADES_AND_SKILLED_LABOR, new String[]{"Pipe Fitting", "Drain Cleaning", "Fixture Installation", "Water Heaters", "Soldering"}));
        roster.addFreelancer(new Freelancer("Uma", "Electrician", Categories.TRADES_AND_SKILLED_LABOR, new String[]{"Wiring", "Troubleshooting", "Panel Upgrades", "Lighting Design", "Circuitry"}));
        
        roster.addFreelancer(new Freelancer("Victor", "Copywriter", Categories.WRITING_AND_TRANSLATION, new String[]{"SEO Writing", "Blogging", "Technical Writing", "Ad Copy", "Editing"}));
        roster.addFreelancer(new Freelancer("Wendy", "Translator", Categories.WRITING_AND_TRANSLATION, new String[]{"Spanish", "French", "German", "Mandarin", "Localization"}));
        roster.addFreelancer(new Freelancer("Xander", "Fiction Writer", Categories.WRITING_AND_TRANSLATION, new String[]{"Creative Writing", "Screenwriting", "Storytelling", "Character Development", "Editing"}));
    }
    
    @Test
    public void testInitializationWithExampleFreelancers() {
        assertNotNull(roster);
        assertFalse(roster.getAllFreelancers().isEmpty());
        assertEquals(51, roster.getAllFreelancers().size());
    }
    
    @Test
    public void testConstructorThrowsExceptionIfNullList() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FreelancerRoster(null);
        });
        assertEquals("Freelancer list cannot be null.", exception.getMessage());
    }

    @Test
    public void testConstructorInitializesWithGivenList() {
        List<Freelancer> initialFreelancers = new ArrayList<>();
        initialFreelancers.add(new Freelancer("TestUser1", "Bio1", Categories.WRITING_AND_TRANSLATION, new String[]{"Editing", "Copywriting", "", "", ""}));
        initialFreelancers.add(new Freelancer("TestUser2", "Bio2", Categories.DESIGN_AND_CREATIVE, new String[]{"Photoshop", "Illustrator", "", "", ""}));
        
        FreelancerRoster customRoster = new FreelancerRoster(initialFreelancers);
        assertEquals(2, customRoster.getAllFreelancers().size());
        assertTrue(customRoster.getAllFreelancers().containsAll(initialFreelancers));
    }

    @Test
    public void testSearchByCategory() {
        List<Freelancer> developers = roster.getFreelancersByCategory(Categories.DEVELOPMENT_AND_IT);
        assertNotNull(developers);
        assertEquals(3, developers.size());
    }

    @Test
    public void testSearchBySkill() {
        List<Freelancer> javaExperts = roster.getFreelancersBySkill("Java");
        assertNotNull(javaExperts);
        assertFalse(javaExperts.isEmpty());
    }
    
    @Test
    public void testSearchBySkillFound() {
        List<Freelancer> javaExperts = roster.getFreelancersBySkill("Accounting");
        assertNotNull(javaExperts);
        assertFalse(javaExperts.isEmpty());
    }

    @Test
    public void testAddFreelancerThrowsExceptionIfNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roster.addFreelancer(null);
        });
        assertEquals("Freelancer cannot be null.", exception.getMessage());
    }

    @Test
    public void testRemoveFreelancerThrowsExceptionIfNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roster.removeFreelancer(null);
        });
        assertEquals("Freelancer cannot be null.", exception.getMessage());
    }

    @Test
    public void testSearchByCategoryThrowsExceptionIfNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roster.getFreelancersByCategory(null);
        });
        assertEquals("Category cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testRosterInitializesWithExampleFreelancers() {
        assertNotNull(roster.getAllFreelancers());
        assertFalse(roster.getAllFreelancers().isEmpty());
    }
    
    @Test
    public void testAddFreelancer() {
        Freelancer newFreelancer = new Freelancer("Zane", "New freelancer", Categories.DEVELOPMENT_AND_IT, new String[]{"Java", "C++", "SQL", "Python", "Git"});
        roster.addFreelancer(newFreelancer);
        assertTrue(roster.getAllFreelancers().contains(newFreelancer));
    }

    @Test
    public void testRemoveFreelancerNotInRoster() {
        Freelancer freelancer = new Freelancer("NonExistent", "Bio", Categories.WRITING_AND_TRANSLATION, new String[]{"Editing", "Copywriting", "", "", ""});
        assertFalse(roster.removeFreelancer(freelancer));
    }

    @Test
    public void testRemoveFreelancerTwice() {
        Freelancer freelancer = roster.getAllFreelancers().get(0);
        assertTrue(roster.removeFreelancer(freelancer));
        assertFalse(roster.removeFreelancer(freelancer));
    }
    
    @Test
    public void testRemoveFreelancer() {
        List<Freelancer> freelancers = roster.getAllFreelancers();
        assertFalse(freelancers.isEmpty());
        Freelancer toRemove = freelancers.get(0);
        roster.removeFreelancer(toRemove);
        assertFalse(roster.getAllFreelancers().contains(toRemove));
    }
    
    @Test
    public void testRemoveFreelancerWithSameAttributes() {
        List<Freelancer> freelancers = roster.getAllFreelancers();
        assertFalse(freelancers.isEmpty());
        Freelancer freelancer = freelancers.get(0);
        Freelancer toRemove = new Freelancer(freelancer.getUserName(), freelancer.getBio(), freelancer.getCategory(), freelancer.getSkills());
        roster.removeFreelancer(toRemove);
        assertFalse(roster.getAllFreelancers().contains(freelancer));
    }
    
    @Test
    public void testFindFreelancersByCategory() {
        List<Freelancer> developers = roster.getFreelancersByCategory(Categories.DEVELOPMENT_AND_IT);
        assertNotNull(developers);
        assertFalse(developers.isEmpty());
        for (Freelancer f : developers) {
            assertEquals(Categories.DEVELOPMENT_AND_IT, f.getCategory());
        }
    }
    
    @Test
    public void testFindFreelancersBySkill() {
        List<Freelancer> javaDevelopers = roster.getFreelancersBySkill("Java");
        assertNotNull(javaDevelopers);
        assertFalse(javaDevelopers.isEmpty());
        boolean foundJava = false;
        for (Freelancer f : javaDevelopers) {
            if (f.containsSkill("Java")) {
                foundJava = true;
            }
        }
        assertTrue(foundJava);
    }
    
    @Test
    public void testFindFreelancersByNullSkill() {
        List<Freelancer> javaDevelopers = roster.getFreelancersBySkill(null);
        assertNotNull(javaDevelopers);
        assertFalse(javaDevelopers.isEmpty());
        assertTrue(roster.getAllFreelancers().containsAll(javaDevelopers));
    }
    
    @Test
    public void testFindFreelancersByEmptySkill() {
        List<Freelancer> javaDevelopers = roster.getFreelancersBySkill("");
        assertNotNull(javaDevelopers);
        assertFalse(javaDevelopers.isEmpty());
        assertTrue(roster.getAllFreelancers().containsAll(javaDevelopers));
    }
    
    @Test
    public void testGetFreelancersBySkillCaseInsensitive() {
        List<Freelancer> javaExperts = roster.getFreelancersBySkill("java");
        assertNotNull(javaExperts);
        assertFalse(javaExperts.isEmpty());
    }

    @Test
    public void testGetFreelancersBySkillPartialMatch() {
        List<Freelancer> sqlExperts = roster.getFreelancersBySkill("A");
        assertNotNull(sqlExperts);
        assertFalse(sqlExperts.isEmpty());
    }
    
    @Test
    public void testFindFreelancersBySkillNotFound() {
        List<Freelancer> javaDevelopers = roster.getFreelancersBySkill("NOT A SKILL");
        assertNotNull(javaDevelopers);
        assertTrue(javaDevelopers.isEmpty());
    }
    
    @Test
    public void testFindFreelancersByName() {
        List<Freelancer> javaDevelopers = roster.getFreelancersByName("Wendy");
        assertNotNull(javaDevelopers);
        assertFalse(javaDevelopers.isEmpty());
        boolean foundJava = false;
        for (Freelancer f : javaDevelopers) {
            if (f.getUserName().contains("Wendy")) {
                foundJava = true;
            }
        }
        assertTrue(foundJava);
    }
    
    @Test
    public void testFindFreelancersByNullName() {
        List<Freelancer> javaDevelopers = roster.getFreelancersByName(null);
        assertNotNull(javaDevelopers);
        assertFalse(javaDevelopers.isEmpty());
        assertTrue(roster.getAllFreelancers().containsAll(javaDevelopers));
    }
    
    @Test
    public void testFindFreelancersByEmptyName() {
        List<Freelancer> javaDevelopers = roster.getFreelancersByName("");
        assertNotNull(javaDevelopers);
        assertFalse(javaDevelopers.isEmpty());
        assertTrue(roster.getAllFreelancers().containsAll(javaDevelopers));
    }
    
    @Test
    public void testGetFreelancersByNameCaseInsensitive() {
        List<Freelancer> javaExperts = roster.getFreelancersByName("wEnDY");
        assertNotNull(javaExperts);
        assertFalse(javaExperts.isEmpty());
    }

    @Test
    public void testGetFreelancersByNamePartialMatch() {
        List<Freelancer> sqlExperts = roster.getFreelancersByName("nd");
        assertNotNull(sqlExperts);
        assertFalse(sqlExperts.isEmpty());
    }
    
    @Test
    public void testFindFreelancersByNameNotFound() {
        List<Freelancer> javaDevelopers = roster.getFreelancersByName("NOT A NAME");
        assertNotNull(javaDevelopers);
        assertTrue(javaDevelopers.isEmpty());
    }
    
    @Test
    public void testMatchByNameAndSkill() {
        List<Freelancer> result = roster.getFreelancersByNameAndSkill("ia", "con");
        assertEquals(2, result.size());
    }

    @Test
    public void testNoOverlapInNameAndSkill() {
        List<Freelancer> result = roster.getFreelancersByNameAndSkill("li", "React");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testOnlyOneMatchesBoth() {
        List<Freelancer> result = roster.getFreelancersByNameAndSkill("Xander", "writing");
        assertEquals(1, result.size());
        assertEquals("Xander", result.get(0).getUserName());
    }

    @Test
    public void testNullNameReturnsAllNameMatches() {
        List<Freelancer> result = roster.getFreelancersByNameAndSkill(null, "Java");
        assertEquals(2, result.size());
    }

    @Test
    public void testBlankNameReturnsAllNameMatches() {
        List<Freelancer> result = roster.getFreelancersByNameAndSkill("", "Spring");
        assertEquals(1, result.size());
        assertEquals("George", result.get(0).getUserName());
    }

    @Test
    public void testNullSkillReturnsAllSkillMatches() {
        List<Freelancer> result = roster.getFreelancersByNameAndSkill("ia", null);
        assertEquals(3, result.size());
    }

    @Test
    public void testBlankSkillReturnsAllSkillMatches() {
        List<Freelancer> result = roster.getFreelancersByNameAndSkill("ia", " ");
        assertEquals(3, result.size());
    }

    @Test
    public void testNoMatchesAtAll() {
        List<Freelancer> result = roster.getFreelancersByNameAndSkill("Z", "Everything");
        assertTrue(result.isEmpty());
    }

}
