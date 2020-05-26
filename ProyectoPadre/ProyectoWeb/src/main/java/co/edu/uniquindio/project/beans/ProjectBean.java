package co.edu.uniquindio.project.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jsf2leaf.model.LatLong;
import com.jsf2leaf.model.Layer;
import com.jsf2leaf.model.Map;
import com.jsf2leaf.model.Marker;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.project.exceptions.RepeatedProjectException;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.Project;

@Named("projectBean")
@ViewScoped
public class ProjectBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map map;
	@EJB
	private WebUserEJB webUserEJB;
	private Project project;
	private double lat, lng;
	@NotNull(message = "Debes ingresar el nombre del proyecto")
	@Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
	private String projectName;
	@NotNull(message = "Debes ingresar la descripción del proyecto")
	private String projectDescription;

	private City projectCity;
	private List<City> cities;
	private List<Project> projects;

	@PostConstruct
	public void init() {
		map = new Map();
		map.setCenter(new LatLong("4.55396", "-75.66038")).setWidth("100%").setHeight("500px").setZoom(10);

		List<Project> projects = webUserEJB.getAllProjects();
		// center="4.55396,-75.66038" width="100%" height="500px" zoom="18" map="{}"
		fillMarkers(projects);

		cities = webUserEJB.getAllCity();
		this.projects = webUserEJB.getAllProjects();

	}

	public void fillMarkers(List<Project> projects) {
		Layer layer = new Layer();

		double lat = 0;
		double lon = 0;
		for (Project project : projects) {
			lat = project.getLatitude();
			lon = project.getLength();
			Marker marker = new Marker(new LatLong(lat + "", lon + ""), project.getName());
			layer.addMarker(marker);
		}
		map.addLayer(layer);
	}

	public void registerProject() {

		try {
			if(lat!=0 && lng!=0) {
				project = new Project();
				project.setLatitude(lat);
				project.setLength(lng);
				project.setName(projectName);
				project.setDescription(projectDescription);
				project.setCity(projectCity);
				webUserEJB.addProject(project);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "Projecto registrado!");
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);
			}
			else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "Debes ingresar la ubicación");
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);
			}
		} catch (RepeatedProjectException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		}
	}

	public Map getMap() {
		return map;
	}

	public void createMarker() {
		java.util.Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String lat = map.get("lat");// Latitud de la ubicación del proyecto
		String lng = map.get("lng");// Longitud
		this.lat = Double.parseDouble(lat);
		this.lng = Double.parseDouble(lng);

		/*FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "POSICIÓN", lat + lng);
		FacesContext.getCurrentInstance().addMessage("pos_message", message);*/

	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public City getProjectCity() {
		return projectCity;
	}

	public void setProjectCity(City projectCity) {
		this.projectCity = projectCity;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}
