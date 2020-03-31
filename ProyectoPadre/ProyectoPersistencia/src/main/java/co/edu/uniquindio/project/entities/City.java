package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: City
 *
 */
@Entity

public class City implements Serializable {

	   
	@Id
	private int code;
	private String name;
	private static final long serialVersionUID = 1L;

	public City() {
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
   
}
