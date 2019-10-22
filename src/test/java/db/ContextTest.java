/* import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

public class ContextTest {

	public static EntityManagerFactory emf;
	public static EntityManager em;

	@Before
	public void iniciarTest() {
		emf = Persistence.createEntityManagerFactory("db");
	}
	@Test
	public void verificarConexion() {
		Assert.assertNotNull(emf);

	}



}
*/
package db;

		import static org.junit.Assert.*;

		import org.junit.Test;
		import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
		import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}

}