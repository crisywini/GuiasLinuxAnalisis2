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

@FacesConfig(version=Version.JSF_2_3)
@Named("searchsBean")
@RequestScoped
public class SearchsBean {
	private String query;
	@EJB
	private WebUserEJB webUserEJB;
	@Inject
	@ManagedProperty(value = "#{param.query}")
	private String queryParam;//Parametro que llega desde la web query=
	private List<Project> projectList;
	@PostConstruct //Despues de que esta clase se instancia invoque el metodo init no es necesario init sino que se ve mejor
	public void init() {
		this.query=queryParam;
		//Si viene algo por parametro se realiza la busqueda, si no pues nada
		if(query!=null) {
			try {
				projectList = webUserEJB.searchProjects(query);
			} catch (NonexistentProject e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	/*
	public String searchProjects(){
		try {
			List<Project> projects = webUserEJB.searchProjects(query);
			return "resultQuery";
		} catch (NonexistentProject e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		
			return null;
		}
	
	}*/
	public String search() {
		return "resultQuery?faces-redirect=true&amp;query="+query;
	}

	public String getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

}
