package co.edu.uniquindio.project.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The class DwellingsObservable
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class DwellingsObservable {

	/** The code. */
	private StringProperty code;

	/** The area. */
	private StringProperty area;

	/** The price. */
	private StringProperty price;

	/** The description. */
	private StringProperty description;

	/** The number rooms. */
	private StringProperty numberRooms;

	/** The number bathrooms. */
	private StringProperty numberBathrooms;

	/** The type. */
	private StringProperty type;

	/**
	 * Instantiates a new dwellings observable.
	 *
	 * @param code            the code
	 * @param area            the area
	 * @param price           the price
	 * @param description     the description
	 * @param numberRooms     the number rooms
	 * @param numberBathrooms the number bathrooms
	 * @param type            the type
	 */
	public DwellingsObservable(String code, String area, String price, String description, String numberRooms,
			String numberBathrooms, String type) {
		super();
		this.code = new SimpleStringProperty(code);
		this.area = new SimpleStringProperty(area);
		this.price = new SimpleStringProperty(price);
		this.description = new SimpleStringProperty(description);
		this.numberRooms = new SimpleStringProperty(numberRooms);
		this.numberBathrooms = new SimpleStringProperty(numberBathrooms);
		this.type = new SimpleStringProperty(type);
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public StringProperty getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(StringProperty code) {
		this.code = code;
	}

	/**
	 * Gets the area.
	 *
	 * @return the area
	 */
	public StringProperty getArea() {
		return area;
	}

	/**
	 * Sets the area.
	 *
	 * @param area the new area
	 */
	public void setArea(StringProperty area) {
		this.area = area;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public StringProperty getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(StringProperty price) {
		this.price = price;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public StringProperty getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(StringProperty description) {
		this.description = description;
	}

	/**
	 * Gets the number rooms.
	 *
	 * @return the number rooms
	 */
	public StringProperty getNumberRooms() {
		return numberRooms;
	}

	/**
	 * Sets the number rooms.
	 *
	 * @param numberRooms the new number rooms
	 */
	public void setNumberRooms(StringProperty numberRooms) {
		this.numberRooms = numberRooms;
	}

	/**
	 * Gets the number bathrooms.
	 *
	 * @return the number bathrooms
	 */
	public StringProperty getNumberBathrooms() {
		return numberBathrooms;
	}

	/**
	 * Sets the number bathrooms.
	 *
	 * @param numberBathrooms the new number bathrooms
	 */
	public void setNumberBathrooms(StringProperty numberBathrooms) {
		this.numberBathrooms = numberBathrooms;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public StringProperty getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(StringProperty type) {
		this.type = type;
	}
}