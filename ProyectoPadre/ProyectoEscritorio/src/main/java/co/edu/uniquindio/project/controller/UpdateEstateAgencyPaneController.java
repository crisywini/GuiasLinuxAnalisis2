/**
 * Sample Skeleton for 'UpdateEstateAgencyPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.model.DelegateTest;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class UpdateEstateAgencyPaneController {

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
    @FXML // fx:id="anchorPane"
    private AnchorPane anchorPane; // Value injected by FXMLLoader
    private String codeEstateAgency;
    

    @FXML
    void handleBackButton(ActionEvent event) {
    	anchorPane.getScene().getWindow().hide();
    }

    @FXML
    void handleUpdateButton(ActionEvent event) {
    	if(isInputValid()) {
    		String code = codeEstateAgency;
    		DelegateTest delegate = DelegateTest.delegateTest;
    		try {
				EstateAgency ea = delegate.getEstateAgency(code);
				ea.setEmail(userField.getText());
				ea.setName(nameField.getText());
				ea.setAddress(addressField.getText());
				delegate.updateEstateAgency(code, ea);
				InitController.showAlert("Se ha actualizado la inmobiliaria: "+ea.getName(), "INFORMACIÓN", "", AlertType.INFORMATION);
				userField.setText("");
				nameField.setText("");
				addressField.setText("");
				userField.setPromptText("Escribe el correo electrónico");
				nameField.setPromptText("Escribe el nombre de la inmobiliaria");
				addressField.setPromptText("Escribe la dirección de la inmobiliaria");
				anchorPane.getScene().getWindow().hide();
			} catch (NonexistentUserException e) {
				InitController.showAlert(e.getMessage(), "ERROR", "", AlertType.ERROR);
			}
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert userField != null : "fx:id=\"userField\" was not injected: check your FXML file 'UpdateEstateAgencyPane.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'UpdateEstateAgencyPane.fxml'.";
        assert addressField != null : "fx:id=\"addressField\" was not injected: check your FXML file 'UpdateEstateAgencyPane.fxml'.";
    }
    public boolean isInputValid() {
    	boolean isValid = false;
    	String errorMessage = "";
    	if(userField.getText().isEmpty())
    		errorMessage += "Debes ingresar el nuevo email de la inmobiliaria\n";
    	else
    		if(!validateEmail())
    			errorMessage += "Debes ingresar un email valido\n";
    	if(nameField.getText().isEmpty())
    		errorMessage += "Debes ingresar el nuevo nombre de la inmobiliaria\n";
    	if(addressField.getText().isEmpty())
    		errorMessage += "Debes ingresar la nueva dirección de la inmobiliaria\n";
    	if(errorMessage.isEmpty())
    		isValid = true;
    	else
    		InitController.showAlert(errorMessage, "ADVERTENCIA", "", AlertType.WARNING);
    	return isValid;
    }

	public void setCodeEstateAgency(String codeEstateAgency) {
		this.codeEstateAgency = codeEstateAgency;
	}
	/**
	 * Based on:
	 * https://stackoverflow.com/questions/8204680/java-regex-email
	 */
	public final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public boolean validateEmail() {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userField.getText());
		return matcher.find();
	}
}
