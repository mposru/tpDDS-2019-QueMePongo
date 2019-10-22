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
    private TipoDePrenda tipoDePrenda;
    private Material material;
    private Color colorPrimario;
    private Borrador borradorZapatillas;
    private Trama trama;
    private Guardarropa guardarropa;
    private boolean esParaLluvia;
    private Material materialInvalido;
    private Usuario magdalena;
    private Calendario calendario;
    private EntityManager manager;
    private EntityManagerFactory emf;
    private Prenda zapatoValido;
    private Evento eventoPersistente;
    private String contraseniaHasheada;

    @Before
    public void iniciarTest() {
        this.magdalena = new Usuario("", calendario,"magui123");
       // this.contraseniaHasheada = DigestUtils.md5(magdalena.getContrasenia());
        this.tipoDePrenda = TipoDePrenda.ZAPATO;
        this.material = Material.CUERO;
        this.colorPrimario = new Color(20, 20, 30);
        this.trama = Trama.LISA;
        this.esParaLluvia = true;
        Set<Usuario> magdalenaLista = new HashSet<>();
        magdalenaLista.add(magdalena);
        this.guardarropa = new Guardarropa(magdalenaLista,new Premium());


        this.emf = Persistence.createEntityManagerFactory("db");
        this.manager = emf.createEntityManager();

        //Creamos una prenda a partir de un borrador
        this.borradorZapatillas = new Borrador();
        this.borradorZapatillas = borradorZapatillas.definirColorPrimario(colorPrimario);
        this.borradorZapatillas = borradorZapatillas.definirTipo(tipoDePrenda);
        this.borradorZapatillas = borradorZapatillas.definirMaterial(material);
        this.borradorZapatillas = borradorZapatillas.definirTrama(trama);
        this.borradorZapatillas = borradorZapatillas.definirEsParaLLuvia(esParaLluvia);
        this.borradorZapatillas = borradorZapatillas.definirGuardarropa(guardarropa);
        this.zapatoValido = borradorZapatillas.crearPrenda();

        this.eventoPersistente = new Evento("Partido Boca-River","La Boca", LocalDateTime.of(2019,10,22,21,30), Periodo.NINGUNO,2);


    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void mostrarContraseniahasheada() {
        System.out.println(this.contraseniaHasheada);
    }
/*
    @Test
    public void guardarPrendaEnLaBase() {
        try {
            manager.getTransaction().begin();
            manager.persist(zapatoValido);
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


}
