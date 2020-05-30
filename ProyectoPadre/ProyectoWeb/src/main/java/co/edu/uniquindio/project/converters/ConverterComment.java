package co.edu.uniquindio.project.converters;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.unihogar.entities.Comment;

@FacesConfig(version = Version.JSF_2_3)
@Named("converterComment")
@ApplicationScoped
public class ConverterComment implements Converter<Comment>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private WebUserEJB webUserEJB;

	@Override
	public Comment getAsObject(FacesContext context, UIComponent component, String value) {
		Comment comment = null;
		if(value!=null) {
			try {
				comment = webUserEJB.getCommentByCode(Integer.parseInt(value));
			} catch (NumberFormatException | NonexistentUserException e) {
				e.printStackTrace();
			}
		}
		return comment;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Comment value) {
		if(value!=null)
			return ""+value.getCode();
		return "";
	}

}
