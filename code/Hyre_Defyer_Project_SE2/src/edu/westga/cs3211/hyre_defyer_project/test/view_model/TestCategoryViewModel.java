package edu.westga.cs3211.hyre_defyer_project.test.view_model;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
import edu.westga.cs3211.hyre_defyer_project.view_helpers.CategorySelectionHelper;
import edu.westga.cs3211.hyre_defyer_project.model.RosterHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;

/**
 * Unit tests for the CategoryViewModel class.
 */
public class TestCategoryViewModel {
    
    private CategorySelectionHelper categoryViewModel;
    private Freelancer freelancer1;
    private Freelancer freelancer2;
    private Freelancer freelancer3;

    @BeforeEach
    void setUp() {
        categoryViewModel = new CategorySelectionHelper();
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
    }

    @Test
    void testSetSelectedCategory() {
        categoryViewModel.setSelectedCategory(Categories.DEVELOPMENT_AND_IT);
        assertEquals(Categories.DEVELOPMENT_AND_IT.toUpperCase().replace(" ", "_"), categoryViewModel.getSelectedCategory(), "Selected category should be set correctly.");
    }

    @Test
    public void testGetFreelancersReturnsCorrectList() {
    	CategorySelectionHelper.selectedCategory = Categories.BUSINESS_AND_FINANCE;
    	List<Freelancer> roster = CategorySelectionHelper.getFreelancers();
    	assertTrue(roster.contains(freelancer1));
    	assertTrue(roster.contains(freelancer2));
    	assertTrue(roster.contains(freelancer3));
    }

    @Test
    public void testGetFreelancersWithNameAndSkillReturnsCorrectList() {
    	CategorySelectionHelper.selectedCategory = Categories.BUSINESS_AND_FINANCE;
    	List<Freelancer> roster = CategorySelectionHelper.getFreelancersWithNameAndSkill(" ", null);
    	assertTrue(roster.contains(freelancer1));
    	assertTrue(roster.contains(freelancer2));
    	assertTrue(roster.contains(freelancer3));
    }

    @Test
    public void testGetFreelancersWithNameAndSkillEmptyResult() {
    	CategorySelectionHelper.selectedCategory = Categories.BUSINESS_AND_FINANCE;
    	List<Freelancer> roster = CategorySelectionHelper.getFreelancersWithNameAndSkill("Someone", "Something");
    	assertFalse(roster.contains(freelancer1));
    	assertFalse(roster.contains(freelancer2));
    	assertFalse(roster.contains(freelancer3));
    }

}