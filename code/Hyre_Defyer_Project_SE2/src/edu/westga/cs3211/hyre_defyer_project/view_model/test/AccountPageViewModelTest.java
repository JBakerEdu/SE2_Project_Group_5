package edu.westga.cs3211.hyre_defyer_project.view_model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.FreelancerRoster;
import edu.westga.cs3211.hyre_defyer_project.view.GUIHelper;
import edu.westga.cs3211.hyre_defyer_project.view_model.AccountPageViewModel;

import java.util.ArrayList;
import java.util.List;

class AccountPageViewModelTest {

    private User testUser;
    private Freelancer testFreelancer;
    private FreelancerRoster freelancerRoster;

    @BeforeEach
    void setUp() {
        new AccountPageViewModel();
        AccountPageViewModel.setUserSelectedToView(null);

        testUser = new User("testUser", "This Is the bio");
        testFreelancer = new Freelancer("testUser", "This Is the bio", Categories.BUSINESS_AND_FINANCE);

        freelancerRoster = GUIHelper.getFreelancerRoster();
        freelancerRoster.addFreelancer(testFreelancer);
    }

    @Test
    void testSetAndGetUserSelectedToView() {
        AccountPageViewModel.setUserSelectedToView(testUser);
        assertEquals(testUser, AccountPageViewModel.getUserSelectedToView(), "The retrieved user should be the same as the one set.");
    }

    @Test
    void testUserSelectedToViewInitiallyNull() {
        assertNull(AccountPageViewModel.getUserSelectedToView(), "Initially, the selected user should be null.");
    }

    @Test
    void testSetUserSelectedToViewToNull() {
        AccountPageViewModel.setUserSelectedToView(testUser);
        assertNotNull(AccountPageViewModel.getUserSelectedToView(), "User should not be null after being set.");

        AccountPageViewModel.setUserSelectedToView(null);
        assertNull(AccountPageViewModel.getUserSelectedToView(), "User should be null after being explicitly set to null.");
    }

    @Test
    void testGetRoster() {
        assertEquals(freelancerRoster, AccountPageViewModel.getRoster(), "The retrieved roster should match the expected roster.");
    }

    @Test
    void testIsSelectedUserFreelancerWhenUserIsFreelancer() {
        AccountPageViewModel.setUserSelectedToView(testUser);
        assertTrue(AccountPageViewModel.isSelectedUserFreelancer(), "User should be identified as a freelancer.");
    }

    @Test
    void testIsSelectedUserFreelancerWhenUserIsNotFreelancer() {
        User nonFreelancerUser = new User("nonFreelancer", "Another bio");
        AccountPageViewModel.setUserSelectedToView(nonFreelancerUser);
        assertFalse(AccountPageViewModel.isSelectedUserFreelancer(), "User should not be identified as a freelancer.");
    }

    @Test
    void testIsSelectedUserFreelancerWhenNoUserIsSelected() {
        AccountPageViewModel.setUserSelectedToView(null);
        assertFalse(AccountPageViewModel.isSelectedUserFreelancer(), "No user selected should return false for freelancer check.");
    }
}
