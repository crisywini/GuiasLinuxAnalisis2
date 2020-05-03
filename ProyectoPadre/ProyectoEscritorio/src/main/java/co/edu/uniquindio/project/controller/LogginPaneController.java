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

public class LogginPaneController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="userField"
	private TextField userField; // Value injected by FXMLLoader

	@FXML // fx:id="passwordField"
	private PasswordField passwordField; // Value injected by FXMLLoader
	private InitController initController;

	@FXML
	void handleLoggingButton(ActionEvent event) {
		DelegateTest delegate = DelegateTest.delegateTest;
		if(isInputValid()) {

			try {
				User admin = delegate.authenticateUser(userField.getText(), passwordField.getText());
				InitController.showAlert("Welcome user: "+userField.getText(), "WELCOME!", "", AlertType.INFORMATION);
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
	 public  Stage loadRootApp() {
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
	    

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert userField != null : "fx:id=\"userField\" was not injected: check your FXML file 'logginPane.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'logginPane.fxml'.";

	}

	public InitController getInitController() {
		return initController;
	}

	public void setInitController(InitController initController) {
		this.initController = initController;
	}
	public boolean isInputValid() {
		String errorMessage = "";
		if(userField.getText().isEmpty())
			errorMessage += "Tienes que agregar el usuario(email)\n";
		if(passwordField.getText().isEmpty())
			errorMessage+="Tienes que agregar la contraseña\n";

		boolean result = false;
		if(errorMessage.isEmpty())
			result = true;
		else
			InitController.showAlert(errorMessage, "WARNING", "", AlertType.WARNING);
		return result;
	}
	@FXML
	void handleCreateNewUserButton(ActionEvent event) {
		initController.loadCreateUserPane();
	}
    @FXML
    void handleGetPasswordEmailButton(ActionEvent event) {
    	initController.loadRecoverPasswordPane();
  
    }
}
