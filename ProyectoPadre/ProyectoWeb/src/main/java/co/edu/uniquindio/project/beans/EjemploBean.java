package co.edu.uniquindio.project.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("ejemploBean")
@RequestScoped
public class EjemploBean {
	private String attribute1;
	private String attribute2;
	public void change() {
		String auxiliar = attribute1;
		attribute1 = attribute2;
		attribute2 = auxiliar;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

}
