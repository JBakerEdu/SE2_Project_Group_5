package edu.westga.cs3211.hyre_defyer_project.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.westga.cs3211.hyre_defyer_project.model.Categories;
import edu.westga.cs3211.hyre_defyer_project.model.Freelancer;
import edu.westga.cs3211.hyre_defyer_project.view_model.CategoryPageViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.SignInViewModel;
import edu.westga.cs3211.hyre_defyer_project.view_model.FreelancerPostPageViewModel;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
	private boolean isUpdatingSkills = false;
	
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
    private Pane row5PeoplePane;
    
    @FXML
    private Pane row6PeoplePane;
    
    @FXML
    private Button applyFiterButton;
    
    @FXML
    private TextField nameTextBox;
    
    @FXML
    private ComboBox<String> categoryComboBox;
    
    @FXML
    private ComboBox<String> skillsComboBox;

    @FXML
    private ListView<String> skillsListView;
    
    @FXML
    void handleApplyFilterButtonClick(ActionEvent event) {
    	String name = this.nameTextBox.getText().trim();
    	CategoryPageViewModel.setSelectedName(name);
    	
    	CategoryPageViewModel.setSelectedSkills(new ArrayList<>(this.skillsListView.getItems()));    	
    	
    	this.freelancers = CategoryPageViewModel.getFreelancersWithNameAndSkills(this.nameTextBox.getText(), CategoryPageViewModel.getSelectedSkills());
    	this.updatePeopleButtons();
    }

    @FXML
    void handleAccountClick(MouseEvent event) {
    	CategoryPageViewModel.clearSelections();
    	if (SignInViewModel.getCurrentUser() != null) {
    		FreelancerPostPageViewModel.setUserSelectedToView(SignInViewModel.getCurrentUser());
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
    void handleHomeClick(MouseEvent event) {
    	CategoryPageViewModel.clearSelections();
    	GUIHelper.switchView(this.anchorPane, Views.HOMEPAGE);
    }

    @FXML
    void handleAboutHyreClick(MouseEvent event) {
    	CategoryPageViewModel.clearSelections();
    	GUIHelper.switchView(this.anchorPane, Views.ABOUT_HYRE);
    }

    @FXML
    void handlePeopleClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int buttonIndex = this.peopleButtons.indexOf(clickedButton);
        int freelancerIndex = (this.currentPage - 1) * this.pageSize + buttonIndex;

        if (freelancerIndex < this.freelancers.size()) {
            Freelancer selectedFreelancer = this.freelancers.get(freelancerIndex);
            FreelancerPostPageViewModel.setUserSelectedToView(selectedFreelancer);
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

        if (SignInViewModel.getCurrentUser() != null) {
            this.accountLabel.textProperty().setValue(SignInViewModel.getCurrentUser().getUserName());
        } else {
            this.accountLabel.textProperty().setValue("Account");
        }
        if (CategoryPageViewModel.getSelectedName() != null) {
            this.nameTextBox.setText(CategoryPageViewModel.getSelectedName());
        }
        if (CategoryPageViewModel.getSelectedSkills() == null) {
        	CategoryPageViewModel.setSelectedSkills(new ArrayList<String>());
        }
        this.skillsComboBox.getItems().setAll(CategoryPageViewModel.getUnselectedSkills());
    	this.skillsListView.getItems().setAll(CategoryPageViewModel.getSelectedSkills());
        this.initializeFilteredPageValues();
        this.initializeFreelancerButtons();
        this.updatePeopleButtons();
        this.initializeCategoryComboBox();
        this.initializeSkillsComboBox();
        this.initializeSkillsListView();
        this.handleApplyFilterButtonClick(null);
    }

	private void initializeSkillsListView() {
		this.skillsListView.getItems().setAll(CategoryPageViewModel.getSelectedSkills());
        this.skillsListView.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Object>() {
                @Override
                public void changed(ObservableValue<?> arg0, Object oldValue, Object newValue) {
                    if (newValue != null && !CategoryPageView.this.isUpdatingSkills) {
                        CategoryPageView.this.isUpdatingSkills = true;
                        String skillName = newValue.toString();
                        try {
                            CategoryPageViewModel.getSelectedSkills().remove(skillName);
                            CategoryPageViewModel.getUnselectedSkills().add(skillName);

                            Platform.runLater(() -> {
                                CategoryPageView.this.skillsListView.getItems().setAll(CategoryPageViewModel.getSelectedSkills());
                                CategoryPageView.this.skillsListView.getSelectionModel().clearSelection();
                                CategoryPageView.this.skillsComboBox.getItems().setAll(CategoryPageViewModel.getUnselectedSkills());
                                CategoryPageView.this.initializeFilteredPageValues();
                                CategoryPageView.this.updatePeopleButtons();
                                CategoryPageView.this.isUpdatingSkills = false;
                            });
                        } catch (Exception ex) {
                            System.err.println("Error handling skill selection: " + skillName);
                            ex.printStackTrace();
                            CategoryPageView.this.isUpdatingSkills = false;
                        }
                    }
                }
            }
        );
	}

	private void initializeSkillsComboBox() {
		this.skillsComboBox.getItems().setAll(CategoryPageViewModel.getUnselectedSkills());
        this.skillsComboBox.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Object>() {
                @Override
                public void changed(ObservableValue<?> arg0, Object oldValue, Object newValue) {
                    if (newValue != null && !CategoryPageView.this.isUpdatingSkills) {
                        CategoryPageView.this.isUpdatingSkills = true;
                        String skillName = newValue.toString();
                        try {
                            CategoryPageViewModel.getUnselectedSkills().remove(skillName);
                            CategoryPageViewModel.getSelectedSkills().add(skillName);

                            Platform.runLater(() -> {
                                CategoryPageView.this.skillsComboBox.getItems().setAll(CategoryPageViewModel.getUnselectedSkills());
                                CategoryPageView.this.skillsListView.getItems().setAll(CategoryPageViewModel.getSelectedSkills());
                                CategoryPageView.this.skillsComboBox.getSelectionModel().clearSelection();

                                CategoryPageView.this.initializeFilteredPageValues();
                                CategoryPageView.this.updatePeopleButtons();
                                CategoryPageView.this.isUpdatingSkills = false;
                            });

                        } catch (Exception ex) {
                            System.err.println("Error handling skill selection: " + skillName);
                            ex.printStackTrace();
                            CategoryPageView.this.isUpdatingSkills = false;
                        }
                    }
                }
            }
        );
	}

	private void initializeCategoryComboBox() {
		this.categoryComboBox.getItems().setAll(Categories.values());
        this.categoryComboBox.getSelectionModel().select(CategoryPageViewModel.selectedCategory.toUpperCase().replace("_", " "));
        this.categoryComboBox.getSelectionModel().selectedItemProperty().addListener(new 
        		ChangeListener<Object>() {

					@Override
					public void changed(ObservableValue<?> arg0, Object oldValue, Object newValue) {
						if (newValue != null) {
							String categoryName = newValue.toString();
			                try {
			                    CategoryPageViewModel.setSelectedCategory(categoryName);
			                    CategoryPageView.this.initializeFilteredPageValues();
			                    CategoryPageView.this.updatePeopleButtons();
			                } catch (IllegalArgumentException ex) {
			                    System.err.println("Invalid category selected: " + categoryName);
			                }
			            }
					}
				});
	}
    
    private void initializeFilteredPageValues() {
    	if (CategoryPageViewModel.getSelectedCategory() != null) {
            this.categoryName.setText(CategoryPageViewModel.getSelectedCategory().toString().toUpperCase().replace("_", " "));
            this.freelancers = CategoryPageViewModel.getFreelancers();
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
