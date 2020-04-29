package co.edu.uniquindio.project;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.project.util.MailSender;
import co.edu.uniquindio.unihogar.entities.Administrator;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.User;

/**
 * Session Bean implementation class PruebaEJB
 */
@Stateless
@LocalBean
public class AdministratorEJB implements AdministratorEJBRemote {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AdministratorEJB() {
	}

	@Override
	public void logginUser(String code, String email, String password) throws RepeatedUserException {
		User newUser = new User(code, email, password);
		User query1 = entityManager.find(User.class, code);

		if (query1 != null)
			throw new RepeatedUserException("The user with code: " + code + " already exist");
		if (isUserEmailRepeated(email))
			throw new RepeatedUserException("The user with email: " + email + " already exist");

		entityManager.persist(newUser);
	}

	public boolean isUserEmailRepeated(String email) {
		TypedQuery<User> query = entityManager.createNamedQuery(User.GET_USER_BY_EMAIL, User.class);
		query.setParameter(":email", email);
		return query.getResultList().size() > 0;
	}

	@Override
	public User authenticateUser(String email, String password) throws AuthenticationException {
		TypedQuery<User> query = entityManager.createNamedQuery(User.AUTHENTICATE_USER, User.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		List<User> resultList = query.getResultList();
		if (resultList.isEmpty())
			throw new AuthenticationException("The user(email): " + email + " does not exist");

		return resultList.get(0);
	}

	@Override
	public void createEstateAgency(String name, String code, String email, String password, String address)
			throws RepeatedUserException {
		EstateAgency query = entityManager.find(EstateAgency.class, code);
		if (query != null)
			throw new RepeatedUserException("The Estate Agency(code): " + code + " already exist");
		if (isUserEmailRepeated(email))
			throw new RepeatedUserException("The Estate Agency(email): " + email + " already exist");
		EstateAgency newEstateAgency = new EstateAgency(name, code, email, password, address);
		entityManager.persist(newEstateAgency);
	}

	@Override
	public EstateAgency getEstateAgency(String code) throws NonexistentUserException {
		EstateAgency query = entityManager.find(EstateAgency.class, code);
		if (query == null)
			throw new NonexistentUserException("The Estate Agency(code): " + code + " does not exist");
		return query;
	}

	@Override
	public void removeEstateAgency(String code) throws NonexistentUserException {
		EstateAgency query = entityManager.find(EstateAgency.class, code);
		if (query == null)
			throw new NonexistentUserException("The Estate Agency(code): " + code + " does not exist");
		entityManager.remove(query);
	}

	@Override
	public List<EstateAgency> listAgencies() {
		TypedQuery<EstateAgency> query = entityManager.createNamedQuery(EstateAgency.GET_ALL_ESTATE_AGENCY,
				EstateAgency.class);
		return query.getResultList();
	}

	@Override
	public List<EstateAgency> listAgenciesByCity(String nameCity) {
		TypedQuery<EstateAgency> query = entityManager.createNamedQuery(EstateAgency.GET_ESTATE_AGENCY_BY_CITY,
				EstateAgency.class);
		query.setParameter("nameCity", nameCity);

		return query.getResultList();
	}

	@Override
	public List<Project> listProjects() {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_ALL_PROJECTS, Project.class);

		return query.getResultList();
	}

	@Override
	public List<Project> listProjectsByCity(String nameCity) {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_ALL_PROJECTS_BY_CITY, Project.class);
		query.setParameter("cityName", nameCity);
		return query.getResultList();
	}

	@Override
	public boolean isEmailWithPasswordSended(String email) throws NonexistentUserException {

		TypedQuery<Administrator> query = entityManager.createNamedQuery(Administrator.ADMINISTRATOR_BY_EMAIL,
				Administrator.class);
		query.setParameter("emailAdmin", email);
		List<Administrator> resultList = query.getResultList();
		if (resultList.isEmpty())
			throw new NonexistentUserException("The user(email): " + email + " does not exist");
		Administrator result = resultList.get(0);
		String recipient = email;
		String subject = "Password recovery";
		String bodyMessage = "Your password is: " + result.getPassword();
		MailSender.sendMailWithGMail(recipient, subject, bodyMessage);
		return true;
	}

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

	@Override
	public EstateAgency updateEstateAgency(String code, EstateAgency estateAgency) throws NonexistentUserException {
		EstateAgency query = entityManager.find(EstateAgency.class, code);
		if (query == null)
			throw new NonexistentUserException("The Estate Agency(code): " + code + " does not exist");

		EstateAgency newEstateAgency = entityManager.merge(estateAgency);
		return newEstateAgency;
	}

}