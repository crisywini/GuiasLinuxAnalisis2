package co.edu.uniquindio.project;

import java.util.List;

import javax.ejb.Remote;

import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.unihogar.dto.QueryEstateAgencyCountProjectsDTO;
import co.edu.uniquindio.unihogar.dto.QueryDwellingByProjectDTO;
import co.edu.uniquindio.unihogar.dto.QueryNumberProjectByCityDTO;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.Dwelling;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.User;

/**
 * The interface AdministratorEJBRemote
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Remote
public interface AdministratorEJBRemote {

	public static final String JNDI = "java:global/ProyectoEAR/ProyectoDeNegocio/AdministratorEJB!co.edu.uniquindio.project.AdministratorEJBRemote";

	/**
	 * Loggin user.
	 *
	 * @param code     the code
	 * @param email    the email
	 * @param password the password
	 * @throws RepeatedUserException the repeated user exception
	 */
	void logginUser(String code, String email, String password) throws RepeatedUserException;

	/**
	 * Gets the project.
	 *
	 * @param code the code
	 * @return the project
	 * @throws NonexistentProject the nonexistent project
	 */
	Project getProject(int code) throws NonexistentProject;

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
	 * List agencies by city.
	 *
	 * @param nameCity the name city
	 * @return the list
	 */
	List<EstateAgency> listAgenciesByCity(String nameCity);

	/**
	 * List projects.
	 *
	 * @return the list
	 */
	List<Project> listProjects();

	/**
	 * List projects by city.
	 *
	 * @param nameCity the name city
	 * @return the list
	 */
	List<Project> listProjectsByCity(String nameCity);

	/**
	 * Checks if is email with password sended.
	 *
	 * @param email the email
	 * @return true, if is email with password sended
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	boolean isEmailWithPasswordSended(String email) throws NonexistentUserException;

	/**
	 * Gets the top 5 list estate agencies by city.
	 *
	 * @param nameCity the name city
	 * @return the top 5 list estate agencies by city
	 */
	List<EstateAgency> getTop5ListEstateAgenciesByCity(String nameCity);

	/**
	 * Gets the top 5 projects by ratings.
	 *
	 * @return the top 5 projects by ratings
	 */
	List<Project> getTop5ProjectsByRatings();

	/**
	 * Gets the top city by projects.
	 *
	 * @return the top city by projects
	 */
	List<QueryNumberProjectByCityDTO> getTopCityByProjects();

	/**
	 * Gets the top projects by dwellings.
	 *
	 * @return the top projects by dwellings
	 */
	List<QueryDwellingByProjectDTO> getTopProjectsByDwellings();

	/**
	 * Creates the estate agency.
	 *
	 * @param name     the name
	 * @param code     the code
	 * @param email    the email
	 * @param password the password
	 * @param address  the address
	 * @throws RepeatedUserException the repeated user exception
	 */
	// CRUD
	void createEstateAgency(String name, String code, String email, String password, String address)
			throws RepeatedUserException;

	/**
	 * Removes the estate agency.
	 *
	 * @param code the code
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	void removeEstateAgency(String code) throws NonexistentUserException;

	/**
	 * Gets the estate agency.
	 *
	 * @param code the code
	 * @return the estate agency
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	EstateAgency getEstateAgency(String code) throws NonexistentUserException;

	/**
	 * List agencies.
	 *
	 * @return the list
	 */
	List<EstateAgency> listAgencies();

	/**
	 * Update estate agency.
	 *
	 * @param code         the code
	 * @param estateAgency the estate agency
	 * @return the estate agency
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	EstateAgency updateEstateAgency(String code, EstateAgency estateAgency) throws NonexistentUserException;

	/**
	 * List estate agency count projects.
	 *
	 * @return the list
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	List<QueryEstateAgencyCountProjectsDTO> listEstateAgencyCountProjects() throws NonexistentUserException;

	/**
	 * Gets the estate agency and count projects.
	 *
	 * @param code the code
	 * @return the estate agency and count projects
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	QueryEstateAgencyCountProjectsDTO getEstateAgencyAndCountProjects(String code) throws NonexistentUserException;

	/**
	 * List dwellings.
	 *
	 * @return the list
	 */
	List<Dwelling> listDwellings();

	/**
	 * Gets the cities.
	 *
	 * @return the cities
	 */
	List<City> getCities();
}
