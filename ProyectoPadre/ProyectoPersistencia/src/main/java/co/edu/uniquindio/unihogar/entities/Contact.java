package co.edu.uniquindio.unihogar.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Contact.
 *
 * @author Critian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */

@Entity
@NamedQueries({ @NamedQuery(name = Contact.GET_ALL_CONTACT, query = "SELECT c FROM Contact c"),
		@NamedQuery(name = Contact.GET_CONTACT_BY_CLIENT_CODE, query = "SELECT c FROM Contact c Where c.clientCode.code =:clientCode"),
		@NamedQuery(name = Contact.GET_CONTACT_BY_PROJECT_CODE, query = "SELECT c FROM Contact c Where c.projectCode.code =:projectCode") })

public class Contact implements Serializable {

	/** The code. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code")
	private int code;

	/** The subject. */
	@Column(name = "subject", nullable = false)
	private String subject;

	/** The content. */
	@Column(name = "content", nullable = false)
	private String content;

	/** The date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "date", nullable = false)
	private Date date;

	/** The project code. */
	@ManyToOne
	@JoinColumn(name = "project_code")
	private Project projectCode;

	/** The client code. */
	@ManyToOne
	@JoinColumn(name = "client_code")
	private Client clientCode;

	private static final long serialVersionUID = 1L;

	// Queries
	public static final String GET_ALL_CONTACT = "GET_ALL_CONTACT";
	public static final String GET_CONTACT_BY_CLIENT_CODE = "GET_CONTACT_BY_CLIENT_CODE";
	public static final String GET_CONTACT_BY_PROJECT_CODE = "GET_CONTACT_BY_PROJECT_CODE";

	/**
	 * Instantiates a new contact.
	 */
	public Contact() {
		super();
	}

	/**
	 * Constructor method from Contact.
	 *
	 * @param code    from {@link Contact} primaty key, and generated value
	 * @param subject from {@link Contact} not nullable
	 * @param content from {@link Contact} not nullable
	 */

	public Contact(int code, String subject, String content) {
		super();
		this.code = code;
		this.subject = subject;
		this.content = content;
		this.date = new Date();
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets the project code.
	 *
	 * @return the project code
	 */
	public Project getProjectCode() {
		return projectCode;
	}

	/**
	 * Sets the project code.
	 *
	 * @param projectCode the new project code
	 */
	public void setProjectCode(Project projectCode) {
		this.projectCode = projectCode;
	}

	/**
	 * Gets the client code.
	 *
	 * @return the client code
	 */
	public Client getClientCode() {
		return clientCode;
	}

	/**
	 * Sets the client code.
	 *
	 * @param clientCode the new client code
	 */
	public void setClientCode(Client clientCode) {
		this.clientCode = clientCode;
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
		result = prime * result + code;
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
		Contact other = (Contact) obj;
		if (code != other.code)
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
		return "Contact [code=" + code + ", subject=" + subject + ", content=" + content + ", date=" + date + "]";
	}

}
