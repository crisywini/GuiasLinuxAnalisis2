/**
 * Sample Skeleton for 'TopProjectsByDwellings.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.project.model.DelegateTest;
import co.edu.uniquindio.unihogar.dto.QueryDwellingByProjectDTO;
import co.edu.uniquindio.unihogar.dto.QueryNumberProjectByCityDTO;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * The class TopProjectsByDwellingsController
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class TopProjectsByDwellingsController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="barChart"
	private BarChart<String, Number> barChart; // Value injected by FXMLLoader

	@FXML // fx:id="categoryAxis"
	private CategoryAxis categoryAxis; // Value injected by FXMLLoader

	@FXML // fx:id="numberAxis"
	private NumberAxis numberAxis; // Value injected by FXMLLoader

	/** The root statistics controller. */
	private RootStatisticsPaneController rootStatisticsController;

	/**
	 * Initialize.
	 */
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert barChart != null : "fx:id=\"barChart\" was not injected: check your FXML file 'TopProjectsByDwellings.fxml'.";
		assert categoryAxis != null : "fx:id=\"categoryAxis\" was not injected: check your FXML file 'TopProjectsByDwellings.fxml'.";
		assert numberAxis != null : "fx:id=\"numberAxis\" was not injected: check your FXML file 'TopProjectsByDwellings.fxml'.";
	}

	/**
	 * Load data to bar chart.
	 */
	public void loadDataToBarChart() {
		barChart.getData().clear();
		DelegateTest delegate = DelegateTest.delegateTest;
		List<QueryDwellingByProjectDTO> query = delegate.getTopProjectsByDwellings();
		barChart.setTitle("Cantidad de viviendas en projectos");
		categoryAxis.setLabel("Proyectos");
		numberAxis.setLabel("Viviendas");

		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Proyectos");
		for (QueryDwellingByProjectDTO object : query) {
			series1.getData().add(new XYChart.Data<>(object.getNameProject(), object.getNumberDwellings()));
		}

		barChart.getData().addAll(series1);
	}

	/**
	 * Sets the root statistics controller.
	 *
	 * @param rootStatisticsController the new root statistics controller
	 */
	public void setRootStatisticsController(RootStatisticsPaneController rootStatisticsController) {
		this.rootStatisticsController = rootStatisticsController;
		loadDataToBarChart();
	}
}
