package edu.westga.cs3211.hyre_defyer_project.view;

import edu.westga.cs3211.hyre_defyer_project.view_model.CategoryViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Codebehind for Category Page View
 * 
 * @author Jacob Baker
 * @version Spring 2025
 */
public class CategoryPageView {
	private CategoryViewModel categoryViewModel;

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
    private ComboBox<?> filter;

    @FXML
    private Label homeLabel;

    @FXML
    private Label hyreLabel;

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
    private Button viewMorePeopleButton;

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
    void handleMorePeopleClick(ActionEvent event) {

    }

    @FXML
    void handlePeopleClick(ActionEvent event) {

    }
    
    @FXML
    void initialize() {
        if (CategoryViewModel.selectedCategory != null) {
            this.categoryName.setText(CategoryViewModel.selectedCategory.toString());
        } else {
            this.categoryName.setText("No category selected");
        }
        if (SignInViewModel.getCurrentUser() != null) {
    		this.accountLabel.textProperty().setValue(SignInViewModel.getCurrentUser().getUserName());
    	} else {
    		this.accountLabel.textProperty().setValue("Account");
    	}
    }

}
