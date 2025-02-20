package edu.westga.cs3211.hyre_defyer_project.view_model.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;

public class TestSignInViewModel {

	@Test
	public void testInvalidInput() {
		SignInViewModel vm = new SignInViewModel();
		assertFalse(vm.signIn("hyredefyer_admin", "wrong password"));
	}
	
	@Test
	public void testValidInput() {
		SignInViewModel vm = new SignInViewModel();
		assertTrue(vm.signIn("hyredefyer_admin", "11111"));
	}
}
