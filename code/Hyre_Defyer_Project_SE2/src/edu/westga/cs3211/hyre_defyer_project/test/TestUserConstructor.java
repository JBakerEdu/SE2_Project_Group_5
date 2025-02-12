package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.hyre_defyer_project.model.User;

public class TestUserConstructor {
	
	@Test
    void testUserDefaultConstructor() {
        User user = new User();
        assertFalse(user.isLoggedIn(), "User should not be logged in by default");
    }
	
	@Test
    void testUserOverriddedConstructor() {
        User user = new User(true);
        assertTrue(user.isLoggedIn(), "User should be logged in when initialized with true");

        User user2 = new User(false);
        assertFalse(user2.isLoggedIn(), "User should not be logged in when initialized with false");
    }

    @Test
    void testSetLoggedIn() {
        User user = new User(false);
        assertFalse(user.isLoggedIn(), "User should initially be logged out");

        user.setLoggedIn(true);
        assertTrue(user.isLoggedIn(), "User should be logged in after calling setLoggedIn(true)");

        user.setLoggedIn(false);
        assertFalse(user.isLoggedIn(), "User should be logged out after calling setLoggedIn(false)");
    }
}
