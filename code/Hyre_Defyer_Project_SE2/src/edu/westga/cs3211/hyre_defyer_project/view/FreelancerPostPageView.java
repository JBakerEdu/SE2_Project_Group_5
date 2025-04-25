package edu.westga.cs3211.hyre_defyer_project.view;

import java.util.List;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.CategoryPageViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.FreelancerPostPageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * This is the class for viewing a freelancer that is within the categories
 * 
 * @author Jacob Baker
 * @version Spring 2025
 */
public class FreelancerPostPageView {
	
	private static final String MSG_MUST_LOGIN = "You must be logged in";
	private static final String MSG_CANNOT_SELF = "You cannot Hyre yourself";

	@FXML
    private Label aboutLabel;

    @FXML
    private ImageView accountBioImage;

    @FXML
    private Label accountLabel;

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Button backButton;

    @FXML
    private Label categoryLabel;

    @FXML
    private TextField categoryTextFeild;

    @FXML
    private TextArea descriptionTextBox;

    @FXML
    private Label dmLabel;

    @FXML
    private Label homeLabel;

    @FXML
    private Button hyreButton;

    @FXML
    private Label hyreMsgErrorLabel;

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
    private Label starRating;

    @FXML
    private Label userLabel;
    
    @FXML
    private Button rateButton;
    
    @FXML
    private ChoiceBox<Integer> starValues;

    @FXML
    private Button submitRateButton;
    
    @FXML
    private Pane rateFreelancerPane;
    
    private User selectedUser;
    
    private Freelancer selectedFreelancer;

    @FXML
    void initialize() {
    	if (SignInViewModel.getCurrentUser() != null) {
    		this.accountLabel.textProperty().setValue(SignInViewModel.getCurrentUser().getUserName());
    	}
    	this.selectedUser = FreelancerPostPageViewModel.getUserSelectedToView();
      this.selectedFreelancer = ServerInterface.getFreelancers().getFreelancersByName(this.selectedUser.getUserName()).get(0);
      
    	this.disableAll();
      this.updateDataShown();
      this.updateHyreButtonAndErrorLabel();
      this.setStarRatingElements();
    }
    
    @FXML
    void handleBackButtonClick(ActionEvent event) {
    	GUIHelper.switchView(this.anchorPane, Views.CATEGORY);
    }

    private void disableAll() {
    	this.descriptionTextBox.setEditable(false);
    	this.skill1TextArea.setEditable(false);
    	this.skill2TextArea.setEditable(false);
    	this.skill3TextArea.setEditable(false);
    	this.skill4TextArea.setEditable(false);
    	this.skill5TextArea.setEditable(false);
    	this.categoryTextFeild.setEditable(false);
    	this.rateFreelancerPane.setVisible(false);
    	this.rateButton.disableProperty().set(true);
	}

    private void updateDataShown() {
        if (this.selectedFreelancer == null) {
            return;
        }
        User tmp = new User(this.selectedUser.getUserName());
        if (SignInViewModel.getCurrentUser() != null && ServerInterface.getMessagableUsers(SignInViewModel.getCurrentUser()).contains(tmp)) {
        	this.rateButton.disableProperty().set(false);
        }
        this.starRating.setText("Rating: " + ServerInterface.getRating(this.selectedFreelancer) + " stars");
        this.userLabel.setText(this.selectedFreelancer.getUserName());
        this.descriptionTextBox.setText(this.selectedFreelancer.getBio());
        this.categoryTextFeild.setText(this.selectedFreelancer.getCategory().replace("_", " "));

        List<String> skills = this.selectedFreelancer.getSkills();
        TextArea[] fields = {
            this.skill1TextArea,
            this.skill2TextArea,
            this.skill3TextArea,
            this.skill4TextArea,
            this.skill5TextArea
        };
        for (int index = 0; index < fields.length; index++) {
        	fields[index].setText(this.getOrBlank(skills, index));
        }
    }
	
    private String getOrBlank(List<String> list, int index) {
	    if (index < list.size()) {
	        return list.get(index);
	    }
	    return "";
    }
    
    private void setStarRatingElements() {
    	this.setStarValues();
    	this.rateButton.setOnAction((event) -> {
    		this.rateFreelancerPane.setVisible(true);
    	});
    	this.submitRateButton.setOnAction((event) -> {
        if (this.starValues.getSelectionModel().getSelectedItem() != null) {
      		int rating = this.starValues.getSelectionModel().getSelectedItem();
      		ServerInterface.rateFreelancer(SignInViewModel.getCurrentUser(), this.selectedFreelancer, rating);
      		this.starRating.setText("Rating: " + ServerInterface.getRating(this.selectedFreelancer) + " stars");
    		
      		this.rateFreelancerPane.setVisible(false);
      		this.starValues.getSelectionModel().clearSelection();
      		this.updateDataShown();
        }
    	});
    }

		private void setStarValues() {
			this.starValues.itemsProperty().get().add(1);
    	this.starValues.itemsProperty().get().add(2);
    	this.starValues.itemsProperty().get().add(3);
    	this.starValues.itemsProperty().get().add(4);
    	this.starValues.itemsProperty().get().add(5);
		}
    
    private void updateHyreButtonAndErrorLabel() {
        User current = SignInViewModel.getCurrentUser();
        User viewed  = FreelancerPostPageViewModel.getUserSelectedToView();

        boolean signedIn    = (current != null);
        boolean viewingSelf = signedIn && current.getUserName().equals(viewed.getUserName());
        boolean enableHyre  = signedIn && !viewingSelf;

        this.hyreButton.setDisable(!enableHyre);

        if (!signedIn) {
            this.hyreMsgErrorLabel.setText(MSG_MUST_LOGIN);
            this.hyreMsgErrorLabel.setVisible(true);
        } else if (viewingSelf) {
            this.hyreMsgErrorLabel.setText(MSG_CANNOT_SELF);
            this.hyreMsgErrorLabel.setVisible(true);
        } else {
            this.hyreMsgErrorLabel.setVisible(false);
        }
    }

    @FXML
    void handleHyreButtonClick(ActionEvent event) {
    	CategoryPageViewModel.clearSelections();
        var current = SignInViewModel.getCurrentUser();
        var selected = FreelancerPostPageViewModel.getUserSelectedToView();
        ServerInterface.addMessageableUser(current, selected);
        GUIHelper.switchView(this.anchorPane, Views.DMS);
    }

    @FXML
    void handleHomeClick(MouseEvent event) {
    	CategoryPageViewModel.clearSelections();
        GUIHelper.switchView(this.anchorPane, Views.HOMEPAGE);
    }

    @FXML
    void handleAccountClick(MouseEvent event) {
    	CategoryPageViewModel.clearSelections();
        if (SignInViewModel.getCurrentUser() != null) {
            GUIHelper.switchView(this.anchorPane, Views.ACCOUNT);
        } else {
            GUIHelper.switchView(this.anchorPane, Views.SIGNIN);
        }
    }

    @FXML
    void handleDMClick(MouseEvent event) {
    	CategoryPageViewModel.clearSelections();
        if (SignInViewModel.getCurrentUser() != null) {
            GUIHelper.switchView(this.anchorPane, Views.DMS);
        } else {
            GUIHelper.switchView(this.anchorPane, Views.SIGNIN);
        }
    }

    @FXML
    void handleAboutHyreClick(MouseEvent event) {
    	CategoryPageViewModel.clearSelections();
        GUIHelper.switchView(this.anchorPane, Views.ABOUT_HYRE);
    }
}
