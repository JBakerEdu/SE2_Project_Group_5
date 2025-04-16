package edu.westga.cs3211.hyre_defyer_project.view;

import edu.westga.cs3211.hyre_defyer_project.model.DirectMessageHandler;
import edu.westga.cs3211.hyre_defyer_project.model.Message;
import edu.westga.cs3211.hyre_defyer_project.model.User;
import edu.westga.cs3211.hyre_defyer_project.server.ServerInterface;
import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.FreelancerPostPageViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
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
    
    @FXML
    private Button refreshChatButton;

    @FXML
    private Button removeContactButton;
    
    private DirectMessageHandler directMessageHandler;

    @FXML
    void handleAccountClick(MouseEvent event) {
    	if (SignInViewModel.getCurrentUser() != null) {
    		FreelancerPostPageViewModel.setUserSelectedToView(SignInViewModel.getCurrentUser());
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
    void handleRefreshChatClick(ActionEvent event) {
    	User selectedUser = this.contactListView.getSelectionModel().getSelectedItem();
		
    	this.updateContactList();

    	if (selectedUser != null) {
			this.updateDisplayedMessages();
			this.contactListView.getSelectionModel().select(selectedUser);
		}
    	
    }
   
    @FXML
    void handleRemoveContactClick(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are you sure you want to remove this contact?");
		alert.setContentText("Click OK to remove the contact, or Cancel to keep the contact.");
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
			this.directMessageHandler.deleteChat(SignInViewModel.getCurrentUser(), this.contactListView.getSelectionModel().getSelectedItem());
			this.updateContactList();
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
    void handleSendMessageClick(ActionEvent event) {
    	String message = this.draftMessageTextArea.getText();
    	User otherPerson = this.contactListView.getSelectionModel().getSelectedItem();
    	User currentUser = SignInViewModel.getCurrentUser();
    	
    	this.directMessageHandler.sendMessage(new Message(message, currentUser, otherPerson));
    	this.updateDisplayedMessages();
    	this.draftMessageTextArea.clear();
    }
    
    @FXML
    void initialize() {
    	if (SignInViewModel.getCurrentUser() != null) {
    		this.accountLabel.textProperty().setValue(SignInViewModel.getCurrentUser().getUserName());
    	} else {
    		this.accountLabel.textProperty().setValue("Account");
    	}

    	this.updateContactList();
    	this.setUpListeners();
    	
    	User selectedUser = FreelancerPostPageViewModel.getUserSelectedToView();
    	if (selectedUser != null) {
    		User userToSelect = null;
    		for (User user : ServerInterface.getMessagableUsers(SignInViewModel.getCurrentUser())) {
    			if (user.getUserName().equals(selectedUser.getUserName())) {
    				userToSelect = user;
    				break;
    			}
    		}
    		this.contactListView.getSelectionModel().select(userToSelect);
    	} else {
    		this.contactListView.getSelectionModel().select(0);
    	}
    	BooleanBinding sendButtonBindings = Bindings.isNull(this.contactListView.getSelectionModel().selectedItemProperty()).or(this.draftMessageTextArea.textProperty().isEmpty());
    	this.sendMessageButton.disableProperty().bind(sendButtonBindings);
    }

		private void setUpListeners() {
			this.contactListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					this.removeContactButton.disableProperty().setValue(false);
					if (newValue.getUserName().equals("admin")) {
						this.removeContactButton.disableProperty().setValue(true);
					}
	    		this.otherPersonUserNameLbel.textProperty().setValue(newValue.getUserName());
	    		this.directMessageHandler = new DirectMessageHandler(SignInViewModel.getCurrentUser(), newValue);
				} else {
					this.removeContactButton.disableProperty().setValue(true);
					this.otherPersonUserNameLbel.textProperty().setValue("Other Person User Name");
				}
    		this.updateDisplayedMessages();
			});
		}

	private void updateDisplayedMessages() {
		this.messageListView.setItems(FXCollections.observableArrayList(this.directMessageHandler.getFullMessageLog()));
	}
	
	private void updateContactList() {
		this.contactListView.setItems(FXCollections.observableArrayList(ServerInterface.getMessagableUsers(SignInViewModel.getCurrentUser())));
	}

}
