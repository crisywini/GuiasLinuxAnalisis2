package co.edu.uniquindio.project.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The class EstateAgencyObservable
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class EstateAgencyObservable {

	/** The code. */
	private StringProperty code;

	/** The email. */
	private StringProperty email;

	/** The name. */
	private StringProperty name;

	/** The address. */
	private StringProperty address;

	/**
	 * Instantiates a new estate agency observable.
	 *
	 * @param code    the code
	 * @param email   the email
	 * @param name    the name
	 * @param address the address
	 */
	public EstateAgencyObservable(String code, String email, String name, String address) {
		super();
		this.code = new SimpleStringProperty(code);
		this.email = new SimpleStringProperty(email);
		this.name = new SimpleStringProperty(name);
		this.address = new SimpleStringProperty(address);
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public StringProperty getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(StringProperty email) {
		this.email = email;
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
	 * Gets the address.
	 *
	 * @return the address
	 */
	public StringProperty getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(StringProperty address) {
		this.address = address;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "[code=" + code + ", email=" + email + ", name=" + name + ", address=" + address + "]";
	}

}