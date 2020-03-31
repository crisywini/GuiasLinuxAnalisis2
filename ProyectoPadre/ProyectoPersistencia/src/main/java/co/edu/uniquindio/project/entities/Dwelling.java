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
	private int code;
	private String urlImage;
	private double area;
	private double price;
	private String description;
	private int numRooms;
	private int numBathrooms;
	private Type type;
	private Project project;
	private static final long serialVersionUID = 1L;

	public Dwelling() {
		super();
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
