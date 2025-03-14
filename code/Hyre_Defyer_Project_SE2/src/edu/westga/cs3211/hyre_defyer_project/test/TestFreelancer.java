package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;

class TestFreelancer {
	private Freelancer freelancer;

	@BeforeEach
    public void setUp() {
        String[] skills = {"Java", "Python", "C++", "JavaScript", "SQL"};
        freelancer = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills);
    }
	
	@Test
    public void testDefaultConstructorValidInputs() {
		freelancer = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT);
        assertEquals("JohnDoe", freelancer.getUserName());
        assertEquals("Experienced Developer", freelancer.getBio());
        assertEquals(Categories.DEVELOPMENT_AND_IT, freelancer.getCategory());
        assertArrayEquals(new String[]{null, null, null, null, null}, freelancer.getSkills());
    }

    @Test
    public void testConstructorValidInputs() {
        assertEquals("JohnDoe", freelancer.getUserName());
        assertEquals("Experienced Developer", freelancer.getBio());
        assertEquals(Categories.DEVELOPMENT_AND_IT, freelancer.getCategory());
        assertArrayEquals(new String[]{"Java", "Python", "C++", "JavaScript", "SQL"}, freelancer.getSkills());
    }

    @Test
    public void testReplaceSkillValid() {
        freelancer.replaceSkill("Java", "Ruby");
        assertTrue(freelancer.containsSkill("Ruby"));
        assertFalse(freelancer.containsSkill("Java"));
    }

    @Test
    public void testReplaceSkillNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            freelancer.replaceSkill("Swift", "Go");
        });
        assertEquals("Old skill not found.", exception.getMessage());
    }
    
    @Test
    public void testReplaceSkillWithNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            freelancer.replaceSkill("Swift", null);
        });
        assertEquals("Skill cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testReplaceNullSkill() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            freelancer.replaceSkill(null, "Swift");
        });
        assertEquals("Skill cannot be null.", exception.getMessage());
    }

    @Test
    public void testContainsSkillValid() {
        assertTrue(freelancer.containsSkill("Python"));
    }

    @Test
    public void testContainsSkillInvalid() {
        assertFalse(freelancer.containsSkill("Swift"));
    }
    
    @Test
    public void testContainsNullSkill() {
    	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    		freelancer.containsSkill(null);
        });
        assertEquals("Skill cannot be null.", exception.getMessage());
    }

    @Test
    public void testSetCategoryValid() {
        freelancer.setCategory(Categories.BUSINESS_AND_FINANCE);
        assertEquals(Categories.BUSINESS_AND_FINANCE, freelancer.getCategory());
    }
    
    @Test
    public void testInvalidCategory() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            freelancer.setCategory(null);
        });
        assertEquals("Category cannot be null.", exception.getMessage());
    }

    @Test
    public void testSetSkillValid() {
        freelancer.setSkill(2, "Go");
        assertTrue(freelancer.containsSkill("Go"));
    }

    @Test
    public void testSetSkillInvalidIndex() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            freelancer.setSkill(5, "Go");
        });
        assertEquals("Index must be between 0 and 4.", exception.getMessage());
    }
    
    @Test
    public void testSetSkillNegativeIndex() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            freelancer.setSkill(-1, "Go");
        });
        assertEquals("Index must be between 0 and 4.", exception.getMessage());
    }
    
    @Test
    public void testSetNullSkill() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            freelancer.setSkill(0, null);
        });
        assertEquals("Skill cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testSetAllSkillsInvalidNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            freelancer.setAllSkills(null);
        });
        assertEquals("Skills array must be of size 5.", exception.getMessage());
    }
    
    @Test
    public void testSetAllSkillsInvalidIndex() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            freelancer.setAllSkills(new String[4]);
        });
        assertEquals("Skills array must be of size 5.", exception.getMessage());
    }


}
