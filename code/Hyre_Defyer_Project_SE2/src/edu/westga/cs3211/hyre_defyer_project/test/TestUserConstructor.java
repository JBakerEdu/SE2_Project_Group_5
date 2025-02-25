package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.hyre_defyer_project.model.User;

public class TestUserConstructor {
	
	@Test
    void testValidUserCreation() {
        User user = new User("Alice", "securePass123");
        assertEquals("Alice", user.getUserName());
        assertEquals("securePass123", user.getPassword());
        assertEquals("", user.getBio(), "Default bio should be empty");
    }

    @Test
    void testValidUserCreationWithBio() {
        User user = new User("Bob", "strongPass456", "Bio");
        assertEquals("Bob", user.getUserName());
        assertEquals("strongPass456", user.getPassword());
        assertEquals("Bio", user.getBio());
    }

    @Test
    void testInvalidUserNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new User("", "password123"));
        assertThrows(IllegalArgumentException.class, () -> new User("   ", "password123"));
        assertThrows(IllegalArgumentException.class, () -> new User(null, "password123"));
    }

    @Test
    void testInvalidPasswordThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new User("Alice", ""));
        assertThrows(IllegalArgumentException.class, () -> new User("Alice", "   "));
        assertThrows(IllegalArgumentException.class, () -> new User("Alice", null));
    }

    @Test
    void testInvalidBioThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new User("Alice", "password123", null));
    }

    @Test
    void testSetUserName() {
        User user = new User("Alice", "password123");
        user.setUserName("Charlie");
        assertEquals("Charlie", user.getUserName());

        assertThrows(IllegalArgumentException.class, () -> user.setUserName(""));
        assertThrows(IllegalArgumentException.class, () -> user.setUserName("   "));
        assertThrows(IllegalArgumentException.class, () -> user.setUserName(null));
    }

    @Test
    void testSetPassword() {
        User user = new User("Alice", "password123");
        user.setPassword("newSecurePass");
        assertEquals("newSecurePass", user.getPassword());

        assertThrows(IllegalArgumentException.class, () -> user.setPassword(""));
        assertThrows(IllegalArgumentException.class, () -> user.setPassword("   "));
        assertThrows(IllegalArgumentException.class, () -> user.setPassword(null));
    }

    @Test
    void testSetBio() {
        User user = new User("Alice", "password123");
        user.setBio("Loves coding in Java!");
        assertEquals("Loves coding in Java!", user.getBio());

        user.setBio(""); // Empty bio is allowed
        assertEquals("", user.getBio());

        assertThrows(IllegalArgumentException.class, () -> user.setBio(null));
    }
}
