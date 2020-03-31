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
	private RatingPK key;
	private int score;
	@ManyToOne
	@MapsId("clientCode")
	private Client clientRating;
	@ManyToOne
	@MapsId("projectCode")
	private Project projectRating;
	private static final long serialVersionUID = 1L;

	public Rating() {
		super();
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
