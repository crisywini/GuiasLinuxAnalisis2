package co.edu.uniquindio.project;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.exceptions.NonexistentCityException;
import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.project.exceptions.NonexistentServiceException;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.exceptions.RepeatedDwellinException;
import co.edu.uniquindio.project.exceptions.RepeatedProjectException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.project.util.MailSender;
import co.edu.uniquindio.unihogar.entities.Administrator;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.Client;
import co.edu.uniquindio.unihogar.entities.Comment;
import co.edu.uniquindio.unihogar.entities.Contact;
import co.edu.uniquindio.unihogar.entities.Dwelling;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.Rating;
import co.edu.uniquindio.unihogar.entities.RatingPK;
import co.edu.uniquindio.unihogar.entities.Service;
import co.edu.uniquindio.unihogar.entities.User;

/**
 * Session Bean implementation class WebUserEJB
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Stateless
@LocalBean
public class WebUserEJB implements WebUserEJBRemote {

	/**
	 * Default constructor.
	 */
	public WebUserEJB() {
	}

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Register client.
	 *
	 * @param client the client
	 * @throws RepeatedUserException the repeated user exception
	 */
	@Override
	public void registerClient(Client client) throws RepeatedUserException {
		String code = client.getCode();
		String email = client.getEmail();
		TypedQuery<Client> query = entityManager.createNamedQuery(Client.GET_USER_BY_ID, Client.class);
		query.setParameter("code", code);
		if (!query.getResultList().isEmpty())
			throw new RepeatedUserException("El usuario con el código: " + code + " ya se encuentra registrado");
		query = entityManager.createNamedQuery(Client.GET_USER_BY_EMAIL, Client.class);
		query.setParameter("email", email);
		if (!query.getResultList().isEmpty())
			throw new RepeatedUserException("El usuario con el correo: " + email + " ya se encuentra registrado");

		entityManager.persist(client);
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
		List<User> users = query.getResultList();

		if (users.isEmpty()) {
			throw new AuthenticationException("Los datos de autentificacion son incorrectos");
		}
		return users.get(0);
	}

	/**
	 * Search projects.
	 *
	 * @param projectName the project name
	 * @return the list
	 * @throws NonexistentProject the nonexistent project
	 */
	@Override
	public List<Project> searchProjects(String projectName) throws NonexistentProject {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_PROJECT_BY_NAME, Project.class);
		query.setParameter("projectName", "%" + projectName + "%");
		if (query.getResultList().isEmpty())
			throw new NonexistentProject("No se han encEontrado proyectos con el nombre: " + projectName);

		return query.getResultList();
	}

	/**
	 * Gets the all projects.
	 *
	 * @return the all projects
	 */
	@Override
	public List<Project> getAllProjects() {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_ALL_PROJECTS, Project.class);

		return query.getResultList();
	}

	/**
	 * Adds the project.
	 *
	 * @param project the project
	 * @throws RepeatedProjectException the repeated project exception
	 */
	@Override
	public void addProject(Project project) throws RepeatedProjectException {
		String projectName = project.getName();
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_PROJECT_BY_NAME, Project.class);
		query.setParameter("projectName", projectName);

		if (!query.getResultList().isEmpty())
			throw new RepeatedProjectException(
					"El proyecto con el nombre: " + projectName + " ya se encuentra registrado");
		entityManager.persist(project);
	}

	/**
	 * Adds the dwelling.
	 *
	 * @param dwelling the dwelling
	 * @throws RepeatedDwellinException the repeated dwellin exception
	 */
	@Override
	public void addDwelling(Dwelling dwelling) throws RepeatedDwellinException {
		int dwellingCode = dwelling.getCode();
		TypedQuery<Dwelling> query = entityManager.createNamedQuery(Dwelling.GET_DWELLING_BY_CODE, Dwelling.class);
		query.setParameter("code", dwellingCode);

		if (!query.getResultList().isEmpty())
			throw new RepeatedDwellinException(
					"La vivienda con el codigo: " + dwellingCode + " ya se encuentra registrada");
		entityManager.persist(dwelling);
	}

	/**
	 * Gets the all city.
	 *
	 * @return the all city
	 */
	@Override
	public List<City> getAllCity() {
		TypedQuery<City> query = entityManager.createNamedQuery(City.GET_ALL_CITY, City.class);

		return query.getResultList();
	}

	/**
	 * Gets the city by code.
	 *
	 * @param code the code
	 * @return the city by code
	 * @throws NonexistentCityException the nonexistent city exception
	 */
	@Override
	public City getCityByCode(int code) throws NonexistentCityException {
		City city = entityManager.find(City.class, code);
		if (city == null)
			throw new NonexistentCityException("La ciudad con el código: " + code + " no existe");
		return city;
	}

	/**
	 * Gets the ea provisional.
	 *
	 * @return the ea provisional
	 */
	public EstateAgency getEaProvisional() {
		EstateAgency ea = entityManager.find(EstateAgency.class, "456");
		return ea;
	}

	/**
	 * Gets the service.
	 *
	 * @param code the code
	 * @return the service
	 * @throws NonexistentServiceException the nonexistent service exception
	 */
	@Override
	public Service getService(int code) throws NonexistentServiceException {
		Service service = entityManager.find(Service.class, code);
		if (service == null)
			throw new NonexistentServiceException("El servicio con el código: " + code + " no existe");
		return service;
	}

	/**
	 * Gets the all services.
	 *
	 * @return the all services
	 */
	@Override
	public List<Service> getAllServices() {
		TypedQuery<Service> query = entityManager.createNamedQuery(Service.GET_ALL_SERVICES, Service.class);
		return query.getResultList();
	}

	/**
	 * Gets the project by code.
	 *
	 * @param code the code
	 * @return the project by code
	 * @throws NonexistentProject the nonexistent project
	 */
	@Override
	public Project getProjectByCode(int code) throws NonexistentProject {
		Project project = entityManager.find(Project.class, code);
		return project;
	}

	/**
	 * Gets the comments by project.
	 *
	 * @param code the code
	 * @return the comments by project
	 */
	@Override
	public List<Comment> getCommentsByProject(int code) {
		TypedQuery<Comment> query = entityManager.createNamedQuery(Project.GET_COMMENTS_BY_PROJECT, Comment.class);
		query.setParameter("code", code);

		return query.getResultList();
	}

	/**
	 * Gets the images by project.
	 *
	 * @param code the code
	 * @return the images by project
	 */
	@Override
	public List<String> getImagesByProject(int code) {
		TypedQuery<String> query = entityManager.createNamedQuery(Project.GET_IMAGES_BY_PROJECT, String.class);
		query.setParameter("code", code);
		return query.getResultList();
	}

	/**
	 * Adds the comment.
	 *
	 * @param client  the client
	 * @param project the project
	 * @param message the message
	 */
	@Override
	public void addComment(Client client, Project project, String message) {
		Comment comment = new Comment();
		comment.setClientCode(client);
		comment.setProjectCode(project);
		comment.setMessage(message);
		entityManager.persist(comment);
	}

	/**
	 * Gets the comment by code.
	 *
	 * @param code the code
	 * @return the comment by code
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	@Override
	public Comment getCommentByCode(int code) throws NonexistentUserException {
		Comment comment = entityManager.find(Comment.class, code);
		if (comment == null)
			throw new NonexistentUserException("El comentario con código: " + code + " no se encuentra!");
		return comment;
	}

	/**
	 * Creates the rating.
	 *
	 * @param client  the client
	 * @param project the project
	 * @param score   the score
	 * @throws RepeatedProjectException the repeated project exception
	 */
	@Override
	public void createRating(Client client, Project project, int score) throws RepeatedProjectException {
		RatingPK pk = new RatingPK(client.getCode(), project.getCode());
		Rating rating = new Rating();
		if (entityManager.find(Rating.class, pk) == null) {
			rating.setClientRating(client);
			rating.setProjectRating(project);
			rating.setKey(pk);
			rating.setScore(score);
			entityManager.persist(rating);
		} else
			throw new RepeatedProjectException("La calificaión ya existe");

	}

	/**
	 * Adds the project favorite.
	 *
	 * @param client   the client
	 * @param favorite the favorite
	 */
	@Override
	public void addProjectFavorite(Client client, Project favorite) {

		favorite.getFavoriteClients().add(client);
		client.getFavoriteProjects().add(favorite);

		entityManager.merge(client);
		entityManager.merge(favorite);
	}

	/**
	 * Recover password.
	 *
	 * @param email the email
	 * @throws NonexistentUserException the nonexistent user exception
	 */
	@Override
	public void recoverPassword(String email) throws NonexistentUserException {
		TypedQuery<Administrator> query2 = entityManager.createNamedQuery(Administrator.ADMINISTRATOR_BY_EMAIL,
				Administrator.class);
		query2.setParameter("emailAdmin", email);
		TypedQuery<Client> query3 = entityManager.createNamedQuery(Client.GET_USER_BY_EMAIL, Client.class);
		query3.setParameter("email", email);

		User user = null;
		if (query2.getResultList().isEmpty()) {
			if (query3.getResultList().isEmpty()) {
				throw new NonexistentUserException("Usuario no encontrado");
			} else {
				user = query3.getResultList().get(0);
			}

		} else
			user = query2.getResultList().get(0);

		String recipient = email;
		String subject = "UNIHOGAR: Recupera tu contraseña.";
		String bodyMessage = "Tu contraseña es: " + user.getPassword();
		MailSender.sendMailWithGMail(recipient, subject, bodyMessage);
	}

	/**
	 * Adds the contact.
	 *
	 * @param contact the contact
	 */
	@Override
	public void addContact(Contact contact) {
		entityManager.persist(contact);

	}
}
