package edu.westga.cs3211.hyre_defyer_project.view;

import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private ImageView accountBioImage;

    @FXML
    private Label accountLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button categoryButton1;

    @FXML
    private Button categoryButton2;

    @FXML
    private Button categoryButton3;

    @FXML
    private Button categoryButton4;

    @FXML
    private Button categoryButton5;

    @FXML
    private Button categoryButton6;

    @FXML
    private ListView<?> categoryListView;

    @FXML
    private Pane catergoryPane;

    @FXML
    private Button closeButton1;

    @FXML
    private Label dmLabel;

    @FXML
    private Label homeLabel;

    @FXML
    private Label hyreLabel;

    @FXML
    private Pane otherCategoryPane;

    @FXML
    private Button signInButton;

    @FXML
    private Button viewCategoriesButton;

    @FXML
    void handleAccountClick(MouseEvent event) {
    	GUIHelper.switchView(this.anchorPane, Views.ACCOUNT);
    }

    @FXML
    void handleCategoryClick(ActionEvent event) {

    }

    @FXML
    void handleCloseClick(ActionEvent event) {

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
    void handleMoreCategoriesClick(ActionEvent event) {

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
