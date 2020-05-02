/**
 * Sample Skeleton for 'TopProjectsByRatingsPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.project.model.DelegateTest;
import co.edu.uniquindio.unihogar.dto.QueryNumberProjectByCityDTO;
import co.edu.uniquindio.unihogar.entities.Project;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class TopProjectsByRatingController {

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
    private RootStatisticsPaneController rootStatisticsController;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert barChart != null : "fx:id=\"barChart\" was not injected: check your FXML file 'TopProjectsByRatingsPane.fxml'.";
        assert categoryAxis != null : "fx:id=\"categoryAxis\" was not injected: check your FXML file 'TopProjectsByRatingsPane.fxml'.";
        assert numberAxis != null : "fx:id=\"numberAxis\" was not injected: check your FXML file 'TopProjectsByRatingsPane.fxml'.";
    } 
    public void loadDataToBarChart() {
    	barChart.getData().clear();
    	DelegateTest delegate = DelegateTest.delegateTest;
    	List<Project> query = delegate.getTop5ProjectsByRatings();
    	 barChart.setTitle("Proyectos mejores calificados");
         categoryAxis.setLabel("Proyectos");       
         numberAxis.setLabel("Calificaciones");
  
         XYChart.Series<String, Number> series1 = new XYChart.Series<>();
         series1.setName("Proyecto");       
         for (Project object : query) {
        	 series1.getData().add(new XYChart.Data<>(object.getName(),object.getRatings().size()));
		}
     
         barChart.getData().addAll(series1);
    }
	public void setRootStatisticsController(RootStatisticsPaneController rootStatisticsController) {
		this.rootStatisticsController = rootStatisticsController;
		loadDataToBarChart();
	}
}
