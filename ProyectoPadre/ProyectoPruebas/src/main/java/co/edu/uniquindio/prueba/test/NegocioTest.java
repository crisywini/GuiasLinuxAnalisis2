package co.edu.uniquindio.prueba.test;

import java.util.List;

import javax.ejb.EJB;


import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

import co.edu.uniquindio.project.AdministratorEJB;
import co.edu.uniquindio.project.exceptions.AuthenticationException;
import co.edu.uniquindio.project.exceptions.NonexistentUserException;
import co.edu.uniquindio.project.util.MailSender;
import co.edu.uniquindio.unihogar.dto.QueryDwellingByProjectDTO;
import co.edu.uniquindio.unihogar.dto.QueryNumberProjectByCityDTO;
import co.edu.uniquindio.unihogar.entities.EstateAgency;
import co.edu.uniquindio.unihogar.entities.Project;
import co.edu.uniquindio.unihogar.entities.User;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class NegocioTest {

	@EJB
	private AdministratorEJB pruebaEJB;

	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(AdministratorEJB.class)

				.addPackage(User.class.getPackage()).addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"unihogar.json"})
	public void authenticateUserTest() {
			try {
				pruebaEJB.authenticateUser("harmaharcri@hotmail.com", "root");
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}
	}
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"unihogar.json"})
	public void sendRecoveryPasswordMailTest() {
		try {
			Assert.assertTrue(pruebaEJB.isEmailWithPasswordSended("harmaharcri@hotmail.com"));
		} catch (NonexistentUserException e) {
			e.printStackTrace();
		}
	}
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"unihogar.json"})
	public void getTop5StateAgenciesByCountProjectsTest() {
		List<EstateAgency> result = pruebaEJB.getTop5ListEstateAgenciesByCity("Armenia");
		System.out.println(result.size());
	}
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"unihogar.json"})
	public void getTop5ProjectRating() {
		List<Project> top5 = pruebaEJB.getTop5ProjectsByRatings();
		System.out.println(top5);
	}
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"unihogar.json"})
	public void getTopCITY() {
		List<QueryNumberProjectByCityDTO> list = pruebaEJB.getTopCityByProjects();
		System.out.println(list);
	}
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"unihogar.json"})
	public void getTopProjectsByDwelling() {
		List<QueryDwellingByProjectDTO> list = pruebaEJB.getTopProjectsByDwellings();
		System.out.println(list);
	}

}
