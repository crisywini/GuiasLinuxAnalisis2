package co.edu.uniquindio.project.beans;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;

/**
 * The class RecoverPasswordBean
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Named("recoverPasswordBean")
@ApplicationScoped
public class RecoverPasswordBean {

	/** The web user EJB. */
	@EJB
	private WebUserEJB webUserEJB;

	/** The email. */
	private String email;

	/**
	 * Send email validation.
	 */
	public void sendEmailValidation() {
		if (!email.isEmpty()) {
			try {
				webUserEJB.recoverPassword(email);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN",
						"Revisa tu correo, te enviamos un mensaje con datos personales.");
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);
			} catch (NonexistentUserException e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
					"Debes ingresar un correo");
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		}
	}

	/**
	 * Redirect recovery page.
	 *
	 * @return the string
	 */
	public String redirectRecoveryPage() {
		return "recoveryPage?faces-redirect=true";
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
