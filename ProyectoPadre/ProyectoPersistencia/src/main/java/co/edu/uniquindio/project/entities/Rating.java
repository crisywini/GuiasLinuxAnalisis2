package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Rating
 *
 */
@Entity

public class Rating implements Serializable {

	@EmbeddedId
	@JoinColumn(name = "key")
	private RatingPK key;
	@Column(name = "score", nullable = false)
	private int score;
	@ManyToOne
	@MapsId("clientCode")
	@JoinColumn(name = "client_rating")
	private Client clientRating;
	@ManyToOne
	@MapsId("projectCode")
	@JoinColumn(name = "project_rating")
	private Project projectRating;
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor method
	 */
	public Rating() {
		super();
	}

	/**
	 * Constructor method
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

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Client getClientRating() {
		return clientRating;
	}

	public void setClientRating(Client clientRating) {
		this.clientRating = clientRating;
	}

	public Project getProjectRating() {
		return projectRating;
	}

	public void setProjectRating(Project projectRating) {
		this.projectRating = projectRating;
	}

}
