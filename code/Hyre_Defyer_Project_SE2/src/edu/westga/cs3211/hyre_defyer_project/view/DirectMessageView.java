package edu.westga.cs3211.hyre_defyer_project.view;

import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * This is the UI for the direct message
 * 
 * @author Jacob Baker
 * @version Spring 2025
 */
public class DirectMessageView {

    @FXML
    private ImageView accountBioImage;

    @FXML
    private Label accountLabel;

    @FXML
    private ListView<?> contactListView;

    @FXML
    private Label dmLabel;

    @FXML
    private TextArea draftMessageTextArea;

    @FXML
    private Label homeLabel;

    @FXML
    private Label hyreLabel;

    @FXML
    private Label otherPersonUserNameLbel;

    @FXML
    private Button sendMessageButton;
    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    void handleAccountClick(MouseEvent event) {
    	GUIHelper.switchView(this.anchorPane, Views.ACCOUNT);
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
