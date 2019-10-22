package db;

import domain.Guardarropa;
import domain.Prenda;
import domain.Usuario;
import domain.guardarropa.Premium;
import domain.prenda.*;
import domain.usuario.Calendario;
import domain.usuario.Evento;
import domain.usuario.Periodo;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PersistenceTest {
    private EntityManager manager;
    private EntityManagerFactory emf;
    private Evento eventoPersistente;


    @Before
    public void iniciarTest() {
        this.emf = Persistence.createEntityManagerFactory("quemepongo");
        this.manager = emf.createEntityManager();
        this.eventoPersistente = new Evento("Partido Boca-River","La Boca", LocalDateTime.of(2019,10,22,21,30), Periodo.NINGUNO,2);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();



    @Test
    public void persistirEvento() {
        try {
            manager.getTransaction().begin();
            manager.persist(eventoPersistente);
            manager.getTransaction().commit();
            manager.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            manager.close();
        }

    }
/*
    @Test
    public void persistirUsuario() {
        try {
            manager.getTransaction().begin();
            manager.persist(eventoPersistente);
            manager.getTransaction().commit();
            manager.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            manager.close();
        }

    }

*/
}
