package co.edu.uniquindio.project.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.unihogar.entities.Project;

/**
 * The class SearchsBean
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("searchsBean")
@RequestScoped
public class SearchsBean {

	/** The query. */
	private String query;

	/** The web user EJB. */
	@EJB
	private WebUserEJB webUserEJB;

	/** The query param. */
	@Inject
	@ManagedProperty(value = "#{param.query}")
	private String queryParam;// Parametro que llega desde la web query=

	/** The project list. */
	private List<Project> projectList;

	/**
	 * Inits the.
	 */
	@PostConstruct // Despues de que esta clase se instancia invoque el metodo init no es necesario
					// init sino que se ve mejor
	public void init() {
		this.query = queryParam;
		// Si viene algo por parametro se realiza la busqueda, si no pues nada
		if (query != null) {
			try {
				projectList = webUserEJB.searchProjects(query);
			} catch (NonexistentProject e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Sets the query.
	 *
	 * @param query the new query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Search.
	 *
	 * @return the string
	 */
	/*
	 * public String searchProjects(){ try { List<Project> projects =
	 * webUserEJB.searchProjects(query); return "resultQuery"; } catch
	 * (NonexistentProject e) { FacesMessage message = new
	 * FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", e.getMessage());
	 * FacesContext.getCurrentInstance().addMessage(null, message);
	 * 
	 * return null; }
	 * 
	 * }
	 */
	public String search() {
		return "resultQuery?faces-redirect=true&amp;query=" + query;
	}

	/**
	 * Gets the query param.
	 *
	 * @return the query param
	 */
	public String getQueryParam() {
		return queryParam;
	}

	/**
	 * Sets the query param.
	 *
	 * @param queryParam the new query param
	 */
	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

	/**
	 * Gets the project list.
	 *
	 * @return the project list
	 */
	public List<Project> getProjectList() {
		return projectList;
	}

	/**
	 * Sets the project list.
	 *
	 * @param projectList the new project list
	 */
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
}
