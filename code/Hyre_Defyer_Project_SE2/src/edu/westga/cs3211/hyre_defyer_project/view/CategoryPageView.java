package edu.westga.cs3211.hyre_defyer_project.view;

import java.util.Arrays;
import java.util.List;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.view_helpers.CategorySelectionHelper;
import edu.westga.cs3211.hyre_defyer_project.view_helpers.UserSignInHelper;
import edu.westga.cs3211.hyre_defyer_project.view_helpers.ViewedUserHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Codebehind for Category Page View
 * 
 * @author Jacob Baker and Kate Anglin
 * @version Spring 2025
 */
public class CategoryPageView {
	private List<Button> peopleButtons;
	private List<Freelancer> freelancers;
	private int currentPage = 1;
	private int pageSize;
	
	@FXML
    private Button nextPageButton;
	
	@FXML
    private Label pageNumber;
	
	@FXML
    private Button previousPageButton;
	
    @FXML
    private ImageView accountBioImage;

    @FXML
    private Label accountLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label categoryName;

    @FXML
    private Label dmLabel;

    @FXML
    private Label homeLabel;

    @FXML
    private Label aboutLabel;

    @FXML
    private Button peopleButton1;

    @FXML
    private Button peopleButton10;

    @FXML
    private Button peopleButton11;

    @FXML
    private Button peopleButton12;

    @FXML
    private Button peopleButton13;

    @FXML
    private Button peopleButton14;

    @FXML
    private Button peopleButton15;

    @FXML
    private Button peopleButton16;

    @FXML
    private Button peopleButton17;

    @FXML
    private Button peopleButton18;

    @FXML
    private Button peopleButton19;

    @FXML
    private Button peopleButton2;

    @FXML
    private Button peopleButton20;

    @FXML
    private Button peopleButton21;

    @FXML
    private Button peopleButton22;

    @FXML
    private Button peopleButton23;

    @FXML
    private Button peopleButton24;

    @FXML
    private Button peopleButton3;

    @FXML
    private Button peopleButton4;

    @FXML
    private Button peopleButton5;

    @FXML
    private Button peopleButton6;

    @FXML
    private Button peopleButton7;

    @FXML
    private Button peopleButton8;

    @FXML
    private Button peopleButton9;

    @FXML
    private AnchorPane peopleScrollPane;

    @FXML
    private Pane row1PeoplePane;

    @FXML
    private Pane row2PeoplePane;

    @FXML
    private Pane row3PeoplePane;

    @FXML
    private Pane row4PeoplePane;
    
    @FXML
    private Button applyFiterButton;
    
    @FXML
    private TextField nameTextBox;
    
    @FXML
    private TextField skillTextBox;
    
    @FXML
    void handleApplyFilterButtonClick(ActionEvent event) {
    	this.freelancers = CategorySelectionHelper.getFreelancersWithNameAndSkill(this.nameTextBox.getText(), this.skillTextBox.getText());
    	this.updatePeopleButtons();
    }

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
    void handlePeopleClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int buttonIndex = this.peopleButtons.indexOf(clickedButton);
        int freelancerIndex = (this.currentPage - 1) * this.pageSize + buttonIndex;

        if (freelancerIndex < this.freelancers.size()) {
            Freelancer selectedFreelancer = this.freelancers.get(freelancerIndex);
            ViewedUserHelper.setUserSelectedToView(selectedFreelancer);
        }
        GUIHelper.switchView(this.anchorPane, Views.FREELANCER_POST);
    }

    @FXML
    void handleNextPageClick(ActionEvent event) {
        if ((this.currentPage * this.pageSize) < this.freelancers.size()) {
            this.currentPage++;
            this.pageNumber.setText("Page: " + this.currentPage);
            this.updatePeopleButtons();
        }
    }

    @FXML
    void handlePreviousPageClick(ActionEvent event) {
        if (this.currentPage > 1) {
        	this.currentPage--;
            this.pageNumber.setText("Page: " + this.currentPage);
            this.updatePeopleButtons();
        }
    }

    @FXML
    void initialize() {
        this.pageNumber.setText("Page: 1");
        this.previousPageButton.setDisable(true);
        this.nextPageButton.setDisable(true);

        if (UserSignInHelper.getCurrentUser() != null) {
            this.accountLabel.textProperty().setValue(UserSignInHelper.getCurrentUser().getUserName());
        } else {
            this.accountLabel.textProperty().setValue("Account");
        }

        this.initializeFilteredPageValues();
        this.initializeFreelancerButtons();
        this.updatePeopleButtons();
    }
    
    private void initializeFilteredPageValues() {
    	if (CategorySelectionHelper.selectedCategory != null) {
            this.categoryName.setText(CategorySelectionHelper.selectedCategory.toString().toUpperCase().replace("_", " "));
            this.freelancers = CategorySelectionHelper.getFreelancers();
        } else {
            this.categoryName.setText("Please Select Category");
        }
    }

    /**
     * Makes all freelancers into buttons with their data on them
     */
    private void initializeFreelancerButtons() {
        this.peopleButtons = Arrays.asList(this.peopleButton1, this.peopleButton2, this.peopleButton3, this.peopleButton4, this.peopleButton5, this.peopleButton6, this.peopleButton7, this.peopleButton8, this.peopleButton9, this.peopleButton10, this.peopleButton11, this.peopleButton12, this.peopleButton13, this.peopleButton14, this.peopleButton15, this.peopleButton16, this.peopleButton17, this.peopleButton18, this.peopleButton19, this.peopleButton20, this.peopleButton21, this.peopleButton22, this.peopleButton23, this.peopleButton24);
        for (Button button : this.peopleButtons) {
            button.setVisible(false);
        }
        this.pageSize = this.peopleButtons.size();
        
    }
    
    /**
     * Updates the buttons based on the selected category's freelancers.
     */
    private void updatePeopleButtons() {
        int startIndex = (this.currentPage - 1) * this.pageSize;
        for (int index = 0; index < this.peopleButtons.size(); index++) {
            if (index + startIndex < this.freelancers.size()) {
            	this.peopleButtons.get(index).setText(this.freelancers.get(index + startIndex).getUserName());
            	this.peopleButtons.get(index).setVisible(true);
            } else {
            	this.peopleButtons.get(index).setVisible(false);
            }
        }
        this.previousPageButton.setDisable(this.currentPage == 1);
        this.nextPageButton.setDisable(this.currentPage * this.pageSize >= this.freelancers.size());
    }
}
