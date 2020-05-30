package co.edu.uniquindio.project.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.project.exceptions.RepeatedProjectException;
import co.edu.uniquindio.unihogar.entities.Client;
import co.edu.uniquindio.unihogar.entities.Comment;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.Rating;
import co.edu.uniquindio.unihogar.entities.User;

@Named("projectDetailBean")
@ViewScoped
public class ProjectDetailBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	@ManagedProperty(value = "#{param.project}")
	private String projectParam;
	@EJB
	private WebUserEJB webUserEJB;
	private Project project;
	private String nameProject;
	private String descriptionProject;
	private String cityProject;
	private List<Comment> comments;
	private List<String> images; 
	private String commentNew;
	private Integer rating;
	private Rating ratingProject;
	@Inject
	@ManagedProperty(value = "#{securityBean.user}")
	private User user;
	@PostConstruct
	public void init() {
		if(projectParam!=null&&!projectParam.isEmpty()) {
			try {

				int code = Integer.parseInt(projectParam);

				project = webUserEJB.getProjectByCode(code);
				nameProject = project.getName();
				descriptionProject = project.getDescription();
				cityProject = project.getCity().getName();
				images = webUserEJB.getImagesByProject(code);
				comments  = webUserEJB.getCommentsByProject(code);
			} catch (NumberFormatException | NonexistentProject e) {
				e.printStackTrace();
			}
		}

	}
	public void commentProject() {
		if(user instanceof Client) {

			webUserEJB.addComment((Client)user, project, commentNew);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "Comentario agregado");
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		}
	}
	public void createRating() {
		if(user instanceof Client) {
			try {
				webUserEJB.createRating((Client)user, project, rating);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "Calificado");
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);
				System.out.println("CALIFICADO");
			} catch (RepeatedProjectException e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);
				System.out.println("No calificado");
			}
		}
		
	}
	public void addFavorites() {
		
		
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getNameProject() {
		return nameProject;
	}

	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}

	public String getDescriptionProject() {
		return descriptionProject;
	}

	public void setDescriptionProject(String descriptionProject) {
		this.descriptionProject = descriptionProject;
	}

	public String getCityProject() {
		return cityProject;
	}

	public void setCityProject(String cityProject) {
		this.cityProject = cityProject;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getCommentNew() {
		return commentNew;
	}

	public void setCommentNew(String commentNew) {
		this.commentNew = commentNew;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	

}
