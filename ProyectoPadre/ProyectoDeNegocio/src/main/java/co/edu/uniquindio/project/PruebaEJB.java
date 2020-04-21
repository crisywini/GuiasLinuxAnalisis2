package co.edu.uniquindio.project;

import java.util.List;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.exceptions.RepeatedUserException;
import co.edu.uniquindio.unihogar.entities.User;

/**
 * Session Bean implementation class PruebaEJB
 */
@Stateless
@LocalBean
public class PruebaEJB implements PruebaEJBRemote {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public PruebaEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void logginUser(String code, String email, String password) throws RepeatedUserException {
		User newUser = new User(code, email, password);
		User query1 = entityManager.find(User.class, code);
		
		if(query1!=null)
			throw new RepeatedUserException("The user with code: " + code+" already exist");
		if(isEmailRepeated(email))
			throw new RepeatedUserException("The user with email: "+email+" already exist");

		entityManager.persist(newUser);
	}
	public boolean isEmailRepeated(String email) {
		TypedQuery<User> query = entityManager.createNamedQuery(User.GET_USER_BY_EMAIL, User.class);
		query.setParameter(":email", email);
		return query.getResultList().size()>0;
	}

	@Override
	public User authenticateUser(String email, String password) throws AuthenticationException{
		TypedQuery<User> query = entityManager.createNamedQuery(User.AUTHENTICATE_USER, User.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		List<User> resultList = query.getResultList();
		if(resultList.isEmpty())
			throw new AuthenticationException("The user(email): "+email+" does not exist");
			
 		return resultList.get(0);
	}

}
