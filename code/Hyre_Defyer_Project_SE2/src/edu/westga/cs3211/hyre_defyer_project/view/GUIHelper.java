package edu.westga.cs3211.hyre_defyer_project.view;

import java.io.IOException;

import edu.westga.cs3211.hyre_defyer_project.Main;
import edu.westga.cs3211.hyre_defyer_project.model.FreelancerRoster;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Helper class for methods that all the codebehind classes will need
 * 
 * @author Myles Debro and Jacob Baker
 * @version Spring 2025
 */
public class GUIHelper {
	
	private static FreelancerRoster freelancerRoster = new FreelancerRoster();
	
	/**
	 * get the roster that is static and saved here for all the UI to be able to get the same roster list
	 * 
	 * @return freelancerRoster which is the roster that is saved in the GUIHelper
	 */
	public static FreelancerRoster getFreelancerRoster() {
		return freelancerRoster;
	}
	
	/**
	 * Switch the current view to a different one
	 * 
	 * @param anchorPane the anchor pane of the current view
	 * @param view the enum of the view you want to switch to
	 * @throws IOException if file is not found (be sure to use "view/" in front of the file name)
	 */
	public static void switchView(AnchorPane anchorPane, Views view) {
		try {
			Stage stage = (Stage) anchorPane.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(view.location()));
			loader.load();
			Parent parent = loader.getRoot();
			Scene scene = new Scene(parent);
			stage.setTitle(Main.getWindowTitle());
			stage.setScene(scene);
		} catch (IOException error) {
			displayError("Display Switch Error", error.getMessage());
		}
	}
	
	/**
	 * Display an error modal window
	 * @param headerTxt the text you want for the header of the window
	 * @param contextTxt the text you want in the main window
	 */
	public static void displayError(String headerTxt, String contextTxt) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(headerTxt);
		alert.setContentText(contextTxt);
		alert.showAndWait();
	}

}
