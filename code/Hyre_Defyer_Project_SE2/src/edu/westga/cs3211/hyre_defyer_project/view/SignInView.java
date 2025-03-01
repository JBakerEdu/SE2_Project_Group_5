package edu.westga.cs3211.hyre_defyer_project.view;

import java.io.IOException;

import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    }

    @FXML
    void handleCreateAccountClick(ActionEvent event) throws IOException {
    	String username = this.userNameCreateAccountTextFeild.textProperty().getValue();
    	String password = this.passwordCreateAccountTextFeild.textProperty().getValue();
    	String confirmPassword = this.confirmPasswordCreateAccountTextFeild.textProperty().getValue();
    	if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
    		return;
    	}
      if (this.vm.createAccount(username, password, confirmPassword)) {
      	Stage stage = (Stage) this.anchorPane.getScene().getWindow();
      	GUIHelper.switchView(stage, "view/HomePageView.fxml");
      } else {
      	GUIHelper.displayError("Unable to create account", "Username: " + username + "\n Password: " + password + "\n Confirm: " + confirmPassword);
      }
    }

    @FXML
    void handleDMClick(MouseEvent event) {

    }

    @FXML
    void handleHomeClick(MouseEvent event) {

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
      	Stage stage = (Stage) this.anchorPane.getScene().getWindow();
      	GUIHelper.switchView(stage, "view/HomePageView.fxml");
      } else {
      	GUIHelper.displayError("Unable to sign in", "Input doesn't match the data in our servers.");
      }
    }
    
    @FXML
    void initialize() {
    	this.userNameCreateAccountTextFeild.textProperty().set("");
    	this.userNameSignInTextFeild.textProperty().set("");
    	this.passwordCreateAccountTextFeild.textProperty().set("");
    	this.passwordSignInTextFeild.textProperty().set("");
    	this.confirmPasswordCreateAccountTextFeild.textProperty().set("");
      this.vm = new SignInViewModel();
    }

}
