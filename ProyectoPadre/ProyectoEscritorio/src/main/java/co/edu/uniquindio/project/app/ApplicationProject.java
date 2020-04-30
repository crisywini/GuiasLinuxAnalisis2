package co.edu.uniquindio.project.app;

import java.io.IOException;


import co.edu.uniquindio.project.controller.InitController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationProject extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			loadMain(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
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
