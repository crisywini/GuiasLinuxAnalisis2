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
import co.edu.uniquindio.project.exceptions.NonexistentCityException;
import co.edu.uniquindio.unihogar.entities.City;
@FacesConfig(version = Version.JSF_2_3)
@Named("converterCity")
@ApplicationScoped//Funciona para todos los clientes igual por lo que tiene sentido dejarlo así
public class ConverterCity implements Converter<City>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private WebUserEJB webUSerEJB;

	@Override
	public City getAsObject(FacesContext context, UIComponent component, String value) {
		City city = null;
		if(value!=null) {
			try {
				city = webUSerEJB.getCityByCode(Integer.parseInt(value));
			} catch (Exception e) {
				throw new ConverterException(new FacesMessage(component.getClientId()+": código no válido"));
			}
		}
		
		return city;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, City value) {
		String primaryKey = "";
		if(value!=null)
			primaryKey = ""+value.getCode();
		return primaryKey;
	}


}
