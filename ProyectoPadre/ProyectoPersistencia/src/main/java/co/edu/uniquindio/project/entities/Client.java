package co.edu.uniquindio.project.entities;

import co.edu.uniquindio.project.entities.User;
import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Client
 *
 */
@Entity

public class Client extends User implements Serializable {

	@Column(name = "complete_name", nullable = false)
	private String completeName;
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	@Temporal(TemporalType.DATE) // This ignore the hour, only the date is important
	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;
	@OneToMany(mappedBy = "clientCode")
	@JoinColumn(name = "contacts")
	private List<Contact> contacts;
	@OneToMany(mappedBy = "clientCode")
	@JoinColumn(name = "comments")
	private List<Comment> comments;
	@OneToMany(mappedBy = "clientRating")
	@JoinColumn(name = "ratings")
	private List<Rating> ratings;
	@ManyToMany
	@JoinColumn(name = "favorite_projects")
	private List<Project> favoriteProjects;// Esta es la entidad propietaria
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method
	 */
	public Client() {
		super();
	}

	/**
	 * Constructor method from Client
	 * 
	 * @param code             from User primary key not nullable
	 * @param email            from User unique not nullable
	 * @param password         from User not nullable
	 * @param completeName     from Client not nullable
	 * @param phoneNumber      from Client not nullable
	 * @param dateOfBirth      from Client not nullable
	 * @param contacts         from relationship with Project
	 * @param comments         from relationship with project
	 * @param ratings          from relationship with project
	 * @param favoriteProjects relationship with project
	 */
	public Client(String code, String email, String password, String completeName, String phoneNumber, Date dateOfBirth,
			List<Contact> contacts, List<Comment> comments, List<Rating> ratings, List<Project> favoriteProjects) {
		super(code, email, password);
		this.completeName = completeName;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.contacts = contacts;
		this.comments = comments;
		this.ratings = ratings;
		this.favoriteProjects = favoriteProjects;
	}

	public String getCompleteName() {
		return this.completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Project> getFavoriteProjects() {
		return favoriteProjects;
	}

	public void setFavoriteProjects(List<Project> favoriteProjects) {
		this.favoriteProjects = favoriteProjects;
	}

}
