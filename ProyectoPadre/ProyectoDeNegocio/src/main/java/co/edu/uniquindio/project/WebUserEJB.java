package co.edu.uniquindio.project;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.project.exceptions.NonexistentCityException;
import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.project.exceptions.RepeatedProjectException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.Client;
import co.edu.uniquindio.unihogar.entities.Project;

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
		if(!query.getResultList().isEmpty())
			throw new RepeatedUserException("El usuario con el código: "+code+" ya se encuentra registrado");
		query = entityManager.createNamedQuery(Client.GET_USER_BY_EMAIL, Client.class);
		query.setParameter("email", email);
		if(!query.getResultList().isEmpty())
			throw new RepeatedUserException("El usuario con el correo: "+email+" ya se encuentra registrado");

		entityManager.persist(client);
	}

	@Override
	public List<Project> searchProjects(String projectName) throws NonexistentProject {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_PROJECT_BY_NAME, Project.class);
		query.setParameter("projectName", "%"+projectName+"%");
		if(query.getResultList().isEmpty())
			throw new NonexistentProject("No se han encontrado proyectos con el nombre: "+projectName);


		return query.getResultList();
	}

	@Override
	public List<Project> getAllProjects() {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_ALL_PROJECTS, Project.class);

		return query.getResultList();
	}

	@Override
	public void addProject(Project project) throws RepeatedProjectException {
		String projectName  = project.getName();
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_PROJECT_BY_NAME, Project.class);
		query.setParameter("projectName", projectName);

		if(!query.getResultList().isEmpty())
			throw new RepeatedProjectException("El proyecto con el nombre: "+projectName+" ya se encuentra registrado");
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
		if(city==null)
			throw new NonexistentCityException("La ciudad con el código: "+code+" no existe");
		return city;
	}





}
