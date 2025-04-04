package edu.westga.cs3211.hyre_defyer_project.view;

import java.util.List;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.view_model.AccountPageViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.CategoryViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
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
	CategoryViewModel categoryViewModel;

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
    private ListView<Categories> categoryListView;

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
    		AccountPageViewModel.setUserSelectedToView(SignInViewModel.getCurrentUser());
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
            Categories selectedCategory = null;
            try {
                selectedCategory = Categories.valueOf(buttonText.replace(" ", "_").toUpperCase());
                this.categoryViewModel.setSelectedCategory(selectedCategory);

            } catch (IllegalArgumentException e) {
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
    	GUIHelper.switchView(anchorPane, Views.ABOUT_HYRE);
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
    	this.categoryViewModel = new CategoryViewModel();
    	this.otherCategoryPane.setVisible(false);
    	List<Button> buttons = List.of(categoryButton1, categoryButton2, categoryButton3, categoryButton4, categoryButton5, categoryButton6);
        Categories[] categories = Categories.values();
        for (int i = 0; i < buttons.size(); i++) {
            if (i < categories.length) {
                buttons.get(i).setText(categories[i].toString());
                buttons.get(i).setVisible(true);
            } else {
                buttons.get(i).setVisible(false);
            }
        }
        this.categoryListView.getItems().setAll(Categories.values());
        this.bindUIAndListeners();
    }
    
    private void bindUIAndListeners() {
        categoryListView.setOnMouseClicked(event -> {
            Object selectedItem = categoryListView.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                String categoryName = selectedItem.toString();
                try {
                    Categories selectedCategory = Categories.valueOf(categoryName.replace(" ", "_").toUpperCase());
                    this.categoryViewModel.setSelectedCategory(selectedCategory);
                    GUIHelper.switchView(this.anchorPane, Views.CATEGORY);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid category selected: " + categoryName);
                }
            } else {
                System.out.println("No item selected from ListView.");
            }
        });
    }


}
