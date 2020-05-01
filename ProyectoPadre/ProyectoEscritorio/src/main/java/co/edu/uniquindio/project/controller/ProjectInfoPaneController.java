/**
 * Sample Skeleton for 'ProjectInfoPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.project.model.ProjectObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private MenuAdminPaneController menuController;

    @FXML
    void handleSearchByNameButton(ActionEvent event) {

    }

    @FXML
    void handleSelectAllButton(ActionEvent event) {

    }

    @FXML
    void handleVisualizeImagesButton(ActionEvent event) {

    }

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

	public void setMenuController(MenuAdminPaneController menuController) {
		this.menuController = menuController;
	}
    
}
