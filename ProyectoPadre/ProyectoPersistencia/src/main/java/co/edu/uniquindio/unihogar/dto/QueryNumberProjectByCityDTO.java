package co.edu.uniquindio.unihogar.dto;

public class QueryNumberProjectByCityDTO {
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
	 

}
