package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Project
 *
 */
@Entity

public class Project implements Serializable {

	@Id
	private int code;
	private String name;
	private double latitude;
	private double length;
	private String description;
	@ManyToMany(mappedBy = "projects") // Esta es la entidad no propietaria
	private List<Service> services;
	@ManyToOne
	private EstateAgency estateAgency;// Esta es la entidad propietaria
	@ManyToOne
	private City city;
	@OneToMany(mappedBy = "project") // Esta es la entidad no propietaria
	private List<Dwelling> dwellings;
	@OneToMany(mappedBy = "projectCode") // Esta es la entidad no propietaria
	private List<Contact> contacts;
	@OneToMany(mappedBy = "projectCode") // Esta es la entidad no propietaria
	private List<Comment> comments;
	@OneToMany(mappedBy = "projectRating") // Esta es la entidad no propietaria
	private List<Rating> ratings;
	@ManyToMany(mappedBy = "favoriteProjects") // Esta es la entidad no propietaria
	private List<Client> favoriteClients;
	private static final long serialVersionUID = 1L;

	public Project() {
		super();
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLength() {
		return this.length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Client> getFavoriteClients() {
		return favoriteClients;
	}

	public void setFavoriteClients(List<Client> favoriteClients) {
		this.favoriteClients = favoriteClients;
	}

}
