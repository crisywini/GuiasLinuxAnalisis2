/**
 * Sample Skeleton for 'TopEstateAgencyByCityPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.project.model.DelegateTest;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class TopEstateAgencyByCityController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cityNameField"
    private RootStatisticsPaneController rootStatisticsController;
    @FXML // fx:id="combo"
    private ComboBox<String> combo; // Value injected by FXMLLoader
    @FXML // fx:id="panePie"
    private HBox panePie; // Value injected by FXMLLoader


    @FXML
    void handleShowDataButton(ActionEvent event) {
    	if(isInpuValid()) {
    		loadData();
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //loadComboBoxData();
    }
	public void setRootStatisticsController(RootStatisticsPaneController rootStatisticsController) {
		this.rootStatisticsController = rootStatisticsController;
		loadComboBoxData();
		loadData();
	}
	public void loadData() {
		panePie.getChildren().clear();
		DelegateTest delegate = DelegateTest.delegateTest;
		String selectedCity = combo.getSelectionModel().getSelectedItem();
		List<EstateAgency> eas = delegate.getTop5ListEstateAgenciesByCity(selectedCity);
		
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		for (EstateAgency ea : eas) {
			data.add(new PieChart.Data(ea.getName(), ea.getProjects().size()));
		}
	    PieChart pieChart = new PieChart(data);
		pieChart.setTitle("PieChart Tutorial 2017");
		pieChart.setLegendSide(Side.LEFT);
		pieChart.setTitleSide(Side.TOP);
		pieChart.setLabelLineLength(60);
		pieChart.setLabelsVisible(true);
		pieChart.getData().forEach(info -> {
		    String percentage = String.format("%.2f%%", (info.getPieValue() / 100));
		    Tooltip toolTip = new Tooltip(percentage);
		    Tooltip.install(info.getNode(), toolTip);
		});
		panePie.getChildren().addAll(pieChart);
	}
	public void loadComboBoxData() {
		combo.getItems().clear();
		ObservableList<String> data = FXCollections.observableArrayList();
		DelegateTest delegate = DelegateTest.delegateTest;
		List<City> cities = delegate.getCities();
		for (City city : cities) {
			data.add(city.getName());
		}
		combo.setItems(data);
	}
	public boolean isInpuValid() {
		boolean isValid = false;
		String errorMessage = "";
		if(combo.getSelectionModel().isEmpty())
			errorMessage += "Debes seleccionar el nombre de la ciudad";
		if(errorMessage.isEmpty())
			isValid = true;
		else
			InitController.showAlert(errorMessage, "ADVERTENCIA", "", AlertType.WARNING);
		return isValid;
	}
}
