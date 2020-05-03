/**
 * Sample Skeleton for 'initPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.io.IOException;


import java.net.URL;



import java.util.ResourceBundle;

import co.edu.uniquindio.project.app.ApplicationProject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The class InitController
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class InitController {

    @FXML// ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="parent"
    private BorderPane parent; // Value injected by FXMLLoader
    
	/** The main. */
    private ApplicationProject main;
    
    /** The loggin pane. */
    AnchorPane logginPane;
    
    /** The loggin controller. */
    LogginPaneController logginController;
    
    /** The create user pane. */
    VBox createUserPane;
    
    /** The create user controller. */
    CreateUserPaneController createUserController;
    
    /** The recover password pane. */
    VBox recoverPasswordPane;
    
    /** The recover password controller. */
    RecoverPasswordPaneController recoverPasswordController;
    
    /** The splash pane. */
    StackPane splashPane;
    
    /** The splash controller. */
    SplashController splashController;
    

    /**
     * Initialize.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	loadLogginPane();

    }
    
    /**
     * Load loggin pane.
     */
    public void loadLogginPane() {
    	if(logginPane == null) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/logginPane.fxml"));
    		try {
				logginPane = loader.load();
				logginController = loader.getController();
				logginController.setInitController(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	parent.setCenter(logginPane);
    }
    
    /**
     * Load create user pane.
     */
    public void loadCreateUserPane() {
    	if(createUserPane == null) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateUserPane.fxml"));
    		try {
				createUserPane = loader.load();
				createUserController = loader.getController();
				createUserController.setInitController(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	parent.setCenter(createUserPane);
    }
    
    /**
     * Load recover password pane.
     */
    public void loadRecoverPasswordPane() {
    	if(recoverPasswordPane==null) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecoverPasswordPane.fxml"));
    		try {
				recoverPasswordPane = loader.load();
				recoverPasswordController =loader.getController();
				recoverPasswordController.setInitController(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	parent.setCenter(recoverPasswordPane);
    }
    
    /**
     * Load splash.
     *
     * @param stageRootApp the stage root app
     */
    public void loadSplash(Stage stageRootApp) {
    	try {
    		FXMLLoader loader = new FXMLLoader(ApplicationProject.class.getResource("/Splash.fxml"));
			Parent rootSplash = loader.load();
			Scene scene = new Scene(rootSplash);
			scene.getStylesheets().add(ApplicationProject.class.getResource("application.css").toExternalForm());
			parent.getStyleClass().add("pane");
			SplashController controllerS = loader.getController();
			controllerS.setInitController(this);
			controllerS.setStage(stageRootApp);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
			parent.getScene().getWindow().hide();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    /**
     * Show alert.
     *
     * @param contentText the content text
     * @param title the title
     * @param headerText the header text
     * @param alertType the alert type
     */
    public static void showAlert(String contentText, String title, String headerText, AlertType alertType){
    	Alert alert = new Alert(alertType);
    	DialogPane dialogPane = alert.getDialogPane();
    	dialogPane.getStyleClass().add("alert");
    	dialogPane.getStylesheets().add(ApplicationProject.class.getResource("application.css").toExternalForm());
    	alert.setContentText(contentText);
    	alert.setHeaderText(headerText);
    	alert.setTitle(title);
    	alert.showAndWait();
    	
    }
    

	/**
	 * Gets the main.
	 *
	 * @return the main
	 */
	public ApplicationProject getMain() {
		return main;
	}
	
	/**
	 * Sets the main.
	 *
	 * @param main the new main
	 */
	public void setMain(ApplicationProject main) {
		this.main = main;
	}
}
