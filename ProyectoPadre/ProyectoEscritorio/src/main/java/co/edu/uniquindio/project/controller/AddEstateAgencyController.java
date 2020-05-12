/**
 * Sample Skeleton for 'AddEstateAgency.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.project.model.DelegateTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * The class AddEstateAgencyController
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class AddEstateAgencyController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="userField"
	private TextField userField; // Value injected by FXMLLoader

	@FXML // fx:id="nameField"
	private TextField nameField; // Value injected by FXMLLoader

	@FXML // fx:id="addressField"
	private TextField addressField; // Value injected by FXMLLoader

	@FXML // fx:id="codeField"
	private TextField codeField; // Value injected by FXMLLoader

	@FXML // fx:id="passwordField"
	private PasswordField passwordField; // Value injected by FXMLLoader

	@FXML // fx:id="anchorPane"
	private AnchorPane anchorPane; // Value injected by FXMLLoader

	/** The last controller. */
	private EstateAgencyInfoPaneController lastController;

	/** The scene. */
	private Scene scene;

	/**
	 * Handle add button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleAddButton(ActionEvent event) {
		if (isInputValid()) {
			DelegateTest delegate = DelegateTest.delegateTest;
			try {
				delegate.createEstateAgency(nameField.getText(), codeField.getText(), userField.getText(),
						passwordField.getText(), addressField.getText());
				InitController.showAlert("Se ha creado la nueva inmobiliaria: " + nameField.getText(), "INFORMACIÓN",
						"", AlertType.INFORMATION);
				scene.getWindow().hide();

			} catch (RepeatedUserException e) {
				InitController.showAlert(e.getMessage(), "ERROR", "", AlertType.ERROR);
			}
		}
	}

	/**
	 * Handle back button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleBackButton(ActionEvent event) {
		scene.getWindow().hide();
	}

	/**
	 * Initialize.
	 */
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert userField != null : "fx:id=\"userField\" was not injected: check your FXML file 'AddEstateAgency.fxml'.";
		assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'AddEstateAgency.fxml'.";
		assert addressField != null : "fx:id=\"addressField\" was not injected: check your FXML file 'AddEstateAgency.fxml'.";
		assert codeField != null : "fx:id=\"codeField\" was not injected: check your FXML file 'AddEstateAgency.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'AddEstateAgency.fxml'.";
	}

	/**
	 * Checks if is input valid.
	 *
	 * @return true, if is input valid
	 */
	public boolean isInputValid() {
		String errorMessage = "";
		boolean isValid = false;
		if (userField.getText().isEmpty())
			errorMessage += "Debes escribir el correo de la inmobiliaria\n";
		else if (!validateEmail())
			errorMessage += "Debes ingresar un correo electrónico válido\n";
		if (nameField.getText().isEmpty())
			errorMessage += "Debes escribir el nombre de la inmobiliaria\n";
		if (addressField.getText().isEmpty())
			errorMessage += "Debes escribir la dirección de la inmobiliaria\n";
		if (codeField.getText().isEmpty())
			errorMessage += "Debes escribir el código de la inmobiliaria\n";
		if (passwordField.getText().isEmpty())
			errorMessage += "Debes escribir la contraseña de la inmobiliaria\n";
		if (errorMessage.isEmpty())
			isValid = true;
		else
			InitController.showAlert(errorMessage, "ADVERTENCIA", "", AlertType.WARNING);
		return isValid;
	}

	/**
	 * Based on: https://stackoverflow.com/questions/8204680/java-regex-email
	 */
	public final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * Validate email.
	 *
	 * @return true, if successful
	 */
	public boolean validateEmail() {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userField.getText());
		return matcher.find();
	}

	/**
	 * Gets the last controller.
	 *
	 * @return the last controller
	 */
	public EstateAgencyInfoPaneController getLastController() {
		return lastController;
	}

	/**
	 * Sets the last controller.
	 *
	 * @param lastController the new last controller
	 */
	public void setLastController(EstateAgencyInfoPaneController lastController) {
		this.lastController = lastController;
	}

	/**
	 * Sets the scene.
	 *
	 * @param scene the new scene
	 */
	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
