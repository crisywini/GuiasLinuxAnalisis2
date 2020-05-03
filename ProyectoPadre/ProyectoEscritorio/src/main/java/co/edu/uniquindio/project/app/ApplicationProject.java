package co.edu.uniquindio.project.app;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.project.controller.InitController;
import co.edu.uniquindio.unihogar.entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The class ApplicationProject
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class ApplicationProject extends Application {

	public static final ArrayList<User> LOGGIN_DATA = new ArrayList<User>();

	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			loadMain(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Load main.
	 *
	 * @param primaryStage the primary stage
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void loadMain(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(ApplicationProject.class.getResource("/initPane.fxml"));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		parent.getStyleClass().add("pane");
		InitController controller = loader.getController();
		controller.setMain(this);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Init application");
		primaryStage.show();
	}
}
