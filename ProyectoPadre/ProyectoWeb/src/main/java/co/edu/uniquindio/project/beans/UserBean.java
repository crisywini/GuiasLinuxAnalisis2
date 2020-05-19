package co.edu.uniquindio.project.beans;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.unihogar.entities.Client;

@Named("userBean")
@ApplicationScoped
public class UserBean {
	@EJB
	private WebUserEJB webUserEJB;
	private Client client;
	public UserBean() {
		client = new Client();
	}
	public void registerClient() {
		try {
			webUserEJB.registerClient(client);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "Registro exitoso");
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		}
		
	}
	
	
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}
