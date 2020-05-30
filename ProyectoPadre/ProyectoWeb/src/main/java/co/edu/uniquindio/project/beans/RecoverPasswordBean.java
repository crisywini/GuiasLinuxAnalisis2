package co.edu.uniquindio.project.beans;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;

@Named("recoverPasswordBean")
@ApplicationScoped
public class RecoverPasswordBean {
	@EJB
	private WebUserEJB webUserEJB;

	private String email;

	public void sendEmailValidation() {
		if(!email.isEmpty()) {
			try {
				webUserEJB.recoverPassword(email);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "Revisa tu correo, te enviamos un mensaje con datos personales.");
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);
			} catch (NonexistentUserException e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);

			}
		}
		else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "Debes ingresar un correo");
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		}
	}
	public String redirectRecoveryPage() {
		return  "recoveryPage?faces-redirect=true";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
