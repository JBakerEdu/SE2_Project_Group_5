package edu.westga.cs3211.hyre_defyer_project.view;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.view_model.AccountPageViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * This is the View for the Account page
 * 
 * @author Jacob Baker
 * @version Spring 2025
 */
public class AccountPageView {

    @FXML
    private ImageView accountBioImage;

    @FXML
    private Label accountLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField categoryTextFeild;

    @FXML
    private ComboBox<Categories> catergoryComboBox;

    @FXML
    private TextArea descriptionTextBox;

    @FXML
    private Label dmLabel;

    @FXML
    private Button editButton;

    @FXML
    private Label homeLabel;

    @FXML
    private Button hyreButton;

    @FXML
    private Label hyreLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Button signOutButton;

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
    private Label userLabel;

    @FXML
    void handleAccountClick(MouseEvent event) {
    	if (SignInViewModel.getCurrentUser() != null) {
    		AccountPageViewModel.setUserSelectedToView(SignInViewModel.getCurrentUser());
    		GUIHelper.switchView(this.anchorPane, Views.ACCOUNT);
    	} else {
    		GUIHelper.switchView(this.anchorPane, Views.SIGNIN);
    	}
    }

    @FXML
    void handleCategorySelected(ActionEvent event) {
    	this.categoryTextFeild.setText(this.catergoryComboBox.getValue().toString());
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
    void handleEditClick(ActionEvent event) {
    	this.toggleEditMode(true);
    }

    @FXML
    void handleHomeClick(MouseEvent event) {
			GUIHelper.switchView(this.anchorPane, Views.HOMEPAGE);
    }

    @FXML
    void handleHyreButtonClick(ActionEvent event) {
    	
    }

    @FXML
    void handleHyreClick(MouseEvent event) {
    	
    }

    @FXML
    void handleSaveClick(ActionEvent event) {
        this.toggleEditMode(false);
    }

    @FXML
    void handleSignOutClick(ActionEvent event) {
    	SignInViewModel.signOut();
    	GUIHelper.switchView(this.anchorPane, Views.HOMEPAGE);
    }

    @FXML
    void initialize() {
    	this.catergoryComboBox.getItems().addAll(Categories.values());
    	User currentUser = SignInViewModel.getCurrentUser();
        User selectedUser = AccountPageViewModel.getUserSelectedToView();
        this.toggleEditMode(false);
        this.categoryTextFeild.setEditable(false);
        if (currentUser != null) {
            this.accountLabel.textProperty().setValue(currentUser.getUserName());            
            if (selectedUser != null && currentUser.equals(selectedUser)) {
                this.userLabel.textProperty().setValue(currentUser.getUserName());
                this.hyreButton.setVisible(false);
                this.signOutButton.setVisible(true);
            } else {
                this.editButton.setVisible(false);
                this.hyreButton.setVisible(true);
            }
        } else {
            this.accountLabel.textProperty().setValue("Account");
            this.userLabel.textProperty().setValue("Account");
        }
    }
    
    private void toggleEditMode(boolean isEditing) {
    	this.saveButton.setVisible(isEditing);
    	this.editButton.setVisible(!isEditing);
    	this.descriptionTextBox.setEditable(isEditing);
        this.catergoryComboBox.setVisible(isEditing);
        this.categoryTextFeild.setVisible(!isEditing);
        this.skill1TextFeild.setEditable(isEditing);
        this.skill2TextFeild.setEditable(isEditing);
        this.skill3TextFeild.setEditable(isEditing);
        this.skill4TextFeild.setEditable(isEditing);
        this.skill5TextFeild.setEditable(isEditing);
    }
}

