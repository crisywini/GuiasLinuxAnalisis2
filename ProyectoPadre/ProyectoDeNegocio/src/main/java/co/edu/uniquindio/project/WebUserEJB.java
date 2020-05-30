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
import co.edu.uniquindio.project.exceptions.RepeatedProjectException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.project.util.MailSender;
import co.edu.uniquindio.unihogar.entities.Administrator;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.Client;
import co.edu.uniquindio.unihogar.entities.Comment;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.Rating;
import co.edu.uniquindio.unihogar.entities.RatingPK;
import co.edu.uniquindio.unihogar.entities.Service;
import co.edu.uniquindio.unihogar.entities.User;

/**
 * Session Bean implementation class WebUserEJB
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

	@Override
	public List<Project> searchProjects(String projectName) throws NonexistentProject {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_PROJECT_BY_NAME, Project.class);
		query.setParameter("projectName", "%" + projectName + "%");
		if (query.getResultList().isEmpty())
			throw new NonexistentProject("No se han encEontrado proyectos con el nombre: " + projectName);

		return query.getResultList();
	}

	@Override
	public List<Project> getAllProjects() {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_ALL_PROJECTS, Project.class);

		return query.getResultList();
	}

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

	@Override
	public List<City> getAllCity() {
		TypedQuery<City> query = entityManager.createNamedQuery(City.GET_ALL_CITY, City.class);

		return query.getResultList();
	}

	@Override
	public City getCityByCode(int code) throws NonexistentCityException {
		City city = entityManager.find(City.class, code);
		if (city == null)
			throw new NonexistentCityException("La ciudad con el código: " + code + " no existe");
		return city;
	}

	public EstateAgency getEaProvisional() {
		EstateAgency ea = entityManager.find(EstateAgency.class, "456");
		return ea;
	}

	@Override
	public Service getService(int code) throws NonexistentServiceException {
		Service service = entityManager.find(Service.class, code);
		if (service == null)
			throw new NonexistentServiceException("El servicio con el código: " + code + " no existe");
		return service;
	}

	@Override
	public List<Service> getAllServices() {
		TypedQuery<Service> query = entityManager.createNamedQuery(Service.GET_ALL_SERVICES, Service.class);
		return query.getResultList();
	}

	@Override
	public Project getProjectByCode(int code) throws NonexistentProject {
		Project project = entityManager.find(Project.class, code);
		return project;
	}

	@Override
	public List<Comment> getCommentsByProject(int code) {
		TypedQuery<Comment> query = entityManager.createNamedQuery(Project.GET_COMMENTS_BY_PROJECT, Comment.class);
		query.setParameter("code", code);

		return query.getResultList();
	}


	@Override
	public List<String> getImagesByProject(int code) {
		TypedQuery<String> query = entityManager.createNamedQuery(Project.GET_IMAGES_BY_PROJECT, String.class);
		query.setParameter("code", code);
		return query.getResultList();
	}

	@Override
	public void addComment(Client client, Project project, String message) {
		Comment comment = new Comment();
		comment.setClientCode(client);
		comment.setProjectCode(project);
		comment.setMessage(message);
		entityManager.persist(comment);
	}

	@Override
	public Comment getCommentByCode(int code) throws NonexistentUserException {
		Comment comment = entityManager.find(Comment.class, code);
		if(comment==null)
			throw new NonexistentUserException("El comentario con código: "+code+" no se encuentra!");
		return comment;
	}

	@Override
	public void createRating(Client client, Project project, int score) throws RepeatedProjectException{
		RatingPK pk = new RatingPK(client.getCode(), project.getCode());
		Rating rating = new Rating();
		if(entityManager.find(Rating.class, pk)==null) {
			rating.setClientRating(client);
			rating.setProjectRating(project);
			rating.setKey(pk);
			rating.setScore(score);
			entityManager.persist(rating);
		}else
			throw new RepeatedProjectException("La calificaión ya existe");

	}

	@Override
	public void addProjectFavorite(Client client, Project favorite) {

		favorite.getFavoriteClients().add(client);
		client.getFavoriteProjects().add(favorite);

		entityManager.merge(client);
		entityManager.merge(favorite);
	}

	@Override
	public void recoverPassword(String email) throws NonexistentUserException {
		TypedQuery<Administrator> query2 = entityManager.createNamedQuery(Administrator.ADMINISTRATOR_BY_EMAIL,
				Administrator.class);
		query2.setParameter("emailAdmin", email);
		TypedQuery<Client> query3 = entityManager.createNamedQuery(Client.GET_USER_BY_EMAIL, Client.class);
		query3.setParameter("email", email);

		User user = null;
		if(query2.getResultList().isEmpty()) {
			if(query3.getResultList().isEmpty()) {
				throw new NonexistentUserException("Usuario no encontrado");
			}else {
				user = query3.getResultList().get(0);
			}

		}else
			user = query2.getResultList().get(0);

		String recipient = email;
		String subject = "UNIHOGAR: Recupera tu contraseña.";
		String bodyMessage = "Tu contraseña es: " + user.getPassword();
		MailSender.sendMailWithGMail(recipient, subject, bodyMessage);
	}
}

