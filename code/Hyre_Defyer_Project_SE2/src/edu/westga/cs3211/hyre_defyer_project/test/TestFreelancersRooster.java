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
    }
    
    @Test
    public void testInitializationWithExampleFreelancers() {
        assertNotNull(roster);
        assertFalse(roster.getAllFreelancers().isEmpty());
        assertEquals(24, roster.getAllFreelancers().size());
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
        initialFreelancers.add(new Freelancer("TestUser1", "password1", "Bio1", Categories.WRITING_AND_TRANSLATION, new String[]{"Editing", "Copywriting", "", "", ""}));
        initialFreelancers.add(new Freelancer("TestUser2", "password2", "Bio2", Categories.DESIGN_AND_CREATIVE, new String[]{"Photoshop", "Illustrator", "", "", ""}));
        
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
    public void testSearchBySkillThrowsExceptionIfNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roster.getFreelancersBySkill(null);
        });
        assertEquals("Skill cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testSearchBySkillThrowsExceptionIfBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roster.getFreelancersBySkill("  ");
        });
        assertEquals("Skill cannot be blank.", exception.getMessage());
    }
    
    @Test
    public void testRosterInitializesWithExampleFreelancers() {
        assertNotNull(roster.getAllFreelancers());
        assertFalse(roster.getAllFreelancers().isEmpty());
    }
    
    @Test
    public void testAddFreelancer() {
        Freelancer newFreelancer = new Freelancer("Zane", "test123", "New freelancer", Categories.DEVELOPMENT_AND_IT, new String[]{"Java", "C++", "SQL", "Python", "Git"});
        roster.addFreelancer(newFreelancer);
        assertTrue(roster.getAllFreelancers().contains(newFreelancer));
    }

    @Test
    public void testRemoveFreelancerNotInRoster() {
        Freelancer freelancer = new Freelancer("NonExistent", "password", "Bio", Categories.WRITING_AND_TRANSLATION, new String[]{"Editing", "Copywriting", "", "", ""});
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
        Freelancer toRemove = new Freelancer(freelancer.getUserName(), freelancer.getPassword(), freelancer.getBio(), freelancer.getCategory(), freelancer.getSkills());
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

}
