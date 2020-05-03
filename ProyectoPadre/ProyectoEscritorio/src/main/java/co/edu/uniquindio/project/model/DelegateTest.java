package co.edu.uniquindio.project.model;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.edu.uniquindio.project.AdministratorEJBRemote;
import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.unihogar.dto.QueryDwellingByProjectDTO;
import co.edu.uniquindio.unihogar.dto.QueryNumberProjectByCityDTO;
import co.edu.uniquindio.unihogar.dto.QueryEstateAgencyCountProjectsDTO;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.Dwelling;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.User;

/**
 * The class DelegateTest
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class DelegateTest implements AdministratorEJBRemote {

	/** The admin EJB. */
	private AdministratorEJBRemote adminEJB;

	/** The delegate test. */
	public static DelegateTest delegateTest = instance();

	/**
	 * Instantiates a new delegate test.
	 */
	private DelegateTest() {
		try {
			adminEJB = (AdministratorEJBRemote) new InitialContext().lookup(AdministratorEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instance.
	 *
	 * @return the delegate test
	 */
	private static DelegateTest instance() {
		if (delegateTest == null)
			delegateTest = new DelegateTest();

		return delegateTest;
	}

	/**
	 * Loggin user.
	 *
	 * @param code     the code
	 * @param email    the email
	 * @param password the password
	 * @throws RepeatedUserException the repeated user exception
	 */
	public void logginUser(String code, String email, String password) throws RepeatedUserException {
		adminEJB.logginUser(code, email, password);
	}

	/**
	 * Authenticate user.
	 *
	 * @param email    the email
	 * @param password the password
	 * @return the user
	 * @throws AuthenticationException the authentication exception
	 */
	public User authenticateUser(String email, String password) throws AuthenticationException {
		return adminEJB.authenticateUser(email, password);
	}

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
	public void createEstateAgency(String name, String code, String email, String password, String address)
			throws RepeatedUserException {
		adminEJB.createEstateAgency(name, code, email, password, address);
	}

	/**
	 * Gets the estate agency.
	 *
	 * @param code the code
	 * @return the estate agency
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	public EstateAgency getEstateAgency(String code) throws NonexistentUserException {
		return adminEJB.getEstateAgency(code);
	}

	/**
	 * Removes the estate agency.
	 *
	 * @param code the code
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	public void removeEstateAgency(String code) throws NonexistentUserException {
		adminEJB.removeEstateAgency(code);
	}

	/**
	 * List agencies.
	 *
	 * @return the list
	 */
	public List<EstateAgency> listAgencies() {
		return adminEJB.listAgencies();
	}

	/**
	 * List agencies by city.
	 *
	 * @param nameCity the name city
	 * @return the list
	 */
	public List<EstateAgency> listAgenciesByCity(String nameCity) {
		return adminEJB.listAgenciesByCity(nameCity);
	}

	/**
	 * List projects.
	 *
	 * @return the list
	 */
	public List<Project> listProjects() {
		return adminEJB.listProjects();
	}

	/**
	 * List projects by city.
	 *
	 * @param nameCity the name city
	 * @return the list
	 */
	public List<Project> listProjectsByCity(String nameCity) {
		return adminEJB.listProjectsByCity(nameCity);
	}

	/**
	 * Checks if is email with password sended.
	 *
	 * @param email the email
	 * @return true, if is email with password sended
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	@Override
	public boolean isEmailWithPasswordSended(String email) throws NonexistentUserException {
		return adminEJB.isEmailWithPasswordSended(email);
	}

	/**
	 * Gets the top 5 list estate agencies by city.
	 *
	 * @param nameCity the name city
	 * @return the top 5 list estate agencies by city
	 */
	@Override
	public List<EstateAgency> getTop5ListEstateAgenciesByCity(String nameCity) {
		return adminEJB.getTop5ListEstateAgenciesByCity(nameCity);
	}

	/**
	 * Gets the top 5 projects by ratings.
	 *
	 * @return the top 5 projects by ratings
	 */
	@Override
	public List<Project> getTop5ProjectsByRatings() {
		return adminEJB.getTop5ProjectsByRatings();
	}

	/**
	 * Update estate agency.
	 *
	 * @param code         the code
	 * @param estateAgency the estate agency
	 * @return the estate agency
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	@Override
	public EstateAgency updateEstateAgency(String code, EstateAgency estateAgency) throws NonexistentUserException {
		return adminEJB.updateEstateAgency(code, estateAgency);
	}

	/**
	 * Gets the top city by projects.
	 *
	 * @return the top city by projects
	 */
	@Override
	public List<QueryNumberProjectByCityDTO> getTopCityByProjects() {
		return adminEJB.getTopCityByProjects();
	}

	/**
	 * Gets the top projects by dwellings.
	 *
	 * @return the top projects by dwellings
	 */
	@Override
	public List<QueryDwellingByProjectDTO> getTopProjectsByDwellings() {
		return adminEJB.getTopProjectsByDwellings();
	}

	/**
	 * Gets the estate agency and count projects.
	 *
	 * @param code the code
	 * @return the estate agency and count projects
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	@Override
	public QueryEstateAgencyCountProjectsDTO getEstateAgencyAndCountProjects(String code)
			throws NonexistentUserException {
		return adminEJB.getEstateAgencyAndCountProjects(code);
	}

	/**
	 * List dwellings.
	 *
	 * @return the list
	 */
	@Override
	public List<Dwelling> listDwellings() {
		return adminEJB.listDwellings();
	}

	/**
	 * Gets the project.
	 *
	 * @param code the code
	 * @return the project
	 * @throws NonexistentProject the nonexistent project
	 */
	@Override
	public Project getProject(int code) throws NonexistentProject {
		return adminEJB.getProject(code);
	}

	/**
	 * List estate agency count projects.
	 *
	 * @return the list
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	@Override
	public List<QueryEstateAgencyCountProjectsDTO> listEstateAgencyCountProjects() throws NonexistentUserException {
		return adminEJB.listEstateAgencyCountProjects();
	}

	/**
	 * Gets the cities.
	 *
	 * @return the cities
	 */
	@Override
	public List<City> getCities() {
		return adminEJB.getCities();
	}

}
