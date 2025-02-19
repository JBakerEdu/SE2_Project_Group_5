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
        ServerActor server = new ServerActor();
        server.createAccount("user1", "password1");
        User user = server.login("user1", "password1");
        assertNotNull(user);
        assertEquals("user1", user.getUserName());
        assertEquals("password1", user.getPassword());
        assertEquals("Bio", user.getBio());
    }
	
	@Test
	public void testSendMessage() {
        ServerActor server = new ServerActor();
        server.createAccount("user1", "password1");
        server.createAccount("user2", "password2");
        server.createAccount("user3", "password3");
        User user1 = server.login("user1", "password1");
        User user2 = server.login("user2", "password2");
        User user3 = server.login("user3", "password3");
        DirectMessageHandler handler = new DirectMessageHandler(user1, user2, server);
        DirectMessageHandler handler1 = new DirectMessageHandler(user1, user3, server);
        Message message = new Message("Hello", user1, user2);
        Message message1 = new Message("World", user1, user3);
        handler1.sendMessage(message1);
        handler.sendMessage(message);
        assertEquals("Hello", handler.getFullMessageLog().get(0).getMessage());
        assertEquals("World", handler1.getFullMessageLog().get(0).getMessage());
    }
	
	@Test
	public void testDuplicateAccount() {
		ServerActor server = new ServerActor();
		assertTrue(server.createAccount("user1", "password1"));
		
        assertFalse(server.createAccount("user1", "password2"));

	}
	
	@Test
	public void testInvalidLogin() {
		ServerActor server = new ServerActor();
		assertTrue(server.createAccount("user1", "password1"));
		
        assertNull(server.login("user1", "notpassword"));

	}
	
}
