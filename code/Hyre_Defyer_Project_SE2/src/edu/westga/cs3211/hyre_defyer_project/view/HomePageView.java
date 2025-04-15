package edu.westga.cs3211.hyre_defyer_project.view;

import java.util.List;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.view_model.CategoryPageViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.FreelancerPostPageViewModel;
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
    private ListView<String> categoryListView;

    @FXML
    private Pane catergoryPane;

    @FXML
    private Button closeButton1;

    @FXML
    private Label dmLabel;

    @FXML
    private Label homeLabel;

    @FXML
    private Label aboutLabel;

    @FXML
    private Pane otherCategoryPane;

    @FXML
    private Button signInButton;

    @FXML
    private Button viewCategoriesButton;

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
    void handleCategoryClick(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button clickedButton = (Button) event.getSource();
            String buttonText = clickedButton.getText();
            try {
                CategoryPageViewModel.setSelectedCategory(buttonText);
            } catch (IllegalArgumentException ex) {
                System.err.println("Invalid category selected: " + buttonText);
                return;
            }
            GUIHelper.switchView(this.anchorPane, Views.CATEGORY);
        }
    }

    @FXML
    void handleCloseClick(ActionEvent event) {
    	this.otherCategoryPane.setVisible(false);
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
    	GUIHelper.switchView(this.anchorPane, Views.ABOUT_HYRE);
    }

    @FXML
    void handleMoreCategoriesClick(ActionEvent event) {
    	this.otherCategoryPane.setVisible(true);
    }

    @FXML
    void handleSignInClick(ActionEvent event) {
    	GUIHelper.switchView(this.anchorPane, Views.SIGNIN);
    }
    
    @FXML
    void initialize() {
    	if (SignInViewModel.getCurrentUser() != null) {
    		this.accountLabel.textProperty().setValue(SignInViewModel.getCurrentUser().getUserName());
    		this.signInButton.setVisible(false);
    	} else {
    		this.accountLabel.textProperty().setValue("Account");
    	}
    	this.otherCategoryPane.setVisible(false);
    	List<Button> buttons = List.of(this.categoryButton1, this.categoryButton2, this.categoryButton3, this.categoryButton4, this.categoryButton5, this.categoryButton6);
        List<String> categories = Categories.values();
        for (int index = 0; index < buttons.size(); index++) {
            if (index < categories.size()) {
                buttons.get(index).setText(categories.get(index));
                buttons.get(index).setVisible(true);
            } else {
                buttons.get(index).setVisible(false);
            }
        }
        
        this.categoryListView.getItems().setAll(Categories.values());
        this.bindUIAndListeners();
    }
    
    private void bindUIAndListeners() {
        this.categoryListView.setOnMouseClicked(event -> {
            Object selectedItem = this.categoryListView.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                String categoryName = selectedItem.toString();
                try {
                    CategoryPageViewModel.setSelectedCategory(categoryName);
                    GUIHelper.switchView(this.anchorPane, Views.CATEGORY);
                } catch (IllegalArgumentException ex) {
                    System.err.println("Invalid category selected: " + categoryName);
                }
            } else {
                System.out.println("No item selected from ListView.");
            }
        });
    }
}
