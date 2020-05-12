/**
 * Sample Skeleton for 'ProjectInfoPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.project.app.ApplicationProject;
import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.project.model.DelegateTest;
import co.edu.uniquindio.project.model.ProjectObservable;
import co.edu.uniquindio.unihogar.entities.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * The class ProjectInfoPaneController
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class ProjectInfoPaneController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="nameField"
	private TextField nameField; // Value injected by FXMLLoader

	@FXML // fx:id="projectTableView"
	private TableView<ProjectObservable> projectTableView; // Value injected by FXMLLoader

	@FXML // fx:id="codeTableColumn"
	private TableColumn<ProjectObservable, String> codeTableColumn; // Value injected by FXMLLoader

	@FXML // fx:id="nameTableColumn"
	private TableColumn<ProjectObservable, String> nameTableColumn; // Value injected by FXMLLoader

	@FXML // fx:id="latTableColumn"
	private TableColumn<ProjectObservable, String> latTableColumn; // Value injected by FXMLLoader

	@FXML // fx:id="lengthTableColumn"
	private TableColumn<ProjectObservable, String> lengthTableColumn; // Value injected by FXMLLoader

	@FXML // fx:id="descTableColumn"
	private TableColumn<ProjectObservable, String> descTableColumn; // Value injected by FXMLLoader

	@FXML // fx:id="cityTableColumn"
	private TableColumn<ProjectObservable, String> cityTableColumn; // Value injected by FXMLLoader

	/** The menu controller. */
	private MenuAdminPaneController menuController;

	/**
	 * Handle search by name button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleSearchByNameButton(ActionEvent event) {
		if (isInputValid()) {
			menuController.loadProjectByCityData(nameField.getText());
			projectTableView.refresh();
		}
	}

	/**
	 * Handle select all button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleSelectAllButton(ActionEvent event) {
		nameField.setText("");
		nameField.setPromptText("Ingrese la ciudad");
		menuController.loadProjectInfoPane();
		projectTableView.refresh();
	}

	/**
	 * Handle visualize images button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleVisualizeImagesButton(ActionEvent event) {
		InitController.showAlert("Esta función aún no está habilitada", "INFORMACIÓN", "", AlertType.INFORMATION);

//    	ProjectObservable projectSelected = projectTableView.getSelectionModel().getSelectedItem();
//    	if(projectSelected!=null) {
//    		DelegateTest delegate = DelegateTest.delegateTest;
//    		try {
//				Project project = delegate.getProject(Integer.parseInt(projectSelected.getCode().get()));
//				System.out.println(project.getImages());
//				if(project.getImages().isEmpty())
//					InitController.showAlert("El projecto: "+project.getName()+" no tiene imágenes", "INFORMACIÓN", "", AlertType.INFORMATION);
//				else {
//					loadVisualizeImages(project);
//				}
//			} catch (NonexistentProject e) {
//				InitController.showAlert(e.getMessage(), "ERROR", "", AlertType.ERROR);
//			}
//    	}
//    	else
//    		InitController.showAlert("Debes seleccionar un projecto", "ADVERTENCIA", "", AlertType.WARNING);
	}

	/**
	 * Initialize.
	 */
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert projectTableView != null : "fx:id=\"projectTableView\" was not injected: check your FXML file 'ProjectInfoPane.fxml'.";
		assert codeTableColumn != null : "fx:id=\"codeTableColumn\" was not injected: check your FXML file 'ProjectInfoPane.fxml'.";
		assert nameTableColumn != null : "fx:id=\"nameTableColumn\" was not injected: check your FXML file 'ProjectInfoPane.fxml'.";
		assert latTableColumn != null : "fx:id=\"latTableColumn\" was not injected: check your FXML file 'ProjectInfoPane.fxml'.";
		assert lengthTableColumn != null : "fx:id=\"lengthTableColumn\" was not injected: check your FXML file 'ProjectInfoPane.fxml'.";
		assert descTableColumn != null : "fx:id=\"descTableColumn\" was not injected: check your FXML file 'ProjectInfoPane.fxml'.";
		assert cityTableColumn != null : "fx:id=\"cityTableColumn\" was not injected: check your FXML file 'ProjectInfoPane.fxml'.";
	}

	/**
	 * Inits the table view.
	 */
	public void initTableView() {
		codeTableColumn.setCellValueFactory(cellData -> cellData.getValue().getCode());
		nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		latTableColumn.setCellValueFactory(cellData -> cellData.getValue().getLatitude());
		lengthTableColumn.setCellValueFactory(cellData -> cellData.getValue().getLength());
		descTableColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
		cityTableColumn.setCellValueFactory(cellData -> cellData.getValue().getCityName());
		projectTableView.setItems(MenuAdminPaneController.PROJECTS_DATA);
		projectTableView.refresh();
	}

	/**
	 * Sets the menu controller.
	 *
	 * @param menuController the new menu controller
	 */
	public void setMenuController(MenuAdminPaneController menuController) {
		this.menuController = menuController;
		initTableView();
	}

	/**
	 * Checks if is input valid.
	 *
	 * @return true, if is input valid
	 */
	public boolean isInputValid() {
		boolean isValid = false;
		String errorMessage = "";
		if (nameField.getText().isEmpty())
			errorMessage += "Debes ingresar la ciudad para buscar los proyectos";
		if (errorMessage.isEmpty())
			isValid = true;
		else
			InitController.showAlert(errorMessage, "ADVERTENCIA", "", AlertType.WARNING);
		return isValid;
	}

	/**
	 * Load visualize images.
	 *
	 * @param project the project
	 */
	public void loadVisualizeImages(Project project) {
		FXMLLoader loader = new FXMLLoader(ApplicationProject.class.getResource("/VisualizeProjectImages.fxml"));
		try {
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			scene.getStylesheets().add(ApplicationProject.class.getResource("application.css").toExternalForm());
			parent.getStyleClass().add("pane");
			Stage stage = new Stage();
			VisualizeProjectImagesController controller = loader.getController();
			controller.setProject(project);
			controller.setScene(scene);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
