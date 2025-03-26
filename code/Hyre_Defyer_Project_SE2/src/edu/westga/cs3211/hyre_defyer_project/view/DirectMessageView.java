package edu.westga.cs3211.hyre_defyer_project.view;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3211.hyre_defyer_project.model.DirectMessageHandler;
import edu.westga.cs3211.hyre_defyer_project.model.Message;
import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
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
    private Label hyreLabel;

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
    	//DELETE LATER
    	ServerInterface.addMessageableUser(SignInViewModel.getCurrentUser(), new User("User1"));
    	//DELETE LATER
    	List<User> users = new ArrayList<>(ServerInterface.getMessagableUsers(SignInViewModel.getCurrentUser()));

    	ObservableList<User> observableListUsers = FXCollections.observableArrayList(users);
    	this.contactListView.setItems(observableListUsers);
    	
    	this.contactListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		this.otherPersonUserNameLbel.textProperty().setValue(newValue.getUserName());
    		this.directMessageHandler = new DirectMessageHandler(SignInViewModel.getCurrentUser(), newValue);
    		this.updateDisplayedMessages();		
    	});
    }

	private void updateDisplayedMessages() {
		this.messageListView.setItems(FXCollections.observableArrayList(this.directMessageHandler.getFullMessageLog()));
	}

}
