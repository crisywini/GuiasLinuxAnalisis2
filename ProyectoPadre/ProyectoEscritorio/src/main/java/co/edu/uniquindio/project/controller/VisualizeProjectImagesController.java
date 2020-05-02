/**
 * Sample Skeleton for 'VisualizeProjectImages.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.unihogar.entities.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class VisualizeProjectImagesController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="anchorPane"
	private AnchorPane anchorPane; // Value injected by FXMLLoader

	@FXML // fx:id="imageView"
	private ImageView imageView; // Value injected by FXMLLoader
	private Scene scene;
	private int posImage = 0;
	private Project project;

	@FXML
	void handleBackButton(ActionEvent event) {
		scene.getWindow().hide();
	}

	@FXML
	void handleBackImageButton(ActionEvent event) {
		if(posImage>=0) {
			imageView.setImage(new Image("file:"+project.getImages().get(posImage)));
			posImage--;
		}
		else
			posImage = 0;
	}

	@FXML
	void handleNextImageButton(ActionEvent event) {
		if(posImage < project.getImages().size()) {
			imageView.setImage(new Image("file:"+project.getImages().get(posImage)));
			posImage++;
		}
		else
			posImage = 0;
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'VisualizeProjectImages.fxml'.";
		assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'VisualizeProjectImages.fxml'.";
		imageView.setImage(new Image("file:"+project.getImages().get(0)));
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
