/**
 * Sample Skeleton for 'RootStatisticsPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class RootStatisticsPaneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rootPane"
    private BorderPane rootPane; // Value injected by FXMLLoader
    private RootApplicationPaneController rootApp;
    VBox easCountProjectPane;
    EstateAgenciesAndCountProjectsController easCountProjectController;

    @FXML
    void handleBackButton(ActionEvent event) {

    }

    @FXML
    void handleEstateAgencyWithProjectMenuItem(ActionEvent event) {
    	loadEstateAgenciesAndCountProject();

    }

    @FXML
    void handleTopCityByProjectMenuItem(ActionEvent event) {

    }

    @FXML
    void handleTopEAByCityMenuItem(ActionEvent event) {

    }

    @FXML
    void handleTopProjectsByDwellingMenuItem(ActionEvent event) {

    }

    @FXML
    void handleTopProjectsByRatingMenuItem(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'RootStatisticsPane.fxml'.";
    }
    public void loadEstateAgenciesAndCountProject() {
    	if(easCountProjectPane==null) {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/EstateAgenciesAndCountProjectsPane.fxml"));
				easCountProjectPane = loader.load();
				easCountProjectController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	easCountProjectController.setRootStatisticsController(this);
    	rootPane.setCenter(easCountProjectPane);
    }

	public void setRootApp(RootApplicationPaneController rootApp) {
		this.rootApp = rootApp;
	}
}

