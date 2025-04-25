package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
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

public class TestServerInterface {
	@Test
	public void testCreateAccount() {
        ServerInterface.createAccount("user1", "password1");
        User user = ServerInterface.login("user1", "password1");
        assertNotNull(user);
        assertEquals("user1", user.getUserName());
        assertEquals("", user.getBio());
        
    }
	
	@Test
	public void testSetUserBio() {
		User user = ServerInterface.login("Alice", "password");
		assertTrue(ServerInterface.setUserBio(user, "Bio"));
		assertEquals("Bio", user.getBio());
	}
	
	@Test
	public void testSetUserBioForNonExistentUser() {
		User user1 = new User("ddummyy", "pass");
		assertFalse(ServerInterface.setUserBio(user1, "lol"));
	}
	
	@Test
	public void testDeleteUser() {
		ServerInterface.createAccount("dummy account", "pass");
		assertTrue(ServerInterface.deleteUser("dummy account"));
	}
	
	@Test
	public void testDeleteChat() {
		ServerInterface.createAccount("dummy account", "pass");
		User user1 = new User("dummy account", "bio");
		User user2 = new User("Alice", "bio");
		ServerInterface.addMessageableUser(user1, user2);
		DirectMessageHandler handler = new DirectMessageHandler(user1, user2);
		handler.deleteChat(user1, user2);
		assertFalse(ServerInterface.getMessagableUsers(user1).contains(user2));
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
        assertEquals(message.toString(), "Connor: Hello");
        assertEquals(user1.toString(), "Connor");
        assertEquals("Hello", handler.getFullMessageLog().get(0).getMessage());
        assertEquals("World", handler.getFullMessageLog().get(1).getMessage());
    }
	
	@Test
	public void testDuplicateAccount() {
		assertTrue(ServerInterface.createAccount("kate", "password1"));
		
        assertFalse(ServerInterface.createAccount("kate", "password2"));
        
        ServerInterface.deleteUser("kate");

	}
	
	@Test
	public void testInvalidLogin() {
		assertTrue(ServerInterface.createAccount("jacob", "password1"));
		
        assertNull(ServerInterface.login("jacob", "notpassword"));
        
        ServerInterface.deleteUser("jacob");

	}
	
	@Test
	public void testGetMessageableUsers() {
		ServerInterface.createAccount("Alec", "password");
		ServerInterface.createAccount("Edgar", "password");
		User user1 = ServerInterface.login("Alec", "password");
        User user2 = ServerInterface.login("Edgar", "password");
        ServerInterface.addMessageableUser(user1, user2);
        List<User> users = ServerInterface.getMessagableUsers(user1);
        assertNotNull(users);
        assertTrue(users.get(0).getUserName().equals("admin"));
        assertTrue(users.get(1).getUserName().equals("Edgar"));
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testForCodeCoverage() {
		/* This test is simply for code coverage, it is not a functional test
		   These are only accessed statically, but code coverage requires instantiation 
		   on everything for some reason (Even a constants class. Also the 
		   last line forces an error to cover the exception block
		*/
		ServerCommunicator obj = new ServerCommunicator();
		ServerInterface obj1 = new ServerInterface();
		Constants obj2 = new Constants();
		assertTrue(ServerCommunicator.sendRequestToServer(null).equals("ERROR"));
	}

	
	@Test
    public void testAddNullFreelancer() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ServerInterface.addFreelancer(null);
        });

        assertEquals("freelancer to add can not be null.",exception.getMessage());
    }
	
	@Test
	public void testAddFreelancer() {
		String[] skills = {"Java", "Python", "C++", "JavaScript", "SQL"};
		Freelancer freelancer = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills);
		
		Boolean response = ServerInterface.addFreelancer(freelancer);
		ServerInterface.removeFreelancer(freelancer);
		
		assertTrue(response);
	}
	
	@Test
	public void testGetMultipleFreelancers() {
		FreelancerRoster result = ServerInterface.getFreelancers();
		
		assertFalse(result.getAllFreelancers().isEmpty());
		assertTrue(result.getAllFreelancers().size() > 3);
	}
	
	@Test
	public void testRemoveFreelancer() {
		String[] skills = {"Java", "Python", "C++", "JavaScript", "SQL"};
		Freelancer freelancer = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills);
		ServerInterface.addFreelancer(freelancer);
		Boolean response = ServerInterface.removeFreelancer(freelancer);
		
		assertTrue(response);
	}
	
	@Test
    public void testRemoveFreelancerThrowsException() {
        String[] skills = {"Java", "Python", "C++", "JavaScript", "SQL"};
        Freelancer freelancer = new Freelancer("JohnDoe", "Experienced Developer", Categories.DEVELOPMENT_AND_IT, skills);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ServerInterface.removeFreelancer(freelancer);
        });

        assertEquals("Freelancer not found in roster.",exception.getMessage());
    }
	
	@Test
    public void testRemoveNullFreelancer() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ServerInterface.removeFreelancer(null);
        });

        assertEquals("freelancer to remove can not be null.",exception.getMessage());
    }
	
	@Test
	public void testGetCatagories() {
		List<String> result = ServerInterface.getCategories();
		
		assertTrue(result.contains(Categories.BUSINESS_AND_FINANCE.toUpperCase().replace("_", " ")));
	}
  
	@Test
	public void testRateNewFreelancer() {
		Freelancer freelancer = new Freelancer("tmp", "", "");
		assertTrue(ServerInterface.addFreelancer(freelancer));
		assertTrue(ServerInterface.getFreelancers().getAllFreelancers().contains(freelancer));
		assertTrue(ServerInterface.createAccount("userrr", "pass"));
		User user = ServerInterface.login("userrr", "pass");
		boolean response = ServerInterface.rateFreelancer(user, freelancer, 3);

		assertTrue(response);
		assertTrue(ServerInterface.deleteUser(user.getUserName()));
		assertTrue(ServerInterface.removeFreelancer(freelancer));
	}
	
	@Test
	public void testRateExistingFreelancer() {
		assertTrue(ServerInterface.createAccount("abcd", "pass"));
		User user = ServerInterface.login("abcd", "pass");
		Freelancer freelancer1 = ServerInterface.getFreelancers().getFreelancersByName("Alice").get(0);
		boolean response = ServerInterface.rateFreelancer(user, freelancer1, 3);
		
		assertTrue(response);
		assertTrue(ServerInterface.deleteUser(user.getUserName()));
	}
	
	@Test
	public void testRateNonFreelancer() {
		assertTrue(ServerInterface.createAccount("abcde", "pass"));
		User user = ServerInterface.login("abcde", "pass");
		Freelancer freelancer = new Freelancer("tmp12", "", "");
		assertFalse(ServerInterface.rateFreelancer(user, freelancer, 2));
		assertTrue(ServerInterface.deleteUser(user.getUserName()));
	}
	
	@Test
	public void testFreelancerWithMultipleRatings() {
		Freelancer freelancer1 = new Freelancer("Dummy Account 123", "", "");
		assertTrue(ServerInterface.addFreelancer(freelancer1));
		assertTrue(ServerInterface.getFreelancers().getAllFreelancers().contains(freelancer1));
		assertTrue(ServerInterface.createAccount("user tmp", "pass"));
		User user = ServerInterface.login("user tmp", "pass");
		boolean isSuccessful = ServerInterface.rateFreelancer(user, freelancer1, 3);
		String freelancersRating = ServerInterface.getRating(freelancer1);
		
		assertTrue(isSuccessful);
		assertEquals("3", freelancersRating);
		
		assertTrue(ServerInterface.createAccount("user tmp1", "pass"));
		user = ServerInterface.login("user tmp1", "pass");
		
		isSuccessful = ServerInterface.rateFreelancer(user, freelancer1, 4);
		freelancersRating = ServerInterface.getRating(freelancer1);
		
		assertEquals(true, isSuccessful);
		assertEquals("3.5", freelancersRating);
		
		assertTrue(ServerInterface.createAccount("user tmp2", "pass"));
		user = ServerInterface.login("user tmp2", "pass");
		
		isSuccessful = ServerInterface.rateFreelancer(user, freelancer1, 3);
		freelancersRating = ServerInterface.getRating(freelancer1);
		
		assertEquals(true, isSuccessful);
		assertEquals("3.3", freelancersRating);
		assertTrue(ServerInterface.removeFreelancer(freelancer1));
		assertTrue(ServerInterface.deleteUser("user tmp"));
		assertTrue(ServerInterface.deleteUser("user tmp1"));
		assertTrue(ServerInterface.deleteUser("user tmp2"));
	}
	
	@Test
	public void testGetRatingNoRatings() {
		Freelancer freelancer1 = new Freelancer("Dummy Account 1234", "", "");
		assertTrue(ServerInterface.addFreelancer(freelancer1));
		assertTrue(ServerInterface.getFreelancers().getAllFreelancers().contains(freelancer1));
		assertEquals("n/a", ServerInterface.getRating(freelancer1));
		assertTrue(ServerInterface.removeFreelancer(freelancer1));
	}
	
	@Test
	public void testGetRatingWithRatings() {
		Freelancer freelancer1 = new Freelancer("Dummy Account 12345", "", "");
		assertTrue(ServerInterface.addFreelancer(freelancer1));
		assertTrue(ServerInterface.getFreelancers().getAllFreelancers().contains(freelancer1));
		assertTrue(ServerInterface.createAccount("user tmp124", "pass"));
		User user = ServerInterface.login("user tmp124", "pass");
		ServerInterface.rateFreelancer(user, freelancer1, 3);
		
		String response = ServerInterface.getRating(freelancer1);
		assertEquals("3", response);
		assertTrue(ServerInterface.removeFreelancer(freelancer1));
	}
}
