/**
 * Sample Skeleton for 'EstateAgencyInfoPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.project.app.ApplicationProject;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.model.DelegateTest;
import co.edu.uniquindio.project.model.EstateAgencyObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
		EstateAgencyObservable eao = estateAgencyTableView.getSelectionModel().getSelectedItem();
		if (eao != null) {
			String code = eao.getCode().get();
			DelegateTest delegate = DelegateTest.delegateTest;
			try {
				delegate.removeEstateAgency(code);
				menuPaneController.loadEstateAgencyInfoPane();
				estateAgencyTableView.refresh();
				InitController.showAlert("La inmobiliaria: \n" + eao + "\nHa sido eliminada", "INFORMACIÓN", "",
						AlertType.INFORMATION);
			} catch (NonexistentUserException e) {
				InitController.showAlert(e.getMessage(), "ERROR", "", AlertType.ERROR);
			}

		} else
			InitController.showAlert("Debes seleccionar una inmobiliaria", "ERROR", "", AlertType.ERROR);

	}

	@FXML
	void handleSearchByCityButton(ActionEvent event) {
		if (isInputValid()) {
			menuPaneController.loadDataEstateAgencyByCity(cityField.getText());
			estateAgencyTableView.refresh();
		}
	}

	@FXML
	void handleSelectAllButton(ActionEvent event) {
		cityField.setText("");
		cityField.setPromptText("Ingresa la ciudad");
		menuPaneController.loadEstateAgencyInfoPane();
		estateAgencyTableView.refresh();
	}

	@FXML
	void handleAddEstateAgencyButton(ActionEvent event) {
		loadAddEstateAgencyPane();
		menuPaneController.loadDataEstateAgency();
		estateAgencyTableView.refresh();
	}

	@FXML
	void handleUpdateEstateAgencyButton(ActionEvent event) {
		EstateAgencyObservable eao = estateAgencyTableView.getSelectionModel().getSelectedItem();
		if(eao!=null) {
			String codeEstateAgency = eao.getCode().get();
			loadUpdateStage(codeEstateAgency);
			menuPaneController.loadEstateAgencyInfoPane();
			estateAgencyTableView.refresh();
		}else
			InitController.showAlert("Debes seleccionar una inmobiliaria", "ERROR", "", AlertType.ERROR);
	}
	public void loadUpdateStage(String codeEstateAgency) {
		FXMLLoader loader = new FXMLLoader(ApplicationProject.class.getResource("/UpdateEstateAgencyPane.fxml"));
		try {
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			scene.getStylesheets().add(ApplicationProject.class.getResource("application.css").toExternalForm());
			parent.getStyleClass().add("pane");
			Stage stage = new Stage();
			UpdateEstateAgencyPaneController controller = loader.getController();
			controller.setCodeEstateAgency(codeEstateAgency);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadAddEstateAgencyPane() {
		FXMLLoader loader = new FXMLLoader(ApplicationProject.class.getResource("/AddEstateAgency.fxml"));
		try {
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			scene.getStylesheets().add(ApplicationProject.class.getResource("application.css").toExternalForm());
			parent.getStyleClass().add("pane");
			AddEstateAgencyController controller = loader.getController();
			controller.setLastController(this);
			controller.setScene(scene);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}	}

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
		if (cityField.getText().isEmpty())
			errorMessage += "Debes ingresar el nombre de la ciudad";
		if (errorMessage.isEmpty())
			isValid = true;
		else
			InitController.showAlert(errorMessage, "ADVERTENCIA", "", AlertType.WARNING);
		return isValid;
	}
}
