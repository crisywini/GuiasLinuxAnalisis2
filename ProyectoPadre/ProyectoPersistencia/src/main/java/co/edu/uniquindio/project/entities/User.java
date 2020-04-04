package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) 
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
	 * @param code     from {@link User} primary key, length max = 10 and not
	 *                 nullable
	 * @param email    from {@link User} unique and not nullable
	 * @param password from {@link User} not nullable
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		User other = (User) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [code=" + code + ", email=" + email + ", password=" + password + "]";
	}
	

}
