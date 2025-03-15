package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.User;

class TestFreelancer {
	private Freelancer freelancer;
	
	private Freelancer freelancer1;
	private Freelancer freelancer2;
	private Freelancer freelancer3;
	
	private String[] skills = {"Java", "Python", "C++", "JavaScript", "SQL"};
	private String[] skills1 = {"Java", "Python", "C++", "JavaScript", "SQL"};
    private  String[] skills2 = {"Java", "Python", "C++", "JavaScript", "SQL"};
    private String[] skills3 = {"Ruby", "Go", "Swift", "HTML", "CSS"};

	@BeforeEach
    public void setUp() {
        freelancer = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills);

        freelancer1 = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills1);
        freelancer2 = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills2);
        freelancer3 = new Freelancer("JaneDoe",  "Experienced Developer", Categories.BUSINESS_AND_FINANCE, skills3);
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
    
    @Test
    public void testEquals_SameObject() {
    	
        assertTrue(freelancer1.equals(freelancer1), "Freelancer should be equal to itself.");
    }
    
    @Test
    public void testEquals_NullObject() {
        assertFalse(freelancer1.equals(null), "Freelancer should not be equal to null.");
    }
    
    @Test
    public void testEquals_DifferetObject() {
    	User user = new User("user", "Bio");
        assertFalse(freelancer1.equals(user), "Freelancer should not be equal to User.");
    }

    @Test
    public void testEquals_SameValues() {
        assertTrue(freelancer1.equals(freelancer2), "Freelancer objects with the same values should be equal.");
    }
    
    @Test
    public void testEquals_DifferentUserName() {
    	String[] skills = {"Ruby", "Go", "Swift", "HTML", "CSS"};
    	Freelancer freelancer4 = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills);
        Freelancer freelancer5 = new Freelancer("JaneDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills);
        assertFalse(freelancer4.equals(freelancer5), "Freelancer objects with different usernames should not be equal.");
    }
    
    @Test
    public void testEquals_DifferentBio() {
    	String[] skills = {"Ruby", "Go", "Swift", "HTML", "CSS"};
    	Freelancer freelancer4 = new Freelancer("JohnDoe", "Experienced DeveloperA", Categories.DEVELOPMENT_AND_IT, skills);
        Freelancer freelancer5 = new Freelancer("JohnDoe", "Experienced DeveloperB", Categories.DEVELOPMENT_AND_IT, skills);
        assertFalse(freelancer4.equals(freelancer5), "Freelancer objects with different Bios should not be equal.");
    }
    
    @Test
    public void testEquals_SameSkillsDifferentPlace() {
    	String[] skills = {"Ruby", "Go", "Swift", "HTML", "CSS"};
    	String[] skills2 = {"Ruby", "Go", "Swift", "HTML", "CSS"};
    	Freelancer freelancer4 = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills);
        Freelancer freelancer5 = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills2);
        assertTrue(freelancer4.equals(freelancer5), "Freelancer objects with same Skills should be equal.");
    }
    
    @Test
    public void testEquals_DifferentSkills() {
    	String[] skills = {"Different", "Go", "Swift", "HTML", "CSS"};
    	String[] skills2 = {"Ruby", "Go", "Swift", "HTML", "CSS"};
    	Freelancer freelancer4 = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills);
        Freelancer freelancer5 = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills2);
        assertFalse(freelancer4.equals(freelancer5), "Freelancer objects with different Skills should not be equal.");
    }

    @Test
    public void testEquals_DifferentValues() {
        assertFalse(freelancer1.equals(freelancer3), "Freelancer objects with different values should not be equal.");
    }

    @Test
    public void testHashCode_SameValues() {
        assertEquals(freelancer1.hashCode(), freelancer2.hashCode(), "Hash codes should be the same for equal objects.");
    }

    @Test
    public void testHashCode_DifferentValues() {
        assertNotEquals(freelancer1.hashCode(), freelancer3.hashCode(), "Hash codes should be different for non-equal objects.");
    }


}
