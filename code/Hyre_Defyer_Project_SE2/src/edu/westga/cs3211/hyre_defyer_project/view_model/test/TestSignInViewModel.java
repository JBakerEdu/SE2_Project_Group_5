package edu.westga.cs3211.hyre_defyer_project.view_model.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;

public class TestSignInViewModel {

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
		assertTrue(vm.createAccount("new_user", "1234567", "1234567"));
	}
	
	@Test
	public void testCreateAccountDuplicateUser() {
		SignInViewModel vm = new SignInViewModel();
		assertTrue(vm.createAccount("new_user", "other password", "other password"));
		assertFalse(vm.createAccount("new_user", "password", "password"));
	}
	
	@Test
	public void testCreateAccountWrongConfirmationPassword() {
		SignInViewModel vm = new SignInViewModel();
		assertFalse(vm.createAccount("new_user", "other password", "password"));
	}
	
	@Test
	public void testSignOut() {
		SignInViewModel vm = new SignInViewModel();
		vm.createAccount("user", "password", "password");
		assertTrue(SignInViewModel.signOut());
	}
	
	@Test
	public void testIsSignedIn() {
		SignInViewModel vm = new SignInViewModel();
		vm.createAccount("user", "password", "password");
		assertTrue(SignInViewModel.isSignedIn());
	}
	
	@Test
	public void testIsNotSignedIn() {
		SignInViewModel vm = new SignInViewModel();
		vm.createAccount("user", "password", "password");
		SignInViewModel.signOut();
		assertFalse(SignInViewModel.isSignedIn());
	}
	
	@Test
	public void testUserDoesNotExist() {
		assertFalse(SignInViewModel.isSignedIn());
	}
}
