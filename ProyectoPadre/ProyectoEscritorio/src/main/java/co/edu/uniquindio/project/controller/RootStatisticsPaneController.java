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

/**
 * The class RootStatisticsPaneController
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class RootStatisticsPaneController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="rootPane"
	private BorderPane rootPane; // Value injected by FXMLLoader

	/** The root app. */
	private RootApplicationPaneController rootApp;

	/** The eas count project pane. */
	VBox easCountProjectPane;

	/** The eas count project controller. */
	EstateAgenciesAndCountProjectsController easCountProjectController;

	/** The top estate agency by city. */
	VBox topEstateAgencyByCity;

	/** The top estate agency by city controller. */
	TopEstateAgencyByCityController topEstateAgencyByCityController;

	/** The top cities by project. */
	VBox topCitiesByProject;

	/** The top city by project controller. */
	TopCityByProjectController topCityByProjectController;

	/** The top projects by rating. */
	VBox topProjectsByRating;

	/** The top projects by rating controller. */
	TopProjectsByRatingController topProjectsByRatingController;

	/** The top projects by dwellings. */
	VBox topProjectsByDwellings;

	/** The top projects by dwellings controller. */
	TopProjectsByDwellingsController topProjectsByDwellingsController;

	/**
	 * Handle back button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleBackButton(ActionEvent event) {
		// Se debe solo cerrar
		rootPane.getScene().getWindow().hide();
	}

	/**
	 * Handle estate agency with project menu item.
	 *
	 * @param event the event
	 */
	@FXML
	void handleEstateAgencyWithProjectMenuItem(ActionEvent event) {
		loadEstateAgenciesAndCountProject();

	}

	/**
	 * Handle top city by project menu item.
	 *
	 * @param event the event
	 */
	@FXML
	void handleTopCityByProjectMenuItem(ActionEvent event) {
		loadTopCitiesByProject();
	}

	/**
	 * Handle top EA by city menu item.
	 *
	 * @param event the event
	 */
	@FXML
	void handleTopEAByCityMenuItem(ActionEvent event) {
		loadTopEstateAgenciesByCity();
	}

	/**
	 * Handle top projects by dwelling menu item.
	 *
	 * @param event the event
	 */
	@FXML
	void handleTopProjectsByDwellingMenuItem(ActionEvent event) {
		loadTopProjectsByDwellings();
	}

	/**
	 * Handle top projects by rating menu item.
	 *
	 * @param event the event
	 */
	@FXML
	void handleTopProjectsByRatingMenuItem(ActionEvent event) {
		loadTopProjectsByRating();
	}

	/**
	 * Initialize.
	 */
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'RootStatisticsPane.fxml'.";
	}

	/**
	 * Load estate agencies and count project.
	 */
	public void loadEstateAgenciesAndCountProject() {
		if (easCountProjectPane == null) {
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

	/**
	 * Load top estate agencies by city.
	 */
	public void loadTopEstateAgenciesByCity() {
		if (topEstateAgencyByCity == null) {
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

	/**
	 * Load top cities by project.
	 */
	public void loadTopCitiesByProject() {
		if (topCitiesByProject == null) {
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

	/**
	 * Load top projects by rating.
	 */
	public void loadTopProjectsByRating() {
		if (topProjectsByRating == null) {
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

	/**
	 * Load top projects by dwellings.
	 */
	public void loadTopProjectsByDwellings() {
		if (topProjectsByDwellings == null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/TopProjectsByDwellings.fxml"));
				topProjectsByDwellings = loader.load();
				topProjectsByDwellingsController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		topProjectsByDwellingsController.setRootStatisticsController(this);
		rootPane.setCenter(topProjectsByDwellings);

	}

	/**
	 * Sets the root app.
	 *
	 * @param rootApp the new root app
	 */
	public void setRootApp(RootApplicationPaneController rootApp) {
		this.rootApp = rootApp;
	}
}
