package co.edu.uniquindio.project.beans;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.unihogar.entities.Client;

/**
 * The class UserBean
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Named("userBean")
@ApplicationScoped
public class UserBean {

	/** The web user EJB. */
	@EJB
	private WebUserEJB webUserEJB;

	/** The client code. */
	@NotNull(message = "Debes ingresar el código")
	@NotEmpty(message = "El código no debe ser vacío")
	@Pattern(regexp = "\\d{10}", message = "El código debe tener solo 10 números")
	private String clientCode;

	/** The client name. */
	@NotNull(message = "Debes ingresar el código")
	@NotEmpty(message = "El código no debe ser vacío")
	private String clientName;

	/** The client email. */
	@NotNull(message = "Debes ingresar el correo electrónico")
	@NotEmpty(message = "El correo electrónico no debe ser vacío")
	@Email(message = "Debes escribir un correo electrónico válido")
	private String clientEmail;

	/** The client phone number. */
	@NotEmpty(message = "Debes ingresar el número de teléfono")
	@Pattern(regexp = "\\d{3}\\d{3}\\d{4}", message = "El número debe ser válido")
	private String clientPhoneNumber;

	/** The client date of birth. */
	@Past(message = "Debes ingresar una fecha válida (en el pasado)")
	private Date clientDateOfBirth;

	/** The client password. */
	@NotNull(message = "Debes ingresar la contraseña")
	@NotEmpty(message = "La contraseña no debe estar vacía")
	private String clientPassword;

	/**
	 * Register client.
	 */
	public void registerClient() {
		try {
			Client client = new Client();
			client.setCode(clientCode);
			client.setDateOfBirth(clientDateOfBirth);
			client.setEmail(clientEmail);
			client.setCompleteName(clientName);
			client.setPhoneNumber(clientPhoneNumber);
			client.setPassword(clientPassword);
			webUserEJB.registerClient(client);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "Registro exitoso");
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);

		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		}
	}

	/**
	 * Gets the client code.
	 *
	 * @return the client code
	 */
	public String getClientCode() {
		return clientCode;
	}

	/**
	 * Sets the client code.
	 *
	 * @param clientCode the new client code
	 */
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	/**
	 * Gets the client name.
	 *
	 * @return the client name
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * Sets the client name.
	 *
	 * @param clientName the new client name
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * Gets the client email.
	 *
	 * @return the client email
	 */
	public String getClientEmail() {
		return clientEmail;
	}

	/**
	 * Sets the client email.
	 *
	 * @param clientEmail the new client email
	 */
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	/**
	 * Gets the client phone number.
	 *
	 * @return the client phone number
	 */
	public String getClientPhoneNumber() {
		return clientPhoneNumber;
	}

	/**
	 * Sets the client phone number.
	 *
	 * @param clientPhoneNumber the new client phone number
	 */
	public void setClientPhoneNumber(String clientPhoneNumber) {
		this.clientPhoneNumber = clientPhoneNumber;
	}

	/**
	 * Gets the client password.
	 *
	 * @return the client password
	 */
	public String getClientPassword() {
		return clientPassword;
	}

	/**
	 * Sets the client password.
	 *
	 * @param clientPassword the new client password
	 */
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	/**
	 * Gets the client date of birth.
	 *
	 * @return the client date of birth
	 */
	public Date getClientDateOfBirth() {
		return clientDateOfBirth;
	}

	/**
	 * Sets the client date of birth.
	 *
	 * @param clientDateOfBirth the new client date of birth
	 */
	public void setClientDateOfBirth(Date clientDateOfBirth) {
		this.clientDateOfBirth = clientDateOfBirth;
	}
}
