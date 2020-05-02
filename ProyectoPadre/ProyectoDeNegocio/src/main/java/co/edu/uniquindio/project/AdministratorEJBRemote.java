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
import co.edu.uniquindio.unihogar.entities.Dwelling;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.User;

@Remote
public interface AdministratorEJBRemote {
	public static final String JNDI = "java:global/ProyectoEAR/ProyectoDeNegocio/AdministratorEJB!co.edu.uniquindio.project.AdministratorEJBRemote";
	void logginUser(String code, String email, String password)throws RepeatedUserException;
	Project getProject(int code)throws NonexistentProject;
	User authenticateUser(String email, String password) throws AuthenticationException;
	List<EstateAgency> listAgenciesByCity(String nameCity);
	List<Project> listProjects();
	List<Project> listProjectsByCity(String nameCity);
	boolean isEmailWithPasswordSended(String email)throws NonexistentUserException;
	List<EstateAgency> getTop5ListEstateAgenciesByCity(String nameCity);
	List<Project> getTop5ProjectsByRatings();
	List<QueryNumberProjectByCityDTO> getTopCityByProjects();
	List<QueryDwellingByProjectDTO> getTopProjectsByDwellings();
	// CRUD
	void createEstateAgency(String name, String code, String email, String password, String address)throws RepeatedUserException;
	void removeEstateAgency(String code) throws NonexistentUserException;
	EstateAgency getEstateAgency(String code)throws NonexistentUserException;
	List<EstateAgency> listAgencies();
	EstateAgency updateEstateAgency(String code, EstateAgency estateAgency) throws NonexistentUserException;
	List<QueryEstateAgencyCountProjectsDTO> listEstateAgencyCountProjects() throws NonexistentUserException ;
	
	QueryEstateAgencyCountProjectsDTO getEstateAgencyAndCountProjects(String code) throws NonexistentUserException;
	List<Dwelling> listDwellings();
}
