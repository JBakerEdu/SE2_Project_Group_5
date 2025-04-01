package edu.westga.cs3211.hyre_defyer_project.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.westga.cs3211.hyre_defyer_project.model.DirectMessageHandler;
import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import javafx.collections.ObservableList;

public class TestDirectMessageHandler {
	
	@Test
	public void testDeleteChat() {
		SignInViewModel signinVM = new SignInViewModel();
		signinVM.signIn("admin", "1234567");
		ServerInterface.addMessageableUser(new User("admin", "1234567"), new User("User1", "pass"));
		DirectMessageHandler dmHandler = new DirectMessageHandler(new User("admin", "1234567"), new User("User1", "pass"));
		assertTrue(this.isInContact(dmHandler.getContactList().getValue(), new User("User1", "pass")));
		dmHandler.deleteChat(new User("admin", "1234567"), new User("User1", "pass"));
		assertFalse(this.isInContact(dmHandler.getContactList(), new User("User1", "pass")));
	}

	private boolean isInContact(ObservableList<User> users, User goalUser) {
		boolean output = false;
		for (User currUser : users) {
			if (goalUser.getUserName().equals(currUser.getUserName())) {
				output = true;
			}
		}
		return output;
	}
}
