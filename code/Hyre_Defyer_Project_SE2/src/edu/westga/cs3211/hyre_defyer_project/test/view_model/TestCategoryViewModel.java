package edu.westga.cs3211.hyre_defyer_project.test.view_model;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.RosterHelper;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
import edu.westga.cs3211.hyre_defyer_project.view_model.CategoryPageViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;

/**
 * Unit tests for the CategoryViewModel class.
 */
public class TestCategoryViewModel {
    
    private CategoryPageViewModel categoryViewModel;
    private Freelancer freelancer1;
    private Freelancer freelancer2;
    private Freelancer freelancer3;

    @BeforeEach
    void setUp() {
        CategoryPageViewModel.clearSelections();

        this.categoryViewModel = new CategoryPageViewModel();
        freelancer1 = new Freelancer("Ben Dover", "Bio", Categories.BUSINESS_AND_FINANCE);
        freelancer2 = new Freelancer("Jane Smith", "Bio", Categories.BUSINESS_AND_FINANCE);
        freelancer3 = new Freelancer("Mike Jones", "Bio", Categories.BUSINESS_AND_FINANCE);
        ServerInterface.addFreelancer(freelancer1);
        ServerInterface.addFreelancer(freelancer2);
        ServerInterface.addFreelancer(freelancer3);
    }
    
    @AfterEach
    void tearDown() {
        RosterHelper.removeFreelancerFromServer(freelancer1);
        RosterHelper.removeFreelancerFromServer(freelancer2);
        RosterHelper.removeFreelancerFromServer(freelancer3);

        CategoryPageViewModel.clearSelections();
    }

    @Test
    void testSetSelectedCategory() {
    	assertNotNull(this.categoryViewModel);
        CategoryPageViewModel.setSelectedCategory(Categories.DEVELOPMENT_AND_IT);
        assertEquals(
            Categories.DEVELOPMENT_AND_IT.toUpperCase().replace(" ", "_"),
            CategoryPageViewModel.getSelectedCategory(),
            "Selected category should be set correctly."
        );
    }

    @Test
    public void testGetFreelancersReturnsCorrectList() {
    	CategoryPageViewModel.setSelectedCategory(Categories.BUSINESS_AND_FINANCE);
    	List<Freelancer> roster = CategoryPageViewModel.getFreelancers();
    	assertTrue(roster.contains(freelancer1));
    	assertTrue(roster.contains(freelancer2));
    	assertTrue(roster.contains(freelancer3));
    }

    @Test
    public void testGetFreelancersWithNameAndSkillReturnsCorrectList() {
    	CategoryPageViewModel.setSelectedCategory(Categories.BUSINESS_AND_FINANCE);
    	List<Freelancer> roster = CategoryPageViewModel.getFreelancersWithNameAndSkills(" ", null);
    	assertTrue(roster.contains(freelancer1));
    	assertTrue(roster.contains(freelancer2));
    	assertTrue(roster.contains(freelancer3));
    }

    @Test
    public void testGetFreelancersWithNameAndSkillEmptyResult() {
        CategoryPageViewModel.setSelectedCategory(Categories.BUSINESS_AND_FINANCE);
        List<String> skills = new ArrayList<String>();
        skills.add("Something");
        List<Freelancer> roster = CategoryPageViewModel.getFreelancersWithNameAndSkills("Someone", skills);
        assertFalse(roster.contains(freelancer1));
        assertFalse(roster.contains(freelancer2));
        assertFalse(roster.contains(freelancer3));
    }

    @Test
    void testSelectedNameSetterAndGetter() {
        assertNull(CategoryPageViewModel.getSelectedName());

        CategoryPageViewModel.setSelectedName("Alice");
        assertEquals("Alice", CategoryPageViewModel.getSelectedName());

        CategoryPageViewModel.setSelectedName("Bob");
        assertEquals("Bob", CategoryPageViewModel.getSelectedName(),
            "Setter should overwrite previous selectedName");
    }

    @Test
    void testSelectedSkillsSetterAndGetter() {
        assertNull(CategoryPageViewModel.getSelectedSkills());

        ArrayList<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Spring");

        CategoryPageViewModel.setSelectedSkills(skills);
        ArrayList<String> retrieved = CategoryPageViewModel.getSelectedSkills();

        assertNotNull(retrieved);
        assertEquals(2, retrieved.size());
        assertTrue(retrieved.contains("Java") && retrieved.contains("Spring"));

        retrieved.remove("Spring");
        assertEquals(1, CategoryPageViewModel.getSelectedSkills().size(),
            "Mutations to returned list should reflect in static field");
    }

    @Test
    void testClearSelectionsResetsAllStaticFields() {
        CategoryPageViewModel.setSelectedCategory("TestCat");
        CategoryPageViewModel.setSelectedName("TestName");
        ArrayList<String> skills = new ArrayList<>();
        skills.add("X");
        CategoryPageViewModel.setSelectedSkills(skills);

        assertNotNull(CategoryPageViewModel.getSelectedCategory());
        assertNotNull(CategoryPageViewModel.getSelectedName());
        assertNotNull(CategoryPageViewModel.getSelectedSkills());

        CategoryPageViewModel.clearSelections();

        assertNull(CategoryPageViewModel.getSelectedCategory());
        assertNull(CategoryPageViewModel.getSelectedName());
        assertNull(CategoryPageViewModel.getSelectedSkills());
    }
    
    @Test
    void testGetUnselectedSkillsFiltersOutSelectedOnes() {
        RosterHelper.removeFreelancerFromServer(freelancer1);
        RosterHelper.removeFreelancerFromServer(freelancer2);
        RosterHelper.removeFreelancerFromServer(freelancer3);

        freelancer1.setAllSkills(Arrays.asList("Editing", "Proofreading", "Blogging"));
        freelancer2.setAllSkills(Arrays.asList("Spanish", "French", "Editing"));
        freelancer3.setAllSkills(Arrays.asList("SEO Writing", "Blogging"));

        ServerInterface.addFreelancer(freelancer1);
        ServerInterface.addFreelancer(freelancer2);
        ServerInterface.addFreelancer(freelancer3);

        CategoryPageViewModel.setSelectedCategory(Categories.BUSINESS_AND_FINANCE);

        CategoryPageViewModel.setSelectedSkills(new ArrayList<>());

        ArrayList<String> selected = CategoryPageViewModel.getSelectedSkills();
        selected.add("Editing");
        
        CategoryPageViewModel.setSelectedSkills(selected);

        List<String> unselected = CategoryPageViewModel.getUnselectedSkills();

        assertFalse(unselected.contains("Editing"), "Unselected list should not include 'Editing'.");
        
    }
}
