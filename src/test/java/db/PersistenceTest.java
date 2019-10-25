package db;

import domain.Atuendo;
import domain.Guardarropa;
import domain.Prenda;
import domain.Usuario;
import domain.guardarropa.Gratuito;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PersistenceTest {
    private EntityManager manager;
    private EntityManagerFactory emf;
    private Evento eventoPersistente;
    private LocalDateTime fechaPartido;
    private Usuario alexis;
    private Calendario calendario;
    private Calendario calendarioVacaciones;
    private Borrador ojotasHavaianasBorrador;
    private Guardarropa guardarropa;
    private Prenda ojotasHavaianas;
    private TipoDePrenda crocs;
    private TipoDePrenda remeraDeportiva;
    private TipoDePrenda pollera;


    private Atuendo atuendoVerano;
    private Prenda musculosa;
    private Prenda blusa;
    private Prenda zapatos;
    private Prenda shortDeJean;
    private Prenda anteojos;
    private Prenda bufandaRoja;
    private Prenda guantesCuero;
    private Color color;
    private Usuario carlos;
    private Prenda polleraDeJean;
    private Set<Prenda> superiores = new HashSet<>();


    @Before
    public void iniciarTest() {
        this.emf = Persistence.createEntityManagerFactory("quemepongo");
        this.manager = emf.createEntityManager();
        this.fechaPartido = LocalDateTime.now();//LocalDateTime.of(2019,10,22,21,30,0);
        //LocalDateTime.of(2019,10,22,21,30,0)
        this.alexis = new Usuario("+54911651651",null,"1234");
        this.alexis.setNombreUsuario("alexis");

        this.eventoPersistente = new Evento("Partido Boca-River","La Boca",fechaPartido , Periodo.NINGUNO,2);
        this.calendario = new Calendario();
        this.calendario.setNombre("calendarioLaboral");
        this.calendarioVacaciones = new Calendario();
        this.calendarioVacaciones.setNombre("Vacaciones norte de Argentina");
        this.alexis.setCalendario(calendarioVacaciones);
        Set<Usuario> usuariosConFlor = new HashSet<>();
        usuariosConFlor.add(alexis);

        this.remeraDeportiva = new TipoDePrenda(Categoria.PARTE_SUPERIOR_ABAJO, Arrays.asList(Material.POLYESTER), 30, 20);
        this.remeraDeportiva.setNombreTipoPrenda("remera deportiva");
        this.crocs = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.GOMA), 40, 18);
        this.crocs.setNombreTipoPrenda("crocs y ojotas");
        this.pollera = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.JEAN), 40, 18);
        this.pollera.setNombreTipoPrenda("Pollera");
        this.guardarropa = new Guardarropa(usuariosConFlor,new Premium());

        //creo las ojotas a partir del borrador
        this.ojotasHavaianasBorrador = new Borrador();
        this.ojotasHavaianasBorrador.definirNombre("ojotas havaianas");
        this.ojotasHavaianasBorrador.definirColorPrimario(new Color(10,86,88));
        this.ojotasHavaianasBorrador.definirTipo(this.crocs);
        this.ojotasHavaianasBorrador.definirMaterial(Material.GOMA);
        this.ojotasHavaianasBorrador.definirTrama(Trama.NINGUNO);
        this.ojotasHavaianasBorrador.definirEsParaLLuvia(true);
        this.ojotasHavaianasBorrador.definirGuardarropa(guardarropa);
        this.ojotasHavaianas =  ojotasHavaianasBorrador.crearPrenda();

        // para el atuendo
        this.musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false, "musculosa");
        this.blusa = new Prenda(TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false, "blusa");
        this.zapatos = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropa, true,"zapato de cuero");
        this.shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropa, false,"short de jean");
        this.polleraDeJean = new Prenda(TipoDePrenda.POLLERA, Material.JEAN, color, null, Trama.LISA, guardarropa, false,"pollera");
        this.anteojos = new Prenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropa, false, "anteojos");
        this.bufandaRoja = new Prenda(TipoDePrenda.BUFANDA, Material.LANA, color, null, Trama.LISA, guardarropa, false,"bufanda");
        this.guantesCuero = new Prenda(TipoDePrenda.GUANTES, Material.CUERO, color, null, Trama.LISA, guardarropa, false,"guantes");
        this.superiores.add(musculosa);
        this.atuendoVerano = new Atuendo(superiores, shortDeJean, ojotasHavaianas, anteojos, bufandaRoja, guantesCuero);
        this.atuendoVerano.setNombre("atuendo veraniego");
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

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
    public void persistirEvento() {
        try {
            manager.getTransaction().begin();
          //  System.out.println("La fecha hora del evento es: "+ eventoPersistente.getFecha());
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
    public void persistirAtuendoConSusPrendas() {
        try {
            manager.getTransaction().begin();
            manager.persist(atuendoVerano);
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
    public void persistirTipoDePrenda() {
        try {
            manager.getTransaction().begin();
            manager.persist(crocs);
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
    public void persistirPrenda() {
        try {
            manager.getTransaction().begin();
            //  System.out.println("La fecha hora del evento es: "+ eventoPersistente.getFecha());
            manager.persist(ojotasHavaianas);
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
    public void persistirGuardarropa() {
        try {
            manager.getTransaction().begin();
            //  System.out.println("La fecha hora del evento es: "+ eventoPersistente.getFecha());
            manager.persist(guardarropa);
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

    @Test
    public void levantarUsuarioDeBBDDyModificarCelular() {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class,1L);
        usuario.setNumeroDeCelular("+15847777");
        try {
            manager.getTransaction().begin();
            manager.merge(usuario);
            manager.getTransaction().commit();
        }
        catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            manager.close();
        }


    }

    @Test
    public void levantarUsuarioDeBBDDyModificarNombre() {
        EntityManager em = emf.createEntityManager();
        //Evento evento = em.find(Evento.class,Long.valueOf(1));
        //evento.getFecha();
        Usuario usuario = em.find(Usuario.class,2L);
        usuario.setNombreUsuario("messi");
        try {
            manager.getTransaction().begin();
            manager.merge(usuario);
            manager.getTransaction().commit();
         //   manager.flush();
        }
        catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            manager.close();
        }


    }
}
