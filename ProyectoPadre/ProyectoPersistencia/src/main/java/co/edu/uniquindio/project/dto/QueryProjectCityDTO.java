package co.edu.uniquindio.project.dto;

public class QueryProjectCityDTO {
	private int projectCode;
	private String projectName;
	private double projectLatitude;
	private double projectLength;
	private String estateAgencyName;
	
	
	public QueryProjectCityDTO(int projectCode, String projectName, double projectLatitude, double projectLength,
			String estateAgencyName) {
		super();
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.projectLatitude = projectLatitude;
		this.projectLength = projectLength;
		this.estateAgencyName = estateAgencyName;
	}
	public int getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(int projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public double getProjectLatitude() {
		return projectLatitude;
	}
	public void setProjectLatitude(double projectLatitude) {
		this.projectLatitude = projectLatitude;
	}
	public double getProjectLength() {
		return projectLength;
	}
	public void setProjectLength(double projectLength) {
		this.projectLength = projectLength;
	}
	public String getEstateAgencyName() {
		return estateAgencyName;
	}
	public void setEstateAgencyName(String estateAgencyName) {
		this.estateAgencyName = estateAgencyName;
	}
	@Override
	public String toString() {
		return "QueryProjectCityDTO [projectCode=" + projectCode + ", projectName=" + projectName + ", projectLatitude="
				+ projectLatitude + ", projectLength=" + projectLength + ", estateAgencyName=" + estateAgencyName + "]";
	}

}
