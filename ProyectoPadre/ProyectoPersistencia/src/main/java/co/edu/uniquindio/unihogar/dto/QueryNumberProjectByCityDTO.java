package co.edu.uniquindio.unihogar.dto;

import java.io.Serializable;

public class QueryNumberProjectByCityDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long numberProjects;
	private String nameCity;
	public QueryNumberProjectByCityDTO(long numberProjects, String nameCity) {
		super();
		this.numberProjects = numberProjects;
		this.nameCity = nameCity;
	}
	@Override
	public String toString() {
		return "QueryNumberProjectByCityDTO [numberProjects=" + numberProjects + ", nameCity=" + nameCity + "]";
	}
	public long getNumberProjects() {
		return numberProjects;
	}
	public void setNumberProjects(long numberProjects) {
		this.numberProjects = numberProjects;
	}
	public String getNameCity() {
		return nameCity;
	}
	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}
}
