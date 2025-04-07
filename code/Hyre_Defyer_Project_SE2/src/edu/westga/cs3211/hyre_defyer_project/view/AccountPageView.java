package edu.westga.cs3211.hyre_defyer_project.view;

import java.util.List;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.FreelancerRoster;
import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
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
import javafx.scene.layout.Pane;

/**
 * This is the View for the Account page
 * 
 * @author Jacob Baker and Kate Anglin
 * @version Spring 2025
 */
public class AccountPageView {
	
	private boolean isFreelancer = false;
	private GUIRosterHelper helper = new GUIRosterHelper();

	@FXML
    private Label aboutLabel;

    @FXML
    private ImageView accountBioImage;

    @FXML
    private Label accountLabel;

    @FXML
    private Button addCategoryButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button becomeFreelancerButton;

    @FXML
    private Button cancelButton;
    
    @FXML
    private Button deleteAccountButton;

    @FXML
    private Button cancelNewCategoryButton;

    @FXML
    private Label categoryLabel;

    @FXML
    private TextField categoryTextFeild;

    @FXML
    private ComboBox<String> catergoryComboBox;

    @FXML
    private Button createCategoryButton;

    @FXML
    private TextField createCategoryTextFeild;

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
    private Label hyreMsgErrorLabel;

    @FXML
    private Pane addNewCategoryPane;

    @FXML
    private Button saveButton;

    @FXML
    private Button signOutButton;

    @FXML
    private TextArea skill1TextArea;

    @FXML
    private TextArea skill2TextArea;

    @FXML
    private TextArea skill3TextArea;

    @FXML
    private TextArea skill4TextArea;

    @FXML
    private TextArea skill5TextArea;

    @FXML
    private Label skillsLabel;

    @FXML
    private Label userLabel;
    
    @FXML
    void handleAddCategoryClick(ActionEvent event) {
    	String newCategory = this.createCategoryTextFeild.getText().trim();

        if (!newCategory.isEmpty() && !this.catergoryComboBox.getItems().contains(newCategory)) {
            this.catergoryComboBox.getItems().add(newCategory);
            this.catergoryComboBox.setValue(newCategory);
        }

        this.createCategoryTextFeild.clear();
        this.addNewCategoryPane.setVisible(false);
    }
    
    @FXML
    void handleCancelNewCategoryClick(ActionEvent event) {
    	this.createCategoryTextFeild.setText("");
    	this.addNewCategoryPane.setVisible(false);
    }
    
    @FXML
    void handleCreateCategoryClick(ActionEvent event) {
    	this.addNewCategoryPane.setVisible(true);
    }

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
    	User currentUser = SignInViewModel.getCurrentUser();
    	User selectedUser = AccountPageViewModel.getUserSelectedToView();
    	ServerInterface.addMessageableUser(currentUser, selectedUser);
    	GUIHelper.switchView(this.anchorPane, Views.DMS);
    }
    
    @FXML
    void handleBecomeFreelancerButtonClick(ActionEvent event) {
        User currentUser = SignInViewModel.getCurrentUser();
        if (currentUser == null) {
        	return;
        }
        Freelancer newFreelancer = new Freelancer(currentUser.getUserName(), currentUser.getBio(), Categories.UNDETERMINED);
        AccountPageViewModel.setUserSelectedToView(newFreelancer);
        FreelancerRoster roster = this.helper.getFreelancerRoster();
        roster.addFreelancer(newFreelancer);
        this.helper.addFreelancerToServer(newFreelancer);
        GUIHelper.switchView(this.anchorPane, Views.ACCOUNT);
    }
    
    @FXML
    void handleDeleteAccountButtonClick(ActionEvent event) {
    	if (this.isFreelancer) {
    		ServerInterface.removeFreelancer(this.getFreelancerByUsername(SignInViewModel.getCurrentUser().getUserName()));
    	}
    	ServerInterface.deleteUser(SignInViewModel.getCurrentUser().getUserName());
    	SignInViewModel.signOut();
    	GUIHelper.switchView(anchorPane, Views.HOMEPAGE);	    
    }
    
    @FXML
    void handleSaveClick(ActionEvent event) {
        User selectedUser = AccountPageViewModel.getUserSelectedToView();
        if (selectedUser == null) {
        	return;
        }
        if (AccountPageViewModel.isSelectedUserFreelancer()) {
        	Freelancer tempFreelancer = this.getFreelancerByUsername(selectedUser.getUserName());
        	Freelancer theFreelancer = new Freelancer(selectedUser.getUserName(), "", Categories.UNDETERMINED);
        	theFreelancer.setBio(this.descriptionTextBox.getText());
        	theFreelancer.setCategory(this.catergoryComboBox.getValue().toUpperCase().replace(" ", "_"));
            TextArea[] skillFields = { this.skill1TextArea, this.skill2TextArea, this.skill3TextArea, this.skill4TextArea, this.skill5TextArea };
            for (int index = 0; index < skillFields.length; index++) {
                String skillText = (skillFields[index].getText() != null) ? skillFields[index].getText().trim() : "";
                theFreelancer.setSkill(index, skillText);
            }
            this.helper.editFreelancerToServer(tempFreelancer, theFreelancer);
        } else {
        	ServerInterface.setUserBio(selectedUser, this.descriptionTextBox.getText());
        }

        this.toggleEditMode(false);
        this.updateDataShown();
    }

    @FXML
    void handleAboutHyreClick(MouseEvent event) {
    	GUIHelper.switchView(this.anchorPane, Views.ABOUT_HYRE);
    }

    @FXML
    void handleCancleClick(ActionEvent event) {
    	this.toggleEditMode(false);
    	this.updateDataShown();
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
        this.accountLabel.setText(currentUser != null ? currentUser.getUserName() : "Account");
        this.userLabel.setText(selectedUser != null ? selectedUser.getUserName() : "Account");
        boolean isViewingOwnProfile = currentUser != null && selectedUser != null && currentUser.getUserName().equals(selectedUser.getUserName());
        this.isFreelancer = selectedUser != null && AccountPageViewModel.isSelectedUserFreelancer();
        this.toggleEditMode(false);
        this.categoryTextFeild.setEditable(false);
        this.addNewCategoryPane.setVisible(false);
        this.createCategoryButton.setVisible(false);
        this.hyreButton.setVisible(this.isFreelancer && !isViewingOwnProfile);
        this.signOutButton.setVisible(isViewingOwnProfile);
        this.editButton.setVisible(isViewingOwnProfile);
        this.becomeFreelancerButton.setVisible(isViewingOwnProfile && !this.isFreelancer);
        this.updateDataShown();
        this.setIsFreelancerView();
    }

    private void toggleEditMode(boolean isEditing) {
    	this.saveButton.setVisible(isEditing);
    	this.cancelButton.setVisible(isEditing);
    	this.deleteAccountButton.setVisible(isEditing);
    	this.editButton.setVisible(!isEditing);
    	this.descriptionTextBox.setEditable(isEditing);
    	if (this.isFreelancer) {
    		this.createCategoryButton.setVisible(isEditing);
    		this.catergoryComboBox.setVisible(isEditing);
            this.categoryTextFeild.setVisible(!isEditing);
            this.skill1TextArea.setEditable(isEditing);
            this.skill2TextArea.setEditable(isEditing);
            this.skill3TextArea.setEditable(isEditing);
            this.skill4TextArea.setEditable(isEditing);
            this.skill5TextArea.setEditable(isEditing);
    	}
    }
    
    private void setIsFreelancerView() {
    	this.catergoryComboBox.setVisible(this.isFreelancer);
        this.categoryTextFeild.setVisible(this.isFreelancer);
        this.skill1TextArea.setVisible(this.isFreelancer);
        this.skill2TextArea.setVisible(this.isFreelancer);
        this.skill3TextArea.setVisible(this.isFreelancer);
        this.skill4TextArea.setVisible(this.isFreelancer);
        this.skill5TextArea.setVisible(this.isFreelancer);
        this.categoryLabel.setVisible(this.isFreelancer);
        this.skillsLabel.setVisible(this.isFreelancer);
    }
    
    private void updateDataShown() {
        User selectedUser = AccountPageViewModel.getUserSelectedToView();
        if (selectedUser == null) {
        	return;
        }
        this.descriptionTextBox.setText(selectedUser.getBio());
        Freelancer freelancer = this.getFreelancerByUsername(selectedUser.getUserName());
        if (freelancer != null) {
        	this.descriptionTextBox.setText(freelancer.getBio());
        	this.catergoryComboBox.setValue(freelancer.getCategory());
            this.categoryTextFeild.setText(freelancer.getCategory().toUpperCase().replace("_", " "));
            List<String> skills = freelancer.getSkills();
            TextArea[] skillFields = { this.skill1TextArea, this.skill2TextArea, this.skill3TextArea, this.skill4TextArea, this.skill5TextArea };
            for (int index = 0; index < skillFields.length; index++) {
                skillFields[index].setText(index < skills.size() ? skills.get(index) : "");
            }
        }
        this.updateHyreButtonAndErrorLabel();
    }

	private void updateHyreButtonAndErrorLabel() {
		User currentUser = SignInViewModel.getCurrentUser();
        if (currentUser == null) {
        	this.hyreButton.disableProperty().set(true);
        	this.hyreMsgErrorLabel.setVisible(true);
        } else {
        	this.hyreButton.disableProperty().set(false);
        	this.hyreMsgErrorLabel.setVisible(false);
        }
	}
    
    private Freelancer getFreelancerByUsername(String username) {
        for (Freelancer freelancer : AccountPageViewModel.getRoster().getAllFreelancers()) {
            if (freelancer.getUserName().equals(username)) {
                return freelancer;
            }
        }
        return null;
    }   
}
