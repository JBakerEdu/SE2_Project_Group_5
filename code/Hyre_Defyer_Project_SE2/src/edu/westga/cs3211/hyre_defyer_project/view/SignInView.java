package edu.westga.cs3211.hyre_defyer_project.view;

import java.io.IOException;

import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * This is the code for the Sign in / create account Page 
 * 
 * @author Jacob Baker & Myles Debro
 * @version Spring 2025
 */
public class SignInView {
	
  	@FXML
  	private AnchorPane anchorPane;

    @FXML
    private ImageView accountBioImage;

    @FXML
    private Label accountLabel;

    @FXML
    private TextField confirmPasswordCreateAccountTextFeild;

    @FXML
    private Button createAccountButton;

    @FXML
    private Label dmLabel;

    @FXML
    private Label homeLabel;

    @FXML
    private Label hyreLabel;

    @FXML
    private TextField passwordCreateAccountTextFeild;

    @FXML
    private TextField passwordSignInTextFeild;

    @FXML
    private Button signInButton;

    @FXML
    private TextField userNameCreateAccountTextFeild;

    @FXML
    private TextField userNameSignInTextFeild;
    
    private SignInViewModel vm;

    @FXML
    void handleAccountClick(MouseEvent event) {
    	if (SignInViewModel.getCurrentUser() != null) {
    		GUIHelper.switchView(this.anchorPane, Views.ACCOUNT);
    	} else {
    		GUIHelper.switchView(this.anchorPane, Views.SIGNIN);
    	}
    }

    @FXML
    void handleCreateAccountClick(ActionEvent event) {
    	String username = this.userNameCreateAccountTextFeild.textProperty().getValue();
    	String password = this.passwordCreateAccountTextFeild.textProperty().getValue();
    	String confirmPassword = this.confirmPasswordCreateAccountTextFeild.textProperty().getValue();
    	if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
    		return;
    	}
      if (this.vm.createAccount(username, password, confirmPassword)) {
      	GUIHelper.switchView(this.anchorPane, Views.HOMEPAGE);
      } else {
      	GUIHelper.displayError("Unable to create account", "Username: " + username + "\n Password: " + password + "\n Confirm: " + confirmPassword);
      }
    }

    @FXML
    void handleDMClick(MouseEvent event) {
    	if (SignInViewModel.getCurrentUser() != null) {
    		GUIHelper.switchView(this.anchorPane, Views.DMS);
    	} else {
    		GUIHelper.switchView(this.anchorPane, Views.SIGNIN);
    	}
    }

    @FXML
    void handleHomeClick(MouseEvent event) {
    	GUIHelper.switchView(this.anchorPane, Views.HOMEPAGE);
    }

    @FXML
    void handleHyreClick(MouseEvent event) {

    }

    @FXML
    void handleSignInClick(ActionEvent event) throws IOException {
    	String username = this.userNameSignInTextFeild.textProperty().getValue();
    	String password = this.passwordSignInTextFeild.textProperty().getValue();
    	if (username.isEmpty() || password.isEmpty()) {
    		return;
    	}
      if (this.vm.signIn(username, password)) {
      	GUIHelper.switchView(this.anchorPane, Views.HOMEPAGE);
      } else {
      	GUIHelper.displayError("Unable to sign in", "Input doesn't match the data in our servers.");
      }
    }
    
    @FXML
    void initialize() {
    	this.setElements();
    	this.setListeners();
    	this.vm = new SignInViewModel();
    	if (SignInViewModel.getCurrentUser() != null) {
    		this.accountLabel.textProperty().setValue(SignInViewModel.getCurrentUser().getUserName());
    	} else {
    		this.accountLabel.textProperty().setValue("Account");
    	}
    }
    
    private void setElements() {
    	this.userNameCreateAccountTextFeild.textProperty().set("");
    	this.userNameSignInTextFeild.textProperty().set("");
    	this.passwordCreateAccountTextFeild.textProperty().set("");
    	this.passwordSignInTextFeild.textProperty().set("");
    	this.confirmPasswordCreateAccountTextFeild.textProperty().set("");
    	this.createAccountButton.disableProperty().setValue(true);
    }
    
    private void setListeners() {
    	this.confirmPasswordCreateAccountTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != oldValue) {
    			this.emptyRequiredFields();
    		}
    	});
    	this.passwordCreateAccountTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != oldValue) {
    			this.emptyRequiredFields();
    		}
    	});
    	this.userNameCreateAccountTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != oldValue) {
    			this.emptyRequiredFields();
    		}
    	});
    }
    
    private void emptyRequiredFields() {
    	if (this.confirmPasswordCreateAccountTextFeild.textProperty().isEmpty().get() 
    			|| this.passwordCreateAccountTextFeild.textProperty().isEmpty().get() 
    			|| this.userNameCreateAccountTextFeild.textProperty().isEmpty().get()) {
    		this.createAccountButton.disableProperty().setValue(true);
    	} else {
    		this.createAccountButton.disableProperty().setValue(false);
    	}
    }

}
