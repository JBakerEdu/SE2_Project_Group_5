package edu.westga.cs3211.hyre_defyer_project.view;

import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * This is the code for Account Page 
 * 
 * @author Jacob Baker & Myles Debro
 * @version Spring 2025
 */
public class AccountPageView {

    @FXML
    private Button signOutButton;

    @FXML
    private ImageView accountBioImage;

    @FXML
    private Label accountLabel;

    @FXML
    private ComboBox<?> catergoryComboBox;

    @FXML
    private Label dmLabel;

    @FXML
    private Label homeLabel;

    @FXML
    private Label hyreLabel;

    @FXML
    private Button editButton;

    @FXML
    private TextField skill1TextFeild;

    @FXML
    private TextField skill2TextFeild;

    @FXML
    private TextField skill3TextFeild;

    @FXML
    private TextField skill4TextFeild;

    @FXML
    private TextField skill5TextFeild;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Label userLabel;

    @FXML
    void handleAccountClick(MouseEvent event) {
    	if (SignInViewModel.getCurrentUser() != null) {
    		GUIHelper.switchView(this.anchorPane, Views.ACCOUNT);
    	} else {
    		GUIHelper.switchView(this.anchorPane, Views.SIGNIN);
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
    void handleSignOutClick(ActionEvent event) {
    	SignInViewModel.signOut();
    	GUIHelper.switchView(this.anchorPane, Views.HOMEPAGE);
    }

    @FXML
    void handleEditClick(ActionEvent event) {

    }
    
    @FXML
    void initialize() {
    	if (SignInViewModel.getCurrentUser() != null) {
    		this.accountLabel.textProperty().setValue(SignInViewModel.getCurrentUser().getUserName());
    		this.userLabel.textProperty().setValue(SignInViewModel.getCurrentUser().getUserName());
    	} else {
    		this.accountLabel.textProperty().setValue("Account");
    		this.userLabel.textProperty().setValue("Account");
    	}
    }

}
