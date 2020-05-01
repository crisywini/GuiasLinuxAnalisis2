/**
 * Sample Skeleton for 'Splash.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SplashController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="rootPane"
	private StackPane rootPane; // Value injected by FXMLLoader

	@FXML // fx:id="imageView"
	private ImageView imageView; // Value injected by FXMLLoader
	private InitController initController;
	private Stage stage;

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'Splash.fxml'.";
		assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'Splash.fxml'.";
		new SplashScreen().start();
	}

	public InitController getInitController() {
		return initController;
	}

	public void setInitController(InitController initController) {
		this.initController = initController;
	}


	class SplashScreen extends Thread{
		@Override
		public void run() {

			try {
				Thread.sleep(5000);
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						getStage().show();
						rootPane.getScene().getWindow().hide();
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void showNewStage(Scene scene) {

	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
