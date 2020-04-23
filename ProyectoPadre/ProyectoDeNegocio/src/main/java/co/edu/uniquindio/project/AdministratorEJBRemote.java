package co.edu.uniquindio.project;

import java.util.List;

import javax.ejb.Remote;


import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.User;

@Remote
public interface AdministratorEJBRemote {
	void logginUser(String code, String email, String password)throws RepeatedUserException;
	User authenticateUser(String email, String password) throws AuthenticationException;
	void createEstateAgency(String name, String code, String email, String password, String address)throws RepeatedUserException;
	EstateAgency getEstateAgency(String code)throws NonexistentUserException;
	void removeEstateAgency(String code) throws NonexistentUserException;
	List<EstateAgency> listAgencies();
	List<EstateAgency> listAgenciesByCity(String nameCity);

}
