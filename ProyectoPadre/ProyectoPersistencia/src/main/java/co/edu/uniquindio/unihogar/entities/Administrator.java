package co.edu.uniquindio.unihogar.entities;

import java.io.Serializable;
import javax.persistence.*;

import co.edu.uniquindio.unihogar.entities.User;

/**
 * Entity implementation class for Entity: Administrator.
 * 
 * @author Critian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */

@Entity
public class Administrator extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method.
	 */
	public Administrator() {
		super();
	}

	/**
	 * Constructor method from Administrator.
	 *
	 * @param code     from {@link User} primary key and not nullable
	 * @param email    from {@link User} unique and not nullable
	 * @param password from {@link User} not nullable
	 */
	public Administrator(String code, String email, String password) {
		super(code, email, password);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
