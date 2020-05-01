/**
 * Sample Skeleton for 'RecoverPasswordPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.model.DelegateTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RecoverPasswordPaneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="userField"
    private TextField userField; // Value injected by FXMLLoader

    @FXML // fx:id="infoLabel"
    private Label infoLabel; // Value injected by FXMLLoader
    private InitController initController;

    @FXML
    void handleSendEmailButton(ActionEvent event) {
    	DelegateTest delegate = DelegateTest.delegateTest;
    	if(isInputValid()) {
    		String email = userField.getText();
    		try {
				boolean sender = delegate.isEmailWithPasswordSended(email);
				if(sender)
					InitController.showAlert("Hemos enviado el correo electrónico\nPor favor revisa para poder recuperar tu contraseña.", "INFORMACIÓN", "", AlertType.INFORMATION);
				else
					InitController.showAlert("Never happened", "NEVER", "HAPPENED", AlertType.ERROR);
				userField.setText("");
				userField.setPromptText("Escribe tu correo electrónico");
				InitController.showAlert("Será enviado a la pagina principal", "INFORMACIÓN", "", AlertType.INFORMATION);
				initController.loadLogginPane();
			} catch (NonexistentUserException e) {
				InitController.showAlert(e.getMessage(), "ERROR", "", AlertType.ERROR);
			}
    	}
    }
    @FXML
    void handleBackButton(ActionEvent event) {
    	initController.loadLogginPane();

    }

    public boolean isInputValid() {
    	boolean isValid = false;
    	String errorMessage = "";
    	if(userField.getText().isEmpty())
    		errorMessage += "Debes ingresar el email\n";
    	if(errorMessage.isEmpty())
    		isValid = true;
    	else
    		InitController.showAlert(errorMessage, "ADVERTENCIA!", "", AlertType.WARNING);
    	return isValid;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert userField != null : "fx:id=\"userField\" was not injected: check your FXML file 'RecoverPasswordPane.fxml'.";
        assert infoLabel != null : "fx:id=\"infoLabel\" was not injected: check your FXML file 'RecoverPasswordPane.fxml'.";
    }

	public InitController getInitController() {
		return initController;
	}

	public void setInitController(InitController initController) {
		this.initController = initController;
        infoLabel.setText("Recibirás un correo electrónico con la información\npara ingresar, si no has recibido el correo vuelve\na darle click al botón “Enviar correo”.");
	}
}
