package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.hyre_defyer_project.model.DirectMessageHandler;
import edu.westga.cs3211.hyre_defyer_project.model.Message;
import edu.westga.cs3211.hyre_defyer_project.model.ServerActor;
import edu.westga.cs3211.hyre_defyer_project.model.User;

public class TempServerTest {
	@Test
	public void testCreateAccount() {
        ServerActor.createAccount("user1", "password1");
        User user = ServerActor.login("user1", "password1");
        assertNotNull(user);
        assertEquals("user1", user.getUserName());
        assertEquals("password1", user.getPassword());
        assertEquals("", user.getBio());
    }
	
	@Test
	public void testSendMessage() {
        ServerActor.createAccount("user1", "password1");
        ServerActor.createAccount("user2", "password2");
        ServerActor.createAccount("user3", "password3");
        User user1 = ServerActor.login("user1", "password1");
        User user2 = ServerActor.login("user2", "password2");
        User user3 = ServerActor.login("user3", "password3");
        DirectMessageHandler handler = new DirectMessageHandler(user1, user2);
        DirectMessageHandler handler1 = new DirectMessageHandler(user1, user3);
        Message message = new Message("Hello", user1, user2);
        Message message1 = new Message("World", user1, user3);
        handler1.sendMessage(message1);
        handler.sendMessage(message);
        assertEquals("Hello", handler.getFullMessageLog().get(0).getMessage());
        assertEquals("World", handler1.getFullMessageLog().get(0).getMessage());
    }
	
	@Test
	public void testDuplicateAccount() {
		assertTrue(ServerActor.createAccount("user1", "password1"));
		
        assertFalse(ServerActor.createAccount("user1", "password2"));

	}
	
	@Test
	public void testInvalidLogin() {
		assertTrue(ServerActor.createAccount("user1", "password1"));
		
        assertNull(ServerActor.login("user1", "notpassword"));

	}
	
}
