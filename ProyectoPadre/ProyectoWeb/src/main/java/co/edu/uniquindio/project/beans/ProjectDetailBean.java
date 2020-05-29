package co.edu.uniquindio.project.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.unihogar.entities.Project;

@Named("projectDetailBean")
@RequestScoped //Alcance de petición
public class ProjectDetailBean {
	
	@Inject
	@ManagedProperty(value = "#{param.project}")
	private String projectParam;
	@EJB
	private WebUserEJB webUserEJB;
	private Project project;
	@PostConstruct
	public void init() {
		if(projectParam!=null&&!projectParam.isEmpty()) {
			try {
				int code = Integer.parseInt(projectParam);
				project = webUserEJB.getProjectByCode(code);
			} catch (NumberFormatException | NonexistentProject e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public String goToProject(int code) {
		return "projectDetail?faces-redirect=true&amp;project="+code;
	}

	public String getProjectParam() {
		return projectParam;
	}

	public void setProjectParam(String projectParam) {
		this.projectParam = projectParam;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
