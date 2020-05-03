package co.edu.uniquindio.project.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProjectObservable {
	
	private StringProperty code;
	private StringProperty name;
	private StringProperty latitude;
	private StringProperty length;
	private StringProperty description;
	private StringProperty cityName;
	
	
	public ProjectObservable(String code, String name, String latitude, String length,
			String description, String cityName) {
		super();
		this.code = new SimpleStringProperty(code);
		this.name = new SimpleStringProperty(name);
		this.latitude = new SimpleStringProperty(latitude);
		this.length = new SimpleStringProperty(length);
		this.description = new SimpleStringProperty(description);
		this.cityName = new SimpleStringProperty(cityName);
	}


	public StringProperty getCode() {
		return code;
	}


	public void setCode(StringProperty code) {
		this.code = code;
	}


	public StringProperty getName() {
		return name;
	}


	public void setName(StringProperty name) {
		this.name = name;
	}


	public StringProperty getLatitude() {
		return latitude;
	}


	public void setLatitude(StringProperty latitude) {
		this.latitude = latitude;
	}


	public StringProperty getLength() {
		return length;
	}


	public void setLength(StringProperty length) {
		this.length = length;
	}


	public StringProperty getDescription() {
		return description;
	}


	public void setDescription(StringProperty description) {
		this.description = description;
	}


	public StringProperty getCityName() {
		return cityName;
	}


	public void setCityName(StringProperty cityName) {
		this.cityName = cityName;
	}

}
