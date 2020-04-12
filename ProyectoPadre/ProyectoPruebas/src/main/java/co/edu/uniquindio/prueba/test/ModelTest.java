package co.edu.uniquindio.prueba.test;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.uniquindio.project.entities.Administrator;
import co.edu.uniquindio.project.entities.City;
import co.edu.uniquindio.project.entities.Client;
import co.edu.uniquindio.project.entities.Comment;
import co.edu.uniquindio.project.entities.Contact;
import co.edu.uniquindio.project.entities.Dwelling;
import co.edu.uniquindio.project.entities.EstateAgency;
import co.edu.uniquindio.project.entities.Project;
import co.edu.uniquindio.project.entities.Rating;
import co.edu.uniquindio.project.entities.RatingPK;
import co.edu.uniquindio.project.entities.Service;
import co.edu.uniquindio.project.entities.Type;
import co.edu.uniquindio.project.entities.User;

/**
 * Class Model Test.
 *
 * @author Critian G. Sanchez Pineda
 * @author Luisa F. Cotte Sanchez
 */

@RunWith(Arquillian.class)
public class ModelTest {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Creates the test archive.
	 *
	 * @return the archive
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,

				"prueba.war").addPackage(Administrator.class.getPackage()).addAsResource("persistenceForTest.xml",

						"META-INF/persistence.xml")

				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	// --------------------------------Test persist----------------------------- //

	/**
	 * Admin persistence test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.COMMIT)
	@UsingDataSet({ "unihogar.json" })
	public void adminPersistenceTest() {

		Administrator registered;
		Administrator admin = new Administrator("1234", "admin51@administrador.com", "password1");
		entityManager.persist(admin);
		entityManager.flush();

		registered = entityManager.find(Administrator.class, "1234");
		Assert.assertEquals(admin, registered);

		admin = new Administrator("1234567", "admin456@administrador.com", "password89");
		entityManager.persist(admin);
		entityManager.flush();
		registered = entityManager.find(Administrator.class, "1234567");
		Assert.assertEquals(admin, registered);

		admin = new Administrator("123456778", "admin4566@administrador.com", "4password89");
		entityManager.persist(admin);
		entityManager.flush();
		registered = entityManager.find(Administrator.class, "123456778");
		Assert.assertEquals(admin, registered);

		admin = new Administrator("123446567", "89admin456@administrador.com", "p46assword89");
		entityManager.persist(admin);
		entityManager.flush();
		registered = entityManager.find(Administrator.class, "123446567");
		Assert.assertEquals(admin, registered);

		admin = new Administrator("4561234567", "6513admin456@administrador.com", "321password89");
		entityManager.persist(admin);
		entityManager.flush();
		registered = entityManager.find(Administrator.class, "4561234567");
		Assert.assertEquals(admin, registered);

		admin = new Administrator("6719683121", "admin45665135@administrador.com", "password89516153");
		entityManager.persist(admin);
		entityManager.flush();
		registered = entityManager.find(Administrator.class, "6719683121");
		Assert.assertEquals(admin, registered);

	}

	/**
	 * City persistence test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void cityPersistenceTest() {

		City registered;
		City city = new City(22, "Salento");
		entityManager.persist(city);
		entityManager.flush();
		registered = entityManager.find(City.class, 22);
		Assert.assertEquals(city, registered);

		city = new City(23, "Filandia");
		entityManager.persist(city);
		entityManager.flush();
		registered = entityManager.find(City.class, 23);
		Assert.assertEquals(city, registered);

		city = new City(24, "Circasia");
		entityManager.persist(city);
		entityManager.flush();
		registered = entityManager.find(City.class, 24);
		Assert.assertEquals(city, registered);

		city = new City(25, "Bogotá");
		entityManager.persist(city);
		entityManager.flush();
		registered = entityManager.find(City.class, 25);
		Assert.assertEquals(city, registered);

		city = new City(26, "Newyork");
		entityManager.persist(city);
		entityManager.flush();
		registered = entityManager.find(City.class, 26);
		Assert.assertEquals(city, registered);

		city = new City(27, "Medellin");
		entityManager.persist(city);
		entityManager.flush();
		registered = entityManager.find(City.class, 27);
		Assert.assertEquals(city, registered);

	}

	/**
	 * Client persistence test.
	 *
	 * @throws ParseException the parse exception
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void clientPersistenceTest() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date dateOfBirth = sdf.parse("15/11/1999");
		Client client = new Client("123", "client5@mail.com", "password", "Crisi sanchez", "3145658898", dateOfBirth);
		entityManager.persist(client);
		entityManager.flush();
		Client registered = entityManager.find(Client.class, "123");
		Assert.assertEquals(client, registered);

		dateOfBirth = sdf.parse("18/11/1999");
		client = new Client("12343", "client15@mail.com", "password", "Crisi sanchez", "3145658898", dateOfBirth);
		entityManager.persist(client);
		entityManager.flush();
		registered = entityManager.find(Client.class, "12343");
		Assert.assertEquals(client, registered);

		dateOfBirth = sdf.parse("15/12/1999");
		client = new Client("123457", "client589@mail.com", "password", "Crisi sanchez", "3145658898", dateOfBirth);
		entityManager.persist(client);
		entityManager.flush();
		registered = entityManager.find(Client.class, "123457");
		Assert.assertEquals(client, registered);

		dateOfBirth = sdf.parse("15/11/2000");
		client = new Client("123000", "client2000@mail.com", "password", "Crisi sanchez", "3145658898", dateOfBirth);
		entityManager.persist(client);
		entityManager.flush();
		registered = entityManager.find(Client.class, "123000");
		Assert.assertEquals(client, registered);

		dateOfBirth = sdf.parse("18/04/1999");
		client = new Client("12379", "clie45nt5@mail.com", "password", "Crisi sanchez", "3145658898", dateOfBirth);
		entityManager.persist(client);
		entityManager.flush();
		registered = entityManager.find(Client.class, "12379");
		Assert.assertEquals(client, registered);

		dateOfBirth = sdf.parse("15/01/1999");
		client = new Client("1266", "client3213215@mail.com", "password", "Crisi sanchez", "3145658898", dateOfBirth);
		entityManager.persist(client);
		entityManager.flush();
		registered = entityManager.find(Client.class, "1266");
		Assert.assertEquals(client, registered);

		dateOfBirth = sdf.parse("15/04/1999");
		client = new Client("12365654", "client7897895@mail.com", "password", "Crisi sanchez", "3145658898",
				dateOfBirth);
		entityManager.persist(client);
		entityManager.flush();
		registered = entityManager.find(Client.class, "12365654");
		Assert.assertEquals(client, registered);

		dateOfBirth = sdf.parse("20/12/1999");
		client = new Client("12344147", "client85885@mail.com", "password", "Crisi sanchez", "3145658898", dateOfBirth);
		entityManager.persist(client);
		entityManager.flush();
		registered = entityManager.find(Client.class, "12344147");
		Assert.assertEquals(client, registered);
	}

	/**
	 * Comment persistence test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void commentPersistenceTest() {

		Comment comment = new Comment(4, "Holita que prueba tan chevere =) ");
		entityManager.persist(comment);
		entityManager.flush();
		Comment registered = entityManager.find(Comment.class, 4);
		Assert.assertEquals(registered, comment);

		comment = new Comment(5, "Holita que prueba tan chevere =) ");
		entityManager.persist(comment);
		entityManager.flush();
		registered = entityManager.find(Comment.class, 5);
		Assert.assertEquals(registered, comment);

		comment = new Comment(6, "Holita que prueba tan chevere =) ");
		entityManager.persist(comment);
		entityManager.flush();
		registered = entityManager.find(Comment.class, 6);
		Assert.assertEquals(registered, comment);

		comment = new Comment(7, "Holita que prueba tan chevere =) ");
		entityManager.persist(comment);
		entityManager.flush();
		registered = entityManager.find(Comment.class, 7);
		Assert.assertEquals(registered, comment);

		comment = new Comment(8, "Holita que prueba tan chevere =) ");
		entityManager.persist(comment);
		entityManager.flush();
		registered = entityManager.find(Comment.class, 8);
		Assert.assertEquals(registered, comment);

		comment = new Comment(9, "Holita que prueba tan chevere =) ");
		entityManager.persist(comment);
		entityManager.flush();
		registered = entityManager.find(Comment.class, 9);
		Assert.assertEquals(registered, comment);

		comment = new Comment(10, "Holita que prueba tan chevere =) ");
		entityManager.persist(comment);
		entityManager.flush();
		registered = entityManager.find(Comment.class, 10);
		Assert.assertEquals(registered, comment);

		comment = new Comment(11, "Holita que prueba tan chevere =) ");
		entityManager.persist(comment);
		entityManager.flush();
		registered = entityManager.find(Comment.class, 11);
		Assert.assertEquals(registered, comment);

	}

	/**
	 * Contact persistence test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void contactPersistenceTest() {

		Contact contact = new Contact(6, "CONTACTO",
				"Hola quería contactarme con ustedes porque es el mejor proyecto que he visto");
		entityManager.persist(contact);
		entityManager.flush();
		Contact registered = entityManager.find(Contact.class, 6);
		Assert.assertEquals(registered, contact);

		contact = new Contact(7, "CONTACTO",
				"Hola  que mas queria saber de ustedes porque es el mejor proyecto que he visto");
		entityManager.persist(contact);
		entityManager.flush();
		registered = entityManager.find(Contact.class, 7);
		Assert.assertEquals(registered, contact);

		contact = new Contact(8, "CONTACTO",
				"Hola  que mas queria saber de ustedes porque es el mejor proyecto que he visto");
		entityManager.persist(contact);
		entityManager.flush();
		registered = entityManager.find(Contact.class, 8);
		Assert.assertEquals(registered, contact);

		contact = new Contact(9, "CONTACTO",
				"Hola  que mas queria saber de ustedes porque es el mejor proyecto que he visto");
		entityManager.persist(contact);
		entityManager.flush();
		registered = entityManager.find(Contact.class, 9);
		Assert.assertEquals(registered, contact);

		contact = new Contact(10, "CONTACTO",
				"Hola  que mas queria saber de ustedes porque es el mejor proyecto que he visto");
		entityManager.persist(contact);
		entityManager.flush();
		registered = entityManager.find(Contact.class, 10);
		Assert.assertEquals(registered, contact);

		contact = new Contact(11, "CONTACTO",
				"Hola  que mas queria saber de ustedes porque es el mejor proyecto que he visto");
		entityManager.persist(contact);
		entityManager.flush();
		registered = entityManager.find(Contact.class, 11);
		Assert.assertEquals(registered, contact);

		contact = new Contact(12, "CONTACTO",
				"Hola  que mas queria saber de ustedes porque es el mejor proyecto que he visto");
		entityManager.persist(contact);
		entityManager.flush();
		registered = entityManager.find(Contact.class, 12);
		Assert.assertEquals(registered, contact);

		contact = new Contact(13, "CONTACTO",
				"Hola  que mas queria saber de ustedes porque es el mejor proyecto que he visto");
		entityManager.persist(contact);
		entityManager.flush();
		registered = entityManager.find(Contact.class, 13);
		Assert.assertEquals(registered, contact);

		contact = new Contact(14, "CONTACTO",
				"Hola  que mas queria saber de ustedes porque es el mejor proyecto que he visto");
		entityManager.persist(contact);
		entityManager.flush();
		registered = entityManager.find(Contact.class, 14);
		Assert.assertEquals(registered, contact);

	}

	/**
	 * Dwelling persistence test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void dwellingPersistenceTest() {

		Dwelling dwelling = new Dwelling(11, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia",
				4, 2, Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		Dwelling registered = entityManager.find(Dwelling.class, 11);
		Assert.assertEquals(dwelling, registered);

		dwelling = new Dwelling(12, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia", 4, 2,
				Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		registered = entityManager.find(Dwelling.class, 12);
		Assert.assertEquals(dwelling, registered);

		dwelling = new Dwelling(13, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia", 4, 2,
				Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		registered = entityManager.find(Dwelling.class, 13);
		Assert.assertEquals(dwelling, registered);

		dwelling = new Dwelling(14, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia", 4, 2,
				Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		registered = entityManager.find(Dwelling.class, 14);
		Assert.assertEquals(dwelling, registered);

		dwelling = new Dwelling(15, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia", 4, 2,
				Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		registered = entityManager.find(Dwelling.class, 15);
		Assert.assertEquals(dwelling, registered);

		dwelling = new Dwelling(16, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia", 4, 2,
				Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		registered = entityManager.find(Dwelling.class, 16);
		Assert.assertEquals(dwelling, registered);

		dwelling = new Dwelling(17, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia", 4, 2,
				Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		registered = entityManager.find(Dwelling.class, 17);
		Assert.assertEquals(dwelling, registered);

		dwelling = new Dwelling(18, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia", 4, 2,
				Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		registered = entityManager.find(Dwelling.class, 18);
		Assert.assertEquals(dwelling, registered);

		dwelling = new Dwelling(19, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia", 4, 2,
				Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		registered = entityManager.find(Dwelling.class, 19);
		Assert.assertEquals(dwelling, registered);

	}

	/**
	 * Estate agency persistence test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void estateAgencyPersistenceTest() {

		EstateAgency registered;

		EstateAgency estateAgency = new EstateAgency("Banderas verdes", "121", "imb11@inmobiliaria.com", "password1",
				"Calle 1 # 2-30");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "121");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Amarillas", "122", "imb22@inmobiliaria.com", "password2",
				"calle 2 # 3 - 40");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "122");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Azules", "123", "imb365@inmobiliaria.com", "password3",
				"calle 3 # 4 - 50");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "123");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Rojas", "124", "imb478@inmobiliaria.com", "password4",
				"calle 4 # 5 - 60");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "124");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Grises", "125", "imb975@inmobiliaria.com", "password5",
				"calle 5 # 6 - 70");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "125");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Negras", "126", "imb63256@inmobiliaria.com", "password6",
				"calle 6 # 7 - 80");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "126");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Blancas", "127", "imb321327@inmobiliaria.com", "password7",
				"calle 7 # 8 - 90");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "127");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Rosas", "128", "imb6548@inmobiliaria.com", "password8",
				"calle 8 # 9 - 10");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "128");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Oscuras", "129", "imb9132165@inmobiliaria.com", "password9",
				"calle 9 # 10 - 20");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "129");
		Assert.assertEquals(estateAgency, registered);

	}

	/**
	 * Project persistence test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void projectPersistenceTest() {

		Project registered;

		Project project = new Project(120, "Proyecto Luna", 1.123, 1.345, "El mejor proyecto de todos");
		entityManager.persist(project);
		entityManager.flush();
		registered = entityManager.find(Project.class, 120);
		Assert.assertEquals(project, registered);

		project = new Project(121, "Proyecto Sol", 1.934, 1.183, "El mejor proyecto de todos");
		entityManager.persist(project);
		entityManager.flush();
		registered = entityManager.find(Project.class, 121);
		Assert.assertEquals(project, registered);

		project = new Project(122, "Proyecto Mercurio", 1.9823, 1.984, "El mejor proyecto de todos");
		entityManager.persist(project);
		entityManager.flush();
		registered = entityManager.find(Project.class, 122);
		Assert.assertEquals(project, registered);

		project = new Project(123, "Proyecto Venus", 1.2983, 1.345, "El mejor proyecto de todos");
		entityManager.persist(project);
		entityManager.flush();
		registered = entityManager.find(Project.class, 123);
		Assert.assertEquals(project, registered);

		project = new Project(124, "Proyecto Tierra", 1.723, 1.34545, "El mejor proyecto de todos");
		entityManager.persist(project);
		entityManager.flush();
		registered = entityManager.find(Project.class, 124);
		Assert.assertEquals(project, registered);

		project = new Project(125, "Proyecto Marte", 1.3454, 1.246, "El mejor proyecto de todos");
		entityManager.persist(project);
		entityManager.flush();
		registered = entityManager.find(Project.class, 125);
		Assert.assertEquals(project, registered);

		project = new Project(126, "Proyecto Jupiter", 1.345, 1.1435, "El mejor proyecto de todos");
		entityManager.persist(project);
		entityManager.flush();
		registered = entityManager.find(Project.class, 126);
		Assert.assertEquals(project, registered);

		project = new Project(127, "Proyecto Saturno", 1.0845, 1.2446, "El mejor proyecto de todos");
		entityManager.persist(project);
		entityManager.flush();
		registered = entityManager.find(Project.class, 127);
		Assert.assertEquals(project, registered);

		project = new Project(128, "Proyecto Urano", 1.10937, 1.294, "El mejor proyecto de todos");
		entityManager.persist(project);
		entityManager.flush();
		registered = entityManager.find(Project.class, 128);
		Assert.assertEquals(project, registered);

		project = new Project(129, "Proyecto Naptuno", 1.09745, 1.962, "El mejor proyecto de todos");
		entityManager.persist(project);
		entityManager.flush();
		registered = entityManager.find(Project.class, 129);
		Assert.assertEquals(project, registered);

	}

	/**
	 * Rating persistence test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ratingPersistenceTest() {

		Rating registered;

		Client client = entityManager.find(Client.class, "1");
		Project project = entityManager.find(Project.class, 2);
		RatingPK key = new RatingPK("1", 2);
		Rating rating = new Rating(key, 152, client, project);
		entityManager.persist(rating);
		entityManager.flush();
		registered = entityManager.find(Rating.class, key);
		Assert.assertEquals(rating, registered);

		client = entityManager.find(Client.class, "3");
		project = entityManager.find(Project.class, 2);
		key = new RatingPK("3", 2);
		rating = new Rating(key, 159, client, project);
		entityManager.persist(rating);
		entityManager.flush();
		registered = entityManager.find(Rating.class, key);
		Assert.assertEquals(rating, registered);

		client = entityManager.find(Client.class, "2");
		project = entityManager.find(Project.class, 4);
		key = new RatingPK("2", 4);
		rating = new Rating(key, 153, client, project);
		entityManager.persist(rating);
		entityManager.flush();
		registered = entityManager.find(Rating.class, key);
		Assert.assertEquals(rating, registered);

		client = entityManager.find(Client.class, "1");
		project = entityManager.find(Project.class, 5);
		key = new RatingPK("1", 5);
		rating = new Rating(key, 157, client, project);
		entityManager.persist(rating);
		entityManager.flush();
		registered = entityManager.find(Rating.class, key);
		Assert.assertEquals(rating, registered);

		client = entityManager.find(Client.class, "2");
		project = entityManager.find(Project.class, 6);
		key = new RatingPK("2", 6);
		rating = new Rating(key, 150, client, project);
		entityManager.persist(rating);
		entityManager.flush();
		registered = entityManager.find(Rating.class, key);
		Assert.assertEquals(rating, registered);

		client = entityManager.find(Client.class, "3");
		project = entityManager.find(Project.class, 7);
		key = new RatingPK("3", 7);
		rating = new Rating(key, 150, client, project);
		entityManager.persist(rating);
		entityManager.flush();
		registered = entityManager.find(Rating.class, key);
		Assert.assertEquals(rating, registered);

	}

	/**
	 * Service persistence test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void servicePersistenceTest() {

		Service registered;

		Service service = new Service(11, "Piscina para bebes");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 11);
		Assert.assertEquals(service, registered);

		service = new Service(12, "Gimnasio al aire libre");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 12);
		Assert.assertEquals(service, registered);

		service = new Service(13, "clases de yoga");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 13);
		Assert.assertEquals(service, registered);

		service = new Service(14, "Tienda de licores");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 14);
		Assert.assertEquals(service, registered);

		service = new Service(15, "Parroquia");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 15);
		Assert.assertEquals(service, registered);

		service = new Service(16, "Dulceria");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 16);
		Assert.assertEquals(service, registered);

		service = new Service(17, "Zona Camping");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 17);
		Assert.assertEquals(service, registered);

		service = new Service(18, "Zapateria");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 18);
		Assert.assertEquals(service, registered);

		service = new Service(19, "Carniceria");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 19);
		Assert.assertEquals(service, registered);

		service = new Service(20, "Fruteria");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 20);
		Assert.assertEquals(service, registered);

	}

	// --------------------------------Test remove---------------------------- //

	/**
	 * Admin remove test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.COMMIT)
	@UsingDataSet({ "unihogar.json" })
	public void adminRemoveTest() {

		Administrator admin = entityManager.find(Administrator.class, "123");
		entityManager.remove(admin);
		Administrator registered = entityManager.find(Administrator.class, "123");
		Assert.assertNull(registered);

		admin = entityManager.find(Administrator.class, "1244567801");
		entityManager.remove(admin);
		registered = entityManager.find(Administrator.class, "1244567801");
		Assert.assertNull(registered);

		admin = entityManager.find(Administrator.class, "1254567801");
		entityManager.remove(admin);
		registered = entityManager.find(Administrator.class, "1254567801");
		Assert.assertNull(registered);

	}

	/**
	 * City remove test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void cityRemoveTest() {

		City city = entityManager.find(City.class, 1);
		entityManager.remove(city);
		City registered = entityManager.find(City.class, 1);
		Assert.assertNull(registered);

		city = entityManager.find(City.class, 2);
		entityManager.remove(city);
		registered = entityManager.find(City.class, 2);
		Assert.assertNull(registered);

		city = entityManager.find(City.class, 3);
		entityManager.remove(city);
		registered = entityManager.find(City.class, 3);
		Assert.assertNull(registered);

		city = entityManager.find(City.class, 4);
		entityManager.remove(city);
		registered = entityManager.find(City.class, 4);
		Assert.assertNull(registered);

		city = entityManager.find(City.class, 5);
		entityManager.remove(city);
		registered = entityManager.find(City.class, 5);
		Assert.assertNull(registered);

		city = entityManager.find(City.class, 6);
		entityManager.remove(city);
		registered = entityManager.find(City.class, 6);
		Assert.assertNull(registered);

		city = entityManager.find(City.class, 7);
		entityManager.remove(city);
		registered = entityManager.find(City.class, 7);
		Assert.assertNull(registered);

		city = entityManager.find(City.class, 8);
		entityManager.remove(city);
		registered = entityManager.find(City.class, 8);
		Assert.assertNull(registered);

		city = entityManager.find(City.class, 9);
		entityManager.remove(city);
		registered = entityManager.find(City.class, 9);
		Assert.assertNull(registered);

		city = entityManager.find(City.class, 10);
		entityManager.remove(city);
		registered = entityManager.find(City.class, 10);
		Assert.assertNull(registered);

		city = entityManager.find(City.class, 11);
		entityManager.remove(city);
		registered = entityManager.find(City.class, 11);
		Assert.assertNull(registered);

		city = entityManager.find(City.class, 12);
		entityManager.remove(city);
		registered = entityManager.find(City.class, 12);
		Assert.assertNull(registered);

		city = entityManager.find(City.class, 13);
		entityManager.remove(city);
		registered = entityManager.find(City.class, 13);
		Assert.assertNull(registered);

	}

	/**
	 * Client remove test.
	 *
	 * @throws ParseException the parse exception
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void clientRemoveTest() throws ParseException {

		Client client = entityManager.find(Client.class, "1");
		entityManager.remove(client);
		Client registered = entityManager.find(Client.class, "1");
		Assert.assertNull(registered);

		client = entityManager.find(Client.class, "2");
		entityManager.remove(client);
		registered = entityManager.find(Client.class, "2");
		Assert.assertNull(registered);

		client = entityManager.find(Client.class, "3");
		entityManager.remove(client);
		registered = entityManager.find(Client.class, "3");
		Assert.assertNull(registered);

	}

	/**
	 * Comment remove test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void commentRemoveTest() {

		Comment comment = entityManager.find(Comment.class, 1);
		entityManager.remove(comment);
		Comment registered = entityManager.find(Comment.class, 1);
		Assert.assertNull(registered);

		comment = entityManager.find(Comment.class, 2);
		entityManager.remove(comment);
		registered = entityManager.find(Comment.class, 2);
		Assert.assertNull(registered);

		comment = entityManager.find(Comment.class, 3);
		entityManager.remove(comment);
		registered = entityManager.find(Comment.class, 3);
		Assert.assertNull(registered);

		comment = entityManager.find(Comment.class, 4);
		entityManager.remove(comment);
		registered = entityManager.find(Comment.class, 4);
		Assert.assertNull(registered);

		comment = entityManager.find(Comment.class, 5);
		entityManager.remove(comment);
		registered = entityManager.find(Comment.class, 5);
		Assert.assertNull(registered);

		comment = entityManager.find(Comment.class, 6);
		entityManager.remove(comment);
		registered = entityManager.find(Comment.class, 6);
		Assert.assertNull(registered);

		comment = entityManager.find(Comment.class, 7);
		entityManager.remove(comment);
		registered = entityManager.find(Comment.class, 7);
		Assert.assertNull(registered);

		comment = entityManager.find(Comment.class, 8);
		entityManager.remove(comment);
		registered = entityManager.find(Comment.class, 8);
		Assert.assertNull(registered);

		comment = entityManager.find(Comment.class, 9);
		entityManager.remove(comment);
		registered = entityManager.find(Comment.class, 9);
		Assert.assertNull(registered);

		comment = entityManager.find(Comment.class, 10);
		entityManager.remove(comment);
		registered = entityManager.find(Comment.class, 10);
		Assert.assertNull(registered);

	}

	/**
	 * Contact remove test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void contactRemoveTest() {

		Contact contact = entityManager.find(Contact.class, 1);
		entityManager.remove(contact);
		Contact registered = entityManager.find(Contact.class, 1);
		Assert.assertNull(registered);

		contact = entityManager.find(Contact.class, 2);
		entityManager.remove(contact);
		registered = entityManager.find(Contact.class, 2);
		Assert.assertNull(registered);

		contact = entityManager.find(Contact.class, 3);
		entityManager.remove(contact);
		registered = entityManager.find(Contact.class, 3);
		Assert.assertNull(registered);

		contact = entityManager.find(Contact.class, 4);
		entityManager.remove(contact);
		registered = entityManager.find(Contact.class, 4);
		Assert.assertNull(registered);

		contact = entityManager.find(Contact.class, 5);
		entityManager.remove(contact);
		registered = entityManager.find(Contact.class, 5);
		Assert.assertNull(registered);

		contact = entityManager.find(Contact.class, 6);
		entityManager.remove(contact);
		registered = entityManager.find(Contact.class, 6);
		Assert.assertNull(registered);

		contact = entityManager.find(Contact.class, 7);
		entityManager.remove(contact);
		registered = entityManager.find(Contact.class, 7);
		Assert.assertNull(registered);

		contact = entityManager.find(Contact.class, 8);
		entityManager.remove(contact);
		registered = entityManager.find(Contact.class, 8);
		Assert.assertNull(registered);

		contact = entityManager.find(Contact.class, 9);
		entityManager.remove(contact);
		registered = entityManager.find(Contact.class, 9);
		Assert.assertNull(registered);

		contact = entityManager.find(Contact.class, 10);
		entityManager.remove(contact);
		registered = entityManager.find(Contact.class, 10);
		Assert.assertNull(registered);

	}

	/**
	 * Dwelling remove test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void dwellingRemoveTest() {

		Dwelling dwelling = entityManager.find(Dwelling.class, 1);
		entityManager.remove(dwelling);
		Dwelling registered = entityManager.find(Dwelling.class, 1);
		Assert.assertNull(registered);

		dwelling = entityManager.find(Dwelling.class, 2);
		entityManager.remove(dwelling);
		registered = entityManager.find(Dwelling.class, 2);
		Assert.assertNull(registered);

		dwelling = entityManager.find(Dwelling.class, 3);
		entityManager.remove(dwelling);
		registered = entityManager.find(Dwelling.class, 3);
		Assert.assertNull(registered);

		dwelling = entityManager.find(Dwelling.class, 4);
		entityManager.remove(dwelling);
		registered = entityManager.find(Dwelling.class, 4);
		Assert.assertNull(registered);

		dwelling = entityManager.find(Dwelling.class, 5);
		entityManager.remove(dwelling);
		registered = entityManager.find(Dwelling.class, 5);
		Assert.assertNull(registered);

		dwelling = entityManager.find(Dwelling.class, 6);
		entityManager.remove(dwelling);
		registered = entityManager.find(Dwelling.class, 6);
		Assert.assertNull(registered);

		dwelling = entityManager.find(Dwelling.class, 7);
		entityManager.remove(dwelling);
		registered = entityManager.find(Dwelling.class, 7);
		Assert.assertNull(registered);

		dwelling = entityManager.find(Dwelling.class, 8);
		entityManager.remove(dwelling);
		registered = entityManager.find(Dwelling.class, 8);
		Assert.assertNull(registered);

		dwelling = entityManager.find(Dwelling.class, 9);
		entityManager.remove(dwelling);
		registered = entityManager.find(Dwelling.class, 9);
		Assert.assertNull(registered);

		dwelling = entityManager.find(Dwelling.class, 10);
		entityManager.remove(dwelling);
		registered = entityManager.find(Dwelling.class, 10);
		Assert.assertNull(registered);

	}

	/**
	 * Estate agency remove test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void estateAgencyRemoveTest() {

		EstateAgency estateAgency = entityManager.find(EstateAgency.class, "IMB1");
		entityManager.remove(estateAgency);
		EstateAgency registered = entityManager.find(EstateAgency.class, "IMB1");
		Assert.assertNull(registered);

		estateAgency = entityManager.find(EstateAgency.class, "IMB3");
		entityManager.remove(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB3");
		Assert.assertNull(registered);

		estateAgency = entityManager.find(EstateAgency.class, "IMB4");
		entityManager.remove(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB4");
		Assert.assertNull(registered);

		estateAgency = entityManager.find(EstateAgency.class, "IMB5");
		entityManager.remove(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB5");
		Assert.assertNull(registered);

		estateAgency = entityManager.find(EstateAgency.class, "IMB6");
		entityManager.remove(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB6");
		Assert.assertNull(registered);

		estateAgency = entityManager.find(EstateAgency.class, "IMB7");
		entityManager.remove(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB7");
		Assert.assertNull(registered);

		estateAgency = entityManager.find(EstateAgency.class, "IMB8");
		entityManager.remove(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB8");
		Assert.assertNull(registered);

		estateAgency = entityManager.find(EstateAgency.class, "IMB9");
		entityManager.remove(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB9");
		Assert.assertNull(registered);

		estateAgency = entityManager.find(EstateAgency.class, "IMB10");
		entityManager.remove(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB10");
		Assert.assertNull(registered);

	}

	/**
	 * Project remove test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void projectRemoveTest() {

		Project project = entityManager.find(Project.class, 1);
		entityManager.remove(project);
		Project registered = entityManager.find(Project.class, 1);
		Assert.assertNull(registered);

		project = entityManager.find(Project.class, 3);
		entityManager.remove(project);
		registered = entityManager.find(Project.class, 3);
		Assert.assertNull(registered);

		project = entityManager.find(Project.class, 4);
		entityManager.remove(project);
		registered = entityManager.find(Project.class, 4);
		Assert.assertNull(registered);

		project = entityManager.find(Project.class, 5);
		entityManager.remove(project);
		registered = entityManager.find(Project.class, 5);
		Assert.assertNull(registered);

		project = entityManager.find(Project.class, 6);
		entityManager.remove(project);
		registered = entityManager.find(Project.class, 6);
		Assert.assertNull(registered);

		project = entityManager.find(Project.class, 7);
		entityManager.remove(project);
		registered = entityManager.find(Project.class, 7);
		Assert.assertNull(registered);

		project = entityManager.find(Project.class, 8);
		entityManager.remove(project);
		registered = entityManager.find(Project.class, 8);
		Assert.assertNull(registered);

		project = entityManager.find(Project.class, 9);
		entityManager.remove(project);
		registered = entityManager.find(Project.class, 9);
		Assert.assertNull(registered);

		project = entityManager.find(Project.class, 10);
		entityManager.remove(project);
		registered = entityManager.find(Project.class, 10);
		Assert.assertNull(registered);

	}

	/**
	 * Rating remove test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ratingRemoveTest() {

		RatingPK key = new RatingPK("1", 1);
		Rating rating = entityManager.find(Rating.class, key);
		entityManager.remove(rating);
		Rating registered = entityManager.find(Rating.class, key);
		Assert.assertNull(registered);

		key = new RatingPK("2", 2);
		rating = entityManager.find(Rating.class, key);
		entityManager.remove(rating);
		registered = entityManager.find(Rating.class, key);
		Assert.assertNull(registered);

		key = new RatingPK("3", 3);
		rating = entityManager.find(Rating.class, key);
		entityManager.remove(rating);
		registered = entityManager.find(Rating.class, key);
		Assert.assertNull(registered);

	}

	/**
	 * Service remove test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void serviceRemoveTest() {

		Service service = entityManager.find(Service.class, 1);
		entityManager.remove(service);
		Service registered = entityManager.find(Service.class, 1);
		Assert.assertNull(registered);

		service = entityManager.find(Service.class, 3);
		entityManager.remove(service);
		registered = entityManager.find(Service.class, 3);
		Assert.assertNull(registered);

		service = entityManager.find(Service.class, 4);
		entityManager.remove(service);
		registered = entityManager.find(Service.class, 4);
		Assert.assertNull(registered);

		service = entityManager.find(Service.class, 5);
		entityManager.remove(service);
		registered = entityManager.find(Service.class, 5);
		Assert.assertNull(registered);

		service = entityManager.find(Service.class, 6);
		entityManager.remove(service);
		registered = entityManager.find(Service.class, 6);
		Assert.assertNull(registered);

		service = entityManager.find(Service.class, 7);
		entityManager.remove(service);
		registered = entityManager.find(Service.class, 7);
		Assert.assertNull(registered);

		service = entityManager.find(Service.class, 8);
		entityManager.remove(service);
		registered = entityManager.find(Service.class, 8);
		Assert.assertNull(registered);

		service = entityManager.find(Service.class, 9);
		entityManager.remove(service);
		registered = entityManager.find(Service.class, 9);
		Assert.assertNull(registered);

		service = entityManager.find(Service.class, 10);
		entityManager.remove(service);
		registered = entityManager.find(Service.class, 10);
		Assert.assertNull(registered);

	}

	// ------------------------------Test merge--------------------------- //

	/**
	 * Admin merge test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.COMMIT)
	@UsingDataSet({ "unihogar.json" })
	public void adminMergeTest() {

		Administrator admin = entityManager.find(Administrator.class, "123");
		admin.setEmail("newAdmin1@mail.com");
		entityManager.merge(admin);
		Administrator registered = entityManager.find(Administrator.class, "123");
		String mailRegistered = registered.getEmail();
		Assert.assertEquals(mailRegistered, admin.getEmail());

		admin = entityManager.find(Administrator.class, "1244567801");
		admin.setEmail("newAdmin11@mail.com");
		entityManager.merge(admin);
		registered = entityManager.find(Administrator.class, "1244567801");
		mailRegistered = registered.getEmail();
		Assert.assertEquals(mailRegistered, admin.getEmail());

		admin = entityManager.find(Administrator.class, "1254567801");
		admin.setEmail("newAdmin8@mail.com");
		admin.setPassword("123123");
		entityManager.merge(admin);
		registered = entityManager.find(Administrator.class, "1254567801");
		mailRegistered = registered.getEmail();
		Assert.assertEquals(mailRegistered, admin.getEmail());
		Assert.assertEquals(registered.getPassword(), admin.getPassword());

	}

	/**
	 * City merge test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void cityMergeTest() {

		City city = entityManager.find(City.class, 1);
		city.setName("Sincelejo");
		entityManager.merge(city);
		City registered = entityManager.find(City.class, 1);
		String nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

		city = entityManager.find(City.class, 2);
		city.setName("Inglaterra");
		entityManager.merge(city);
		registered = entityManager.find(City.class, 2);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

		city = entityManager.find(City.class, 3);
		city.setName("Armenia");
		entityManager.merge(city);
		registered = entityManager.find(City.class, 3);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

		city = entityManager.find(City.class, 4);
		city.setName("Bogota");
		entityManager.merge(city);
		registered = entityManager.find(City.class, 4);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

		city = entityManager.find(City.class, 5);
		city.setName("Pereira");
		entityManager.merge(city);
		registered = entityManager.find(City.class, 5);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

		city = entityManager.find(City.class, 6);
		city.setName("Medellin");
		entityManager.merge(city);
		registered = entityManager.find(City.class, 6);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

		city = entityManager.find(City.class, 7);
		city.setName("Cali");
		entityManager.merge(city);
		registered = entityManager.find(City.class, 7);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

		city = entityManager.find(City.class, 8);
		city.setName("Suiza");
		entityManager.merge(city);
		registered = entityManager.find(City.class, 8);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

		city = entityManager.find(City.class, 9);
		city.setName("Canada");
		entityManager.merge(city);
		registered = entityManager.find(City.class, 9);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

		city = entityManager.find(City.class, 10);
		city.setName("Neiva");
		entityManager.merge(city);
		registered = entityManager.find(City.class, 10);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

		city = entityManager.find(City.class, 11);
		city.setName("Sincelejo");
		entityManager.merge(city);
		registered = entityManager.find(City.class, 11);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

		city = entityManager.find(City.class, 12);
		city.setName("Cartagena");
		entityManager.merge(city);
		registered = entityManager.find(City.class, 12);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

		city = entityManager.find(City.class, 13);
		city.setName("Tumaco");
		entityManager.merge(city);
		registered = entityManager.find(City.class, 13);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, city.getName());

	}

	/**
	 * Client merge test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void clientMergeTest() {

		Client client = entityManager.find(Client.class, "1");
		client.setPassword("yanoespassword.com");
		entityManager.merge(client);
		Client registered = entityManager.find(Client.class, "1");
		String emailRegistered = registered.getPassword();
		Assert.assertEquals(emailRegistered, client.getPassword());

		client = entityManager.find(Client.class, "2");
		client.setPassword("yanoespassword2.com");
		entityManager.merge(client);
		registered = entityManager.find(Client.class, "2");
		emailRegistered = registered.getPassword();
		Assert.assertEquals(emailRegistered, client.getPassword());

		client = entityManager.find(Client.class, "3");
		client.setEmail("nuevoemail@mail.com");
		entityManager.merge(client);
		registered = entityManager.find(Client.class, "3");
		emailRegistered = registered.getEmail();
		Assert.assertEquals(emailRegistered, client.getEmail());

	}

	/**
	 * Comment merge test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void commentMergeTest() {

		Comment comment = entityManager.find(Comment.class, 1);
		comment.setComment("This is the first best comment but in english");
		entityManager.merge(comment);
		Comment registered = entityManager.find(Comment.class, 1);
		String commentRegistered = registered.getComment();
		Assert.assertEquals(commentRegistered, comment.getComment());

		comment = entityManager.find(Comment.class, 2);
		comment.setComment("This is the second best comment but in english");
		entityManager.merge(comment);
		registered = entityManager.find(Comment.class, 2);
		commentRegistered = registered.getComment();
		Assert.assertEquals(commentRegistered, comment.getComment());

		comment = entityManager.find(Comment.class, 3);
		comment.setComment("This is the second best comment but in english");
		entityManager.merge(comment);
		registered = entityManager.find(Comment.class, 3);
		commentRegistered = registered.getComment();
		Assert.assertEquals(commentRegistered, comment.getComment());

		comment = entityManager.find(Comment.class, 4);
		comment.setComment("This is the fourth best comment but in english");
		entityManager.merge(comment);
		registered = entityManager.find(Comment.class, 4);
		commentRegistered = registered.getComment();
		Assert.assertEquals(commentRegistered, comment.getComment());

		comment = entityManager.find(Comment.class, 5);
		comment.setComment("This is the fifth best comment but in english");
		entityManager.merge(comment);
		registered = entityManager.find(Comment.class, 5);
		commentRegistered = registered.getComment();
		Assert.assertEquals(commentRegistered, comment.getComment());

		comment = entityManager.find(Comment.class, 6);
		comment.setComment("This is the sixth best comment but in english");
		entityManager.merge(comment);
		registered = entityManager.find(Comment.class, 6);
		commentRegistered = registered.getComment();
		Assert.assertEquals(commentRegistered, comment.getComment());

		comment = entityManager.find(Comment.class, 7);
		comment.setComment("This is the seventh best comment but in english");
		entityManager.merge(comment);
		registered = entityManager.find(Comment.class, 7);
		commentRegistered = registered.getComment();
		Assert.assertEquals(commentRegistered, comment.getComment());

		comment = entityManager.find(Comment.class, 8);
		comment.setComment("This is the eigth best comment but in english");
		entityManager.merge(comment);
		registered = entityManager.find(Comment.class, 8);
		commentRegistered = registered.getComment();
		Assert.assertEquals(commentRegistered, comment.getComment());

		comment = entityManager.find(Comment.class, 9);
		comment.setComment("This is the nine best comment but in english");
		entityManager.merge(comment);
		registered = entityManager.find(Comment.class, 9);
		commentRegistered = registered.getComment();
		Assert.assertEquals(commentRegistered, comment.getComment());

		comment = entityManager.find(Comment.class, 10);
		comment.setComment("This is the ten best comment but in english");
		entityManager.merge(comment);
		registered = entityManager.find(Comment.class, 10);
		commentRegistered = registered.getComment();
		Assert.assertEquals(commentRegistered, comment.getComment());

	}

	/**
	 * Contact merge test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void contactMergeTest() {

		Contact contact = entityManager.find(Contact.class, 1);
		contact.setContent("Este contacto es para saber del proyecto");
		entityManager.merge(contact);
		Contact registered = entityManager.find(Contact.class, 1);
		String contentRegistered = registered.getContent();
		Assert.assertEquals(contentRegistered, contact.getContent());

		contact = entityManager.find(Contact.class, 2);
		contact.setContent("Este contacto es para saber del creador del proyecto");
		entityManager.merge(contact);
		registered = entityManager.find(Contact.class, 2);
		contentRegistered = registered.getContent();
		Assert.assertEquals(contentRegistered, contact.getContent());

		contact = entityManager.find(Contact.class, 3);
		contact.setContent("Este contacto es para saber quien hizo proyecto");
		entityManager.merge(contact);
		registered = entityManager.find(Contact.class, 3);
		contentRegistered = registered.getContent();
		Assert.assertEquals(contentRegistered, contact.getContent());

		contact = entityManager.find(Contact.class, 4);
		contact.setContent("Este contacto es para saber por qué el proyecto es tan bueno");
		entityManager.merge(contact);
		registered = entityManager.find(Contact.class, 4);
		contentRegistered = registered.getContent();
		Assert.assertEquals(contentRegistered, contact.getContent());

		contact = entityManager.find(Contact.class, 5);
		contact.setContent("Este contacto es para conocer del proyecto");
		entityManager.merge(contact);
		registered = entityManager.find(Contact.class, 5);
		contentRegistered = registered.getContent();
		Assert.assertEquals(contentRegistered, contact.getContent());

		contact = entityManager.find(Contact.class, 6);
		contact.setContent("Este contacto es para identificar el origen proyecto");
		entityManager.merge(contact);
		registered = entityManager.find(Contact.class, 6);
		contentRegistered = registered.getContent();
		Assert.assertEquals(contentRegistered, contact.getContent());

		contact = entityManager.find(Contact.class, 7);
		contact.setContent("Este contacto es para saber que hace el proyecto");
		entityManager.merge(contact);
		registered = entityManager.find(Contact.class, 7);
		contentRegistered = registered.getContent();
		Assert.assertEquals(contentRegistered, contact.getContent());

		contact = entityManager.find(Contact.class, 8);
		contact.setContent("Este contacto es para saber del proyecto de analisis");
		entityManager.merge(contact);
		registered = entityManager.find(Contact.class, 8);
		contentRegistered = registered.getContent();
		Assert.assertEquals(contentRegistered, contact.getContent());

		contact = entityManager.find(Contact.class, 9);
		contact.setContent("Este contacto es para conocer algo del proyecto");
		entityManager.merge(contact);
		registered = entityManager.find(Contact.class, 9);
		contentRegistered = registered.getContent();
		Assert.assertEquals(contentRegistered, contact.getContent());

		contact = entityManager.find(Contact.class, 10);
		contact.setContent("Este contacto es para saber si el proyecto puede mandar correos");
		entityManager.merge(contact);
		registered = entityManager.find(Contact.class, 10);
		contentRegistered = registered.getContent();
		Assert.assertEquals(contentRegistered, contact.getContent());

	}

	/**
	 * Dwelling merge test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void dwellingMergeTest() {

		Dwelling dwelling = entityManager.find(Dwelling.class, 1);
		dwelling.setDescription("Empty description");
		entityManager.merge(dwelling);
		String descString = dwelling.getDescription();
		Dwelling registered = entityManager.find(Dwelling.class, 1);
		String descriptionRegistered = registered.getDescription();
		Assert.assertEquals(descriptionRegistered, descString);

		dwelling = entityManager.find(Dwelling.class, 2);
		dwelling.setDescription("full description");
		entityManager.merge(dwelling);
		registered = entityManager.find(Dwelling.class, 2);
		descriptionRegistered = registered.getDescription();
		Assert.assertEquals(descriptionRegistered, dwelling.getDescription());

		dwelling = entityManager.find(Dwelling.class, 3);
		dwelling.setDescription("Almost Empty description");
		String descDwelling = dwelling.getDescription();
		entityManager.merge(dwelling);
		registered = entityManager.find(Dwelling.class, 3);
		descriptionRegistered = registered.getDescription();
		Assert.assertEquals("Error: " + descDwelling + " /n" + descriptionRegistered, descriptionRegistered,
				descDwelling);

		dwelling = entityManager.find(Dwelling.class, 4);
		dwelling.setDescription("good description!!");
		entityManager.merge(dwelling);
		registered = entityManager.find(Dwelling.class, 4);
		descriptionRegistered = registered.getDescription();
		Assert.assertEquals(descriptionRegistered, dwelling.getDescription());

		dwelling = entityManager.find(Dwelling.class, 5);
		dwelling.setDescription("Empty but good description");
		entityManager.merge(dwelling);
		registered = entityManager.find(Dwelling.class, 5);
		descriptionRegistered = registered.getDescription();
		Assert.assertEquals(descriptionRegistered, dwelling.getDescription());

		dwelling = entityManager.find(Dwelling.class, 6);
		dwelling.setDescription("Empty solar description");
		entityManager.merge(dwelling);
		registered = entityManager.find(Dwelling.class, 6);
		descriptionRegistered = registered.getDescription();
		Assert.assertEquals(descriptionRegistered, dwelling.getDescription());

		dwelling = entityManager.find(Dwelling.class, 7);
		dwelling.setDescription("Empty house description");
		entityManager.merge(dwelling);
		registered = entityManager.find(Dwelling.class, 7);
		descriptionRegistered = registered.getDescription();
		Assert.assertEquals(descriptionRegistered, dwelling.getDescription());

		dwelling = entityManager.find(Dwelling.class, 8);
		dwelling.setDescription("old description");
		entityManager.merge(dwelling);
		registered = entityManager.find(Dwelling.class, 8);
		descriptionRegistered = registered.getDescription();
		Assert.assertEquals(descriptionRegistered, dwelling.getDescription());

		dwelling = entityManager.find(Dwelling.class, 9);
		dwelling.setDescription("Significant description");
		entityManager.merge(dwelling);
		registered = entityManager.find(Dwelling.class, 9);
		descriptionRegistered = registered.getDescription();
		Assert.assertEquals(descriptionRegistered, dwelling.getDescription());

		dwelling = entityManager.find(Dwelling.class, 10);
		dwelling.setDescription("you know the description");
		entityManager.merge(dwelling);
		registered = entityManager.find(Dwelling.class, 10);
		descriptionRegistered = registered.getDescription();
		Assert.assertEquals(descriptionRegistered, dwelling.getDescription());

	}

	/**
	 * Estate agency merge test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void estateAgencyMergeTest() {

		EstateAgency estateAgency = entityManager.find(EstateAgency.class, "IMB1");
		estateAgency.setName("Banderas blancas");
		entityManager.merge(estateAgency);
		EstateAgency registered = entityManager.find(EstateAgency.class, "IMB1");
		String nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, estateAgency.getName());

		estateAgency = entityManager.find(EstateAgency.class, "IMB2");
		estateAgency.setEmail("imb33@inmobiliaria.com");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB2");
		nameRegistered = registered.getEmail();
		Assert.assertEquals(nameRegistered, estateAgency.getEmail());

		estateAgency = entityManager.find(EstateAgency.class, "IMB4");
		estateAgency.setName("imb44@inmobiliaria.com");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB4");
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, estateAgency.getName());

		estateAgency = entityManager.find(EstateAgency.class, "IMB5");
		estateAgency.setName("Banderines");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB5");
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, estateAgency.getName());

		estateAgency = entityManager.find(EstateAgency.class, "IMB6");
		estateAgency.setName("Banderitas");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB6");
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, estateAgency.getName());

		estateAgency = entityManager.find(EstateAgency.class, "IMB7");
		estateAgency.setName("Banderas moradas");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB7");
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, estateAgency.getName());

		estateAgency = entityManager.find(EstateAgency.class, "IMB5");
		estateAgency.setName("Banderines");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB5");
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, estateAgency.getName());

		estateAgency = entityManager.find(EstateAgency.class, "IMB6");
		estateAgency.setName("Banderitas");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB6");
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, estateAgency.getName());

		estateAgency = entityManager.find(EstateAgency.class, "IMB7");
		estateAgency.setName("Banderas moradas");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB7");
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, estateAgency.getName());

		estateAgency = entityManager.find(EstateAgency.class, "IMB8");
		estateAgency.setName("Sin banderas");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB8");
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, estateAgency.getName());

		estateAgency = entityManager.find(EstateAgency.class, "IMB9");
		estateAgency.setEmail("imb99@inmobiliaria.com");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB9");
		nameRegistered = registered.getEmail();
		Assert.assertEquals(nameRegistered, estateAgency.getEmail());

		estateAgency = entityManager.find(EstateAgency.class, "IMB10");
		estateAgency.setEmail("imb100@inmobiliaria.com");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB10");
		nameRegistered = registered.getEmail();
		Assert.assertEquals(nameRegistered, estateAgency.getEmail());

	}

	/**
	 * Project merge test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void projectMergeTest() {

		Project project = entityManager.find(Project.class, 1);
		project.setName("Un cambio nuevo");
		entityManager.merge(project);
		Project registered = entityManager.find(Project.class, 1);
		String nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, project.getName());

		project = entityManager.find(Project.class, 3);
		project.setDescription("Este es el mejor proyecto de todos");
		entityManager.merge(project);
		registered = entityManager.find(Project.class, 3);
		nameRegistered = registered.getDescription();
		Assert.assertEquals(nameRegistered, project.getDescription());

		project = entityManager.find(Project.class, 4);
		project.setDescription("Este no es tan bueno como los otros");
		entityManager.merge(project);
		registered = entityManager.find(Project.class, 4);
		nameRegistered = registered.getDescription();
		Assert.assertEquals(nameRegistered, project.getDescription());

		project = entityManager.find(Project.class, 5);
		project.setName("Villas de santa isabel");
		entityManager.merge(project);
		registered = entityManager.find(Project.class, 5);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, project.getName());

		project = entityManager.find(Project.class, 6);
		project.setName("Villa carolina");
		entityManager.merge(project);
		registered = entityManager.find(Project.class, 6);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, project.getName());

		project = entityManager.find(Project.class, 7);
		project.setName("Villas cristina");
		entityManager.merge(project);
		registered = entityManager.find(Project.class, 7);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, project.getName());

		project = entityManager.find(Project.class, 8);
		project.setName("Villa sofia");
		entityManager.merge(project);
		registered = entityManager.find(Project.class, 8);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, project.getName());

		project = entityManager.find(Project.class, 9);
		project.setName("Villa fernandez");
		entityManager.merge(project);
		registered = entityManager.find(Project.class, 9);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, project.getName());

		project = entityManager.find(Project.class, 10);
		project.setName("Villa lucia");
		entityManager.merge(project);
		registered = entityManager.find(Project.class, 10);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, project.getName());

	}

	/**
	 * Rating merge test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ratingMergeTest() {

		RatingPK key = new RatingPK("1", 1);
		Rating rating = entityManager.find(Rating.class, key);
		rating.setScore(160);
		entityManager.merge(rating);
		Rating registered = entityManager.find(Rating.class, key);
		int scoreRegistered = registered.getScore();
		Assert.assertEquals(scoreRegistered, rating.getScore());

		key = new RatingPK("2", 2);
		rating = entityManager.find(Rating.class, key);
		rating.setScore(150);
		entityManager.merge(rating);
		registered = entityManager.find(Rating.class, key);
		scoreRegistered = registered.getScore();
		Assert.assertEquals(scoreRegistered, rating.getScore());

		key = new RatingPK("3", 3);
		rating = entityManager.find(Rating.class, key);
		rating.setScore(160);
		entityManager.merge(rating);
		registered = entityManager.find(Rating.class, key);
		scoreRegistered = registered.getScore();
		Assert.assertEquals(scoreRegistered, rating.getScore());

	}

	/**
	 * Service merge test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void serviceMergeTest() {

		Service service = entityManager.find(Service.class, 1);
		service.setName("Carritos chocones");
		entityManager.merge(service);
		Service registered = entityManager.find(Service.class, 1);
		String nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, service.getName());

		service = entityManager.find(Service.class, 3);
		service.setName("Peluqueria");
		entityManager.merge(service);
		registered = entityManager.find(Service.class, 3);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, service.getName());

		service = entityManager.find(Service.class, 4);
		service.setName("Cafeteria");
		entityManager.merge(service);
		registered = entityManager.find(Service.class, 4);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, service.getName());

		service = entityManager.find(Service.class, 5);
		service.setName("Panaderia");
		entityManager.merge(service);
		registered = entityManager.find(Service.class, 5);
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, service.getName());

	}

	// ------------------------------Test find------------------------------ //

	/**
	 * Administrator find test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void administratorFindTest() {

		Administrator adminQuery = entityManager.find(Administrator.class, "123");
		Assert.assertEquals(adminQuery.getEmail(), "admin1@administrador.com");

		adminQuery = entityManager.find(Administrator.class, "1244567801");
		Assert.assertEquals(adminQuery.getCode(), "1244567801");

		adminQuery = entityManager.find(Administrator.class, "1254567801");
		Assert.assertEquals(adminQuery.getPassword(), "password3");

	}

	/**
	 * City find test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void cityFindTest() {

		City cityQuery = entityManager.find(City.class, 1);
		String nameCityQuery = cityQuery.getName();
		Assert.assertTrue(nameCityQuery.length() == 7);

		cityQuery = entityManager.find(City.class, 2);
		nameCityQuery = cityQuery.getName();
		Assert.assertTrue(nameCityQuery.length() == 6);

		cityQuery = entityManager.find(City.class, 3);
		nameCityQuery = cityQuery.getName();
		Assert.assertTrue(nameCityQuery.length() == 10);

		cityQuery = entityManager.find(City.class, 4);
		nameCityQuery = cityQuery.getName();
		Assert.assertTrue(nameCityQuery.length() == 8);

		cityQuery = entityManager.find(City.class, 5);
		nameCityQuery = cityQuery.getName();
		Assert.assertEquals(nameCityQuery, "Circasia");

		cityQuery = entityManager.find(City.class, 6);
		nameCityQuery = cityQuery.getName();
		Assert.assertEquals(nameCityQuery, "Medellin");

		cityQuery = entityManager.find(City.class, 7);
		nameCityQuery = cityQuery.getName();
		Assert.assertTrue(nameCityQuery.length() == 4);

		cityQuery = entityManager.find(City.class, 8);
		nameCityQuery = cityQuery.getName();
		Assert.assertEquals(nameCityQuery, "Bogotá");

		cityQuery = entityManager.find(City.class, 9);
		nameCityQuery = cityQuery.getName();
		Assert.assertTrue(nameCityQuery.length() == 9);

		cityQuery = entityManager.find(City.class, 10);
		nameCityQuery = cityQuery.getName();
		Assert.assertEquals(nameCityQuery, "Tumaco");

		cityQuery = entityManager.find(City.class, 11);
		nameCityQuery = cityQuery.getName();
		Assert.assertEquals(nameCityQuery, "Amazonas");

		cityQuery = entityManager.find(City.class, 12);
		nameCityQuery = cityQuery.getName();
		Assert.assertEquals(nameCityQuery, "Sincelejo");

		cityQuery = entityManager.find(City.class, 13);
		nameCityQuery = cityQuery.getName();
		Assert.assertTrue(nameCityQuery.length() == 12);

	}

	/**
	 * Comment find test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void commentFindTest() {

		Comment commentQuery = entityManager.find(Comment.class, 1);
		int projectCode = commentQuery.getProjectCode().getCode();
		Assert.assertTrue((projectCode - 1) == 0);

		commentQuery = entityManager.find(Comment.class, 2);
		projectCode = commentQuery.getProjectCode().getCode();
		Assert.assertTrue((projectCode - 1) == 1);

		commentQuery = entityManager.find(Comment.class, 3);
		projectCode = commentQuery.getProjectCode().getCode();
		Assert.assertTrue((projectCode - 1) == 1);

		commentQuery = entityManager.find(Comment.class, 4);
		projectCode = commentQuery.getProjectCode().getCode();
		Assert.assertTrue((projectCode - 1) == 0);

		commentQuery = entityManager.find(Comment.class, 5);
		projectCode = commentQuery.getProjectCode().getCode();
		Assert.assertTrue((projectCode - 1) == 0);

		commentQuery = entityManager.find(Comment.class, 6);
		projectCode = commentQuery.getProjectCode().getCode();
		Assert.assertTrue((projectCode - 1) == 0);

		commentQuery = entityManager.find(Comment.class, 7);
		projectCode = commentQuery.getProjectCode().getCode();
		Assert.assertTrue((projectCode - 1) == 0);

		commentQuery = entityManager.find(Comment.class, 8);
		projectCode = commentQuery.getProjectCode().getCode();
		Assert.assertTrue((projectCode - 1) == 0);

		commentQuery = entityManager.find(Comment.class, 9);
		projectCode = commentQuery.getProjectCode().getCode();
		Assert.assertTrue((projectCode - 1) == 0);

		commentQuery = entityManager.find(Comment.class, 10);
		projectCode = commentQuery.getProjectCode().getCode();
		Assert.assertTrue((projectCode - 1) == 0);

	}

	/**
	 * Contact find test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void contactFindTest() {

		Contact contactQuery = entityManager.find(Contact.class, 1);
		String contentContactQuery = contactQuery.getContent();
		Assert.assertEquals(contentContactQuery, "Este es el primer contenido de prueba");

		contactQuery = entityManager.find(Contact.class, 2);
		contentContactQuery = contactQuery.getContent();
		Assert.assertEquals(contentContactQuery, "Este es el segundo contenido de prueba");

		contactQuery = entityManager.find(Contact.class, 3);
		contentContactQuery = contactQuery.getContent();
		Assert.assertEquals(contentContactQuery, "Este es el tercer contenido de prueba");

		contactQuery = entityManager.find(Contact.class, 4);
		contentContactQuery = contactQuery.getContent();
		Assert.assertEquals(contentContactQuery, "Este es el Cuarto contenido de prueba");

		contactQuery = entityManager.find(Contact.class, 5);
		contentContactQuery = contactQuery.getContent();
		Assert.assertEquals(contentContactQuery, "Este es el quinto contenido de prueba");

		contactQuery = entityManager.find(Contact.class, 6);
		contentContactQuery = contactQuery.getContent();
		Assert.assertEquals(contentContactQuery, "Este es el sexto contenido de prueba");

		contactQuery = entityManager.find(Contact.class, 7);
		contentContactQuery = contactQuery.getContent();
		Assert.assertEquals(contentContactQuery, "Este es el septimo contenido de prueba");

		contactQuery = entityManager.find(Contact.class, 8);
		contentContactQuery = contactQuery.getContent();
		Assert.assertEquals(contentContactQuery, "Este es el octavo contenido de prueba");

		contactQuery = entityManager.find(Contact.class, 9);
		contentContactQuery = contactQuery.getContent();
		Assert.assertEquals(contentContactQuery, "Este es el noveno contenido de prueba");

		contactQuery = entityManager.find(Contact.class, 10);
		contentContactQuery = contactQuery.getContent();
		Assert.assertEquals(contentContactQuery, "Este es el decimo contenido de prueba");

	}

	/**
	 * Dwelling find test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void dwellingFindTest() {

		Dwelling dwellingQuery = entityManager.find(Dwelling.class, 1);
		String dwellingDescriptionQuery = dwellingQuery.getDescription();
		Assert.assertEquals(dwellingDescriptionQuery, "Esta es la primera descripcion de prueba");

		dwellingQuery = entityManager.find(Dwelling.class, 2);
		dwellingDescriptionQuery = dwellingQuery.getDescription();
		Assert.assertEquals(dwellingDescriptionQuery, "Esta es la segunda descripcion de prueba");

		dwellingQuery = entityManager.find(Dwelling.class, 3);
		dwellingDescriptionQuery = dwellingQuery.getDescription();
		Assert.assertEquals(dwellingDescriptionQuery, "Esta es la tercera descripcion de prueba");

		dwellingQuery = entityManager.find(Dwelling.class, 4);
		dwellingDescriptionQuery = dwellingQuery.getDescription();
		Assert.assertEquals(dwellingDescriptionQuery, "Esta es la cuarta descripcion de prueba");

		dwellingQuery = entityManager.find(Dwelling.class, 5);
		dwellingDescriptionQuery = dwellingQuery.getDescription();
		Assert.assertEquals(dwellingDescriptionQuery, "Esta es la quinta descripcion de prueba");

		dwellingQuery = entityManager.find(Dwelling.class, 6);
		dwellingDescriptionQuery = dwellingQuery.getDescription();
		Assert.assertEquals(dwellingDescriptionQuery, "Esta es la sexta descripcion de prueba");

		dwellingQuery = entityManager.find(Dwelling.class, 7);
		dwellingDescriptionQuery = dwellingQuery.getDescription();
		Assert.assertEquals(dwellingDescriptionQuery, "Esta es la septima descripcion de prueba");

		dwellingQuery = entityManager.find(Dwelling.class, 8);
		dwellingDescriptionQuery = dwellingQuery.getDescription();
		Assert.assertEquals(dwellingDescriptionQuery, "Esta es la octava descripcion de prueba");

		dwellingQuery = entityManager.find(Dwelling.class, 9);
		dwellingDescriptionQuery = dwellingQuery.getDescription();
		Assert.assertEquals(dwellingDescriptionQuery, "Esta es la novena descripcion de prueba");

		dwellingQuery = entityManager.find(Dwelling.class, 10);
		dwellingDescriptionQuery = dwellingQuery.getDescription();
		Assert.assertEquals(dwellingDescriptionQuery, "Esta es la decima descripcion de prueba");

	}

	/**
	 * Estate agency find test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void estateAgencyFindTest() {

		EstateAgency estateAgencyQuery = entityManager.find(EstateAgency.class, "IMB1");
		String estateAgencyNameQuery = estateAgencyQuery.getName();
		Assert.assertEquals(estateAgencyNameQuery, "Banderas Verdes");

		estateAgencyQuery = entityManager.find(EstateAgency.class, "IMB2");
		estateAgencyNameQuery = estateAgencyQuery.getName();
		Assert.assertEquals(estateAgencyNameQuery, "Campos Verdes");

		estateAgencyQuery = entityManager.find(EstateAgency.class, "IMB3");
		estateAgencyNameQuery = estateAgencyQuery.getName();
		Assert.assertEquals(estateAgencyNameQuery, "Casas bellas");

		estateAgencyQuery = entityManager.find(EstateAgency.class, "IMB4");
		estateAgencyNameQuery = estateAgencyQuery.getName();
		Assert.assertEquals(estateAgencyNameQuery, "El renacer");

		estateAgencyQuery = entityManager.find(EstateAgency.class, "IMB5");
		estateAgencyNameQuery = estateAgencyQuery.getName();
		Assert.assertEquals(estateAgencyNameQuery, "Porvenir");

		estateAgencyQuery = entityManager.find(EstateAgency.class, "IMB6");
		estateAgencyNameQuery = estateAgencyQuery.getName();
		Assert.assertEquals(estateAgencyNameQuery, "Lux");

		estateAgencyQuery = entityManager.find(EstateAgency.class, "IMB7");
		estateAgencyNameQuery = estateAgencyQuery.getName();
		Assert.assertEquals(estateAgencyNameQuery, "Rio claro");

		estateAgencyQuery = entityManager.find(EstateAgency.class, "IMB8");
		estateAgencyNameQuery = estateAgencyQuery.getName();
		Assert.assertEquals(estateAgencyNameQuery, "Santa maria");

		estateAgencyQuery = entityManager.find(EstateAgency.class, "IMB9");
		estateAgencyNameQuery = estateAgencyQuery.getName();
		Assert.assertEquals(estateAgencyNameQuery, "Villa celmira");

		estateAgencyQuery = entityManager.find(EstateAgency.class, "IMB10");
		estateAgencyNameQuery = estateAgencyQuery.getName();
		Assert.assertEquals(estateAgencyNameQuery, "Bella Suiza");

	}

	/**
	 * Project find test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void projectFindTest() {

		Project projectQuery = entityManager.find(Project.class, 1);
		String projectDescriptionQuery = projectQuery.getDescription();
		Assert.assertEquals(projectDescriptionQuery, "El mejor proyecto de todos");

		projectQuery = entityManager.find(Project.class, 2);
		projectDescriptionQuery = projectQuery.getDescription();
		Assert.assertEquals(projectDescriptionQuery, "El segundo mejor proyecto de todos");

		projectQuery = entityManager.find(Project.class, 3);
		projectDescriptionQuery = projectQuery.getDescription();
		Assert.assertEquals(projectDescriptionQuery, "El tercer mejor proyecto de todos");

		projectQuery = entityManager.find(Project.class, 4);
		projectDescriptionQuery = projectQuery.getDescription();
		Assert.assertEquals(projectDescriptionQuery, "El cuarto mejor proyecto de todos");

		projectQuery = entityManager.find(Project.class, 5);
		projectDescriptionQuery = projectQuery.getDescription();
		Assert.assertEquals(projectDescriptionQuery, "El quinto mejor proyecto de todos");

		projectQuery = entityManager.find(Project.class, 6);
		projectDescriptionQuery = projectQuery.getDescription();
		Assert.assertEquals(projectDescriptionQuery, "El sexto mejor proyecto de todos");

		projectQuery = entityManager.find(Project.class, 7);
		projectDescriptionQuery = projectQuery.getDescription();
		Assert.assertEquals(projectDescriptionQuery, "El septimo mejor proyecto de todos");

		projectQuery = entityManager.find(Project.class, 8);
		projectDescriptionQuery = projectQuery.getDescription();
		Assert.assertEquals(projectDescriptionQuery, "El octavo mejor proyecto de todos");

		projectQuery = entityManager.find(Project.class, 9);
		projectDescriptionQuery = projectQuery.getDescription();
		Assert.assertEquals(projectDescriptionQuery, "El noveno mejor proyecto de todos");

		projectQuery = entityManager.find(Project.class, 10);
		projectDescriptionQuery = projectQuery.getDescription();
		Assert.assertEquals(projectDescriptionQuery, "El decimo mejor proyecto de todos");

	}

	/**
	 * Rating find test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ratingFindTest() {

		RatingPK key = new RatingPK("1", 1);
		Rating ratingQuery = entityManager.find(Rating.class, key);
		int ratingScoreQuery = ratingQuery.getScore();
		Assert.assertEquals(ratingScoreQuery, 150);

		key = new RatingPK("2", 2);
		ratingQuery = entityManager.find(Rating.class, key);
		ratingScoreQuery = ratingQuery.getScore();
		Assert.assertEquals(ratingScoreQuery, 155);

		key = new RatingPK("3", 3);
		ratingQuery = entityManager.find(Rating.class, key);
		ratingScoreQuery = ratingQuery.getScore();
		Assert.assertEquals(ratingScoreQuery, 157);

	}

	/**
	 * Service find test.
	 */
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void serviceFindTest() {

		Service serviceQuery = entityManager.find(Service.class, 1);
		String serviceNameQuery = serviceQuery.getName();
		Assert.assertEquals(serviceNameQuery, "piscinita");

		serviceQuery = entityManager.find(Service.class, 2);
		serviceNameQuery = serviceQuery.getName();
		Assert.assertEquals(serviceNameQuery, "Gimnasio");

		serviceQuery = entityManager.find(Service.class, 3);
		serviceNameQuery = serviceQuery.getName();
		Assert.assertEquals(serviceNameQuery, "BBQ");

		serviceQuery = entityManager.find(Service.class, 4);
		serviceNameQuery = serviceQuery.getName();
		Assert.assertEquals(serviceNameQuery, "Tienda");

		serviceQuery = entityManager.find(Service.class, 5);
		serviceNameQuery = serviceQuery.getName();
		Assert.assertEquals(serviceNameQuery, "Iglesia");

		serviceQuery = entityManager.find(Service.class, 6);
		serviceNameQuery = serviceQuery.getName();
		Assert.assertEquals(serviceNameQuery, "Candy shop");

		serviceQuery = entityManager.find(Service.class, 7);
		serviceNameQuery = serviceQuery.getName();
		Assert.assertEquals(serviceNameQuery, "Camping zone");

		serviceQuery = entityManager.find(Service.class, 8);
		serviceNameQuery = serviceQuery.getName();
		Assert.assertEquals(serviceNameQuery, "Canchas de futbol");

		serviceQuery = entityManager.find(Service.class, 9);
		serviceNameQuery = serviceQuery.getName();
		Assert.assertEquals(serviceNameQuery, "Canchas de baloncesto");

		serviceQuery = entityManager.find(Service.class, 10);
		serviceNameQuery = serviceQuery.getName();
		Assert.assertEquals(serviceNameQuery, "Parque para perros");
	}

	// -----------------Queries Tests----------------
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void authenticateUserTest() {

		TypedQuery<User> query = entityManager.createNamedQuery(User.AUTHENTICATE_USER, User.class);
		query.setParameter("email", "c1@hotmail.com");
		query.setParameter("password", "password1");
		User user = entityManager.find(Client.class, "1");

		List<User> resultList = query.getResultList();
		Assert.assertTrue("", resultList.contains(user));

	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getDwellingAreaListTest() {

		TypedQuery<Dwelling> query = entityManager.createNamedQuery(Dwelling.DWELLINGS_AREA_LIST, Dwelling.class);
		query.setParameter("area", 18.5);
		List<Dwelling> resultList = query.getResultList();

		Assert.assertEquals(resultList.size(), 10);

	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getAllUsersQueryTest() {
		TypedQuery<User> query = entityManager.createNamedQuery(User.GET_ALL_USERS, User.class);
		List<User> resultList = query.getResultList();

		int size = resultList.size();
		int expected = 16;
		Assert.assertTrue("<getAllUsersQueryTest>Size: " + size + " expected: " + expected, size == expected);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getAllEstateAgencyTest() {
		TypedQuery<EstateAgency> query = entityManager.createNamedQuery(EstateAgency.GET_ALL_ESTATE_AGENCY,
				EstateAgency.class);
		List<EstateAgency> resultList = query.getResultList();

		int size = resultList.size();
		int expected = 10;
		Assert.assertTrue("<getAllEstateAgencyQueryTest>Size: " + size + " expected: " + expected, size == expected);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getAllProjectsTest() {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_ALL_PROJECTS, Project.class);
		List<Project> resultList = query.getResultList();

		int size = resultList.size();
		int expected = 10;
		Assert.assertTrue("<getAllProjectsQueryTest>Size: " + size + " expected: " + expected, size == expected);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getAllRatingTest() {
		TypedQuery<Rating> query = entityManager.createNamedQuery(Rating.GET_ALL_RATING, Rating.class);
		List<Rating> resultList = query.getResultList();

		int size = resultList.size();
		int expected = 3;
		Assert.assertTrue("<getAllRatingQueryTest>Size: " + size + " expected: " + expected, size == expected);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getAllServiceTest() {
		TypedQuery<Service> query = entityManager.createNamedQuery(Service.GET_ALL_SERVICES, Service.class);
		List<Service> resultList = query.getResultList();

		int size = resultList.size();
		int expected = 10;
		Assert.assertTrue("<getAllServiceQueryTest>Size: " + size + " expected: " + expected, size == expected);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getMaxLimitUsersQueryTest() {
		TypedQuery<User> query = entityManager.createNamedQuery(User.GET_ALL_USERS, User.class);
		query = query.setMaxResults(1);// Al ser superclase lo que hace es generar los maximos resultados de cada
										// subclase
										// Es decir: max = 1, genera 1 de cada subclase
		List<User> resultList = query.getResultList();
		int size = resultList.size();
		int expected = 3;
		Assert.assertTrue("<getAllUsersQueryTest>Size: " + size + " expected: " + expected, size == expected);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getLastUserClientTest() {
		TypedQuery<Client> query = entityManager.createNamedQuery(Client.GET_ALL_CLIENT, Client.class);
		query = query.setFirstResult(2);
		Client client = query.getSingleResult();
		Client requested = entityManager.find(Client.class, "1");
		Assert.assertEquals(client, requested);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getProjectsByItsCityTest() {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_ALL_PROJECTS_BY_CITY, Project.class);
		query.setParameter("cityCode", 1);
		List<Project> resultList = query.getResultList();
		int sizeResult = resultList.size();
		int expected = 3;

		Assert.assertTrue("<getProjectsByItsCity>Size: " + sizeResult + " expected: " + expected,
				expected == sizeResult);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getProjectByLatitudeLengthTest() {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_PROJECT_BY_LATTITUDE_LENGTH,
				Project.class);
		query.setParameter("latitude", 1.42);
		query.setParameter("length", 1.456);
		Project projectResult = query.getSingleResult();
		Project projectExpected = entityManager.find(Project.class, 1);
		Assert.assertEquals(projectResult, projectExpected);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getProjectsByNameTest() {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_PROJECT_BY_NAME, Project.class);
		query.setParameter("projectName", "Villas del sol");
		Project projectResult = query.getSingleResult();
		Project projectExpected = entityManager.find(Project.class, 3);
		Assert.assertEquals(projectResult, projectExpected);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getProjectByIdTest() {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_PROJECT_BY_ID, Project.class);
		query.setParameter("projectCode", 3);
		Project projectResult = query.getSingleResult();
		Project projectExpected = entityManager.find(Project.class, 3);
		Assert.assertEquals(projectResult, projectExpected);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getProjectsByEstateAgencyTest() {
		TypedQuery<Project> query = entityManager.createNamedQuery(Project.GET_ALL_PROJECTS_BY_ESTATE_AGENCY,
				Project.class);
		query.setParameter("estateAgencyCode", "IMB1");
		List<Project> projectResult = query.getResultList();
		int sizeResult = projectResult.size();
		int sizeExpected = 1;
		Assert.assertTrue("<getProjectsByEstateAgency>Size: " + sizeResult + " expected: " + sizeExpected,
				sizeResult == sizeExpected);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getNameEstateAgencyProjectTest() {
		TypedQuery<String> query = entityManager.createNamedQuery(Project.GET_NAME_ESTATE_AGENCY, String.class);
		query.setParameter("projectCode", 1);
		String nameResult = query.getSingleResult();
		Assert.assertEquals("Banderas Verdes", nameResult);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getProjectAndScoreTest() {
		TypedQuery<Object[]> query = entityManager.createNamedQuery(Client.GET_PROJECTS_RATING_UNIQUE, Object[].class);
		query.setParameter("clientCode", "1");
		List<Object[]> resultList = query.getResultList();
		int sizeResult = resultList.size();
		int expected = 2;
		Assert.assertTrue("<getProjectAndScoreTest>Size: " + sizeResult + " expected: " + expected,
				sizeResult == expected);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getCodeEstateAgencyAndProjectsTest() {
		TypedQuery<Object[]> query = entityManager.createNamedQuery(EstateAgency.GET_ALL_ESTATE_AGENCY_PROJECTS,
				Object[].class);
		List<Object[]> resultList = query.getResultList();
		int sizeResult = resultList.size();
		int expected = 10;
		Assert.assertTrue("<getCodeEstateAgencyAndProjectsTest>Size: " + sizeResult + " expected: " + expected,
				sizeResult == expected);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getClientsContactProjectTest() {
		TypedQuery<Client> query = entityManager.createNamedQuery(Project.GET_CLIENTS_CONTACT, Client.class);
		query.setParameter("projectCode", 1);
		List<Client> resultList = query.getResultList();
		int sizeResult = resultList.size();
		int expected = 2;
		Assert.assertTrue("<getClientsContactProjectTest>Size: " + sizeResult + " expected: " + expected,
				expected == sizeResult);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getProjectRatingTest() {
		TypedQuery<Object[]> query = entityManager.createNamedQuery(Project.GET_PROJECTS_RATING, Object[].class);
		List<Object[]> resultList = query.getResultList();
		int sizeResult = resultList.size();
		int expected = 10;
		Assert.assertTrue("<getProjectRatingTest>Size: " + sizeResult + " expected: " + expected,
				expected == sizeResult);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getCommentClientNonRepeatedTest() {
		TypedQuery<Client> query = entityManager.createNamedQuery(Comment.GET_CLIENT_NO_REPEATED, Client.class);
		query.setParameter("projectCode", 1);
		List<Client> resultList = query.getResultList();
		int sizeResult = resultList.size();
		int expected = 1;
		Assert.assertTrue("<getCommentClientNonRepeatedTest>Size: " + sizeResult + " expected: " + expected,
				expected == sizeResult);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void getInfoProjectsByCity() {
		TypedQuery<Object[]> query = entityManager.createNamedQuery(Project.GET_PROJECTS_CITY_ASSOCIATED,
				Object[].class);
		query.setParameter("cityCode", 1);
		List<Object[]> resultList = query.getResultList();
		String infoConsole = "";
		for (Object[] objects : resultList) {
			infoConsole += (Integer) objects[0] + " " + (String) objects[1] + " " + (Double) objects[2] + " "
					+ (Double) objects[3] + " " + (String) objects[4];
			infoConsole += "\n";
		}
		String outExpected = "1 Villas del mar 1.42 1.456 IMB1\n" + 
				"2 Cafe del cielo 1.43 1.466 IMB2\n" + 
				"3 Villas del sol 1.4782 1.4566846 IMB3";
		Assert.assertEquals(outExpected, infoConsole);
	}
}