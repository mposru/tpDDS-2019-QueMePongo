package db;

import domain.*;
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
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PersistenceTest {
    private EntityManager manager;
    private EntityManagerFactory emf;
    private Evento partidoRiverBoca;
    private LocalDateTime fechaPartido;
    private Usuario alexis;
    private Calendario calendarioVacaciones;
    private Borrador ojotasHavaianasBorrador;
    private Guardarropa guardarropa;
    private Guardarropa guardarropaVerano;
    private Guardarropa guardarropaInvierno;
    private Prenda ojotasHavaianas;
    private TipoDePrenda crocs;
    private TipoDePrenda remeraDeportiva;
    private TipoDePrenda pollera;
    private Imagen imagen;
    private Atuendo atuendoVerano;
    private Prenda musculosa;
    private Prenda blusa;
    private Prenda zapatos;
    private Prenda shortDeJean;
    private Prenda anteojos;
    private Prenda bufandaRoja;
    private Prenda guantesCuero;
    private Color color;
    private Color colorSecundario;
    private Usuario carlos;
    private Prenda polleraDeJean;
    private Usuario diego;
    private Usuario marina;
    private Usuario daiana;
    private Set<Prenda> superiores = new HashSet<>();
    private Set<Usuario> usuariosConFlor = new HashSet<>();
    private Set<Usuario> usuarios = new HashSet<>();
    private Set<Usuario> usuarios2 = new HashSet<>();
    private Evento pizzaALaParri;
    private Evento recitalMonaJimenez;
    private Evento asadoDominguero;
    private Evento casorio;
    private Prenda remeraLisa;
    private Prenda alpargatas;
    private Prenda gorraDeSol;
    private Prenda remeraMangaLarga;
    private Prenda polera;
    private Prenda pantalonDeJean;
    private Prenda gorroDeLana;
    private Prenda remeraLisa2;
    private Prenda zapatillas;
    private Prenda campera;
    private Color color2;
    private Color color3;
    private Color color4;
    private Color color5;
    private Color color6;
    private Calendario calendarioAlexis;
    private Calendario calendarioDiego;
    private Calendario calendarioDaiana;
    private Calendario calendarioMarina;



    @Before
    public void iniciarTest() throws IOException {
        this.emf = Persistence.createEntityManagerFactory("dxffzlciern157vi");
        this.manager = emf.createEntityManager();
        this.fechaPartido = LocalDateTime.of(2019,10,22,21,30,0);

        this.calendarioAlexis = new Calendario();
        this.calendarioDaiana = new Calendario();
        this.calendarioDiego = new Calendario();
        this.calendarioMarina  = new Calendario();
        this.calendarioAlexis.setNombre("Calendario de Alexis");
        this.calendarioDaiana.setNombre("Calendario de Daiana");
        this.calendarioDiego.setNombre("Calendario de Diego");
        this.calendarioMarina.setNombre("Calendario de Marina");


        this.partidoRiverBoca = new Evento("Partido Boca-River","La Boca",fechaPartido , Periodo.NINGUNO,2);
        this.pizzaALaParri = new Evento("Pizzas a la parrilla", "Carapachay",LocalDateTime.of(2019,11,23,21,30,0),Periodo.MENSUAL,0);
        this.recitalMonaJimenez = new Evento("Recital de la mona Jimenez", "Luna Park",LocalDateTime.of(2019,11,9,21,30,0),Periodo.NINGUNO,0);
        this.asadoDominguero = new Evento ("Asado de domingo al mediodia","Los talas del Entrerriano",LocalDateTime.of(2019,11,9,12,30,0),Periodo.MENSUAL,1);
        this.casorio = new Evento ("Casamiento","Pilar",LocalDateTime.of(2019,12,15,21,30,0),Periodo.NINGUNO,1);

        this.imagen = new Imagen();
        this.imagen.leerDeFileSystem("src/imagenes/ojotas.png");
        this.calendarioVacaciones = new Calendario();
        this.calendarioVacaciones.setNombre("Vacaciones norte de Argentina");
        this.alexis = new Usuario("+54911651651",calendarioAlexis,"1234","alexis.dona@gmail.com","Alexis","Dona");
        this.diego = new Usuario("+54911687895",calendarioDiego,"4321","diegoignaciopeccia@gmail.com","Diego","Peccia");
        this.marina = new Usuario("+5491131458855",calendarioMarina,"Marina123","maruposru@gmail.com","Marina","Posru");
        this.daiana = new Usuario("+5491154632210",calendarioDaiana,"daiu","daiu.szwimer@gmail.com","Daiana","Swimmer");
        this.usuarios.add(alexis);
        this.usuarios.add(diego);
        this.usuarios2.add(marina);
        this.usuarios2.add(daiana);
        this.alexis.agregarEvento(partidoRiverBoca);
        this.diego.agregarEvento(partidoRiverBoca);
        this.diego.agregarEvento(pizzaALaParri);
        this.daiana.agregarEvento(asadoDominguero);
        this.marina.agregarEvento(casorio);
        this.alexis.agregarEvento(recitalMonaJimenez);
        this.usuariosConFlor.add(alexis);
        this.guardarropaVerano = new Guardarropa("Guardarropa de verano",100);
        this.guardarropaInvierno = new Guardarropa("Guardarropa de invierno",0);
        this.daiana.agregarGuardarropa(guardarropaInvierno);
        this.marina.agregarGuardarropa(guardarropaInvierno);
        this.alexis.agregarGuardarropa(guardarropaVerano);
        this.diego.agregarGuardarropa(guardarropaVerano);

        this.guardarropaVerano.agregarUsuario(alexis);
        this.guardarropaVerano.agregarUsuario(diego);
        this.guardarropaInvierno.agregarUsuario(marina);
        this.guardarropaInvierno.agregarUsuario(daiana);
        System.out.println(guardarropaInvierno.obtenerUsuarios());
        System.out.println(guardarropaInvierno.obtenerUsuarios());
        System.out.println(alexis.getGuardarropas());
        System.out.println(diego.getGuardarropas());
        System.out.println(marina.getGuardarropas());
        System.out.println(daiana.getGuardarropas());



        this.remeraDeportiva = new TipoDePrenda(Categoria.PARTE_SUPERIOR_ABAJO, Arrays.asList(Material.POLYESTER), 30, 20, "Remera deportiva");
        this.crocs = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.GOMA), 40, 18, "Crocs");
        this.pollera = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.JEAN), 40, 18,"Pollera");
        this.guardarropa = new Guardarropa("GuardarropaFlor",0);

        this.color = new Color(10,11,12);
        this.color2 = new Color(20,0,2);
        this.color3 = new Color(0,100,15);
        this.color4 = new Color(88,50,25);
        this.color5 = new Color(1,20,35);
        this.color6 = new Color(10,5,0);


        this.colorSecundario = new Color (82,16,88);


        // Prendas del guardarropa de verano
        this.musculosa = new Prenda("Musculosa a cuadros",TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropaVerano, false);
        this.remeraLisa = new Prenda("Remera lisa",TipoDePrenda.REMERA, Material.ALGODON, color6,color2, Trama.LISA, guardarropaVerano, false);
        this.alpargatas = new Prenda("Alpargatas",TipoDePrenda.ZAPATO, Material.ALGODON, color4, color5, Trama.LISA, guardarropaVerano, true);
        this.shortDeJean = new Prenda("Short",TipoDePrenda.SHORT, Material.JEAN, color3, color, Trama.LISA, guardarropaVerano, false);
        this.gorraDeSol = new Prenda("Gorra de sol",TipoDePrenda.GORRO, Material.POLYESTER, color2, null, Trama.LISA, guardarropaVerano, false);
        this.guardarropaVerano.guardarPrenda(musculosa);
        this.guardarropaVerano.guardarPrenda(remeraLisa);
        this.guardarropaVerano.guardarPrenda(alpargatas);
        this.guardarropaVerano.guardarPrenda(shortDeJean);
        this.guardarropaVerano.guardarPrenda(gorraDeSol);

        //Guadarropa de invierno
        this.remeraMangaLarga = new Prenda("Remera manga larga a cuadros",TipoDePrenda.REMERA_MANGA_LARGA, Material.ALGODON, color, null, Trama.CUADROS, guardarropaVerano, false);
        this.remeraLisa2 = new Prenda("Remera lisa",TipoDePrenda.REMERA, Material.ALGODON, color, null, Trama.LISA, guardarropaVerano, false);
        this.zapatillas = new Prenda("zapatillas",TipoDePrenda.ZAPATO, Material.ALGODON, color, null, Trama.LISA, guardarropaVerano, true);
        this.pantalonDeJean = new Prenda("pantalon de jean",TipoDePrenda.PANTALON_INVIERNO, Material.JEAN, color, null, Trama.LISA, guardarropaVerano, false);
        this.gorroDeLana = new Prenda("Gorra de lana",TipoDePrenda.GORRO, Material.LANA, color, null, Trama.LISA, guardarropaVerano, false);
        this.polera = new Prenda("Polera",TipoDePrenda.REMERA_MANGA_LARGA, Material.ALGODON, color, null, Trama.CUADROS, guardarropaVerano, false);
        this.campera = new Prenda("Campera uniqlo",TipoDePrenda.CAMPERA, Material.POLYESTER, color, null, Trama.CUADROS, guardarropaVerano, false);
        this.guardarropaInvierno.guardarPrenda(remeraMangaLarga);
        this.guardarropaInvierno.guardarPrenda(remeraLisa2);
        this.guardarropaInvierno.guardarPrenda(zapatillas);
        this.guardarropaInvierno.guardarPrenda(gorroDeLana);
        this.guardarropaInvierno.guardarPrenda(polera);
        this.guardarropaInvierno.guardarPrenda(campera);
        this.remeraDeportiva = new TipoDePrenda(Categoria.PARTE_SUPERIOR_ABAJO, Arrays.asList(Material.POLYESTER), 30, 20, "Remera deportiva");
        this.crocs = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.GOMA), 40, 18, "Crocs");
        this.pollera = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.JEAN), 40, 18,"Pollera");
        this.guardarropa = new Guardarropa("GuardarropaFlor",0);
        this.guardarropa.setNombreGuardarropa("guardarropa formal");

        //creo las ojotas a partir del borrador
        this.ojotasHavaianasBorrador = new Borrador();
        this.ojotasHavaianasBorrador.definirNombre("ojotas havaianas");
        this.ojotasHavaianasBorrador.definirColorPrimario(new Color(10,86,88));
        this.ojotasHavaianasBorrador.definirColorSecundario(colorSecundario);
        this.ojotasHavaianasBorrador.definirTipo(this.crocs);
        this.ojotasHavaianasBorrador.definirMaterial(Material.GOMA);
        this.ojotasHavaianasBorrador.definirTrama(Trama.NINGUNO);
        this.ojotasHavaianasBorrador.definirEsParaLLuvia(true);
        this.ojotasHavaianasBorrador.definirGuardarropa(guardarropa);
        this.ojotasHavaianas =  ojotasHavaianasBorrador.crearPrenda();

        // para el atuendo
        this.musculosa = new Prenda("Musculosa",TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false);
        this.blusa = new Prenda("Blusa",TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false);
        this.zapatos = new Prenda("Zapato",TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropa, true);
        this.shortDeJean = new Prenda("Short",TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropa, false);
        this.polleraDeJean = new Prenda("Pollera",TipoDePrenda.POLLERA, Material.JEAN, color, null, Trama.LISA, guardarropa, false);
        this.anteojos = new Prenda("Anteojos",TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropa, false);
        this.bufandaRoja = new Prenda("Bufanda",TipoDePrenda.BUFANDA, Material.LANA, color, null, Trama.LISA, guardarropa, false);
        this.guantesCuero = new Prenda("Guantes de Cuero",TipoDePrenda.GUANTES, Material.CUERO, color, null, Trama.LISA, guardarropa, false);
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
            manager.persist(diego);
            manager.persist(marina);
            manager.persist(daiana);
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
    public void persistirPrendas() {
        try {
            manager.getTransaction().begin();
            manager.persist(remeraLisa);
            manager.persist(alpargatas);
            manager.persist(gorraDeSol);
            manager.persist(remeraMangaLarga);
            manager.persist(pantalonDeJean);
            manager.persist(gorroDeLana);
            manager.persist(remeraLisa2);
            manager.persist(zapatillas);
            manager.persist(campera);
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

    /* @Test
    public void persistirEventos() {
        try {
            manager.getTransaction().begin();
            manager.persist(partidoRiverBoca);
            manager.persist(pizzaALaParri);
            manager.persist(asadoDominguero);
            manager.persist(recitalMonaJimenez);
            manager.persist(casorio);
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
    public void persistirAtuendoConSusPrendas() {
        try {
            manager.getTransaction().begin();
            manager.persist(atuendoVerano);
            manager.getTransaction().commit();
//            manager.flush();
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
    public void persistirGuardarropa() {
        try {
            manager.getTransaction().begin();
            guardarropaInvierno.obtenerUsuarios().forEach(usuario -> System.out.println(usuario.getEmail()));
            guardarropa.obtenerUsuarios().forEach(usuario -> System.out.println(usuario.getEmail()));
            guardarropaVerano.obtenerUsuarios().forEach(usuario -> System.out.println(usuario.getEmail()));
            manager.persist(guardarropa);
            manager.persist(guardarropaInvierno);
            manager.persist(guardarropaVerano);
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
    public void levantarUsuarioDeBBDDyModificarCelular() {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class,38L);
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

        Usuario usuario = em.find(Usuario.class,38L);
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
