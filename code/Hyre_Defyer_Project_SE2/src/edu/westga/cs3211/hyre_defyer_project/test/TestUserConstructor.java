package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.hyre_defyer_project.model.User;

public class TestUserConstructor {
	
	@Test
    void testValidUserCreation() {
        User user = new User("Alice");
        assertEquals("Alice", user.getUserName());
        assertEquals("", user.getBio(), "Default bio should be empty");
    }

    @Test
    void testValidUserCreationWithBio() {
        User user = new User("Bob", "Bio");
        assertEquals("Bob", user.getUserName());
        assertEquals("Bio", user.getBio());
    }

    @Test
    void testInvalidUserNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new User(""));
        assertThrows(IllegalArgumentException.class, () -> new User("   "));
        assertThrows(IllegalArgumentException.class, () -> new User(null));
    }

    @Test
    void testInvalidBioThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new User("Alice", null));
    }

    @Test
    void testSetUserName() {
        User user = new User("Alice");
        user.setUserName("Charlie");
        assertEquals("Charlie", user.getUserName());

        assertThrows(IllegalArgumentException.class, () -> user.setUserName(""));
        assertThrows(IllegalArgumentException.class, () -> user.setUserName("   "));
        assertThrows(IllegalArgumentException.class, () -> user.setUserName(null));
    }

    @Test
    void testSetBio() {
        User user = new User("Alice");
        user.setBio("Loves coding in Java!");
        assertEquals("Loves coding in Java!", user.getBio());

        user.setBio("");
        assertEquals("", user.getBio());

        assertThrows(IllegalArgumentException.class, () -> user.setBio(null));
    }
}
