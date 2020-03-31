package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Map;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: EstateAgency
 *
 */
@Entity

public class EstateAgency extends User implements Serializable {

	private String address;
	@ElementCollection
	private Map<String, String> phoneNumbers;
	@ElementCollection
	private Map<String, String> advisor;// Asesor inmobiliario el cual el primero es el nombre y el segundo es el numero
										// de telefono
	private static final long serialVersionUID = 1L;

	public EstateAgency() {
		super();
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<String, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Map<String, String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Map<String, String> getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Map<String, String> advisor) {
		this.advisor = advisor;
	}

}
