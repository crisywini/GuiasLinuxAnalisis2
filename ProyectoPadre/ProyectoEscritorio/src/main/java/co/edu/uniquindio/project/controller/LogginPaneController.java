/**
 * Sample Skeleton for 'logginPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import co.edu.uniquindio.project.app.ApplicationProject;
import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.model.DelegateTest;
import co.edu.uniquindio.unihogar.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The class LogginPaneController
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class LogginPaneController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="userField"
	private TextField userField; // Value injected by FXMLLoader

	@FXML // fx:id="passwordField"
	private PasswordField passwordField; // Value injected by FXMLLoader

	/** The init controller. */
	private InitController initController;

	/**
	 * Handle logging button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleLoggingButton(ActionEvent event) {
		DelegateTest delegate = DelegateTest.delegateTest;
		if (isInputValid()) {

			try {
				User admin = delegate.authenticateUser(userField.getText(), passwordField.getText());
				InitController.showAlert("Welcome user: " + userField.getText(), "WELCOME!", "", AlertType.INFORMATION);
				userField.setText("");
				passwordField.setText("");
				userField.setPromptText("Escribe tu usuario");
				passwordField.setPromptText("Escribe tu contraseña");
				ApplicationProject.LOGGIN_DATA.add(admin);
				Stage rootApp = loadRootApp();
				rootApp.setResizable(false);
				initController.loadSplash(rootApp);
			} catch (AuthenticationException e) {
				InitController.showAlert(e.getMessage(), "ERROR", "", AlertType.ERROR);
			}
		}
	}

	/**
	 * Load root app.
	 *
	 * @return the stage
	 */
	public Stage loadRootApp() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/RootApplicationPane.fxml"));
		Parent root = null;
		Scene scene = null;
		Stage stage = null;
		try {
			root = loader.load();
			scene = new Scene(root);
			scene.getStylesheets().add(ApplicationProject.class.getResource("application.css").toExternalForm());
			root.getStyleClass().add("root-pane-app");
			RootApplicationPaneController controller = loader.getController();
			controller.setLogginController(this);
			stage = new Stage();
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stage;
	}

	/**
	 * Initialize.
	 */
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert userField != null : "fx:id=\"userField\" was not injected: check your FXML file 'logginPane.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'logginPane.fxml'.";

	}

	/**
	 * Gets the inits the controller.
	 *
	 * @return the inits the controller
	 */
	public InitController getInitController() {
		return initController;
	}

	/**
	 * Sets the inits the controller.
	 *
	 * @param initController the new inits the controller
	 */
	public void setInitController(InitController initController) {
		this.initController = initController;
	}

	/**
	 * Checks if is input valid.
	 *
	 * @return true, if is input valid
	 */
	public boolean isInputValid() {
		String errorMessage = "";
		if (userField.getText().isEmpty())
			errorMessage += "Tienes que agregar el usuario(email)\n";
		if (passwordField.getText().isEmpty())
			errorMessage += "Tienes que agregar la contraseña\n";

		boolean result = false;
		if (errorMessage.isEmpty())
			result = true;
		else
			InitController.showAlert(errorMessage, "WARNING", "", AlertType.WARNING);
		return result;
	}

	/**
	 * Handle create new user button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleCreateNewUserButton(ActionEvent event) {
		initController.loadCreateUserPane();
	}

	/**
	 * Handle get password email button.
	 *
	 * @param event the event
	 */
	@FXML
	void handleGetPasswordEmailButton(ActionEvent event) {
		initController.loadRecoverPasswordPane();

	}
}
