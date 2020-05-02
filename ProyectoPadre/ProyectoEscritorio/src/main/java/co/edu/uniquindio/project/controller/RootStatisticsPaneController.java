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
    VBox topEstateAgencyByCity;
    TopEstateAgencyByCityController topEstateAgencyByCityController;
    VBox topCitiesByProject;
    TopCityByProjectController topCityByProjectController;
    VBox topProjectsByRating;
    TopProjectsByRatingController topProjectsByRatingController;

    @FXML
    void handleBackButton(ActionEvent event) {
    	//Se debe solo cerrar 
    	rootPane.getScene().getWindow().hide();
    }

    @FXML
    void handleEstateAgencyWithProjectMenuItem(ActionEvent event) {
    	loadEstateAgenciesAndCountProject();

    }

    @FXML
    void handleTopCityByProjectMenuItem(ActionEvent event) {
    	loadTopCitiesByProject();
    }

    @FXML
    void handleTopEAByCityMenuItem(ActionEvent event) {
    	loadTopEstateAgenciesByCity();
    }

    @FXML
    void handleTopProjectsByDwellingMenuItem(ActionEvent event) {

    }

    @FXML
    void handleTopProjectsByRatingMenuItem(ActionEvent event) {
    	loadTopProjectsByRating();
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
    public void loadTopEstateAgenciesByCity() {
    	if(topEstateAgencyByCity==null) {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/TopEstateAgencyByCityPane.fxml"));
				topEstateAgencyByCity = loader.load();
				topEstateAgencyByCityController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	topEstateAgencyByCityController.setRootStatisticsController(this);
    	rootPane.setCenter(topEstateAgencyByCity);
    }
    public void loadTopCitiesByProject() {
    	if(topCitiesByProject==null) {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/TopCityByProjectPane.fxml"));
				topCitiesByProject = loader.load();
				topCityByProjectController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	topCityByProjectController.setRootStatisticsController(this);
    	rootPane.setCenter(topCitiesByProject);
    }
    public void loadTopProjectsByRating() {
    	if(topProjectsByRating==null) {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/TopProjectsByRatingsPane.fxml"));
				topProjectsByRating = loader.load();
				topProjectsByRatingController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	topProjectsByRatingController.setRootStatisticsController(this);
    	rootPane.setCenter(topProjectsByRating);
    }


	public void setRootApp(RootApplicationPaneController rootApp) {
		this.rootApp = rootApp;
	}
}

