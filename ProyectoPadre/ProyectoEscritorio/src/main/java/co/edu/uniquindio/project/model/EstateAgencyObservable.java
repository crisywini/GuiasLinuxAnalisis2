package co.edu.uniquindio.project.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EstateAgencyObservable {
	private StringProperty code;
	private StringProperty email;
	private StringProperty name;
	private StringProperty address;
	
	
	public EstateAgencyObservable(String code, String email, String name, String address) {
		super();
		this.code = new SimpleStringProperty(code);
		this.email =new SimpleStringProperty(email);
		this.name = new SimpleStringProperty(name);
		this.address = new SimpleStringProperty(address);
	}
	public StringProperty getCode() {
		return code;
	}
	public void setCode(StringProperty code) {
		this.code = code;
	}
	public StringProperty getEmail() {
		return email;
	}
	public void setEmail(StringProperty email) {
		this.email = email;
	}
	public StringProperty getName() {
		return name;
	}
	public void setName(StringProperty name) {
		this.name = name;
	}
	public StringProperty getAddress() {
		return address;
	}
	public void setAddress(StringProperty address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "[code=" + code + ", email=" + email + ", name=" + name + ", address=" + address
				+ "]";
	}
	

}
