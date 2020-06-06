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

/**
 * The class ProjectDetailBean
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Named("projectDetailBean")
@ViewScoped
public class ProjectDetailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The project param. */
	@Inject
	@ManagedProperty(value = "#{param.project}")
	private String projectParam;

	/** The web user EJB. */
	@EJB
	private WebUserEJB webUserEJB;

	/** The project. */
	private Project project;

	/** The name project. */
	private String nameProject;

	/** The description project. */
	private String descriptionProject;

	/** The city project. */
	private String cityProject;

	/** The comments. */
	private List<Comment> comments;

	/** The images. */
	private List<String> images;

	/** The comment new. */
	private String commentNew;

	/** The rating. */
	private Integer rating;

	/** The rating project. */
	private Rating ratingProject;

	/** The user. */
	@Inject
	@ManagedProperty(value = "#{securityBean.user}")
	private User user;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		if (projectParam != null && !projectParam.isEmpty()) {
			try {

				int code = Integer.parseInt(projectParam);

				project = webUserEJB.getProjectByCode(code);
				nameProject = project.getName();
				descriptionProject = project.getDescription();
				cityProject = project.getCity().getName();
				images = webUserEJB.getImagesByProject(code);
				comments = webUserEJB.getCommentsByProject(code);
			} catch (NumberFormatException | NonexistentProject e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Comment project.
	 */
	public void commentProject() {
		if (user instanceof Client) {

			webUserEJB.addComment((Client) user, project, commentNew);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "Comentario agregado");
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		}
	}

	/**
	 * Creates the rating.
	 */
	public void createRating() {
		if (user instanceof Client) {
			try {
				webUserEJB.createRating((Client) user, project, rating);
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

	/**
	 * Adds the favorites.
	 */
	public void addFavorites() {

	}

	/**
	 * Go to project.
	 *
	 * @param code the code
	 * @return the string
	 */
	public String goToProject(int code) {
		return "projectDetail?faces-redirect=true&amp;project=" + code;
	}

	/**
	 * Gets the project param.
	 *
	 * @return the project param
	 */
	public String getProjectParam() {
		return projectParam;
	}

	/**
	 * Sets the project param.
	 *
	 * @param projectParam the new project param
	 */
	public void setProjectParam(String projectParam) {
		this.projectParam = projectParam;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * Gets the name project.
	 *
	 * @return the name project
	 */
	public String getNameProject() {
		return nameProject;
	}

	/**
	 * Sets the name project.
	 *
	 * @param nameProject the new name project
	 */
	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}

	/**
	 * Gets the description project.
	 *
	 * @return the description project
	 */
	public String getDescriptionProject() {
		return descriptionProject;
	}

	/**
	 * Sets the description project.
	 *
	 * @param descriptionProject the new description project
	 */
	public void setDescriptionProject(String descriptionProject) {
		this.descriptionProject = descriptionProject;
	}

	/**
	 * Gets the city project.
	 *
	 * @return the city project
	 */
	public String getCityProject() {
		return cityProject;
	}

	/**
	 * Sets the city project.
	 *
	 * @param cityProject the new city project
	 */
	public void setCityProject(String cityProject) {
		this.cityProject = cityProject;
	}

	/**
	 * Gets the images.
	 *
	 * @return the images
	 */
	public List<String> getImages() {
		return images;
	}

	/**
	 * Sets the images.
	 *
	 * @param images the new images
	 */
	public void setImages(List<String> images) {
		this.images = images;
	}

	/**
	 * Gets the comment new.
	 *
	 * @return the comment new
	 */
	public String getCommentNew() {
		return commentNew;
	}

	/**
	 * Sets the comment new.
	 *
	 * @param commentNew the new comment new
	 */
	public void setCommentNew(String commentNew) {
		this.commentNew = commentNew;
	}

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public Integer getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
