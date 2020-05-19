package co.edu.uniquindio.project;

import java.util.List;

import javax.ejb.Remote;

import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.project.exceptions.RepeatedProjectException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.unihogar.entities.Client;
import co.edu.uniquindio.unihogar.entities.Project;

@Remote
public interface WebUserEJBRemote {
	
	void registerClient(Client client)throws RepeatedUserException;
	List<Project> searchProjects(String projectName)throws NonexistentProject;
	List<Project> getAllProjects();
	void addProject(Project project) throws RepeatedProjectException;
}
