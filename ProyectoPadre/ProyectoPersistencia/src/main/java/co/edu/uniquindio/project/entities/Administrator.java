package co.edu.uniquindio.project.entities;

import co.edu.uniquindio.project.entities.User;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrator
 *
 */
@Entity

public class Administrator extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method
	 */
	public Administrator() {
		super();
	}

	/**
	 * Constructor method
	 * 
	 * @param code     from {@link User} primary key and not nullable
	 * @param email    from {@link User} unique and not nullable
	 * @param password from {@link User} not nullable
	 */
	public Administrator(String code, String email, String password) {
		super(code, email, password);
	}

}
