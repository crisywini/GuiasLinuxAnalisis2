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
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "date", nullable = false)
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
	 */

	public Comment(int code, String comment) {
		super();
		this.code = code;
		this.comment = comment;
		this.date = new Date();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
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
		Comment other = (Comment) obj;
		if (code != other.code)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [code=" + code + ", comment=" + comment + ", date=" + date + ", clientCode=" + clientCode
				+ ", projectCode=" + projectCode + "]";
	}
	

}
