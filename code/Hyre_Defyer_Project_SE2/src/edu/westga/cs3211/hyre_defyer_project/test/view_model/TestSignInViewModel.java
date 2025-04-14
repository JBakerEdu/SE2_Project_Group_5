package edu.westga.cs3211.hyre_defyer_project.test.view_model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
import edu.westga.cs3211.hyre_defyer_project.view_helpers.UserSignInHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSignInViewModel {

	@Test
	public void testSigninSuccess() {
		UserSignInHelper vm = new UserSignInHelper();
		vm.createAccount("new account", "password", "password");
		assertTrue(vm.signIn("new account", "password"));
		UserSignInHelper.signOut();
	}
	
	@Test
	public void testSigninFail() {
		UserSignInHelper vm = new UserSignInHelper();
		vm.createAccount("user", "password", "password");
		assertFalse(vm.signIn("not user", "password"));
		UserSignInHelper.signOut();
	}
	
	@Test
	public void testCreateAccountSuccess() {
		UserSignInHelper vm = new UserSignInHelper();
		assertTrue(vm.createAccount("user13", "1234567", "1234567"));
		
		ServerInterface.deleteUser("user13");
	}
	
	@Test
	public void testCreateAccountDuplicateUser() {
		UserSignInHelper vm = new UserSignInHelper();
		assertTrue(vm.createAccount("new user", "other password", "other password"));
		assertFalse(vm.createAccount("new user", "password", "password"));
		
		ServerInterface.deleteUser("new user");
	}
	
	@Test
	public void testSignOut() {
		UserSignInHelper vm = new UserSignInHelper();
		vm.signIn("user", "password");
		assertTrue(UserSignInHelper.signOut());
	}
	
	@Test
	public void testIsSignedIn() {
		UserSignInHelper vm = new UserSignInHelper();
		vm.createAccount("user", "password", "password");
		vm.signIn("user", "password");
		assertTrue(UserSignInHelper.isSignedIn());
		assertEquals("user", UserSignInHelper.getCurrentUser().getUserName());
		UserSignInHelper.signOut();
	}
	
	@Test
	public void testIsNotSignedIn() {
		UserSignInHelper vm = new UserSignInHelper();
		vm.signIn("user", "password");
		UserSignInHelper.signOut();
		assertFalse(UserSignInHelper.isSignedIn());
	}
	
	@Test
	public void testUserDoesNotExist() {
		assertFalse(UserSignInHelper.isSignedIn());
	}		
}
