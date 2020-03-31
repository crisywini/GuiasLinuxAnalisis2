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
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.uniquindio.entidades.Genero;
import co.edu.uniquindio.entidades.PersonaEntidades;

@RunWith(Arquillian.class)
public class ModeloTest {
	@PersistenceContext
	private EntityManager entityManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,

				"prueba.war").addPackage(PersonaEntidades.class.getPackage()).addAsResource("persistenceForTest.xml",

						"META-INF/persistence.xml")

				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void generarTest() {
	}

	@Test
	@Transactional(value = TransactionMode.COMMIT)
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

}
