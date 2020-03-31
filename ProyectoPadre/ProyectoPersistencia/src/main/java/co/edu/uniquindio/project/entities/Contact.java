package co.edu.uniquindio.project.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Contact
 *
 */
@Entity

public class Contact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	private String subject;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
	private Date date;
	@ManyToOne
	private Project projectCode;
	@ManyToOne
	private Client clientCode;
	private static final long serialVersionUID = 1L;

	public Contact() {
		super();
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Project getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(Project projectCode) {
		this.projectCode = projectCode;
	}

	public Client getClientCode() {
		return clientCode;
	}

	public void setClientCode(Client clientCode) {
		this.clientCode = clientCode;
	}

}
