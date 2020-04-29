package co.edu.uniquindio.project.model;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.edu.uniquindio.project.AdministratorEJBRemote;
import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.unihogar.dto.QueryDwellingByProjectDTO;
import co.edu.uniquindio.unihogar.dto.QueryNumberProjectByCityDTO;
import co.edu.uniquindio.unihogar.dto.QueryEstateAgencyCountProjectsDTO;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.User;

public class DelegateTest implements AdministratorEJBRemote {
	private AdministratorEJBRemote adminEJB;
	public static DelegateTest delegateTest = instance();

	private DelegateTest() {
		try {
			adminEJB = (AdministratorEJBRemote) new InitialContext().lookup(AdministratorEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static DelegateTest instance() {
		if (delegateTest == null)
			delegateTest = new DelegateTest();

		return delegateTest;
	}

	public void logginUser(String code, String email, String password) throws RepeatedUserException {
		adminEJB.logginUser(code, email, password);
	}

	public User authenticateUser(String email, String password) throws AuthenticationException {
		return adminEJB.authenticateUser(email, password);
	}

	public void createEstateAgency(String name, String code, String email, String password, String address)
			throws RepeatedUserException {
		adminEJB.createEstateAgency(name, code, email, password, address);
	}

	public EstateAgency getEstateAgency(String code) throws NonexistentUserException {
		return adminEJB.getEstateAgency(code);
	}

	public void removeEstateAgency(String code) throws NonexistentUserException {
		adminEJB.removeEstateAgency(code);
	}

	public List<EstateAgency> listAgencies() {
		return adminEJB.listAgencies();
	}

	public List<EstateAgency> listAgenciesByCity(String nameCity) {
		return adminEJB.listAgenciesByCity(nameCity);
	}

	public List<Project> listProjects() {
		return adminEJB.listProjects();
	}

	public List<Project> listProjectsByCity(String nameCity) {
		return adminEJB.listProjectsByCity(nameCity);
	}

	@Override
	public boolean isEmailWithPasswordSended(String email) throws NonexistentUserException {
		return adminEJB.isEmailWithPasswordSended(email);
	}

	@Override
	public List<EstateAgency> getTop5ListEstateAgenciesByCity(String nameCity) {
		return adminEJB.getTop5ListEstateAgenciesByCity(nameCity);
	}

	@Override
	public List<Project> getTop5ProjectsByRatings() {
		return adminEJB.getTop5ProjectsByRatings();
	}

	@Override
	public EstateAgency updateEstateAgency(String code, EstateAgency estateAgency) throws NonexistentUserException {
		return adminEJB.updateEstateAgency(code, estateAgency);
	}

	@Override
	public List<QueryNumberProjectByCityDTO> getTopCityByProjects() {
		return adminEJB.getTopCityByProjects();
	}

	@Override
	public List<QueryDwellingByProjectDTO> getTopProjectsByDwellings() {
		return adminEJB.getTopProjectsByDwellings();
	}

	@Override
	public QueryEstateAgencyCountProjectsDTO getEstateAgencyAndCountProjects(String code)
			throws NonexistentUserException {
		return adminEJB.getEstateAgencyAndCountProjects(code);
	}

}
