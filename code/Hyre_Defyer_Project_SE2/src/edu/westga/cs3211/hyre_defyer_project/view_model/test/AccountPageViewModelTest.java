package edu.westga.cs3211.hyre_defyer_project.view_model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.view_model.AccountPageViewModel;

class AccountPageViewModelTest {

    @BeforeEach
    void setUp() {
        AccountPageViewModel.setUserSelectedToView(null);
    }

    @Test
    void testSetAndGetUserSelectedToView() {
        User testUser = new User("testUser", "This Is the bio");

        AccountPageViewModel.setUserSelectedToView(testUser);

        assertEquals(testUser, AccountPageViewModel.getUserSelectedToView(), "The retrieved user should be the same as the one set.");
    }

    @Test
    void testUserSelectedToViewInitiallyNull() {
        assertNull(AccountPageViewModel.getUserSelectedToView(), "Initially, the selected user should be null.");
    }

    @Test
    void testSetUserSelectedToViewToNull() {
        User testUser = new User("testUser", "This is the Bio");

        AccountPageViewModel.setUserSelectedToView(testUser);
        assertNotNull(AccountPageViewModel.getUserSelectedToView(), "User should not be null after being set.");

        AccountPageViewModel.setUserSelectedToView(null);
        assertNull(AccountPageViewModel.getUserSelectedToView(), "User should be null after being explicitly set to null.");
    }
}

