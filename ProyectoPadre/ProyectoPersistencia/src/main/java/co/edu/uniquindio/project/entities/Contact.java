
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
	@Column(name = "code")
	private int code;

	@Column(name = "subject", nullable = false)
	private String subject;

	@Column(name = "content", nullable = false)
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "date", nullable = false)
	private Date date;

	@ManyToOne
	@JoinColumn(name = "project_code")
	private Project projectCode;

	@ManyToOne
	@JoinColumn(name = "client_code")
	private Client clientCode;

	private static final long serialVersionUID = 1L;

	public Contact() {
		super();
	}

	/**
	 * 
	 * Constructor method from Contact
	 * 
	 * @param code        from {@link Contact} primaty key, and generated value
	 * @param subject     from {@link Contact} not nullable
	 * @param content     from {@link Contact} not nullable
	 * @param date        from {@link Contact} column definition, and not nullable
	 * @param projectCode from relationship with {@link Project}
	 * @param clientCode  from relationship with {@link Client}
	 */

	public Contact(int code, String subject, String content, Date date, Project projectCode, Client clientCode) {
		super();
		this.code = code;
		this.subject = subject;
		this.content = content;
		this.date = date;
		this.projectCode = projectCode;
		this.clientCode = clientCode;
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
		Contact other = (Contact) obj;
		if (code != other.code)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [code=" + code + ", subject=" + subject + ", content=" + content + ", date=" + date + "]";
	}
	

}
