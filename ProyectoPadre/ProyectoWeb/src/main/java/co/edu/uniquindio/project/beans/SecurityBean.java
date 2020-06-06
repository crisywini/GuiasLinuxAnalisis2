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

/**
 * The class SecurityBean
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Named("securityBean")
@SessionScoped
public class SecurityBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The user. */
	private User user;

	/** The authenticated. */
	private boolean authenticated;

	/** The email login. */
	private String emailLogin;

	/** The password login. */
	private String passwordLogin;

	/** The i estate agency. */
	private boolean iEstateAgency;

	/** The web user EJB. */
	@EJB
	private WebUserEJB webUserEJB;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		user = new User();
		authenticated = false;
		iEstateAgency = false;
	}

	/**
	 * Sign off.
	 *
	 * @return the string
	 */
	public String signOff() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index?faces-redirect=true";
	}

	/**
	 * Authenticate user.
	 *
	 * @return the string
	 */
	public String authenticateUser() {

		try {
			user = webUserEJB.authenticateUser(emailLogin, passwordLogin);
			authenticated = true;

			if (user instanceof EstateAgency) {
				iEstateAgency = true;
			}

			return "/index?faces-redirect=true";

		} catch (AuthenticationException e) {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("message_login", message);
		}
		return "";
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Checks if is authenticated.
	 *
	 * @return true, if is authenticated
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}

	/**
	 * Sets the authenticated.
	 *
	 * @param authenticated the new authenticated
	 */
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	/**
	 * Gets the email login.
	 *
	 * @return the email login
	 */
	public String getEmailLogin() {
		return emailLogin;
	}

	/**
	 * Sets the email login.
	 *
	 * @param emailLogin the new email login
	 */
	public void setEmailLogin(String emailLogin) {
		this.emailLogin = emailLogin;
	}

	/**
	 * Gets the password login.
	 *
	 * @return the password login
	 */
	public String getPasswordLogin() {
		return passwordLogin;
	}

	/**
	 * Sets the password login.
	 *
	 * @param passwordLogin the new password login
	 */
	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}

	/**
	 * Gets the web user EJB.
	 *
	 * @return the web user EJB
	 */
	public WebUserEJB getWebUserEJB() {
		return webUserEJB;
	}

	/**
	 * Sets the web user EJB.
	 *
	 * @param webUserEJB the new web user EJB
	 */
	public void setWebUserEJB(WebUserEJB webUserEJB) {
		this.webUserEJB = webUserEJB;
	}

	/**
	 * Gets the i estate agency.
	 *
	 * @return the i estate agency
	 */
	public boolean getIEstateAgency() {
		return iEstateAgency;
	}

	/**
	 * Sets the i estate agency.
	 *
	 * @param isEstateAgency the new i estate agency
	 */
	public void setIEstateAgency(boolean isEstateAgency) {
		this.iEstateAgency = isEstateAgency;
	}
}
