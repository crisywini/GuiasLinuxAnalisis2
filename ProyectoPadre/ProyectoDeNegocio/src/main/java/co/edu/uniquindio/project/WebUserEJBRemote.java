package co.edu.uniquindio.project;

import java.util.List;

import javax.ejb.Remote;

import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.exceptions.NonexistentCityException;
import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.project.exceptions.NonexistentServiceException;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.exceptions.RepeatedDwellinException;
import co.edu.uniquindio.project.exceptions.RepeatedProjectException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.Client;
import co.edu.uniquindio.unihogar.entities.Comment;
import co.edu.uniquindio.unihogar.entities.Contact;
import co.edu.uniquindio.unihogar.entities.Dwelling;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.Service;
import co.edu.uniquindio.unihogar.entities.User;

/**
 * The Interface WebUserEJBRemote
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Remote
public interface WebUserEJBRemote {

	/**
	 * Register client.
	 *
	 * @param client the client
	 * @throws RepeatedUserException the repeated user exception
	 */
	void registerClient(Client client) throws RepeatedUserException;

	/**
	 * Search projects.
	 *
	 * @param projectName the project name
	 * @return the list
	 * @throws NonexistentProject the nonexistent project
	 */
	List<Project> searchProjects(String projectName) throws NonexistentProject;

	/**
	 * Gets the all projects.
	 *
	 * @return the all projects
	 */
	List<Project> getAllProjects();

	/**
	 * Adds the project.
	 *
	 * @param project the project
	 * @throws RepeatedProjectException the repeated project exception
	 */
	void addProject(Project project) throws RepeatedProjectException;

	/**
	 * Gets the all city.
	 *
	 * @return the all city
	 */
	List<City> getAllCity();

	/**
	 * Gets the city by code.
	 *
	 * @param code the code
	 * @return the city by code
	 * @throws NonexistentCityException the nonexistent city exception
	 */
	City getCityByCode(int code) throws NonexistentCityException;

	/**
	 * Gets the service.
	 *
	 * @param code the code
	 * @return the service
	 * @throws NonexistentServiceException the nonexistent service exception
	 */
	Service getService(int code) throws NonexistentServiceException;

	/**
	 * Gets the project by code.
	 *
	 * @param code the code
	 * @return the project by code
	 * @throws NonexistentProject the nonexistent project
	 */
	Project getProjectByCode(int code) throws NonexistentProject;

	/**
	 * Gets the all services.
	 *
	 * @return the all services
	 */
	List<Service> getAllServices();

	/**
	 * Authenticate user.
	 *
	 * @param email    the email
	 * @param password the password
	 * @return the user
	 * @throws AuthenticationException the authentication exception
	 */
	User authenticateUser(String email, String password) throws AuthenticationException;

	/**
	 * Gets the comments by project.
	 *
	 * @param code the code
	 * @return the comments by project
	 */
	List<Comment> getCommentsByProject(int code);

	/**
	 * Gets the images by project.
	 *
	 * @param code the code
	 * @return the images by project
	 */
	List<String> getImagesByProject(int code);

	/**
	 * Adds the comment.
	 *
	 * @param client  the client
	 * @param project the project
	 * @param message the message
	 */
	void addComment(Client client, Project project, String message);

	/**
	 * Gets the comment by code.
	 *
	 * @param code the code
	 * @return the comment by code
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	Comment getCommentByCode(int code) throws NonexistentUserException;

	/**
	 * Creates the rating.
	 *
	 * @param client  the client
	 * @param project the project
	 * @param score   the score
	 * @throws RepeatedProjectException the repeated project exception
	 */
	void createRating(Client client, Project project, int score) throws RepeatedProjectException;

	/**
	 * Adds the project favorite.
	 *
	 * @param client   the client
	 * @param favorite the favorite
	 */
	void addProjectFavorite(Client client, Project favorite);

	/**
	 * Recover password.
	 *
	 * @param email the email
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	void recoverPassword(String email) throws NonexistentUserException;

	/**
	 * Adds the dwelling.
	 *
	 * @param dwelling the dwelling
	 * @throws RepeatedDwellinException the repeated dwellin exception
	 */
	void addDwelling(Dwelling dwelling) throws RepeatedDwellinException;

	/**
	 * Adds the contact.
	 *
	 * @param contact the contact
	 */
	void addContact(Contact contact);
}
