package edu.westga.cs3211.hyre_defyer_project.view;

import edu.westga.cs3211.hyre_defyer_project.view_helpers.ViewedUserHelper;
import edu.westga.cs3211.hyre_defyer_project.view_helpers.UserSignInHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

/**
 * Codebehind for AboutHyrePageView
 * 
 * @author Jacob Baker
 * @version Spring 2025
 */
public class AboutHyrePageView {
	@FXML
    private Label aboutLabel;

    @FXML
    private ImageView accountBioImage;

    @FXML
    private Label accountLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label dmLabel;

    @FXML
    private Label homeLabel;

    @FXML
    private Button signInButton;
    
    @FXML
    private Label signInLabel;
    
    @FXML
    private Line signInBar;

    @FXML
    void handleAccountClick(MouseEvent event) {
    	if (UserSignInHelper.getCurrentUser() != null) {
    		ViewedUserHelper.setUserSelectedToView(UserSignInHelper.getCurrentUser());
    		GUIHelper.switchView(this.anchorPane, Views.ACCOUNT);
    	} else {
    		GUIHelper.switchView(this.anchorPane, Views.SIGNIN);
    	}
    }

    @FXML
    void handleDMClick(MouseEvent event) {
    	if (UserSignInHelper.getCurrentUser() != null) {
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
    void handleSignInClick(ActionEvent event) {
    	GUIHelper.switchView(this.anchorPane, Views.SIGNIN);
    }
    
    @FXML
    void initialize() {
    	if (UserSignInHelper.getCurrentUser() != null) {
    		this.accountLabel.textProperty().setValue(UserSignInHelper.getCurrentUser().getUserName());
    		this.signInButton.setVisible(false);
    		this.signInBar.setVisible(false);
    		this.signInLabel.setVisible(false);
    	} else {
    		this.accountLabel.textProperty().setValue("Account");
    	}
    }
}
