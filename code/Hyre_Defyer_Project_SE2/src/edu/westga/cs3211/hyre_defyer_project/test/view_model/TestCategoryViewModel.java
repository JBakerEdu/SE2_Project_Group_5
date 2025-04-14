package edu.westga.cs3211.hyre_defyer_project.test.view_model;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
import edu.westga.cs3211.hyre_defyer_project.view_model.CategoryPageViewModel;
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
    
    private CategoryPageViewModel categoryViewModel;
    private Freelancer freelancer1;
    private Freelancer freelancer2;
    private Freelancer freelancer3;

    @BeforeEach
    void setUp() {
        categoryViewModel = new CategoryPageViewModel();
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
    	CategoryPageViewModel.selectedCategory = Categories.BUSINESS_AND_FINANCE;
    	List<Freelancer> roster = CategoryPageViewModel.getFreelancers();
    	assertTrue(roster.contains(freelancer1));
    	assertTrue(roster.contains(freelancer2));
    	assertTrue(roster.contains(freelancer3));
    }

    @Test
    public void testGetFreelancersWithNameAndSkillReturnsCorrectList() {
    	CategoryPageViewModel.selectedCategory = Categories.BUSINESS_AND_FINANCE;
    	List<Freelancer> roster = CategoryPageViewModel.getFreelancersWithNameAndSkill(" ", null);
    	assertTrue(roster.contains(freelancer1));
    	assertTrue(roster.contains(freelancer2));
    	assertTrue(roster.contains(freelancer3));
    }

    @Test
    public void testGetFreelancersWithNameAndSkillEmptyResult() {
    	CategoryPageViewModel.selectedCategory = Categories.BUSINESS_AND_FINANCE;
    	List<Freelancer> roster = CategoryPageViewModel.getFreelancersWithNameAndSkill("Someone", "Something");
    	assertFalse(roster.contains(freelancer1));
    	assertFalse(roster.contains(freelancer2));
    	assertFalse(roster.contains(freelancer3));
    }

}