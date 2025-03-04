package edu.westga.cs3211.hyre_defyer_project.view;

import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Codebehind for HomePageView
 * 
 * @author Myles Debro & Jacob Baker
 * @version Spring 2025
 */
public class HomePageView {

    @FXML
    private Button viewCatergoriesButton;

    @FXML
    private ImageView accountBioImage;

    @FXML
    private Label accountLabel;

    @FXML
    private Button catergoryButton1;

    @FXML
    private Button catergoryButton2;

    @FXML
    private Button catergoryButton3;

    @FXML
    private Button catergoryButton4;

    @FXML
    private Button catergoryButton5;

    @FXML
    private Button catergoryButton6;

    @FXML
    private Pane catergoryPane;

    @FXML
    private Label dmLabel;

    @FXML
    private Label homeLabel;

    @FXML
    private Label hyreLabel;

    @FXML
    private Button signInButton;
    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    void handleAccountClick(MouseEvent event) {
    	GUIHelper.switchView(this.anchorPane, Views.ACCOUNT);
    }

    @FXML
    void handleCatergoryClick(ActionEvent event) {
    	
    }

    @FXML
    void handleDMClick(MouseEvent event) {
    	GUIHelper.switchView(this.anchorPane, Views.DMS);
    }

    @FXML
    void handleHomeClick(MouseEvent event) {
    	GUIHelper.switchView(this.anchorPane, Views.HOMEPAGE);
    }

    @FXML
    void handleHyreClick(MouseEvent event) {
    	
    }

    @FXML
    void handleMoreCatergoriesClick(ActionEvent event) {
    	
    }

    @FXML
    void handleSignInClick(ActionEvent event) {
    	GUIHelper.switchView(this.anchorPane, Views.SIGNIN);
    }
    
    @FXML
    void initialize() {
    	if (SignInViewModel.getCurrentUser() != null) {
    		this.accountLabel.textProperty().setValue(SignInViewModel.getCurrentUser().getUserName());
    	} else {
    		this.accountLabel.textProperty().setValue("Account");
    	}
    }

}
