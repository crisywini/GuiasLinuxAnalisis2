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

	private String completeName;
	private String phoneNumber;
	private Date dateOfBirth;
	@OneToMany(mappedBy = "clientCode")
	private List<Contact> contacts;
	@OneToMany(mappedBy = "clientCode")
	private List<Comment> comments;
	@OneToMany(mappedBy = "clientRating")
	private List<Rating> ratings;
	@ManyToMany
	private List<Project> favoriteProjects;// Esta es la entidad propietaria
	private static final long serialVersionUID = 1L;

	public Client() {
		super();
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
