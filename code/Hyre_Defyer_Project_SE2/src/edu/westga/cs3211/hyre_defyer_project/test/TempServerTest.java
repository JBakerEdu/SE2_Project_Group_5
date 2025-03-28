package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.DirectMessageHandler;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.FreelancerRoster;
import edu.westga.cs3211.hyre_defyer_project.model.Message;
import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.server.Constants;
import edu.westga.cs3211.hyre_defyer_project.server.ServerCommunicator;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;

public class TempServerTest {
	@Test
	public void testCreateAccount() {
        ServerInterface.createAccount("user1", "password1");
        User user = ServerInterface.login("user1", "password1");
        assertNotNull(user);
        assertEquals("user1", user.getUserName());
        assertEquals("", user.getBio());
        
    }
	
	@Test
	public void testSendMessage() {
        ServerInterface.createAccount("paul", "password1");
        ServerInterface.createAccount("todd", "password2");
        ServerInterface.createAccount("karen", "password3");
        User user1 = ServerInterface.login("paul", "password1");
        User user2 = ServerInterface.login("todd", "password2");
        User user3 = ServerInterface.login("karen", "password3");
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
	public void testSendMultipleMessages() {
        ServerInterface.createAccount("Connor", "password1");
        ServerInterface.createAccount("Dokken", "password2");
        User user1 = ServerInterface.login("Connor", "password1");
        User user2 = ServerInterface.login("Dokken", "password2");
        DirectMessageHandler handler = new DirectMessageHandler(user1, user2);
        Message message = new Message("Hello", user1, user2);
        Message message1 = new Message("World", user1, user2);
        handler.sendMessage(message);
        handler.sendMessage(message1);
        assertEquals("Hello", handler.getFullMessageLog().get(0).getMessage());
        assertEquals("World", handler.getFullMessageLog().get(1).getMessage());
    }
	
	@Test
	public void testDuplicateAccount() {
		assertTrue(ServerInterface.createAccount("kate", "password1"));
		
        assertFalse(ServerInterface.createAccount("kate", "password2"));

	}
	
	@Test
	public void testInvalidLogin() {
		assertTrue(ServerInterface.createAccount("jacob", "password1"));
		
        assertNull(ServerInterface.login("jacob", "notpassword"));

	}
	
	@Test
	public void testAddFreelancer() {
		String[] skills = {"Java", "Python", "C++", "JavaScript", "SQL"};
		Freelancer freelancer = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills);
		
		Boolean response = ServerInterface.addFreelancer(freelancer);
		
		assertTrue(response);
	}
	
	@Test
	public void testGetFreelancersEmpty() {
		FreelancerRoster result = ServerInterface.getFreelancers();
		assertTrue(result.getAllFreelancers().isEmpty());
	}
	
	@Test
	public void testGetMultipleFreelancers() {
		String[] skills = {"Java", "Python", "C++", "JavaScript", "SQL"};
		Freelancer freelancer = new Freelancer("Larry", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills);

		ServerInterface.addFreelancer(freelancer);
		
		String[] skills2 =  {"Ruby", "Go", "Swift", "HTML", "CSS"};
		Freelancer freelancer2 = new Freelancer("David", "Moderate Developer", Categories.DEVELOPMENT_AND_IT, skills2);

		ServerInterface.addFreelancer(freelancer2);
		FreelancerRoster result = ServerInterface.getFreelancers();
		
		assertFalse(result.getAllFreelancers().isEmpty());
		assertTrue(result.getAllFreelancers().get(0).getUserName().equals("Larry"));
		assertTrue(result.getAllFreelancers().get(1).getUserName().equals("David"));
	}
	
}
