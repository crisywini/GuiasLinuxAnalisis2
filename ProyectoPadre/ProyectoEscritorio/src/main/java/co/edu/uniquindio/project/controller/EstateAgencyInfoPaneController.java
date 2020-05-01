/**
 * Sample Skeleton for 'EstateAgencyInfoPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.project.model.EstateAgencyObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EstateAgencyInfoPaneController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="cityField"
	private TextField cityField; // Value injected by FXMLLoader

	@FXML // fx:id="estateAgencyTableView"
	private TableView<EstateAgencyObservable> estateAgencyTableView; // Value injected by FXMLLoader

	@FXML // fx:id="codeColumn"
	private TableColumn<EstateAgencyObservable, String> codeColumn; // Value injected by FXMLLoader

	@FXML // fx:id="nameColumn"
	private TableColumn<EstateAgencyObservable, String> nameColumn; // Value injected by FXMLLoader

	@FXML // fx:id="emailColumn"
	private TableColumn<EstateAgencyObservable, String> emailColumn; // Value injected by FXMLLoader
	@FXML // fx:id="addressColumn"
	private TableColumn<EstateAgencyObservable, String> addressColumn; // Value injected by FXMLLoader
	private MenuAdminPaneController menuPaneController;

	@FXML
	void handleRemoveEstateAgencyButton(ActionEvent event) {

	}

	@FXML
	void handleSearchByCityButton(ActionEvent event) {
		if(isInputValid()) {
			menuPaneController.loadDataEstateAgencyByCity(cityField.getText());
			estateAgencyTableView.refresh();
		}
	}

	@FXML
	void handleSelectAllButton(ActionEvent event) {
		//cargar la tabla otra vez
		menuPaneController.loadEstateAgencyInfoPane();
		estateAgencyTableView.refresh();
		//No se sabe si funciona jejeje
	}
	@FXML
	void handleAddEstateAgencyButton(ActionEvent event) {
		//Saltar otra pantalla
	}


	@FXML
	void handleUpdateEstateAgencyButton(ActionEvent event) {
		//Saltar a otra pantalla con la información del estate agency
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert cityField != null : "fx:id=\"cityField\" was not injected: check your FXML file 'EstateAgencyInfoPane.fxml'.";
		assert estateAgencyTableView != null : "fx:id=\"estateAgencyTableView\" was not injected: check your FXML file 'EstateAgencyInfoPane.fxml'.";
		assert codeColumn != null : "fx:id=\"codeColumn\" was not injected: check your FXML file 'EstateAgencyInfoPane.fxml'.";
		assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'EstateAgencyInfoPane.fxml'.";
		assert emailColumn != null : "fx:id=\"emailColumn\" was not injected: check your FXML file 'EstateAgencyInfoPane.fxml'.";
	}
	public void initTableView() {
		codeColumn.setCellValueFactory(cellData -> cellData.getValue().getCode());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmail());
		addressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
		estateAgencyTableView.setItems(MenuAdminPaneController.ESTATE_AGENCY_DATA);
		estateAgencyTableView.refresh();
	}

	public void setMenuPaneController(MenuAdminPaneController menuPaneController) {
		this.menuPaneController = menuPaneController;
		initTableView();
	}
	public boolean isInputValid() {
		String errorMessage = "";
		boolean isValid = false;
		if(cityField.getText().isEmpty())
			errorMessage += "Debes ingresar el nombre de la ciudad";
		if(errorMessage.isEmpty())
			isValid = true;
		else
			InitController.showAlert(errorMessage, "ADVERTENCIA", "", AlertType.WARNING);
		return isValid;
	}
}

