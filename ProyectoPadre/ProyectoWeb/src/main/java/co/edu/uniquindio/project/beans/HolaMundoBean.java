package co.edu.uniquindio.project.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("holaMundoBean")
@ApplicationScoped
public class HolaMundoBean {
	private String message = "Hola mundo BEAN!";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
