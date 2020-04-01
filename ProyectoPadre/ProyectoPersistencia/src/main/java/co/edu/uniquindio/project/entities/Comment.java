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
	@Column(name = "code")
	private int code;

	@Lob
	@Column(name = "comment", nullable = false)
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false)
	private Date date;

	@ManyToOne
	@JoinColumn(name = "client_code")
	private Client clientCode;

	@ManyToOne
	@JoinColumn(name = "project_code")
	private Project projectCode;

	private static final long serialVersionUID = 1L;

	public Comment() {
		super();
	}

	/**
	 * Constructor method from comment
	 * 
	 * @param code        from {@link Comment} primary key, and generated value
	 * @param comment     from {@link Comment} not nullable
	 * @param date        from {@link Comment} not nullable
	 * @param clientCode  from relationship with {@link Client}
	 * @param projectCode from relationship with {@link Project}
	 */

	public Comment(int code, String comment, Date date, Client clientCode, Project projectCode) {
		super();
		this.code = code;
		this.comment = comment;
		this.date = date;
		this.clientCode = clientCode;
		this.projectCode = projectCode;
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
