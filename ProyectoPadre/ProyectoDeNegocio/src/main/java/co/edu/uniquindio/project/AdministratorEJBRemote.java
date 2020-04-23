package co.edu.uniquindio.project;

import javax.ejb.Remote;

import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.unihogar.entities.User;

@Remote
public interface AdministratorEJBRemote {
	void logginUser(String code, String email, String password)throws RepeatedUserException;
	User authenticateUser(String email, String password) throws AuthenticationException;

}
