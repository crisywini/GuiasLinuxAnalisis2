package co.edu.uniquindio.ejemploe;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Estudiante
 *
 */
@Entity

public class Estudiante implements Serializable {

	@Id
	private String codigo;
	private String nombre;
	private String email;
	@ManyToMany(mappedBy = "estudiantes") // Nombre del atribudo de la otra entidad, es decir la entidad propietaria
	private List<EspacioAcademico> espaciosAcademicos;
	private static final long serialVersionUID = 1L;

	public Estudiante() {
		super();
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
