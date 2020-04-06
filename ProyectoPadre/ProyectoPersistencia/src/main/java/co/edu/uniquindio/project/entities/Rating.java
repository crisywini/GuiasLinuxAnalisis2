package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Rating.
 *
 * @author Critian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */

@Entity
public class Rating implements Serializable {

	/** The key. */
	@EmbeddedId
	@JoinColumn(name = "key")
	private RatingPK key;

	/** The score. */
	@Column(name = "score", nullable = false)
	private int score;

	/** The client rating. */
	@ManyToOne
	@MapsId("clientCode")
	@JoinColumn(name = "client_rating")
	private Client clientRating;

	/** The project rating. */
	@ManyToOne
	@MapsId("projectCode")
	@JoinColumn(name = "project_rating")
	private Project projectRating;

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method.
	 */
	public Rating() {
		super();
	}

	/**
	 * Constructor method from Rating.
	 * 
	 * @param key           from {@link RatingPK} primary key from the relationship
	 *                      between {@link Client} and {@link Project}
	 * @param score         from {@link Rating} an integer not nullable
	 * @param clientRating  from relationship between {@link Client} and
	 *                      {@link Project}
	 * @param projectRating from relationship between {@link Client} and
	 *                      {@link Project}
	 */
	public Rating(RatingPK key, int score, Client clientRating, Project projectRating) {
		this.key = key;
		this.score = score;
		this.clientRating = clientRating;
		this.projectRating = projectRating;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Gets the client rating.
	 *
	 * @return the client rating
	 */
	public Client getClientRating() {
		return clientRating;
	}

	/**
	 * Sets the client rating.
	 *
	 * @param clientRating the new client rating
	 */
	public void setClientRating(Client clientRating) {
		this.clientRating = clientRating;
	}

	/**
	 * Gets the project rating.
	 *
	 * @return the project rating
	 */
	public Project getProjectRating() {
		return projectRating;
	}

	/**
	 * Sets the project rating.
	 *
	 * @param projectRating the new project rating
	 */
	public void setProjectRating(Project projectRating) {
		this.projectRating = projectRating;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public RatingPK getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(RatingPK key) {
		this.key = key;
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
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		Rating other = (Rating) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
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
		return "Rating [key=" + key + ", score=" + score + ", clientRating=" + clientRating + ", projectRating="
				+ projectRating + "]";
	}

}
