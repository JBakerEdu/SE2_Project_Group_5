package edu.westga.cs3211.hyre_defyer_project.test.view_model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.westga.cs3211.hyre_defyer_project.server.Constants;
import edu.westga.cs3211.hyre_defyer_project.server.ServerCommunicator;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSignInViewModel {

	@Test
	public void testSigninSuccess() {
		SignInViewModel vm = new SignInViewModel();
		vm.createAccount("new account", "password", "password");
		assertTrue(vm.signIn("new account", "password"));
		SignInViewModel.signOut();
	}
	
	@Test
	public void testSigninFail() {
		SignInViewModel vm = new SignInViewModel();
		vm.createAccount("user", "password", "password");
		assertFalse(vm.signIn("not user", "password"));
		SignInViewModel.signOut();
	}
	
	@Test
	public void testCreateAccountSuccess() {
		SignInViewModel vm = new SignInViewModel();
		assertTrue(vm.createAccount("user13", "1234567", "1234567"));
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
		vm.createAccount("user", "password", "password");
		vm.signIn("user", "password");
		assertTrue(SignInViewModel.isSignedIn());
		assertEquals("user", SignInViewModel.getCurrentUser().getUserName());
		SignInViewModel.signOut();
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
	
	@Test
	public void zTestServerFailedConnectionHandling() {
		// This test is simply for code coverage, it is not a functional test
		// The server has to fail its connection for this test to pass, thus it needs to be run last hence the z in its name
		// and it being in the wrong test class
		ServerCommunicator.changePortForCoverage(1234);
		ServerCommunicator obj = new ServerCommunicator();
		ServerInterface obj1 = new ServerInterface();
		Constants obj2 = new Constants();
		assertThrows(Exception.class, () -> ServerInterface.createAccount("user", "password"));
		
	}
}
