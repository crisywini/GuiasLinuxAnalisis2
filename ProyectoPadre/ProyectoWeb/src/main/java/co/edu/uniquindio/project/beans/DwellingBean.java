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

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

import co.edu.uniquindio.project.WebUserEJB;
import co.edu.uniquindio.unihogar.entities.Dwelling;
import co.edu.uniquindio.unihogar.entities.Type;

@Named("dwellingBean")
@RequestScoped
public class DwellingBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private WebUserEJB webUserEJB;
	private Dwelling dwelling;
	
	@NotNull(message = "Debes ingresar el area")
	private double area;
	@NotNull(message = "Debes ingresar la descripcion")
	private String description;
	@NotNull(message = "Debes ingresar el numero de banios")
	private int numBathroom;
	@NotNull(message = "Debes ingresar el numero de habitaciones")
	private int numRoom;
	@NotNull(message = "Debes ingresar el precio")
	private double price;
	@NotNull(message = "Debes ingresar el tipo de vivienda")
	private Type type;

	@NotNull(message = "Debes ingresar las imagenes")
	private ArrayList<UploadedFile> imagesUploaded;
	private ArrayList<String> dwellingImages;
	
	@PostConstruct
	public void init() {
		
		dwellingImages = new ArrayList<String>();
		imagesUploaded = new ArrayList<UploadedFile>();
		type = Type.HOUSE;
		
	}
	
	public void registerDwelling() {

		try {
				if(!dwellingImages.isEmpty()) {
					dwelling = new Dwelling();
					dwelling.setArea(area);
					dwelling.setDescription(description);
					dwelling.setNumBathrooms(numBathroom);
					dwelling.setNumRooms(numRoom);
					dwelling.setPrice(price);
					dwelling.setType(type);
					createDirectoryImagesProject();
					webUserEJB.addDwelling(dwelling);
//					dwelling.setProject();
					
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
		UploadedFile file = event.getFile();
		String filename = FilenameUtils.getName(file.getFileName()); 
		String extension = FilenameUtils.getExtension(file.getFileName());
		imagesUploaded.add(file);
		dwellingImages.add(filename+"." + extension);
	}
	
	public void createDirectoryImagesProject() {
		File directory = new File("/home/luisacotte/eclipse/glassfish5/glassfish/domains/domain1/docroot/unihogar/"+area+"/");
		boolean isCreatedDir = directory.mkdir();
		System.out.println("CARPETA CREADA"+isCreatedDir);
		if(isCreatedDir) {
			System.out.println("CARPETA CREADA"+isCreatedDir);
			for (UploadedFile file : imagesUploaded) {
				Path folder = Paths.get("/home/luisacotte/eclipse/glassfish5/glassfish/domains/domain1/docroot/unihogar/"+area+"/");
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
		for (String nameImage : dwellingImages) {
			withAbsolute.add("/home/luisacotte/eclipse/glassfish5/glassfish/domains/domain1/docroot/unihogar/"+area+"/"+nameImage);
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

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public WebUserEJB getWebUserEJB() {
		return webUserEJB;
	}

	public void setWebUserEJB(WebUserEJB webUserEJB) {
		this.webUserEJB = webUserEJB;
	}

	public int getNumBathroom() {
		return numBathroom;
	}

	public void setNumBathroom(int numBathroom) {
		this.numBathroom = numBathroom;
	}

	public int getNumRoom() {
		return numRoom;
	}

	public void setNumRoom(int numRoom) {
		this.numRoom = numRoom;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

}
