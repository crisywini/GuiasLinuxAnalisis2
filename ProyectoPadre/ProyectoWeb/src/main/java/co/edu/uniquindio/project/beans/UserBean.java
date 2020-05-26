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

@Named("userBean")
@ApplicationScoped
public class UserBean {
	@EJB
	private WebUserEJB webUserEJB;
	@NotNull(message = "Debes ingresar el código")
	@NotEmpty(message = "El código no debe ser vacío")
	@Pattern(regexp = "\\d{10}", message = "El código debe tener solo 10 números")
	private String clientCode;
	@NotNull(message = "Debes ingresar el código")
	@NotEmpty(message = "El código no debe ser vacío")
	private String clientName;
	@NotNull(message = "Debes ingresar el correo electrónico")
	@NotEmpty(message = "El correo electrónico no debe ser vacío")
	@Email(message = "Debes escribir un correo electrónico válido")
	private String clientEmail;
	@NotEmpty(message = "Debes ingresar el número de teléfono")
	@Pattern(regexp = "\\d{3}\\d{3}\\d{4}", message = "El número debe ser válido")
	private String clientPhoneNumber;
	@Past(message = "Debes ingresar una fecha válida (en el pasado)")
	private Date clientDateOfBirth;
	@NotNull(message = "Debes ingresar la contraseña")
	@NotEmpty(message = "La contraseña no debe estar vacía")
	private String clientPassword;
	

	public void registerClient() {
		try {
			Client client = new Client();
			webUserEJB.registerClient(client);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "Registro exitoso");
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		}
		
	}

	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getClientPhoneNumber() {
		return clientPhoneNumber;
	}
	public void setClientPhoneNumber(String clientPhoneNumber) {
		this.clientPhoneNumber = clientPhoneNumber;
	}
	public String getClientPassword() {
		return clientPassword;
	}
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public Date getClientDateOfBirth() {
		return clientDateOfBirth;
	}

	public void setClientDateOfBirth(Date clientDateOfBirth) {
		this.clientDateOfBirth = clientDateOfBirth;
	}
}
