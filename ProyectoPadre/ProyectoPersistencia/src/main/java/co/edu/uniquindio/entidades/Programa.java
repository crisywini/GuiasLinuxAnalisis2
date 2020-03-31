package co.edu.uniquindio.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Programa
 *
 */
@Entity

public class Programa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Por defecto lo que hace es crear una tabla sequencia, con la
														// estrategia lo que hace es pasar la responsabilidad a mysql de
														// asignar los valores
	@Column(name = "codigo")
	private Integer codigo;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "descripcion")
	@Lob // Para el tipo de dato más grande
	private String descripcion;
	@Column(name = "version", columnDefinition = "varchar(255) DEFAULT '0.1'") // Para el uso del argumento
																				// columnDefinition se tiene que
																				// escribir en sql puro
	private String version;
	private static final long serialVersionUID = 1L;

	public Programa() {
		super();
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Programa other = (Programa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
