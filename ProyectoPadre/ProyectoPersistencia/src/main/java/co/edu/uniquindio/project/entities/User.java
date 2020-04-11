package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User.
 *
 * @author Critian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@NamedQueries({
	@NamedQuery(name = User.AUTHENTICATE_USERS, query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
})
public class User implements Serializable {

	/** The code. */
	@Id
	@Column(name = "code", nullable = false, length = 10)
	private String code;

	/** The email. */
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	/** The password. */
	@Column(name = "password", nullable = false)
	private String password;
	
	//Queries
	public static final String AUTHENTICATE_USERS = "AUTHENTICATE_USERS";

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method.
	 */
	public User() {
		super();
	}

	/**
	 * Constructor method from User.
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

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
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

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "User [code=" + code + ", email=" + email + ", password=" + password + "]";
	}

}
