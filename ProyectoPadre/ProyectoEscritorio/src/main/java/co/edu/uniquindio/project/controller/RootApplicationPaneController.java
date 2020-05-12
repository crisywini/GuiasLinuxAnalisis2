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

/**
 * The class RootApplicationPaneController
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class RootApplicationPaneController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="root"
	private BorderPane root; // Value injected by FXMLLoader

	/** The init controller. */
	private InitController initController;

	/** The user. */
	private User user;

	/** The loggin controller. */
	private LogginPaneController logginController;

	/** The menu admin pane. */
	HBox menuAdminPane;

	/** The menu admin controller. */
	MenuAdminPaneController menuAdminController;

	/**
	 * Initialize.
	 */
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'RootApplicationPane.fxml'.";
		loadMenuAdminPane();
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
	 * Load menu admin pane.
	 */
	public void loadMenuAdminPane() {

		if (menuAdminPane == null) {
			FXMLLoader loader = new FXMLLoader(ApplicationProject.class.getResource("/MenuAdminPane.fxml"));
			try {
				menuAdminPane = loader.load();
				menuAdminController = loader.getController();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		int last = ApplicationProject.LOGGIN_DATA.size() - 1;
		User user = ApplicationProject.LOGGIN_DATA.get(last);
		menuAdminController.setRootController(this);
		menuAdminController.setUser(user);
		root.setCenter(menuAdminPane);
	}

	/**
	 * Sets the loggin controller.
	 *
	 * @param logginController the new loggin controller
	 */
	public void setLogginController(LogginPaneController logginController) {
		this.logginController = logginController;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the root.
	 *
	 * @return the root
	 */
	public BorderPane getRoot() {
		return root;
	}

	/**
	 * Sets the root.
	 *
	 * @param root the new root
	 */
	public void setRoot(BorderPane root) {
		this.root = root;
	}

}
