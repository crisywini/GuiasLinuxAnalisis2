package co.edu.uniquindio.project;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.unihogar.entities.Administrator;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.Project;

/**
 * Session Bean implementation class SetupEJB
 */
@Singleton
@LocalBean
@Startup
public class SetupEJB {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public SetupEJB() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void config(){
		if(!isThereAnyAdministrator()) {
			Administrator newAdmin = new Administrator("root", "cgsanchezp@uqvirtual.edu.co", "root");
			entityManager.persist(newAdmin);
			loadInfo();
		}
	}
	
	public boolean isThereAnyAdministrator() {
		TypedQuery<Administrator> query = entityManager.createNamedQuery(Administrator.ADMINISTRATORS_LIST, Administrator.class);
		return !query.getResultList().isEmpty();
	}
	public void loadInfo() {
		EstateAgency ea = new EstateAgency("nueva","456","ema@hotmail.com","123","porla 14");
		Project newProject = new Project(12, "banderas hota", 1, 45, "description");
		ArrayList<String> images = new ArrayList<String>();
		newProject.setImages(images);
		newProject.getImages().add("~/Imágenes/fondoApp.jpg");
		newProject.getImages().add("~/Imágenes/Splash1.jpeg");
		newProject.getImages().add("~/Imágenes/backAbue.jpeg");
		newProject.getImages().add("~/Imágenes/ backMami.jpeg");
		City city = new City(45, "Armenia");
		newProject.setCity(city);
		newProject.setEstateAgency(ea);
		entityManager.persist(city);
		ea.getProjects().add(newProject);
		entityManager.persist(newProject);
		entityManager.persist(ea);
		//entityManager.persist(images);
	}
}
