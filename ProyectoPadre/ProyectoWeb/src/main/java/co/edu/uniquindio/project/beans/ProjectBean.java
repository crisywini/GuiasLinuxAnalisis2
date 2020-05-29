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
	@NotNull(message = "Debes ingresar la ubicación")
	private double lat, lng;
	@NotNull(message = "Debes ingresar el nombre del proyecto")
	@Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
	private String projectName;
	@NotNull(message = "Debes ingresar la descripción del proyecto")
	private String projectDescription;
	private City projectCity;
	private List<City> cities;
	private List<Project> projects;
	private ArrayList<String> projectImages;
	private EstateAgency estateAgencyProject;
	private ArrayList<UploadedFile> imagesUploaded;
	private List<Service> projectServices;
	private Service[] selectedServices;
	
	@Inject
	@ManagedProperty(value = "#{securityBean.user}")
	private User user;

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
				if(!projectImages.isEmpty() && user instanceof EstateAgency) {
					project = new Project();
					project.setServices(Arrays.asList(selectedServices));
					project.setLatitude(lat);
					project.setLength(lng);
					project.setName(projectName);
					project.setDescription(projectDescription);
					project.setCity(projectCity);
					createDirectoryImagesProject();
					project.setImages(projectImages);
					project.setEstateAgency((EstateAgency)user);
					
					createDirectoryImagesProject();
					
					webUserEJB.addProject(project);
					
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "Projecto registrado!");
					FacesContext.getCurrentInstance().addMessage("messages_bean", message);
				}else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "Debes agregar por lo menos una imagen!");
					FacesContext.getCurrentInstance().addMessage("messages_bean", message);
				}
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

		/*FacesMessage emessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "POSICIÓN", lat + lng);
		FacesContext.getCurrentInstance().addMessage("pos_message", message);*/
	}

	/**
	 * Me queda la duda con ese write, si le funciona?
	 * pues es que como daba ese error con la ciudad no se si debería 
	 * funcionar con la ciudad
	 * Lo de la ciudad ya quedó bien. Intente con ese write o si no
	 * implemente un método que haga uso de un FileOutputStream
	 * Listo profe, es que en lenguaje manejamos una cosa de bytes 
	 * para las imagenes, pero bueno, profe otra cosa
	 * en la parte del script, hay dos métodos, pero con el template
	 * se inicia en ya le explico 
	 * PONER TODA LA RUTA DE LAS IMAGENES EN EL PROYECTO NO SOLO EL NOMBRE
	 * @param event
	 */
	public void uploadFiles(FileUploadEvent event) {
		UploadedFile file = event.getFile();
//		try {
//			Path folder = Paths.get("/home/crisisanchezp/uniquindio/analisis2");
//			String filename = FilenameUtils.getBaseName(file.getFileName()); 
//			String extension = FilenameUtils.getExtension(file.getFileName());
//			Path fileP = Files.createTempFile(folder, filename + "-", "." + extension);
//			try (InputStream input = file.getInputStream()) {
//				Files.copy(input, fileP, StandardCopyOption.REPLACE_EXISTING);
//				System.out.println("Escritura correcta");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String filename = FilenameUtils.getName(file.getFileName()); 
		String extension = FilenameUtils.getExtension(file.getFileName());
		imagesUploaded.add(file);
		projectImages.add(filename+"." + extension);
	}
	public void createDirectoryImagesProject() {
		File directory = new File("/home/luisacotte/eclipse/glassfish5/glassfish/domains/domain1/docroot/unihogar/"+projectName+"/");
		boolean isCreatedDir = directory.mkdir();
		System.out.println("CARPETA CREADA"+isCreatedDir);
		if(isCreatedDir) {
			System.out.println("CARPETA CREADA"+isCreatedDir);
			for (UploadedFile file : imagesUploaded) {
				Path folder = Paths.get("/home/crisisanchezp/uniquindio/analisis2/glassfish-5.1.0/glassfish5/glassfish/domains/domain1/docroot/unihogar/"+projectName+"/");
				String filename = FilenameUtils.getName(file.getFileName()); 
				String extension = FilenameUtils.getExtension(file.getFileName());
				Path fileP;
				try {
					fileP = Files.createTempFile(folder, filename, "." + extension);
					try (InputStream input = file.getInputStream()) {
						Files.copy(input, fileP, StandardCopyOption.REPLACE_EXISTING);
						System.out.println("Escritura correcta");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		ArrayList<String> withAbsolute = new ArrayList<String>();
		for (String nameImage : projectImages) {
			withAbsolute.add("/home/crisisanchezp/uniquindio/analisis2/glassfish-5.1.0/glassfish5/glassfish/domains/domain1/docroot/unihogar/"+projectName+"/"+nameImage);
		}
		projectImages.clear();
		projectImages = withAbsolute;
		
	}
	public void onItemUnselect(UnselectEvent<Service> event) {
        FacesContext context = FacesContext.getCurrentInstance();
         
        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
         
        context.addMessage(null, msg);
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

	public ArrayList<String> getProjectImages() {
		return projectImages;
	}

	public void setProjectImages(ArrayList<String> projectImages) {
		this.projectImages = projectImages;
	}

	public EstateAgency getEstateAgencyProject() {
		return estateAgencyProject;
	}

	public void setEstateAgencyProject(EstateAgency estateAgencyProject) {
		this.estateAgencyProject = estateAgencyProject;
	}

	public List<Service> getProjectServices() {
		return projectServices;
	}

	public void setProjectServices(List<Service> projectServices) {
		this.projectServices = projectServices;
	}

	public Service[] getSelectedServices() {
		return selectedServices;
	}

	public void setSelectedServices(Service[] selectedServices) {
		this.selectedServices = selectedServices;
	}
	

}
