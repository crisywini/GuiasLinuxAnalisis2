package co.edu.uniquindio.project;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.unihogar.entities.Administrator;

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
			System.out.println(newAdmin);
			entityManager.persist(newAdmin);
		}
	}
	
	public boolean isThereAnyAdministrator() {
		TypedQuery<Administrator> query = entityManager.createNamedQuery(Administrator.ADMINISTRATORS_LIST, Administrator.class);
		return !query.getResultList().isEmpty();
	}

}
