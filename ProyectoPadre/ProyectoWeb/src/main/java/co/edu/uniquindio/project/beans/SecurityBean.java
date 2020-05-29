package co.edu.uniquindio.project.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.User;

@Named("securityBean")
@SessionScoped
public class SecurityBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private boolean authenticated;
	private String emailLogin;
	private String passwordLogin;
	private boolean iEstateAgency;

	@EJB
	private WebUserEJB webUserEJB;

	@PostConstruct
	public void init() {

		user = new User();
		authenticated = false;
		iEstateAgency = false;
	}
	
	public String signOff() {
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index?faces-redirect=true";
	}

	public String authenticateUser() {

		try {
			user = webUserEJB.authenticateUser(emailLogin, passwordLogin);
			authenticated = true;
			
			if(user instanceof EstateAgency) {
				iEstateAgency = true;
			}
			
			return "/index?faces-redirect=true";
			
		} catch (AuthenticationException e) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("message_login", message);
		}

		return "";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getEmailLogin() {
		return emailLogin;
	}

	public void setEmailLogin(String emailLogin) {
		this.emailLogin = emailLogin;
	}

	public String getPasswordLogin() {
		return passwordLogin;
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}

	public WebUserEJB getWebUserEJB() {
		return webUserEJB;
	}

	public void setWebUserEJB(WebUserEJB webUserEJB) {
		this.webUserEJB = webUserEJB;
	}

	public boolean getIEstateAgency() {
		return iEstateAgency;
	}

	public void setIEstateAgency(boolean isEstateAgency) {
		this.iEstateAgency = isEstateAgency;
	}

	
	
}
