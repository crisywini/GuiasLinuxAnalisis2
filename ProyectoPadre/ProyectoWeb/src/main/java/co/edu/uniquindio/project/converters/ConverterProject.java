package co.edu.uniquindio.project.converters;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.project.exceptions.NonexistentProject;
import co.edu.uniquindio.unihogar.entities.Project;

/**
 * The class ConverterProject
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("converterProject")
@ApplicationScoped
public class ConverterProject implements Converter<Project>, Serializable {

	private static final long serialVersionUID = 1L;

	/** The web user EJB. */
	@EJB
	private WebUserEJB webUserEJB;

	/**
	 * Gets the as object.
	 *
	 * @param context   the context
	 * @param component the component
	 * @param value     the value
	 * @return the as object
	 */
	@Override
	public Project getAsObject(FacesContext context, UIComponent component, String value) {
		Project project = null;
		if (value != null) {
			try {
				project = webUserEJB.getProjectByCode(Integer.parseInt(value));
			} catch (NumberFormatException | NonexistentProject e) {
				throw new ConverterException(new FacesMessage(component.getClientId() + ": código no válido"));
			}
		}
		return project;
	}

	/**
	 * Gets the as string.
	 *
	 * @param context   the context
	 * @param component the component
	 * @param value     the value
	 * @return the as string
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Project value) {
		String code = "";
		if (value != null)
			code = value.getCode() + "";
		return code;
	}
}
