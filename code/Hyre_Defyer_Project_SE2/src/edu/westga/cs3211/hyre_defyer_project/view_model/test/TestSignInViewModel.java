package edu.westga.cs3211.hyre_defyer_project.view_model.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import edu.westga.cs3211.hyre_defyer_project.model.ServerActor;
import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;

public class TestSignInViewModel {

	@AfterAll void knockDown() {
		User remove = null;
		for (User user : ServerActor.getUsers()) {
			if (user.getUserName().equals("new account")
					|| user.getUserName().equals("user")
					|| user.getUserName().equals("not user")
					|| user.getUserName().equals("users")
					|| user.getUserName().equals("not user")) {
				remove = user;
			}
		}
		if (remove != null) {
			ServerActor.getUsers().remove(remove);
		} 
	}

	@Test
	public void testSigninSuccess() {
		SignInViewModel vm = new SignInViewModel();
		vm.createAccount("new account", "password", "password");
		assertTrue(vm.signIn("new account", "password"));
	}
	
	@Test
	public void testSigninFail() {
		SignInViewModel vm = new SignInViewModel();
		vm.createAccount("user", "password", "password");
		assertFalse(vm.signIn("not user", "password"));
	}
	
	@Test
	public void testCreateAccountSuccess() {
		SignInViewModel vm = new SignInViewModel();
		assertTrue(vm.createAccount("users", "1234567", "1234567"));
	}
	
	@Test
	public void testCreateAccountDuplicateUser() {
		SignInViewModel vm = new SignInViewModel();
		assertTrue(vm.createAccount("new user", "other password", "other password"));
		assertFalse(vm.createAccount("new user", "password", "password"));
	}
	
	@Test
	public void testSignOut() {
		SignInViewModel vm = new SignInViewModel();
		vm.signIn("user", "password");
		assertTrue(SignInViewModel.signOut());
	}
	
	@Test
	public void testIsSignedIn() {
		SignInViewModel vm = new SignInViewModel();
		vm.signIn("user", "password");
		assertTrue(SignInViewModel.isSignedIn());
	}
	
	@Test
	public void testIsNotSignedIn() {
		SignInViewModel vm = new SignInViewModel();
		vm.signIn("user", "password");
		SignInViewModel.signOut();
		assertFalse(SignInViewModel.isSignedIn());
	}
	
	@Test
	public void testUserDoesNotExist() {
		assertFalse(SignInViewModel.isSignedIn());
	}
}
