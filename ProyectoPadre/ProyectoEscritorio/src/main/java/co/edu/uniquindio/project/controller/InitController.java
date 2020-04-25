/**
 * Sample Skeleton for 'initPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.io.IOException;
import java.net.URL;



import java.util.ResourceBundle;

import co.edu.uniquindio.project.app.ApplicationProject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class InitController {

    @FXML// ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // fx:id="parent"
    private BorderPane parent; // Value injected by FXMLLoader
    private ApplicationProject main;
    AnchorPane logginPane;
    LogginPaneController logginController;
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	loadLogginPane();

    }
    public void loadLogginPane() {
    	if(logginPane == null) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/logginPane.fxml"));
    		try {
				logginPane = loader.load();
				logginController = loader.getController();
				logginController.setInitController(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	parent.setCenter(logginPane);
    }
    
    public static void showAlert(String contentText, String title, String headerText, AlertType alertType){
    	Alert alert = new Alert(alertType);
    	alert.setContentText(contentText);
    	alert.setHeaderText(headerText);
    	alert.setTitle(title);
    	alert.showAndWait();
    }
    

	public ApplicationProject getMain() {
		return main;
	}
	public void setMain(ApplicationProject main) {
		this.main = main;
	}
}
