package edu.westga.cs3211.hyre_defyer_project.view;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3211.hyre_defyer_project.model.DirectMessageHandler;
import edu.westga.cs3211.hyre_defyer_project.model.Message;
import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
import edu.westga.cs3211.hyre_defyer_project.view_model.AccountPageViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @author Jacob Baker & Myles Debro
 * @version Spring 2025
 */
public class DirectMessageView {

    @FXML
    private ImageView accountBioImage;

    @FXML
    private Label accountLabel;

    @FXML
    private ListView<User> contactListView;

    @FXML
    private Label dmLabel;

    @FXML
    private TextArea draftMessageTextArea;

    @FXML
    private Label homeLabel;

    @FXML
    private Label aboutLabel;

    @FXML
    private Label otherPersonUserNameLbel;

    @FXML
    private Button sendMessageButton;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private ListView<Message> messageListView;
    
    private DirectMessageHandler directMessageHandler;

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
    void handleAboutHyreClick(MouseEvent event) {
    	GUIHelper.switchView(anchorPane, Views.ABOUT_HYRE);
    }

    @FXML
    void handleSendMessageClick(ActionEvent event) {
    	String message = this.draftMessageTextArea.getText();
    	User otherPerson = this.contactListView.getSelectionModel().getSelectedItem();
    	User currentUser = SignInViewModel.getCurrentUser();
    	
    	this.directMessageHandler.sendMessage(new Message(message, currentUser, otherPerson));
    	this.updateDisplayedMessages();
    }
    
    @FXML
    void initialize() {
    	if (SignInViewModel.getCurrentUser() != null) {
    		this.accountLabel.textProperty().setValue(SignInViewModel.getCurrentUser().getUserName());
    	} else {
    		this.accountLabel.textProperty().setValue("Account");
    	}
    	List<User> users = new ArrayList<>(ServerInterface.getMessagableUsers(SignInViewModel.getCurrentUser()));

    	ObservableList<User> observableListUsers = FXCollections.observableArrayList(users);
    	this.contactListView.setItems(observableListUsers);
    	
    	this.contactListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		this.otherPersonUserNameLbel.textProperty().setValue(newValue.getUserName());
    		this.directMessageHandler = new DirectMessageHandler(SignInViewModel.getCurrentUser(), newValue);
    		updateDisplayedMessages();
    	});
    	
    	User selectedUser = AccountPageViewModel.getUserSelectedToView();
		if (selectedUser != null) {
			User userToSelect = null;
			for (User user : users) {
				if (user.getUserName().equals(selectedUser.getUserName())) {
					userToSelect = user;
					break;
				}
			}
			this.contactListView.getSelectionModel().select(userToSelect);
		} else {
			this.contactListView.getSelectionModel().select(0);
		}
    }

	private void updateDisplayedMessages() {
		this.messageListView.setItems(FXCollections.observableArrayList(this.directMessageHandler.getFullMessageLog()));
	}

}
