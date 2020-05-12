package co.edu.uniquindio.unihogar.dto;

/**
 * The class QueryProjectCityDTO
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
public class QueryProjectCityDTO {

	/** The project code. */
	private int projectCode;

	/** The project name. */
	private String projectName;

	/** The project latitude. */
	private double projectLatitude;

	/** The project length. */
	private double projectLength;

	/** The estate agency name. */
	private String estateAgencyName;

	/**
	 * Instantiates a new query project city DTO.
	 *
	 * @param projectCode      the project code
	 * @param projectName      the project name
	 * @param projectLatitude  the project latitude
	 * @param projectLength    the project length
	 * @param estateAgencyName the estate agency name
	 */
	public QueryProjectCityDTO(int projectCode, String projectName, double projectLatitude, double projectLength,
			String estateAgencyName) {
		super();
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.projectLatitude = projectLatitude;
		this.projectLength = projectLength;
		this.estateAgencyName = estateAgencyName;
	}

	/**
	 * Gets the project code.
	 *
	 * @return the project code
	 */
	public int getProjectCode() {
		return projectCode;
	}

	/**
	 * Sets the project code.
	 *
	 * @param projectCode the new project code
	 */
	public void setProjectCode(int projectCode) {
		this.projectCode = projectCode;
	}

	/**
	 * Gets the project name.
	 *
	 * @return the project name
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Sets the project name.
	 *
	 * @param projectName the new project name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * Gets the project latitude.
	 *
	 * @return the project latitude
	 */
	public double getProjectLatitude() {
		return projectLatitude;
	}

	/**
	 * Sets the project latitude.
	 *
	 * @param projectLatitude the new project latitude
	 */
	public void setProjectLatitude(double projectLatitude) {
		this.projectLatitude = projectLatitude;
	}

	/**
	 * Gets the project length.
	 *
	 * @return the project length
	 */
	public double getProjectLength() {
		return projectLength;
	}

	/**
	 * Sets the project length.
	 *
	 * @param projectLength the new project length
	 */
	public void setProjectLength(double projectLength) {
		this.projectLength = projectLength;
	}

	/**
	 * Gets the estate agency name.
	 *
	 * @return the estate agency name
	 */
	public String getEstateAgencyName() {
		return estateAgencyName;
	}

	/**
	 * Sets the estate agency name.
	 *
	 * @param estateAgencyName the new estate agency name
	 */
	public void setEstateAgencyName(String estateAgencyName) {
		this.estateAgencyName = estateAgencyName;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "QueryProjectCityDTO [projectCode=" + projectCode + ", projectName=" + projectName + ", projectLatitude="
				+ projectLatitude + ", projectLength=" + projectLength + ", estateAgencyName=" + estateAgencyName + "]";
	}

}
