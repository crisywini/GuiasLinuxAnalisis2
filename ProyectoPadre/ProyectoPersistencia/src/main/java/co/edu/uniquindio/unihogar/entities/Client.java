package co.edu.uniquindio.unihogar.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import co.edu.uniquindio.unihogar.entities.User;

/**
 * Entity implementation class for Entity: Client.
 * 
 * @author Critian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Client.GET_ALL_CLIENT, query = "SELECT c FROM Client c"),
		@NamedQuery(name = Client.GET_PROJECTS_RATING_UNIQUE, query = "SELECT r.projectRating, r.score FROM Client c INNER JOIN c.ratings r WHERE c.code = :clientCode"),
		@NamedQuery(name = Client.GET_FAVOURITES_PROJECTS, query = "SELECT fp FROM Client c LEFT JOIN c.favoriteProjects fp WHERE c.code = :clientCode"),
		@NamedQuery(name = Client.GET_CLIENTS_GMAIL, query = "SELECT c FROM Client c WHERE c.email LIKE '%gmail%'")
})
public class Client extends User implements Serializable {

	/** The complete name. */
	@Column(name = "complete_name", nullable = false)
	private String completeName;

	/** The phone number. */
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	/** The date of birth. */
	@Temporal(TemporalType.DATE) // This ignore the hour, only the date is important
	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

	/** The contacts. */
	@OneToMany(mappedBy = "clientCode")
	@JoinColumn(name = "contacts")
	private List<Contact> contacts;

	/** The comments. */
	@OneToMany(mappedBy = "clientCode")
	@JoinColumn(name = "comments")
	private List<Comment> comments;

	/** The ratings. */
	@OneToMany(mappedBy = "clientRating")
	@JoinColumn(name = "ratings")
	private List<Rating> ratings;

	/** The favorite projects. */
	@ManyToMany
	@JoinColumn(name = "favorite_projects")
	private List<Project> favoriteProjects;// Esta es la entidad propietaria
	
	//Queries
	public static final String GET_ALL_CLIENT = "GET_ALL_CLIENT";
	public static final String GET_PROJECTS_RATING_UNIQUE = "GET_PROJECTS_RATING_UNIQUE";
	public static final String GET_FAVOURITES_PROJECTS = "GET_FAVOURITES_PROJECTS";
	public static final String GET_CLIENTS_GMAIL = "GET_CLIENTS_GMAIL";

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method.
	 */
	public Client() {
		super();
	}

	/**
	 * Constructor method from Client.
	 *
	 * @param code         from {@link User} primary key not nullable
	 * @param email        from {@link User} unique not nullable
	 * @param password     from {@link User} not nullable
	 * @param completeName from {@link Client} not nullable
	 * @param phoneNumber  from {@link Client} not nullable
	 * @param dateOfBirth  from {@link Client} not nullable
	 */
	public Client(String code, String email, String password, String completeName, String phoneNumber,
			Date dateOfBirth) {
		super(code, email, password);
		this.completeName = completeName;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the complete name.
	 *
	 * @return the complete name
	 */
	public String getCompleteName() {
		return this.completeName;
	}

	/**
	 * Sets the complete name.
	 *
	 * @param completeName the new complete name
	 */
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the new date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the contacts.
	 *
	 * @return the contacts
	 */
	public List<Contact> getContacts() {
		return contacts;
	}

	/**
	 * Sets the contacts.
	 *
	 * @param contacts the new contacts
	 */
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
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
	 * Gets the ratings.
	 *
	 * @return the ratings
	 */
	public List<Rating> getRatings() {
		return ratings;
	}

	/**
	 * Sets the ratings.
	 *
	 * @param ratings the new ratings
	 */
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	/**
	 * Gets the favorite projects.
	 *
	 * @return the favorite projects
	 */
	public List<Project> getFavoriteProjects() {
		return favoriteProjects;
	}

	/**
	 * Sets the favorite projects.
	 *
	 * @param favoriteProjects the new favorite projects
	 */
	public void setFavoriteProjects(List<Project> favoriteProjects) {
		this.favoriteProjects = favoriteProjects;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Client [completeName=" + completeName + ", phoneNumber=" + phoneNumber + "]";
	}

}
