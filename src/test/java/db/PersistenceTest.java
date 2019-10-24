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
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PersistenceTest {
    private EntityManager manager;
    private EntityManagerFactory emf;
    private Evento eventoPersistente;
    private LocalDateTime fechaPartido;
    private Usuario alexis;
    private Calendario calendario;


    @Before
    public void iniciarTest() {
        this.emf = Persistence.createEntityManagerFactory("quemepongo");
        this.manager = emf.createEntityManager();
        this.fechaPartido = LocalDateTime.of(2019,10,22,21,30,0);
        //LocalDateTime.of(2019,10,22,21,30,0)
        this.alexis = new Usuario("+54911651651",null,"1234");
        this.alexis.setNombreUsuario("alexis");
        this.eventoPersistente = new Evento("Partido Boca-River","La Boca",fechaPartido , Periodo.NINGUNO,2);
        this.calendario = new Calendario();
        this.calendario.setNombre("calendarioLaboral");
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();



    @Test
    public void persistirEvento() {
        try {
            manager.getTransaction().begin();
            System.out.println("La fecha hora del evento es: "+ eventoPersistente.getFecha());
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

    @Test
    public void persistirUsuario() {
        try {
            manager.getTransaction().begin();
            manager.persist(alexis);
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

    @Test
    public void persistirCalendario() {
        try {
            manager.getTransaction().begin();
            manager.persist(calendario);
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

}
