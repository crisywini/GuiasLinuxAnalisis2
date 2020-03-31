package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comment
 *
 */
@Entity

public class Comment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	private String comment;
	private Date date;
	@ManyToOne
	private Client clientCode;
	@ManyToOne
	private Project projectCode;
	private static final long serialVersionUID = 1L;

	public Comment() {
		super();
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client getClientCode() {
		return clientCode;
	}

	public void setClientCode(Client clientCode) {
		this.clientCode = clientCode;
	}

	public Project getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(Project projectCode) {
		this.projectCode = projectCode;
	}

}
