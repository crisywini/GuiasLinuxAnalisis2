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
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.primefaces.shaded.commons.io.FilenameUtils;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.unihogar.entities.Dwelling;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.Type;

/**
 * The class DwellingBean
 *
 * @author Cristian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Named("dwellingBean")
@ViewScoped
public class DwellingBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The web user EJB. */
	@EJB
	private WebUserEJB webUserEJB;

	/** The dwelling. */
	private Dwelling dwelling;

	/** The file. */
	private UploadedFile file;

	/** The area. */
	private String area;

	/** The description. */
	private String description;

	/** The num bathroom. */
	private String numBathroom;

	/** The num room. */
	private String numRoom;

	/** The price. */
	private String price;

	/** The type. */
	private Type type;

	/** The types. */
	private ArrayList<Type> types;

	/** The images uploaded. */
	private ArrayList<UploadedFile> imagesUploaded;

	/** The dwelling images. */
	private ArrayList<String> dwellingImages;

	/** The project. */
	private Project project;

	/** The projects. */
	private List<Project> projects;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		projects = webUserEJB.getAllProjects();

		dwellingImages = new ArrayList<String>();
		imagesUploaded = new ArrayList<UploadedFile>();
		type = Type.HOUSE;
		types = new ArrayList<Type>();
		types.add(Type.APARTMENT);
		types.add(Type.HOUSE);
	}

	/**
	 * Register dwelling.
	 */
	public void registerDwelling() {

		try {
			System.out.println("----------------------" + imagesUploaded.size());
			if (!imagesUploaded.isEmpty()) {
				dwelling = new Dwelling();
				dwelling.setArea(Double.parseDouble(area));
				dwelling.setDescription(description);
				dwelling.setNumBathrooms(Integer.parseInt(numBathroom));
				dwelling.setNumRooms(Integer.parseInt(numRoom));
				dwelling.setPrice(Double.parseDouble(price));
				dwelling.setType(type);
				createDirectoryImagesProject();
				dwelling.setUrlImage(dwellingImages.get(0));
				webUserEJB.addDwelling(dwelling);

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN",
						"Vivienda registrado!");
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA",
						"Debes agregar por lo menos una imagen!");
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);
			}

		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		}
	}

	/**
	 * Upload files.
	 *
	 * @param event the event
	 */
	public void uploadFiles(FileUploadEvent event) {
		imagesUploaded.add(event.getFile());
		System.out.println("IMAGEN AGREHADA AÑL ARRAYLIST");
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
		dwellingImages.clear();
		dwellingImages = withAbsolute;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the dwelling.
	 *
	 * @return the dwelling
	 */
	public Dwelling getDwelling() {
		return dwelling;
	}

	/**
	 * Sets the dwelling.
	 *
	 * @param dwelling the new dwelling
	 */
	public void setDwelling(Dwelling dwelling) {
		this.dwelling = dwelling;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Gets the images uploaded.
	 *
	 * @return the images uploaded
	 */
	public ArrayList<UploadedFile> getImagesUploaded() {
		return imagesUploaded;
	}

	/**
	 * Sets the images uploaded.
	 *
	 * @param imagesUploaded the new images uploaded
	 */
	public void setImagesUploaded(ArrayList<UploadedFile> imagesUploaded) {
		this.imagesUploaded = imagesUploaded;
	}

	/**
	 * Gets the dwelling images.
	 *
	 * @return the dwelling images
	 */
	public ArrayList<String> getDwellingImages() {
		return dwellingImages;
	}

	/**
	 * Sets the dwelling images.
	 *
	 * @param dwellingImages the new dwelling images
	 */
	public void setDwellingImages(ArrayList<String> dwellingImages) {
		this.dwellingImages = dwellingImages;
	}

	/**
	 * Gets the types.
	 *
	 * @return the types
	 */
	public ArrayList<Type> getTypes() {
		return types;
	}

	/**
	 * Sets the types.
	 *
	 * @param types the new types
	 */
	public void setTypes(ArrayList<Type> types) {
		this.types = types;
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
	 * Sets the projects.
	 *
	 * @param projects the new projects
	 */
	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	/**
	 * Gets the area.
	 *
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * Sets the area.
	 *
	 * @param area the new area
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * Gets the num bathroom.
	 *
	 * @return the num bathroom
	 */
	public String getNumBathroom() {
		return numBathroom;
	}

	/**
	 * Sets the num bathroom.
	 *
	 * @param numBathroom the new num bathroom
	 */
	public void setNumBathroom(String numBathroom) {
		this.numBathroom = numBathroom;
	}

	/**
	 * Gets the num room.
	 *
	 * @return the num room
	 */
	public String getNumRoom() {
		return numRoom;
	}

	/**
	 * Sets the num room.
	 *
	 * @param numRoom the new num room
	 */
	public void setNumRoom(String numRoom) {
		this.numRoom = numRoom;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file the new file
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}
}
