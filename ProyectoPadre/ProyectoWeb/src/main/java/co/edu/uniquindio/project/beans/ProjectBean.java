package co.edu.uniquindio.project.beans;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.IOUtils;

import com.jsf2leaf.model.LatLong;
import com.jsf2leaf.model.Layer;
import com.jsf2leaf.model.Map;
import com.jsf2leaf.model.Marker;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.project.exceptions.RepeatedProjectException;
import co.edu.uniquindio.unihogar.entities.City;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
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

	@PostConstruct
	public void init() {
		projectImages = new ArrayList<String>();
		map = new Map();
		map.setCenter(new LatLong("4.55396", "-75.66038")).setWidth("100%").setHeight("500px").setZoom(10);

		List<Project> projects = webUserEJB.getAllProjects();
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
				if(!projectImages.isEmpty()) {
					project = new Project();
					project.setLatitude(lat);
					project.setLength(lng);
					project.setName(projectName);
					project.setDescription(projectDescription);
					project.setCity(projectCity);
					project.setImages(projectImages);
					project.setEstateAgency(estateAgencyProject);
					webUserEJB.addProject(project);
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "Projecto registrado!");
					FacesContext.getCurrentInstance().addMessage("messages_bean", message);
					createDirectoryImagesProject();
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
	 * 
	 * @param event
	 */
	public void uploadFiles(FileUploadEvent event) {
		System.out.println("AYUDA PARA SABER DONDE ESTÁ EN EL LOG");
		UploadedFile file = event.getFile();
		InputStream reader = null;
		OutputStream writer = null;
		try {
			System.out.println("todo bien parce");
			reader = file.getInputStream();
			byte[] buffer = new byte[1024];
			System.out.println("seguimos bien con el buffer");
			writer = new FileOutputStream("~/uniquindio/analisis2/");
			int length;
			System.out.println("Está a punto de copiar");
			while ((length = reader.read(buffer)) > 0) {
				writer.write(buffer, 0, length);
			}
			//IOUtils.copy(reader, writer);
			System.out.println("Imprimió cosas cheveres!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		finally {
//			if(reader!=null)
//				try {
//					reader.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			if(writer!=null)
//				try {
//					writer.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//		}

		projectImages.add(file.getFileName());
	}
	public void createDirectoryImagesProject() {
		for (String image : projectImages) {
			File directory = new File("~/uniquindio/analisis2/glassfish-5.1.0/glassfish5/glassfish/domains/domain1/docroot/unihogar/"+projectName+"/");
			boolean isCreatedDir = directory.mkdir();
			if(isCreatedDir) {
				File imageFile = new File("~/uniquindio/analisis2/glassfish-5.1.0/glassfish5/glassfish/domains/domain1/docroot/"+projectName+"/"+image);
				try {
					boolean isCreated = imageFile.createNewFile();
					if(isCreated) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN","IMAGEN CARGADA");
						FacesContext.getCurrentInstance().addMessage("messages_bean", message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
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

}
