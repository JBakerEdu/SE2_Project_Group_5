package edu.westga.cs3211.hyre_defyer_project.view;

import java.io.IOException;

import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.AccountViewModel;
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
    private Label passwordWarningText;

    @FXML
    private Button createAccountButton;

    @FXML
    private Label dmLabel;

    @FXML
    private Label homeLabel;

    @FXML
    private Label aboutLabel;

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
    		AccountViewModel.setUserSelectedToView(SignInViewModel.getCurrentUser());
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
      if (this.vm.createAccount(username, password, confirmPassword)) {
      	GUIHelper.switchView(this.anchorPane, Views.HOMEPAGE);
      } else {
      	GUIHelper.displayError("Unable to create account", "Username is taken. Please try another.");
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
    void handleAboutHyreClick(MouseEvent event) {
    	GUIHelper.switchView(this.anchorPane, Views.ABOUT_HYRE);
    }

    @FXML
    void handleSignInClick(ActionEvent event) throws IOException {
    	String username = this.userNameSignInTextFeild.textProperty().getValue();
    	String password = this.passwordSignInTextFeild.textProperty().getValue();
      if (this.vm.signIn(username, password)) {
      	GUIHelper.switchView(this.anchorPane, Views.HOMEPAGE);
      } else {
      	GUIHelper.displayError("Log in Failed", "Incorrect username or password.");
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
    	this.signInButton.disableProperty().setValue(true);
    	this.createAccountButton.disableProperty().setValue(true);
    }
    
    private void setListeners() {
    	this.confirmPasswordCreateAccountTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != oldValue) {
    			this.invalidCreateAccountRequiredFields();
    		}
    	});
    	this.passwordCreateAccountTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != oldValue) {
    			this.invalidCreateAccountRequiredFields();
    		}
    	});
    	this.userNameCreateAccountTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != oldValue) {
    			this.invalidCreateAccountRequiredFields();
    		}
    	});
    	this.passwordSignInTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != oldValue) {
    			this.emptySignInRequiredFields();
    		}
    	});
    	this.userNameSignInTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != oldValue) {
    			this.emptySignInRequiredFields();
    		}
    	});
    }
    
    private void invalidCreateAccountRequiredFields() {
    	if (this.confirmPasswordCreateAccountTextFeild.textProperty().isEmpty().get() 
    			|| this.passwordCreateAccountTextFeild.textProperty().isEmpty().get() 
    			|| this.userNameCreateAccountTextFeild.textProperty().isEmpty().get()) {
    		this.createAccountButton.disableProperty().setValue(true);
    		this.passwordWarningText.visibleProperty().setValue(false);
    	} else if (this.passwordsDontMatch()) {
    		this.createAccountButton.disableProperty().setValue(true);
    		this.passwordWarningText.visibleProperty().setValue(true);
    	} else {
    		this.createAccountButton.disableProperty().setValue(false);
    		this.passwordWarningText.visibleProperty().setValue(false);
    	}
    }
    
    private void emptySignInRequiredFields() {
    	if (this.passwordSignInTextFeild.textProperty().isEmpty().get()
    			|| this.userNameSignInTextFeild.textProperty().isEmpty().get()) {
    		this.signInButton.disableProperty().setValue(true);
    	} else {
    		this.signInButton.disableProperty().setValue(false);
    	}
    }
    
    private boolean passwordsDontMatch() {
    	if (!this.confirmPasswordCreateAccountTextFeild.textProperty().get().equals(this.passwordCreateAccountTextFeild.textProperty().get())) {
    		return true;
    	}
    	return false;
    }

}
