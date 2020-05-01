/**
 * Sample Skeleton for 'MenuAdminPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.project.model.DelegateTest;
import co.edu.uniquindio.project.model.DwellingsObservable;
import co.edu.uniquindio.project.model.EstateAgencyObservable;
import co.edu.uniquindio.project.model.ProjectObservable;
import co.edu.uniquindio.unihogar.entities.Dwelling;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MenuAdminPaneController {
	public static ObservableList<DwellingsObservable> DWELLING_DATA = FXCollections.observableArrayList();
	public static ObservableList<EstateAgencyObservable> ESTATE_AGENCY_DATA = FXCollections.observableArrayList();
	public static ObservableList<ProjectObservable> PROJECTS_DATA = FXCollections.observableArrayList(); 

	private RootApplicationPaneController rootController;
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="image"
	private ImageView image; // Value injected by FXMLLoader

	@FXML // fx:id="userLabel"
	private Label userLabel; // Value injected by FXMLLoader

	@FXML // fx:id="tablePane"
	private BorderPane tablePane; // Value injected by FXMLLoader
	private User user;
	VBox estateAgencyInfoPane;
	EstateAgencyInfoPaneController estateAgencyInfoController;

	@FXML
	void handleDetailEstateAgencyButton(ActionEvent event) {
		loadEstateAgencyInfoPane();

	}

	@FXML
	void handleDetailProjectButton(ActionEvent event) {

	}

	@FXML
	void handleDwellingsButton(ActionEvent event) {

	}

	@FXML
	void handleLogOutButton(ActionEvent event) {

	}

	@FXML
	void handleStatisticsButton(ActionEvent event) {

	}

	@FXML
	void handleUpdateInfoButton(ActionEvent event) {

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'MenuAdminPane.fxml'.";
		assert userLabel != null : "fx:id=\"userLabel\" was not injected: check your FXML file 'MenuAdminPane.fxml'.";
		assert tablePane != null : "fx:id=\"tablePane\" was not injected: check your FXML file 'MenuAdminPane.fxml'.";
		loadEstateAgencyInfoPane();

	}

	public RootApplicationPaneController getRootController() {
		return rootController;
	}

	public void setRootController(RootApplicationPaneController rootController) {
		this.rootController = rootController;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		userLabel.setText(user.getCode());
	}
	public void loadEstateAgencyInfoPane() {
		if(estateAgencyInfoPane == null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/EstateAgencyInfoPane.fxml"));
			try {
				estateAgencyInfoPane = loader.load();
				estateAgencyInfoController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		loadDataEstateAgency();
		estateAgencyInfoController.setMenuPaneController(this);
		tablePane.setCenter(estateAgencyInfoPane);
	}
	public void loadDataEstateAgency() {
		DelegateTest delegate = DelegateTest.delegateTest;

		List<EstateAgency> listEA = delegate.listAgencies();
		ESTATE_AGENCY_DATA.clear();
		for (EstateAgency estateAgency : listEA) {
			ESTATE_AGENCY_DATA.add(new EstateAgencyObservable(estateAgency.getCode(), estateAgency.getEmail(), estateAgency.getName(), estateAgency.getAddress()));
		}
	}
	public void loadDataDwelling() {
		DelegateTest delegate = DelegateTest.delegateTest;

		DWELLING_DATA.clear();
		List<Dwelling> listD = delegate.listDwellings();
		for (Dwelling dwelling : listD) {
			DWELLING_DATA.add(new DwellingsObservable(""+dwelling.getCode(), dwelling.getArea()+"", dwelling.getPrice()+"", dwelling.getDescription(), dwelling.getNumRooms()+"", ""+dwelling.getNumBathrooms(), dwelling.getType().toString()));
		}
	}
	public void loadDataProject() {
		DelegateTest delegate = DelegateTest.delegateTest;

		PROJECTS_DATA.clear();
		List<Project> projects = delegate.listProjects();
		for (Project project : projects) {
			PROJECTS_DATA.add(new ProjectObservable(""+project.getCode(), project.getName(), ""+project.getLatitude(), ""+project.getLength(), project.getDescription(), project.getCity().getName()));
		}
	}
	public void loadDataEstateAgencyByCity(String nameCity) {
		DelegateTest delegate = DelegateTest.delegateTest;

		List<EstateAgency> listEA = delegate.listAgenciesByCity(nameCity);
		ESTATE_AGENCY_DATA.clear();
		for (EstateAgency estateAgency : listEA) {
			ESTATE_AGENCY_DATA.add(new EstateAgencyObservable(estateAgency.getCode(), estateAgency.getEmail(), estateAgency.getName(), estateAgency.getAddress()));
		}
	}

}
