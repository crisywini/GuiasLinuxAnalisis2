package co.edu.uniquindio.prueba.test;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import co.edu.uniquindio.entidades.Genero;
import co.edu.uniquindio.entidades.PersonaEntidades;
import co.edu.uniquindio.project.entities.Administrator;
import co.edu.uniquindio.project.entities.City;
import co.edu.uniquindio.project.entities.Client;
import co.edu.uniquindio.project.entities.Comment;
import co.edu.uniquindio.project.entities.Contact;
import co.edu.uniquindio.project.entities.Dwelling;
import co.edu.uniquindio.project.entities.EstateAgency;
import co.edu.uniquindio.project.entities.Project;
import co.edu.uniquindio.project.entities.Rating;
import co.edu.uniquindio.project.entities.Service;
import co.edu.uniquindio.project.entities.Type;
import co.edu.uniquindio.project.entities.User;

@RunWith(Arquillian.class)
public class ModelTest {
	@PersistenceContext
	private EntityManager entityManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,

				"prueba.war").addPackage(Administrator.class.getPackage()).addAsResource("persistenceForTest.xml",

						"META-INF/persistence.xml")

				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void generarTest() {
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	/**
	 * La manera de ejecutar la prueba: en Modo COMMIT comprueba que la inserción en
	 * la base de datos sea correcta y deja la persona guardada, en modo ROLLBACK lo
	 * que hace es modificarla y despues lo elimina, lo que ideal para las pruebas,
	 * para que no se almacenen en la base de datos
	 */
	public void probarPersistencia() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date fecha = sdf.parse("15/11/1999");

		Map<String, String> telefonos = new HashMap<String, String>();
		telefonos.put("Casa", "2641965");
		telefonos.put("Celular", "3147579868");

		PersonaEntidades persona = new PersonaEntidades();
		persona.setCedula("123456");
		persona.setNombre("Cristian");
		persona.setApellido("Sánchez");
		persona.setEmail("harmaharsa@hotmail.com");
		persona.setGenero(Genero.ANDROGINO);
		persona.setFechaNacimiento(fecha);
		persona.setNumerosDeTelefono(telefonos);

		entityManager.persist(persona);
		entityManager.flush();// Obliga a la transacción para que se mande al motor de la base de datos

		PersonaEntidades newPersona2 = new PersonaEntidades();
		newPersona2.setCedula("12346");
		newPersona2.setNombre("Juan");
		newPersona2.setApellido("Loaiza");
		newPersona2.setEmail("jp4532@hotmail.com");
		newPersona2.setFechaNacimiento(fecha);
		newPersona2.setNumerosDeTelefono(telefonos);

		entityManager.persist(newPersona2);
		entityManager.flush();

	}

	// Test persist
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

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void dwellingPersistenceTest() {
		Dwelling dwelling = new Dwelling(8, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia",
				4, 2, Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		Dwelling registered = entityManager.find(Dwelling.class, 8);
		Assert.assertEquals(dwelling, registered);

		dwelling = new Dwelling(9, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia", 4, 2,
				Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		registered = entityManager.find(Dwelling.class, 9);
		Assert.assertEquals(dwelling, registered);

		dwelling = new Dwelling(10, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia", 4, 2,
				Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		registered = entityManager.find(Dwelling.class, 10);
		Assert.assertEquals(dwelling, registered);

		dwelling = new Dwelling(11, "/images/apartacho.png", 45, 14000000.0, "Apartamento con vista a Armenia", 4, 2,
				Type.APARTMENT);
		entityManager.persist(dwelling);
		entityManager.flush();
		registered = entityManager.find(Dwelling.class, 11);
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

	}

	// Test remove
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

	// Test merge
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

	// Test find

	// Test persist
	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void estateAgencyPersistenceTest() {

		EstateAgency registered;

		EstateAgency estateAgency = new EstateAgency("Banderas verdes", "121", "imb1@inmobiliaria.com", "password1",
				"Calle 1 # 2-30");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "121");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Amarillas", "122", "imb2@inmobiliaria.com", "password2",
				"calle 2 # 3 - 40");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "122");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Azules", "123", "imb3@inmobiliaria.com", "password3",
				"calle 3 # 4 - 50");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "123");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Rojas", "124", "imb4@inmobiliaria.com", "password4",
				"calle 4 # 5 - 60");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "124");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Grises", "125", "imb5@inmobiliaria.com", "password5",
				"calle 5 # 6 - 70");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "125");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Negras", "126", "imb6@inmobiliaria.com", "password6",
				"calle 6 # 7 - 80");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "126");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Blancas", "127", "imb7@inmobiliaria.com", "password7",
				"calle 7 # 8 - 90");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "127");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Rosas", "128", "imb8@inmobiliaria.com", "password8",
				"calle 8 # 9 - 10");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "128");
		Assert.assertEquals(estateAgency, registered);

		estateAgency = new EstateAgency("Banderas Oscuras", "129", "imb9@inmobiliaria.com", "password9",
				"calle 9 # 10 - 20");
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(EstateAgency.class, "129");
		Assert.assertEquals(estateAgency, registered);
	}

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

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void ratingPersistenceTest() {
		
		// TODO: REVISAR

		Rating registered;

		Rating estateAgency = new Rating(1, 150, clientRating, projectRating)
		entityManager.persist(estateAgency);
		entityManager.flush();
		registered = entityManager.find(Rating.class, "121");
		Assert.assertEquals(estateAgency, registered);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void servicePersistenceTest() {

		Service registered;

		Service service = new Service(1, "Piscina");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 1);
		Assert.assertEquals(service, registered);

		service = new Service(2, "Gimnasio");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 2);
		Assert.assertEquals(service, registered);

		service = new Service(3, "BBQ");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 3);
		Assert.assertEquals(service, registered);

		service = new Service(4, "Tienda");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 4);
		Assert.assertEquals(service, registered);

		service = new Service(5, "Parroquia");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 5);
		Assert.assertEquals(service, registered);

		service = new Service(6, "Dulceria");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 6);
		Assert.assertEquals(service, registered);

		service = new Service(7, "Zona Camping");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 7);
		Assert.assertEquals(service, registered);

		service = new Service(8, "Zapateria");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 8);
		Assert.assertEquals(service, registered);

		service = new Service(9, "Carniceria");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 9);
		Assert.assertEquals(service, registered);

		service = new Service(10, "Fruteria");
		entityManager.persist(service);
		entityManager.flush();
		registered = entityManager.find(Service.class, 10);
		Assert.assertEquals(service, registered);
	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void userPersistenceTest() {

		// TODO: REVISAR

		User registered;

		User user = new User("110", "user@mail.com", "password");
		entityManager.persist(user);
		entityManager.flush();
		registered = entityManager.find(User.class, "110");
		Assert.assertEquals(user, registered);

		user = new User("111", "user1@mail.com", "password1");
		entityManager.persist(user);
		entityManager.flush();
		registered = entityManager.find(User.class, "111");
		Assert.assertEquals(user, registered);

		user = new User("112", "user2@mail.com", "password2");
		entityManager.persist(user);
		entityManager.flush();
		registered = entityManager.find(User.class, "112");
		Assert.assertEquals(user, registered);

		user = new User("113", "user3@mail.com", "password3");
		entityManager.persist(user);
		entityManager.flush();
		registered = entityManager.find(User.class, "113");
		Assert.assertEquals(user, registered);

		user = new User("114", "user4@mail.com", "password4");
		entityManager.persist(user);
		entityManager.flush();
		registered = entityManager.find(User.class, "114");
		Assert.assertEquals(user, registered);

		user = new User("115", "user5@mail.com", "password5");
		entityManager.persist(user);
		entityManager.flush();
		registered = entityManager.find(User.class, "115");
		Assert.assertEquals(user, registered);

		user = new User("116", "user6@mail.com", "password6");
		entityManager.persist(user);
		entityManager.flush();
		registered = entityManager.find(User.class, "116");
		Assert.assertEquals(user, registered);

		user = new User("117", "user7@mail.com", "password7");
		entityManager.persist(user);
		entityManager.flush();
		registered = entityManager.find(User.class, "117");
		Assert.assertEquals(user, registered);

		user = new User("118", "user8@mail.com", "password8");
		entityManager.persist(user);
		entityManager.flush();
		registered = entityManager.find(User.class, "118");
		Assert.assertEquals(user, registered);

		user = new User("119", "user9@mail.com", "password9");
		entityManager.persist(user);
		entityManager.flush();
		registered = entityManager.find(User.class, "119");
		Assert.assertEquals(user, registered);
	}

	// Test remove

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void estateAgencyRemoveTest() {

		EstateAgency estateAgency = entityManager.find(EstateAgency.class, "IMB1");
		entityManager.remove(estateAgency);
		EstateAgency registered = entityManager.find(EstateAgency.class, "IMB1");
		Assert.assertNull(registered);

		estateAgency = entityManager.find(EstateAgency.class, "IMB2");
		entityManager.remove(estateAgency);
		registered = entityManager.find(EstateAgency.class, "IMB2");
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

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void projectRemoveTest() {

		Project project = entityManager.find(Project.class, 1);
		entityManager.remove(project);
		Project registered = entityManager.find(Project.class, 1);
		Assert.assertNull(registered);

		project = entityManager.find(Project.class, 2);
		entityManager.remove(project);
		registered = entityManager.find(Project.class, 2);
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

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void serviceRemoveTest() {

		Service service = entityManager.find(Service.class, 1);
		entityManager.remove(service);
		Service registered = entityManager.find(Service.class, 1);
		Assert.assertNull(registered);

		service = entityManager.find(Service.class, 2);
		entityManager.remove(service);
		registered = entityManager.find(Service.class, 2);
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

	@Ignore
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" })
	public void userRemoveTest() {

		// TODO: REVISAR

		User user = entityManager.find(User.class, 1);
		entityManager.remove(user);
		User registered = entityManager.find(User.class, 1);
		Assert.assertNull(registered);

		user = entityManager.find(User.class, 2);
		entityManager.remove(user);
		registered = entityManager.find(User.class, 2);
		Assert.assertNull(registered);
	}

	// Test merge

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
		estateAgency.setName("San patricio");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "imb2");
		nameRegistered = registered.getName();
		Assert.assertEquals(nameRegistered, estateAgency.getName());

		estateAgency = entityManager.find(EstateAgency.class, "IMB3");
		estateAgency.setEmail("imb33@inmobiliaria.com");
		entityManager.merge(estateAgency);
		registered = entityManager.find(EstateAgency.class, "imb3");
		nameRegistered = registered.getEmail();
		Assert.assertEquals(nameRegistered, estateAgency.getName());
	}

}