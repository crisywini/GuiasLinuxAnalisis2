package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // Me preocupa esa advertencia
@MappedSuperclass
public class User implements Serializable {

	@Id
	@Column(name = "code", nullable = false, length = 10)
	private String code;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method
	 */
	public User() {
		super();
	}

	/**
	 * Constructor method
	 * 
	 * @param code     from User primary key
	 * @param email    from User unique
	 * @param password from User
	 */
	public User(String code, String email, String password) {
		super();
		this.code = code;
		this.email = email;
		this.password = password;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
