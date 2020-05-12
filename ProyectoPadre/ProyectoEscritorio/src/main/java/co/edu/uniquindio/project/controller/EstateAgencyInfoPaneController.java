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
import co.edu.uniquindio.unihogar.entities.EstateAgency;
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

/**
 * The class EstateAgencyInfoPaneController
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
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

	/** The menu pane controller. */
	private MenuAdminPaneController menuPaneController;

	/**
	 * Handle remove estate agency button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleRemoveEstateAgencyButton(ActionEvent event) {
		InitController.showAlert("No se puede eliminar las inmobiliarias", "INFORMACIÓN", "", AlertType.INFORMATION);
//		EstateAgencyObservable eao = estateAgencyTableView.getSelectionModel().getSelectedItem();
//		if (eao != null) {
//			String code = eao.getCode().get();
//			DelegateTest delegate = DelegateTest.delegateTest;
//			try {
//				EstateAgency ea = delegate.getEstateAgency(code);
//				if(ea.getProjects().size()==0)
//				{
//					try {
//						delegate.removeEstateAgency(code);
//
//						menuPaneController.loadEstateAgencyInfoPane();
//						estateAgencyTableView.refresh();
//						InitController.showAlert("La inmobiliaria: \n" + eao + "\nHa sido eliminada", "INFORMACIÓN", "",
//								AlertType.INFORMATION);
//					} catch (NonexistentUserException e) {
//						InitController.showAlert(e.getMessage(), "ERROR", "", AlertType.ERROR);
//					}
//				}
//			} catch (NonexistentUserException e1) {
//				System.out.println("Never happen");
//			}
//
//
//		} else
//			InitController.showAlert("Debes seleccionar una inmobiliaria", "ERROR", "", AlertType.ERROR);

	}

	/**
	 * Handle search by city button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleSearchByCityButton(ActionEvent event) {
		if (isInputValid()) {
			menuPaneController.loadDataEstateAgencyByCity(cityField.getText());
			estateAgencyTableView.refresh();
		}
	}

	/**
	 * Handle select all button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleSelectAllButton(ActionEvent event) {
		cityField.setText("");
		cityField.setPromptText("Ingresa la ciudad");
		menuPaneController.loadEstateAgencyInfoPane();
		estateAgencyTableView.refresh();
	}

	/**
	 * Handle add estate agency button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleAddEstateAgencyButton(ActionEvent event) {
		loadAddEstateAgencyPane();
		menuPaneController.loadDataEstateAgency();
		estateAgencyTableView.refresh();
	}

	/**
	 * Handle update estate agency button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleUpdateEstateAgencyButton(ActionEvent event) {
		EstateAgencyObservable eao = estateAgencyTableView.getSelectionModel().getSelectedItem();
		if (eao != null) {
			String codeEstateAgency = eao.getCode().get();
			loadUpdateStage(codeEstateAgency);
			menuPaneController.loadEstateAgencyInfoPane();
			estateAgencyTableView.refresh();
		} else
			InitController.showAlert("Debes seleccionar una inmobiliaria", "ERROR", "", AlertType.ERROR);
	}

	/**
	 * Load update stage.
	 *
	 * @param codeEstateAgency the code estate agency
	 */
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

	/**
	 * Load add estate agency pane.
	 */
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
		}
	}

	/**
	 * Initialize.
	 */
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert cityField != null : "fx:id=\"cityField\" was not injected: check your FXML file 'EstateAgencyInfoPane.fxml'.";
		assert estateAgencyTableView != null : "fx:id=\"estateAgencyTableView\" was not injected: check your FXML file 'EstateAgencyInfoPane.fxml'.";
		assert codeColumn != null : "fx:id=\"codeColumn\" was not injected: check your FXML file 'EstateAgencyInfoPane.fxml'.";
		assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'EstateAgencyInfoPane.fxml'.";
		assert emailColumn != null : "fx:id=\"emailColumn\" was not injected: check your FXML file 'EstateAgencyInfoPane.fxml'.";
	}

	/**
	 * Inits the table view.
	 */
	public void initTableView() {
		codeColumn.setCellValueFactory(cellData -> cellData.getValue().getCode());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmail());
		addressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
		estateAgencyTableView.setItems(MenuAdminPaneController.ESTATE_AGENCY_DATA);
		estateAgencyTableView.refresh();
	}

	/**
	 * Sets the menu pane controller.
	 *
	 * @param menuPaneController the new menu pane controller
	 */
	public void setMenuPaneController(MenuAdminPaneController menuPaneController) {
		this.menuPaneController = menuPaneController;
		initTableView();
	}

	/**
	 * Checks if is input valid.
	 *
	 * @return true, if is input valid
	 */
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
