package co.edu.uniquindio.project;

import java.util.ArrayList;
import java.util.LinkedList;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.project.util.MailSender;
import co.edu.uniquindio.unihogar.dto.QueryEstateAgencyCountProjectsDTO;
import co.edu.uniquindio.unihogar.dto.QueryDwellingByProjectDTO;
import co.edu.uniquindio.unihogar.dto.QueryNumberProjectByCityDTO;
import co.edu.uniquindio.unihogar.entities.Administrator;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.Dwelling;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.User;

/**
 * Session Bean implementation class PruebaEJB
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Stateless
@LocalBean
public class AdministratorEJB implements AdministratorEJBRemote {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AdministratorEJB() {
	}

	/**
	 * Loggin user.
	 *
	 * @param code     the code
	 * @param email    the email
	 * @param password the password
	 * @throws RepeatedUserException the repeated user exception
	 */
	@Override
	public void logginUser(String code, String email, String password) throws RepeatedUserException {
		Administrator query1 = entityManager.find(Administrator.class, code);

		if (query1 != null)
			throw new RepeatedUserException("El usuario con el codigo: " + code + " ya existe.");
		if (isUserEmailRepeated(email))
			throw new RepeatedUserException("El ususario con el correo: " + email + " ya existe.");
		Administrator newUser = new Administrator();
		newUser.setCode(code);
		newUser.setEmail(email);
		newUser.setPassword(password);
		entityManager.persist(newUser);
	}

	/**
	 * Checks if is user email repeated.
	 *
	 * @param email the email
	 * @return true, if is user email repeated
	 */
	public boolean isUserEmailRepeated(String email) {
		TypedQuery<Administrator> query = entityManager.createNamedQuery(Administrator.ADMINISTRATOR_BY_EMAIL,
				Administrator.class);
		query.setParameter("emailAdmin", email);
		return !query.getResultList().isEmpty();
	}

	/**
	 * Authenticate user.
	 *
	 * @param email    the email
	 * @param password the password
	 * @return the user
	 * @throws AuthenticationException the authentication exception
	 */
	@Override
	public User authenticateUser(String email, String password) throws AuthenticationException {
		TypedQuery<User> query = entityManager.createNamedQuery(User.AUTHENTICATE_USER, User.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		List<User> resultList = query.getResultList();
		if (resultList.isEmpty())
			throw new AuthenticationException("El usuario (correo): " + email + " no existe.");

		return resultList.get(0);
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
	@Override
	public void createEstateAgency(String name, String code, String email, String password, String address)
			throws RepeatedUserException {
		EstateAgency query = entityManager.find(EstateAgency.class, code);
		if (query != null)
			throw new RepeatedUserException("La inmobiliaria (codigo): " + code + " ya existe.");
		if (isUserEmailRepeated(email))
			throw new RepeatedUserException("La inmobiliaria (correo): " + email + " ya existe.");
		EstateAgency newEstateAgency = new EstateAgency(name, code, email, password, address);
		entityManager.persist(newEstateAgency);
	}

	/**
	 * Gets the estate agency.
	 *
	 * @param code the code
	 * @return the estate agency
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	@Override
	public EstateAgency getEstateAgency(String code) throws NonexistentUserException {
		EstateAgency query = entityManager.find(EstateAgency.class, code);
		if (query == null)
			throw new NonexistentUserException("La inmobiliaria (codigo): " + code + " no existe.");
		return query;
	}

	/**
	 * Removes the estate agency.
	 *
	 * @param code the code
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	@Override
	public void removeEstateAgency(String code) throws NonexistentUserException {
		EstateAgency query = entityManager.find(EstateAgency.class, code);
		if (query == null)
			throw new NonexistentUserException("La inmobiliaria (cogido): " + code + " no existe.");
		entityManager.remove(query);
	}

	/**
	 * List agencies.
	 *
	 * @return the list
	 */
	@Override
	public List<EstateAgency> listAgencies() {
		TypedQuery<EstateAgency> query = entityManager.createNamedQuery(EstateAgency.GET_ALL_ESTATE_AGENCY,
				EstateAgency.class);
		return query.getResultList();
	}

	/**
	 * List agencies by city.
	 *
	 * @param nameCity the name city
	 * @return the list
	 */
	@Override
	public List<EstateAgency> listAgenciesByCity(String nameCity) {
		TypedQuery<EstateAgency> query = entityManager.createNamedQuery(EstateAgency.GET_ESTATE_AGENCY_BY_CITY,
				EstateAgency.class);
		query.setParameter("nameCity", nameCity);

		return query.getResultList();
	}

	/**
	 * List projects.
	 *
	 * @return the list
	 */
	@Override
	public List<Project> listProjects() {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_ALL_PROJECTS, Project.class);

		return query.getResultList();
	}

	/**
	 * List projects by city.
	 *
	 * @param nameCity the name city
	 * @return the list
	 */
	@Override
	public List<Project> listProjectsByCity(String nameCity) {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_ALL_PROJECTS_BY_CITY, Project.class);
		query.setParameter("cityName", nameCity);
		return query.getResultList();
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

		TypedQuery<Administrator> query = entityManager.createNamedQuery(Administrator.ADMINISTRATOR_BY_EMAIL,
				Administrator.class);
		query.setParameter("emailAdmin", email);
		List<Administrator> resultList = query.getResultList();
		if (resultList.isEmpty())
			throw new NonexistentUserException("El usuario (correo): " + email + " no existe.");
		Administrator result = resultList.get(0);
		String recipient = email;
		String subject = "UNIHOGAR: Recupera tu contraseña.";
		String bodyMessage = "Tu contraseña es: " + result.getPassword();
		MailSender.sendMailWithGMail(recipient, subject, bodyMessage);
		return true;
	}

	/**
	 * Gets the top 5 list estate agencies by city.
	 *
	 * @param nameCity the name city
	 * @return the top 5 list estate agencies by city
	 */
	@Override
	public List<EstateAgency> getTop5ListEstateAgenciesByCity(String nameCity) {
		TypedQuery<EstateAgency> query = entityManager.createNamedQuery(EstateAgency.GET_ESTATE_AGENCY_BY_CITY,
				EstateAgency.class);
		query.setParameter("nameCity", nameCity);
		List<EstateAgency> resultList = query.getResultList();
		List<EstateAgency> top5EstateAgencies = new LinkedList<EstateAgency>();
		EstateAgency eaAux = null;
		for (int i = 0; i < 5; i++) {
			eaAux = getEstateAgencyMaxSizeProjects(resultList, top5EstateAgencies);
			if (eaAux != null)
				top5EstateAgencies.add(eaAux);
		}
		return top5EstateAgencies;
	}

	/**
	 * Gets the estate agency max size projects.
	 *
	 * @param estateAgencies       the estate agencies
	 * @param estateAgenciesSorted the estate agencies sorted
	 * @return the estate agency max size projects
	 */
	public EstateAgency getEstateAgencyMaxSizeProjects(List<EstateAgency> estateAgencies,
			List<EstateAgency> estateAgenciesSorted) {
		int max = 0;
		EstateAgency eaMax = null;
		for (EstateAgency estateAgency : estateAgencies) {
			if (estateAgency.getProjects().size() > max && !estateAgenciesSorted.contains(estateAgency)) {
				max = estateAgency.getProjects().size();
				eaMax = estateAgency;
			}
		}
		return eaMax;
	}

	/**
	 * Gets the top 5 projects by ratings.
	 *
	 * @return the top 5 projects by ratings
	 */
	@Override
	public List<Project> getTop5ProjectsByRatings() {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_TOP_PROJECTS_RATING, Project.class);
		List<Project> resultList = query.getResultList();
		List<Project> top5 = new LinkedList<Project>();
		for (int i = 1; i < resultList.size() && i < 5; i++) {
			top5.add(resultList.get(i));
		}
		return top5;
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
		EstateAgency query = entityManager.find(EstateAgency.class, code);
		if (query == null)
			throw new NonexistentUserException("La inmobiliaria (codigo): " + code + " no existe.");

		EstateAgency newEstateAgency = entityManager.merge(estateAgency);
		return newEstateAgency;
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
		EstateAgency query = entityManager.find(EstateAgency.class, code);
		if (query == null)
			throw new NonexistentUserException("La inmobiliaria (codigo): " + code + " no existe.");
		TypedQuery<QueryEstateAgencyCountProjectsDTO> queryE = entityManager.createNamedQuery(
				EstateAgency.GET_ESTATE_AGENCY_WITH_COUNT_PROJECTS, QueryEstateAgencyCountProjectsDTO.class);
		queryE.setParameter("code", code);
		List<QueryEstateAgencyCountProjectsDTO> resultList = queryE.getResultList();

		return resultList.get(0);
	}

	/**
	 * Gets the top city by projects.
	 *
	 * @return the top city by projects
	 */
	@Override
	public List<QueryNumberProjectByCityDTO> getTopCityByProjects() {
		TypedQuery<QueryNumberProjectByCityDTO> query = entityManager
				.createNamedQuery(Project.GET_NUMBER_PROJECTS_BY_CITY, QueryNumberProjectByCityDTO.class);
		return query.getResultList();
	}

	/**
	 * Gets the top projects by dwellings.
	 *
	 * @return the top projects by dwellings
	 */
	@Override
	public List<QueryDwellingByProjectDTO> getTopProjectsByDwellings() {
		TypedQuery<QueryDwellingByProjectDTO> query = entityManager
				.createNamedQuery(Dwelling.GET_NUMBER_DWELLINGS_BY_PROJECT, QueryDwellingByProjectDTO.class);

		return query.getResultList();
	}

	/**
	 * List dwellings.
	 *
	 * @return the list
	 */
	@Override
	public List<Dwelling> listDwellings() {
		TypedQuery<Dwelling> query = entityManager.createNamedQuery(Dwelling.GET_ALL_DWELLING, Dwelling.class);
		return query.getResultList();
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
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_PROJECT_BY_ID, Project.class);
		query.setParameter("projectCode", code);
		List<Project> list = query.getResultList();
		if (list.isEmpty())
			throw new NonexistentProject("El proyecto con id: " + code + " no exta registrado");
		return list.get(0);
	}

	/**
	 * List estate agency count projects.
	 *
	 * @return the list
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	@Override
	public List<QueryEstateAgencyCountProjectsDTO> listEstateAgencyCountProjects() throws NonexistentUserException {
		List<EstateAgency> agencies = listAgencies();
		List<QueryEstateAgencyCountProjectsDTO> resultList = new ArrayList<QueryEstateAgencyCountProjectsDTO>();
		for (EstateAgency ea : agencies) {
			resultList.add(getEstateAgencyAndCountProjects(ea.getCode()));
		}
		return resultList;
	}

	/**
	 * Gets the cities.
	 *
	 * @return the cities
	 */
	@Override
	public List<City> getCities() {
		TypedQuery<City> query = entityManager.createNamedQuery(City.GET_ALL_CITY, City.class);

		return query.getResultList();
	}

}