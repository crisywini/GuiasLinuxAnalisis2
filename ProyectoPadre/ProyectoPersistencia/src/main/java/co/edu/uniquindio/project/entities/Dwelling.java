package co.edu.uniquindio.project.entities;

import co.edu.uniquindio.project.entities.Project;
import co.edu.uniquindio.project.entities.Type;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Dwelling
 *
 */
@Entity

public class Dwelling implements Serializable {

	@Id
	@Column(name = "code", nullable = false, length = 10)
	private int code;

	@Column(name = "urlImage", nullable = false)
	private String urlImage;

	@Column(name = "area", nullable = false, length = 10)
	private double area;

	@Column(name = "price", nullable = false)
	private double price;

	@Lob
	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "numRooms", nullable = false, length = 10)
	private int numRooms;

	@Column(name = "numBathrooms", nullable = false, length = 10)
	private int numBathrooms;

	@Column(name = "type", nullable = false)
	private Type type;

	@ManyToOne // Entidad propietaria
	@JoinColumn(name = "project")
	private Project project;

	private static final long serialVersionUID = 1L;

	public Dwelling() {
		super();
	}

	/**
	 * Constructor method from Dwelling
	 * 
	 * @param code         from {@link Dwelling} primary key, not nullable and
	 *                     length max = 10
	 * @param urlImage     from {@link Dwelling} not nullable
	 * @param area         from {@link Dwelling} not nullable and length max = 10
	 * @param price        from {@link Dwelling} not nullable
	 * @param description  from {@link Dwelling} not nullable and Lob
	 * @param numRooms     from {@link Dwelling} not nullable and length max = 10
	 * @param numBathrooms from {@link Dwelling} not nullable and length max = 10
	 * @param type         from {@link Dwelling} not nullable
	 * @param project      from relationship with {@link Project}
	 */

	public Dwelling(int code, String urlImage, double area, double price, String description, int numRooms,
			int numBathrooms, Type type, Project project) {
		super();
		this.code = code;
		this.urlImage = urlImage;
		this.area = area;
		this.price = price;
		this.description = description;
		this.numRooms = numRooms;
		this.numBathrooms = numBathrooms;
		this.type = type;
		this.project = project;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getUrlImage() {
		return this.urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public double getArea() {
		return this.area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumRooms() {
		return this.numRooms;
	}

	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}

	public int getNumBathrooms() {
		return this.numBathrooms;
	}

	public void setNumBathrooms(int numBathrooms) {
		this.numBathrooms = numBathrooms;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
