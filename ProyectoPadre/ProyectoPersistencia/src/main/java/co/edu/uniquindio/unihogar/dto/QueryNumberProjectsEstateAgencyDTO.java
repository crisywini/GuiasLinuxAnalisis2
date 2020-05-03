package co.edu.uniquindio.unihogar.dto;

/**
 * The class QueryNumberProjectsEstateAgencyDTO
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class QueryNumberProjectsEstateAgencyDTO {

	/** The name estate agency. */
	private String nameEstateAgency;

	/** The address estate agency. */
	private String addressEstateAgency;

	/** The number projects. */
	private long numberProjects;

	/**
	 * Instantiates a new query number projects estate agency DTO.
	 *
	 * @param nameEstateAgency    the name estate agency
	 * @param addressEstateAgency the address estate agency
	 * @param numberProjects      the number projects
	 */
	public QueryNumberProjectsEstateAgencyDTO(String nameEstateAgency, String addressEstateAgency,
			long numberProjects) {
		this.nameEstateAgency = nameEstateAgency;
		this.addressEstateAgency = addressEstateAgency;
		this.numberProjects = numberProjects;
	}

	/**
	 * Gets the name estate agency.
	 *
	 * @return the name estate agency
	 */
	public String getNameEstateAgency() {
		return nameEstateAgency;
	}

	/**
	 * Sets the name estate agency.
	 *
	 * @param nameEstateAgency the new name estate agency
	 */
	public void setNameEstateAgency(String nameEstateAgency) {
		this.nameEstateAgency = nameEstateAgency;
	}

	/**
	 * Gets the address estate agency.
	 *
	 * @return the address estate agency
	 */
	public String getAddressEstateAgency() {
		return addressEstateAgency;
	}

	/**
	 * Sets the address estate agency.
	 *
	 * @param addressEstateAgency the new address estate agency
	 */
	public void setAddressEstateAgency(String addressEstateAgency) {
		this.addressEstateAgency = addressEstateAgency;
	}

	/**
	 * Gets the number projects.
	 *
	 * @return the number projects
	 */
	public long getNumberProjects() {
		return numberProjects;
	}

	/**
	 * Sets the number projects.
	 *
	 * @param numberProjects the new number projects
	 */
	public void setNumberProjects(long numberProjects) {
		this.numberProjects = numberProjects;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "QueryNumberProjectsEstateAgencyDTO [nameEstateAgency=" + nameEstateAgency + ", addressEstateAgency="
				+ addressEstateAgency + ", numberProjects=" + numberProjects + "]";
	}

}
