package co.edu.uniquindio.project.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DwellingsObservable {
	private StringProperty code;
	private StringProperty area;
	private StringProperty price;
	private StringProperty description;
	private StringProperty numberRooms;
	private StringProperty numberBathrooms;
	private StringProperty type;
	
	public DwellingsObservable(String code, String  area, String price,
			String description, String  numberRooms, String numberBathrooms,
			String type) {
		super();
		this.code = new SimpleStringProperty(code);
		this.area = new SimpleStringProperty(area);
		this.price = new SimpleStringProperty(price);
		this.description = new SimpleStringProperty(description);
		this.numberRooms = new SimpleStringProperty(numberRooms);
		this.numberBathrooms = new SimpleStringProperty(numberBathrooms);
		this.type = new SimpleStringProperty(type);
	}
	public StringProperty getCode() {
		return code;
	}
	public void setCode(StringProperty code) {
		this.code = code;
	}
	public StringProperty getArea() {
		return area;
	}
	public void setArea(StringProperty area) {
		this.area = area;
	}
	public StringProperty getPrice() {
		return price;
	}
	public void setPrice(StringProperty price) {
		this.price = price;
	}
	public StringProperty getDescription() {
		return description;
	}
	public void setDescription(StringProperty description) {
		this.description = description;
	}
	public StringProperty getNumberRooms() {
		return numberRooms;
	}
	public void setNumberRooms(StringProperty numberRooms) {
		this.numberRooms = numberRooms;
	}
	public StringProperty getNumberBathrooms() {
		return numberBathrooms;
	}
	public void setNumberBathrooms(StringProperty numberBathrooms) {
		this.numberBathrooms = numberBathrooms;
	}
	public StringProperty getType() {
		return type;
	}
	public void setType(StringProperty type) {
		this.type = type;
	}
}
