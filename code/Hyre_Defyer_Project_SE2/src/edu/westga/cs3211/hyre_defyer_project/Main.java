package edu.westga.cs3211.hyre_defyer_project;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Entry point for the program​
 *​
 * @author Jacob Baker & Myles Debro​
 * @version Spring 2025​
 */
public class Main extends Application {

	private static final String WINDOW_TITLE = "Hyre Defyer";
	private static final String GUI_RESOURCE = "view/HomePageView.fxml";
	  
	/** JavaFX entry point.​
	  *​
	  * @precondition none​
	  * @postcondition none​
	  *​
	  * @throws IOException unable to load fxml for MainWindow​
	  */
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(Main.GUI_RESOURCE));
		Scene scene = new Scene(parent);
		primaryStage.setTitle(Main.WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/** Primary Java entry point.​
	  *​
	  * @precondition none​
	  * @postcondition none​
	  *​
	  * @param args command line arguments​
	  */
	public static void main(String[] args) {
		Main.launch(args);
	}
	
	/** Get the window title
	 * 
	 * @return window title
	 */
	public static String getWindowTitle() {
		return Main.WINDOW_TITLE;
	}
}
