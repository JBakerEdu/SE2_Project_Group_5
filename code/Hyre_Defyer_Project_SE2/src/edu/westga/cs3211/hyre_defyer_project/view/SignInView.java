package edu.westga.cs3211.hyre_defyer_project.view;

import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * This is the code for the Sign in / create account Page 
 * 
 * @author Jacob Baker
 * @version Spring 2025
 */
public class SignInView {

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
    void handleCreateAccountClick(ActionEvent event) {

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
    void handleSignInClick(ActionEvent event) {
    	this.vm.signIn(this.userNameSignInTextFeild.toString(), this.passwordSignInTextFeild.toString());
    }

}
