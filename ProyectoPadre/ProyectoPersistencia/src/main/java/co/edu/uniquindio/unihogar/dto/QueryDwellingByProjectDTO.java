package co.edu.uniquindio.unihogar.dto;

public class QueryDwellingByProjectDTO {
	private long numberDwellings;
	private String nameProject;
	
	public QueryDwellingByProjectDTO(long numberDwellings, String nameProject) {
		super();
		this.numberDwellings = numberDwellings;
		this.nameProject = nameProject;
	}
	public long getNumberDwellings() {
		return numberDwellings;
	}
	public void setNumberDwellings(long numberDwellings) {
		this.numberDwellings = numberDwellings;
	}
	public String getNameProject() {
		return nameProject;
	}
	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}
	@Override
	public String toString() {
		return "QueryDwellingByProjectDTO [numberDwellings=" + numberDwellings + ", nameProject=" + nameProject + "]";
	}

}
