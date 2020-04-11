package co.edu.uniquindio.project.entities;

import co.edu.uniquindio.project.entities.Project;
import co.edu.uniquindio.project.entities.Type;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Dwelling.
 *
 * @author Critian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */

@Entity
@NamedQueries({
	@NamedQuery(name = Dwelling.DWELLINGS_AREA_LIST, query = "SELECT d FROM Dwelling d WHERE d.area > :area")
})
public class Dwelling implements Serializable {

	/** The code. */
	@Id
	@Column(name = "code", nullable = false, length = 10)
	private int code;

	/** The url image. */
	@Column(name = "urlImage", nullable = false)
	private String urlImage;

	/** The area. */
	@Column(name = "area", nullable = false, length = 10)
	private double area;

	/** The price. */
	@Column(name = "price", nullable = false)
	private double price;

	/** The description. */
	@Lob
	@Column(name = "description", nullable = false)
	private String description;

	/** The num rooms. */
	@Column(name = "numRooms", nullable = false, length = 10)
	private int numRooms;

	/** The num bathrooms. */
	@Column(name = "numBathrooms", nullable = false, length = 10)
	private int numBathrooms;

	/** The type. */
	@Column(name = "type", nullable = false)
	private Type type;

	/** The project. */
	@ManyToOne // Entidad propietaria
	@JoinColumn(name = "project")
	private Project project;
	
	//Queries
	public static final String DWELLINGS_AREA_LIST = "DWELLINGS_AREA_LIST";
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method.
	 */
	public Dwelling() {
		super();
	}

	/**
	 * Constructor method from Dwelling.
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
	 */

	public Dwelling(int code, String urlImage, double area, double price, String description, int numRooms,
			int numBathrooms, Type type) {
		this.code = code;
		this.urlImage = urlImage;
		this.area = area;
		this.price = price;
		this.description = description;
		this.numRooms = numRooms;
		this.numBathrooms = numBathrooms;
		this.type = type;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets the url image.
	 *
	 * @return the url image
	 */
	public String getUrlImage() {
		return this.urlImage;
	}

	/**
	 * Sets the url image.
	 *
	 * @param urlImage the new url image
	 */
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	/**
	 * Gets the area.
	 *
	 * @return the area
	 */
	public double getArea() {
		return this.area;
	}

	/**
	 * Sets the area.
	 *
	 * @param area the new area
	 */
	public void setArea(double area) {
		this.area = area;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the num rooms.
	 *
	 * @return the num rooms
	 */
	public int getNumRooms() {
		return this.numRooms;
	}

	/**
	 * Sets the num rooms.
	 *
	 * @param numRooms the new num rooms
	 */
	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}

	/**
	 * Gets the num bathrooms.
	 *
	 * @return the num bathrooms
	 */
	public int getNumBathrooms() {
		return this.numBathrooms;
	}

	/**
	 * Sets the num bathrooms.
	 *
	 * @param numBathrooms the new num bathrooms
	 */
	public void setNumBathrooms(int numBathrooms) {
		this.numBathrooms = numBathrooms;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Type getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return this.project;
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dwelling other = (Dwelling) obj;
		if (code != other.code)
			return false;
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Dwelling [code=" + code + ", urlImage=" + urlImage + ", area=" + area + ", price=" + price
				+ ", description=" + description + ", numRooms=" + numRooms + ", numBathrooms=" + numBathrooms
				+ ", type=" + type + ", project=" + project + "]";
	}

}
