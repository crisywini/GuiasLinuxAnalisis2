package co.edu.uniquindio.project;

import java.util.List;

import javax.ejb.Remote;

import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.exceptions.NonexistentCityException;
import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.project.exceptions.NonexistentServiceException;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.exceptions.RepeatedDwellinException;
import co.edu.uniquindio.project.exceptions.RepeatedProjectException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.Client;
import co.edu.uniquindio.unihogar.entities.Comment;
import co.edu.uniquindio.unihogar.entities.Dwelling;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.Service;
import co.edu.uniquindio.unihogar.entities.User;

@Remote
public interface WebUserEJBRemote {
	
	void registerClient(Client client)throws RepeatedUserException;
	List<Project> searchProjects(String projectName)throws NonexistentProject;
	List<Project> getAllProjects();
	void addProject(Project project) throws RepeatedProjectException;
	List<City> getAllCity();
	City getCityByCode(int code)throws NonexistentCityException;
	Service getService(int code) throws NonexistentServiceException;
	Project getProjectByCode(int code) throws NonexistentProject;
	List<Service> getAllServices();
	
	User authenticateUser(String email, String password) throws AuthenticationException;
	List<Comment> getCommentsByProject(int code);
	List<String> getImagesByProject(int code);
	void addComment(Client client, Project project, String message);
	Comment getCommentByCode(int code) throws NonexistentUserException;
	void createRating(Client client, Project project, int score) throws RepeatedProjectException;
	void addProjectFavorite(Client client, Project favorite);
	void recoverPassword(String email)throws NonexistentUserException;

	void addDwelling(Dwelling dwelling) throws RepeatedDwellinException;
}
