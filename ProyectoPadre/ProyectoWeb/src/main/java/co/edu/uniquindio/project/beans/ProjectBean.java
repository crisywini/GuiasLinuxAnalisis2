package co.edu.uniquindio.project.beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

import com.jsf2leaf.model.LatLong;
import com.jsf2leaf.model.Layer;
import com.jsf2leaf.model.Map;
import com.jsf2leaf.model.Marker;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.project.exceptions.RepeatedProjectException;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.Service;
import co.edu.uniquindio.unihogar.entities.User;

/**
 * The class ProjectBean
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Named("projectBean")
@ViewScoped
public class ProjectBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The map. */
	private Map map;

	/** The web user EJB. */
	@EJB
	private WebUserEJB webUserEJB;

	/** The project. */
	private Project project;

	/** The lng. */
	@NotNull(message = "Debes ingresar la ubicación")
	private double lat, lng;

	/** The project name. */
	@NotNull(message = "Debes ingresar el nombre del proyecto")
	@Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
	private String projectName;

	/** The project description. */
	@NotNull(message = "Debes ingresar la descripción del proyecto")
	private String projectDescription;

	/** The project city. */
	private City projectCity;

	/** The cities. */
	private List<City> cities;

	/** The projects. */
	private List<Project> projects;

	/** The project images. */
	private ArrayList<String> projectImages;

	/** The estate agency project. */
	private EstateAgency estateAgencyProject;

	/** The images uploaded. */
	private ArrayList<UploadedFile> imagesUploaded;

	/** The project services. */
	private List<Service> projectServices;

	/** The selected services. */
	private Service[] selectedServices;

	/** The user. */
	@Inject
	@ManagedProperty(value = "#{securityBean.user}")
	private User user;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		projectImages = new ArrayList<String>();
		imagesUploaded = new ArrayList<UploadedFile>();
		map = new Map();
		map.setCenter(new LatLong("4.55396", "-75.66038")).setWidth("100%").setHeight("500px").setZoom(10);

		List<Project> projects = webUserEJB.getAllProjects();
		projectServices = webUserEJB.getAllServices();
		// center="4.55396,-75.66038" width="100%" height="500px" zoom="18" map="{}"
		fillMarkers(projects);

		cities = webUserEJB.getAllCity();
		estateAgencyProject = webUserEJB.getEaProvisional();
		this.projects = webUserEJB.getAllProjects();
	}

	/**
	 * Fill markers.
	 *
	 * @param projects the projects
	 */
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

	/**
	 * Register project.
	 */
	public void registerProject() {

		try {
			if (lat != 0 && lng != 0) {
				if (!projectImages.isEmpty() && user instanceof EstateAgency) {
					project = new Project();
					project.setServices(Arrays.asList(selectedServices));
					project.setLatitude(lat);
					project.setLength(lng);
					project.setName(projectName);
					project.setDescription(projectDescription);
					project.setCity(projectCity);
					createDirectoryImagesProject();
					project.setImages(projectImages);
					project.setEstateAgency((EstateAgency) user);

					webUserEJB.addProject(project);

					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN",
							"Projecto registrado!");
					FacesContext.getCurrentInstance().addMessage("messages_bean", message);
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
							"Debes agregar por lo menos una imagen!");
					FacesContext.getCurrentInstance().addMessage("messages_bean", message);
				}
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
						"Debes ingresar la ubicación");
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);
			}
		} catch (RepeatedProjectException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		}
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Creates the marker.
	 */
	public void createMarker() {
		java.util.Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String lat = map.get("lat");// Latitud de la ubicación del proyecto
		String lng = map.get("lng");// Longitud
		this.lat = Double.parseDouble(lat);
		this.lng = Double.parseDouble(lng);

		/*
		 * FacesMessage emessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
		 * "POSICIÓN", lat + lng);
		 * FacesContext.getCurrentInstance().addMessage("pos_message", message);
		 */
	}

	/**
	 * Me queda la duda con ese write, si le funciona? pues es que como daba ese
	 * error con la ciudad no se si debería funcionar con la ciudad Lo de la ciudad
	 * ya quedó bien. Intente con ese write o si no implemente un método que haga
	 * uso de un FileOutputStream Listo profe, es que en lenguaje manejamos una cosa
	 * de bytes para las imagenes, pero bueno, profe otra cosa en la parte del
	 * script, hay dos métodos, pero con el template se inicia en ya le explico
	 * PONER TODA LA RUTA DE LAS IMAGENES EN EL PROYECTO NO SOLO EL NOMBRE
	 *
	 * @param event the event
	 */
	public void uploadFiles(FileUploadEvent event) {
		UploadedFile file = event.getFile();
		// try {
		// Path folder = Paths.get("/home/crisisanchezp/uniquindio/analisis2");
		// String filename = FilenameUtils.getBaseName(file.getFileName());
		// String extension = FilenameUtils.getExtension(file.getFileName());
		// Path fileP = Files.createTempFile(folder, filename + "-", "." + extension);
		// try (InputStream input = file.getInputStream()) {
		// Files.copy(input, fileP, StandardCopyOption.REPLACE_EXISTING);
		// System.out.println("Escritura correcta");
		// }
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		String filename = FilenameUtils.getBaseName(file.getFileName());
		String extension = FilenameUtils.getExtension(file.getFileName());
		imagesUploaded.add(file);
		projectImages.add(filename + "." + extension);
	}

	/**
	 * Creates the directory images project.
	 */
	public void createDirectoryImagesProject() {
		ArrayList<String> withAbsolute = new ArrayList<String>();

		for (UploadedFile file : imagesUploaded) {
			Path folder = Paths.get(
					"/home/crisisanchezp/uniquindio/analisis2/glassfish-5.1.0/glassfish5/glassfish/domains/domain1/docroot/unihogar/");
			String filename = FilenameUtils.getBaseName(file.getFileName());
			String extension = FilenameUtils.getExtension(file.getFileName());
			System.out.println("-------------->>" + filename);
			System.out.println("-------------__>" + extension);
			Path fileP;
			try {
				File file2 = null;
				fileP = Files.createTempFile(folder, filename, "." + extension);
				file2 = fileP.toFile();
				System.out.println(file2.getName());
				withAbsolute.add(file2.getName());
				try (InputStream input = file.getInputStream()) {
					Files.copy(input, fileP, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Escritura correcta");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		projectImages.clear();
		projectImages = withAbsolute;

	}

	/**
	 * On item unselect.
	 *
	 * @param event the event
	 */
	public void onItemUnselect(UnselectEvent<Service> event) {
		FacesContext context = FacesContext.getCurrentInstance();

		FacesMessage msg = new FacesMessage();
		msg.setSummary("Item unselected: " + event.getObject().toString());
		msg.setSeverity(FacesMessage.SEVERITY_INFO);

		context.addMessage(null, msg);
	}

	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * Gets the lat.
	 *
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * Sets the lat.
	 *
	 * @param lat the new lat
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * Gets the lng.
	 *
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * Sets the lng.
	 *
	 * @param lng the new lng
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}

	/**
	 * Gets the project name.
	 *
	 * @return the project name
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Sets the project name.
	 *
	 * @param projectName the new project name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * Gets the project description.
	 *
	 * @return the project description
	 */
	public String getProjectDescription() {
		return projectDescription;
	}

	/**
	 * Sets the project description.
	 *
	 * @param projectDescription the new project description
	 */
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	/**
	 * Gets the project city.
	 *
	 * @return the project city
	 */
	public City getProjectCity() {
		return projectCity;
	}

	/**
	 * Sets the project city.
	 *
	 * @param projectCity the new project city
	 */
	public void setProjectCity(City projectCity) {
		this.projectCity = projectCity;
	}

	/**
	 * Gets the cities.
	 *
	 * @return the cities
	 */
	public List<City> getCities() {
		return cities;
	}

	/**
	 * Sets the cities.
	 *
	 * @param cities the new cities
	 */
	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	/**
	 * Gets the projects.
	 *
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}

	/**
	 * Sets the projects.
	 *
	 * @param projects the new projects
	 */
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	/**
	 * Gets the project images.
	 *
	 * @return the project images
	 */
	public ArrayList<String> getProjectImages() {
		return projectImages;
	}

	/**
	 * Sets the project images.
	 *
	 * @param projectImages the new project images
	 */
	public void setProjectImages(ArrayList<String> projectImages) {
		this.projectImages = projectImages;
	}

	/**
	 * Gets the estate agency project.
	 *
	 * @return the estate agency project
	 */
	public EstateAgency getEstateAgencyProject() {
		return estateAgencyProject;
	}

	/**
	 * Sets the estate agency project.
	 *
	 * @param estateAgencyProject the new estate agency project
	 */
	public void setEstateAgencyProject(EstateAgency estateAgencyProject) {
		this.estateAgencyProject = estateAgencyProject;
	}

	/**
	 * Gets the project services.
	 *
	 * @return the project services
	 */
	public List<Service> getProjectServices() {
		return projectServices;
	}

	/**
	 * Sets the project services.
	 *
	 * @param projectServices the new project services
	 */
	public void setProjectServices(List<Service> projectServices) {
		this.projectServices = projectServices;
	}

	/**
	 * Gets the selected services.
	 *
	 * @return the selected services
	 */
	public Service[] getSelectedServices() {
		return selectedServices;
	}

	/**
	 * Sets the selected services.
	 *
	 * @param selectedServices the new selected services
	 */
	public void setSelectedServices(Service[] selectedServices) {
		this.selectedServices = selectedServices;
	}
}
