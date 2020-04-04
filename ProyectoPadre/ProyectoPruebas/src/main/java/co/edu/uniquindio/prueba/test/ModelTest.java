package co.edu.uniquindio.prueba.test;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

import co.edu.uniquindio.ejemploe.Estudiante;
import co.edu.uniquindio.entidades.Genero;
import co.edu.uniquindio.entidades.PersonaEntidades;
import co.edu.uniquindio.project.entities.Administrator;
import co.edu.uniquindio.project.entities.City;
import co.edu.uniquindio.project.entities.Project;

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

	//Test persist
	@Ignore
	@Test
	@Transactional(value = TransactionMode.COMMIT)
	@UsingDataSet({"unihogar.json"})
	public void adminPersistenceTest() {

		Administrator admin = new Administrator("1234", "admin51@administrador.com", "password1");
		entityManager.persist(admin);
		entityManager.flush();
		
		Administrator registered = entityManager.find(Administrator.class, "1234");
		System.out.println(admin.toString());
		System.out.println(registered.toString());

		Assert.assertEquals(admin, registered);
	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "unihogar.json" }) // Esto se pone para utilizar los archivos json
	public void cityPersistenceTest() {

//		City searchedCity = entityManager.find(City.class, 1);// El cero es muy trivial
//		Assert.assertNotNull(searchedCity);
		City city = new City(22, "Salento");
		entityManager.persist(city);
		entityManager.flush();
		
		City registered = entityManager.find(City.class, 22);
		Assert.assertEquals(city, registered);
		
	}
	//Test remove
	//Test merge
	//Test find

}