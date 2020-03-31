package co.edu.uniquindio.ejemploe;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProgramaAcademico
 *
 */
@Entity

public class ProgramaAcademico implements Serializable {

	@Id
	private String codigo;
	private String nombre;
	private int telefono;
	@OneToMany(mappedBy = "programa") // Nombre del atributo de la otra entidad, es decir, la entidad propietaria
	private List<EspacioAcademico> espaciosAcademicos;
	private static final long serialVersionUID = 1L;

	public ProgramaAcademico() {
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

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}
