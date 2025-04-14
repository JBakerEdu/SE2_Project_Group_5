package edu.westga.cs3211.hyre_defyer_project.test.view_model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.view_model.AccountViewModel;
import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.FreelancerRoster;
import edu.westga.cs3211.hyre_defyer_project.model.RosterHelper;

class AccountPageViewModelTest {

    private User testUser;
    private Freelancer testFreelancer;
    private FreelancerRoster freelancerRoster;

    @BeforeEach
    void setUp() {
        new AccountViewModel();
        AccountViewModel.setUserSelectedToView(null);

        testUser = new User("testUser", "This Is the bio");
        testFreelancer = new Freelancer("testUser", "This Is the bio", Categories.BUSINESS_AND_FINANCE);

        RosterHelper.addFreelancerToServer(testFreelancer);
        freelancerRoster = RosterHelper.getFreelancerRoster();
        
    }
    
    @AfterEach
    void tearDown() {
    	RosterHelper.removeFreelancerFromServer(testFreelancer);
    }

    @Test
    void testSetAndGetUserSelectedToView() {
        AccountViewModel.setUserSelectedToView(testUser);
        assertEquals(testUser, AccountViewModel.getUserSelectedToView(), "The retrieved user should be the same as the one set.");
    }

    @Test
    void testUserSelectedToViewInitiallyNull() {
        assertNull(AccountViewModel.getUserSelectedToView(), "Initially, the selected user should be null.");
    }

    @Test
    void testSetUserSelectedToViewToNull() {
        AccountViewModel.setUserSelectedToView(testUser);
        assertNotNull(AccountViewModel.getUserSelectedToView(), "User should not be null after being set.");

        AccountViewModel.setUserSelectedToView(null);
        assertNull(AccountViewModel.getUserSelectedToView(), "User should be null after being explicitly set to null.");
    }

    @Test
    void testGetRoster() {
    	FreelancerRoster actualRoster = AccountViewModel.getRoster();
    	
    	List<Freelancer> expectedFreelancers = freelancerRoster.getAllFreelancers();
        List<Freelancer> actualFreelancers = actualRoster.getAllFreelancers();

        assertEquals(expectedFreelancers.size(), actualFreelancers.size(), "The number of freelancers should match.");

        assertTrue(actualFreelancers.containsAll(expectedFreelancers), "The actual roster should contain all expected freelancers.");
        assertTrue(expectedFreelancers.containsAll(actualFreelancers), "The expected roster should contain all actual freelancers.");
    }

    @Test
    void testIsSelectedUserFreelancerWhenUserIsFreelancer() {
        AccountViewModel.setUserSelectedToView(testUser);
        assertTrue(AccountViewModel.isSelectedUserFreelancer(), "User should be identified as a freelancer.");
    }

    @Test
    void testIsSelectedUserFreelancerWhenUserIsNotFreelancer() {
        User nonFreelancerUser = new User("nonFreelancer", "Another bio");
        AccountViewModel.setUserSelectedToView(nonFreelancerUser);
        assertFalse(AccountViewModel.isSelectedUserFreelancer(), "User should not be identified as a freelancer.");
    }

    @Test
    void testIsSelectedUserFreelancerWhenNoUserIsSelected() {
        AccountViewModel.setUserSelectedToView(null);
        assertFalse(AccountViewModel.isSelectedUserFreelancer(), "No user selected should return false for freelancer check.");
    }
}
