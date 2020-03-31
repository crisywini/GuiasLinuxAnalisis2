package co.edu.uniquindio.ejemploe;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: EspacioAcademico
 *
 */
@Entity
//Entidad propietaria
public class EspacioAcademico implements Serializable {

	@Id
	private int codigo;
	private String nombre;
	private int creditos;
	@ManyToOne
	private ProgramaAcademico programa;
	@ManyToMany
	private List<Estudiante> estudiantes;
	private static final long serialVersionUID = 1L;

	public EspacioAcademico() {
		super();
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCreditos() {
		return this.creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

}
