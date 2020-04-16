package co.edu.uniquindio.unihogar.dto;

public class QueryNumberProjectsEstateAgencyDTO {
	private String nameEstateAgency;
	private String addressEstateAgency;
	private long numberProjects;
	public QueryNumberProjectsEstateAgencyDTO(String nameEstateAgency, String addressEstateAgency,
			long numberProjects) {
		this.nameEstateAgency = nameEstateAgency;
		this.addressEstateAgency = addressEstateAgency;
		this.numberProjects = numberProjects;
	}
	public String getNameEstateAgency() {
		return nameEstateAgency;
	}
	public void setNameEstateAgency(String nameEstateAgency) {
		this.nameEstateAgency = nameEstateAgency;
	}
	public String getAddressEstateAgency() {
		return addressEstateAgency;
	}
	public void setAddressEstateAgency(String addressEstateAgency) {
		this.addressEstateAgency = addressEstateAgency;
	}
	public long getNumberProjects() {
		return numberProjects;
	}
	public void setNumberProjects(long numberProjects) {
		this.numberProjects = numberProjects;
	}
	@Override
	public String toString() {
		return "QueryNumberProjectsEstateAgencyDTO [nameEstateAgency=" + nameEstateAgency + ", addressEstateAgency="
				+ addressEstateAgency + ", numberProjects=" + numberProjects + "]";
	}
	
}
