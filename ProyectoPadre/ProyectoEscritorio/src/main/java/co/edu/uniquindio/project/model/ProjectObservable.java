package co.edu.uniquindio.project.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The class ProjectObservable
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class ProjectObservable {

	/** The code. */
	private StringProperty code;

	/** The name. */
	private StringProperty name;

	/** The latitude. */
	private StringProperty latitude;

	/** The length. */
	private StringProperty length;

	/** The description. */
	private StringProperty description;

	/** The city name. */
	private StringProperty cityName;

	/**
	 * Instantiates a new project observable.
	 *
	 * @param code        the code
	 * @param name        the name
	 * @param latitude    the latitude
	 * @param length      the length
	 * @param description the description
	 * @param cityName    the city name
	 */
	public ProjectObservable(String code, String name, String latitude, String length, String description,
			String cityName) {
		super();
		this.code = new SimpleStringProperty(code);
		this.name = new SimpleStringProperty(name);
		this.latitude = new SimpleStringProperty(latitude);
		this.length = new SimpleStringProperty(length);
		this.description = new SimpleStringProperty(description);
		this.cityName = new SimpleStringProperty(cityName);
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public StringProperty getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(StringProperty name) {
		this.name = name;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public StringProperty getLatitude() {
		return latitude;
	}

	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(StringProperty latitude) {
		this.latitude = latitude;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public StringProperty getLength() {
		return length;
	}

	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(StringProperty length) {
		this.length = length;
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
	 * Gets the city name.
	 *
	 * @return the city name
	 */
	public StringProperty getCityName() {
		return cityName;
	}

	/**
	 * Sets the city name.
	 *
	 * @param cityName the new city name
	 */
	public void setCityName(StringProperty cityName) {
		this.cityName = cityName;
	}
}
