/**
 * Sample Skeleton for 'RootApplicationPane.fxml' Controller Class
 */

package co.edu.uniquindio.project.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.project.app.ApplicationProject;
import co.edu.uniquindio.unihogar.entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class RootApplicationPaneController {

	

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="root"
    private BorderPane root; // Value injected by FXMLLoader
    private InitController initController;
    private User user;
    private LogginPaneController logginController;
    HBox menuAdminPane;
    MenuAdminPaneController menuAdminController;
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'RootApplicationPane.fxml'.";
        loadMenuAdminPane();
    }

	public InitController getInitController() {
		return initController;
	}

	public void setInitController(InitController initController) {
		this.initController = initController;
	}
	public void loadMenuAdminPane() {

		if(menuAdminPane==null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuAdminPane.fxml"));
			try {
				menuAdminPane = loader.load();
				menuAdminController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		int last = ApplicationProject.LOGGIN_DATA.size()-1;
		User user = ApplicationProject.LOGGIN_DATA.get(last);
		menuAdminController.setRootController(this);
		menuAdminController.setUser(user);
		root.setCenter(menuAdminPane);
	}

	public void setLogginController(LogginPaneController logginController) {
		this.logginController = logginController;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
