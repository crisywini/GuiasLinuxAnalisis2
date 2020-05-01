/**
 * Sample Skeleton for 'Splash.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.project.app.ApplicationProject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'Splash.fxml'.";
        assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'Splash.fxml'.";
        new SplashScreen().start();
   }
   
    class SplashScreen extends Thread{
    	@Override
    	public void run() {
    		
    		try {
				Thread.sleep(5000);
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
			    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/RootApplicationPane.fxml"));
						Parent root = null;
						try {
							root = loader.load();
							Scene scene = new Scene(root);
							scene.getStylesheets().add(ApplicationProject.class.getResource("application.css").toExternalForm());
							root.getStyleClass().add("pane");
							Stage stage = new Stage();
							stage.setScene(scene);
							stage.show();
							rootPane.getScene().getWindow().hide();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    	}
    }
}
