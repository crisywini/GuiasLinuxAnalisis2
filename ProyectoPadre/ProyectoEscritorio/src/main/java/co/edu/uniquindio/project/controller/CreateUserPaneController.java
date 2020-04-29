/**
 * Sample Skeleton for 'CreateUserPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateUserPaneController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="cedulaField"
	private TextField cedulaField; // Value injected by FXMLLoader

	@FXML // fx:id="emailField"
	private TextField emailField; // Value injected by FXMLLoader

	@FXML // fx:id="passwordField"
	private PasswordField passwordField; // Value injected by FXMLLoader

	@FXML // fx:id="rePasswordField"
	private PasswordField rePasswordField; // Value injected by FXMLLoader
	private InitController initController;

	@FXML
	void handleCreateUserButton(ActionEvent event) {

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert cedulaField != null : "fx:id=\"cedulaField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert rePasswordField != null : "fx:id=\"rePasswordField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";

	}

	public InitController getInitController() {
		return initController;
	}

	public void setInitController(InitController initController) {
		this.initController = initController;
	}
	@FXML
	void handleBackButton(ActionEvent event) {
		initController.loadLogginPane();

	}
}
