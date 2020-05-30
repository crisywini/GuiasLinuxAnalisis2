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

@Named("dwellingBean")
@ViewScoped
public class DwellingBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private WebUserEJB webUserEJB;
	private Dwelling dwelling;
	private UploadedFile file;

	private String area;
	private String description;
	private String numBathroom;
	private String numRoom;
	private String price;
	private Type type;
	private ArrayList<Type> types;

	private ArrayList<UploadedFile> imagesUploaded;
	private ArrayList<String> dwellingImages;

	private Project project;
	private List<Project> projects;
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

	public void registerDwelling() {

		try {
			System.out.println("BOBO----------------------"+imagesUploaded.size());
			if(!imagesUploaded.isEmpty()) {
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


				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", "Vivienda registrado!");
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "Debes agregar por lo menos una imagen!");
				FacesContext.getCurrentInstance().addMessage("messages_bean", message);
			}

		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("messages_bean", message);
		}
	}

	public void uploadFiles(FileUploadEvent event) {
		imagesUploaded.add(event.getFile());
		System.out.println("IMAGEN AGREHADA AÑL ARRAYLIST");
	}

	public void createDirectoryImagesProject() {
		ArrayList<String> withAbsolute = new ArrayList<String>();

		for (UploadedFile file : imagesUploaded) {
			Path folder = Paths.get("/home/crisisanchezp/uniquindio/analisis2/glassfish-5.1.0/glassfish5/glassfish/domains/domain1/docroot/unihogar/");
			String filename = FilenameUtils.getBaseName(file.getFileName()); 
			String extension = FilenameUtils.getExtension(file.getFileName());
			System.out.println("-------------->>"+filename);
			System.out.println("-------------__>"+extension);
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
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Dwelling getDwelling() {
		return dwelling;
	}

	public void setDwelling(Dwelling dwelling) {
		this.dwelling = dwelling;
	}


	public WebUserEJB getWebUserEJB() {
		return webUserEJB;
	}

	public void setWebUserEJB(WebUserEJB webUserEJB) {
		this.webUserEJB = webUserEJB;
	}


	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public ArrayList<UploadedFile> getImagesUploaded() {
		return imagesUploaded;
	}

	public void setImagesUploaded(ArrayList<UploadedFile> imagesUploaded) {
		this.imagesUploaded = imagesUploaded;
	}

	public ArrayList<String> getDwellingImages() {
		return dwellingImages;
	}

	public void setDwellingImages(ArrayList<String> dwellingImages) {
		this.dwellingImages = dwellingImages;
	}

	public ArrayList<Type> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<Type> types) {
		this.types = types;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNumBathroom() {
		return numBathroom;
	}

	public void setNumBathroom(String numBathroom) {
		this.numBathroom = numBathroom;
	}

	public String getNumRoom() {
		return numRoom;
	}

	public void setNumRoom(String numRoom) {
		this.numRoom = numRoom;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
