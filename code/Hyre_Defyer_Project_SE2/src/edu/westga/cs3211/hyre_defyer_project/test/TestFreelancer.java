package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        assertEquals(new ArrayList<String>(), freelancer.getSkills());
    }

    @Test
    public void testConstructorValidInputs() {
        assertEquals("JohnDoe", freelancer.getUserName());
        assertEquals("Experienced Developer", freelancer.getBio());
        assertEquals(Categories.DEVELOPMENT_AND_IT, freelancer.getCategory());
        ArrayList<String> list = new ArrayList<String>();
        list.add("Java");
        list.add("Python");
        list.add("C++");
        list.add("JavaScript");
        list.add("SQL");
        assertEquals(list, freelancer.getSkills());
    }
    
    @Test
    public void testNewConstructor() {
    	String userName = "johnDoe";
    	String userBio = "Experienced developer";
        String category = Categories.DEVELOPMENT_AND_IT;
        List<String> skills = Arrays.asList("Java", "Python", "SQL");
        
    	Freelancer freelancer5 = new Freelancer(userName, userBio, category, skills);

        assertNotNull(freelancer5);
        assertEquals(userName, freelancer5.getUserName());
        assertEquals(userBio, freelancer5.getBio());
        assertEquals(1, freelancer5.getCategories().size());
        assertEquals(category, freelancer5.getCategories().get(0));
        assertTrue(freelancer5.getSkills().containsAll(skills));
    }
    
    @Test
    public void testSetCategoriesValid() {
    	List<String> validCategories = Arrays.asList(Categories.DESIGN_AND_CREATIVE, Categories.DEVELOPMENT_AND_IT); 
        freelancer = new Freelancer("johnDoe", "Experienced developer", Categories.DEVELOPMENT_AND_IT, Arrays.asList("Java", "Python"));
        freelancer.setCategories(validCategories);
        assertEquals(validCategories, freelancer.getCategories());
    }

    @Test
    public void testSetCategoriesNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            freelancer.setCategories(null);
        });
    }

    @Test
    public void testSetCategoriesEmptyList() {
    	freelancer.setCategories(Arrays.asList());
        assertTrue(freelancer.getCategories().isEmpty());
    }

    @Test
    public void testSetCategoriesWithOneCategory() {
    	freelancer = new Freelancer("johnDoe", "Experienced developer", Categories.DEVELOPMENT_AND_IT, Arrays.asList("Java", "Python"));
        freelancer.setCategories(Arrays.asList(Categories.DEVELOPMENT_AND_IT));
        assertEquals(1, freelancer.getCategories().size());
        assertTrue(freelancer.getCategories().contains(Categories.DEVELOPMENT_AND_IT));
    }
    
    @Test
    public void testSetAllSkillsWithListValid() {
    	List<String> validSkills = Arrays.asList("Java", "Python", "SQL");
        freelancer.setAllSkills(validSkills);
        assertEquals(validSkills, freelancer.getSkills());
    }

    @Test
    public void testSetAllSkillsWithListNull() {
    	List<String> nullSkills = null;
        assertThrows(IllegalArgumentException.class, () -> {
            freelancer.setAllSkills(nullSkills);
        });
    }

    @Test
    public void testSetAllSkillsWithEmptyList() {
    	List<String> emptySkills = Arrays.asList();
        freelancer.setAllSkills(emptySkills);
        assertTrue(freelancer.getSkills().isEmpty());
    }

    @Test
    public void testSetAllSkillsWithArrayValid() {
    	String[] validSkillsArray = new String[] {"Java", "Python", "SQL"};
        freelancer.setAllSkills(validSkillsArray);
        assertTrue(freelancer.getSkills().containsAll(Arrays.asList(validSkillsArray)));
    }

    @Test
    public void testSetAllSkillsWithListNull1() {
    	List<String> nullSkills = null;
        assertThrows(IllegalArgumentException.class, () -> {
            freelancer.setAllSkills(nullSkills);
        });
    }
    
    @Test
    public void testSetAllSkillsWithArrayNull() {
    	String[] nullSkillsArray = new String[] {null, null, null};
        assertThrows(IllegalArgumentException.class, () -> {
            freelancer.setAllSkills(nullSkillsArray);
        });
    }
    
    @Test
    public void testSetAllSkillsWithArrayContainsNull() {
        String[] skillsWithNull = new String[] {"Java", null, "Python"};
        assertThrows(IllegalArgumentException.class, () -> {
            freelancer.setAllSkills(skillsWithNull);
        });
    }

    @Test
    public void testSetAllSkillsWithEmptyArray() {
    	String[]  emptySkillsArray = new String[] {};
        freelancer.setAllSkills(emptySkillsArray);
        assertTrue(freelancer.getSkills().isEmpty());
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
        assertEquals("Categories cannot be null.", exception.getMessage());
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
