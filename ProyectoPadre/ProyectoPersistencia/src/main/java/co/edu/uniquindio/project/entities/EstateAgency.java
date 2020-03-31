package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: EstateAgency
 *
 */
@Entity

public class EstateAgency implements Serializable {

	   
	@Id
	private String code;
	private String address;
	private static final long serialVersionUID = 1L;

	public EstateAgency() {
		super();
	}   
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}   
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
   
}
