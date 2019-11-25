package domain;

import domain.estadoAtuendo.*;
import domain.guardarropa.Premium;
import domain.usuario.Calendario;
import domain.prenda.Color;
import domain.prenda.Material;
import domain.prenda.TipoDePrenda;
import domain.prenda.Trama;
import org.junit.*;
import exceptions.*;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Set;

public class AtuendoTest {
    private Guardarropa guardarropa;
    private Atuendo atuendoVerano;
    private Prenda musculosa;
    private Prenda blusa;
    private Prenda crocs;
    private Prenda zapatos;
    private Prenda shortDeJean;
    private Prenda pollera;
    private Prenda pañuelo;
    private Prenda anteojos;
    private Prenda bufandaRoja;
    private Prenda guantesCuero;
    private Color color;
    private Usuario carlos;
    private Atuendo atuendoInvalido;
    private Calendario calendario;
    private Set<Prenda> superiores = new HashSet<>();
    private Set<Prenda> inferiores = new HashSet<>();
    private Set<Prenda> calzados = new HashSet<>();

    @Before
    public void iniciarTest() {
        this.carlos = new Usuario("1534544344", calendario, "carlos123","","",null);
        Set<Usuario> carlosLista = new HashSet<>();
        carlosLista.add(carlos);
        this.guardarropa = new Guardarropa("GuardarropaCarlos",0);
        this.color = new Color(1, 2, 3);
        this.musculosa = new Prenda("Musculosa",TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false);
        this.blusa = new Prenda("Blusa",TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false);
        this.crocs = new Prenda("Crocs",TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropa, true);
        this.zapatos = new Prenda("Zapatos",TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropa, true);
        this.shortDeJean = new Prenda("Short",TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropa, false);
        this.pollera = new Prenda("Pollera",TipoDePrenda.POLLERA, Material.JEAN, color, null, Trama.LISA, guardarropa, false);
        this.pañuelo = new Prenda("Panuelo",TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropa, false);
        this.anteojos = new Prenda("Anteojos",TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropa, false);
        this.bufandaRoja = new Prenda("BufandaRoja",TipoDePrenda.BUFANDA, Material.LANA, color, null, Trama.LISA, guardarropa, false);
        this.guantesCuero = new Prenda("Guantes",TipoDePrenda.GUANTES, Material.CUERO, color, null, Trama.LISA, guardarropa, false);
        this.superiores.add(musculosa);
        this.atuendoVerano = new Atuendo(superiores, shortDeJean, crocs, anteojos, bufandaRoja, guantesCuero);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void calificarAtuendoNuevo() {
        exception.expect(NoSePuedeCalificarException.class);
        exception.expectMessage("No se puede calificar un atuendo con estado Nuevo.");
        this.atuendoVerano.calificar(4);
    }

    @Test
    public void calificarAtuendoRechazado() {
        exception.expect(NoSePuedeCalificarException.class);
        exception.expectMessage("No se puede calificar un atuendo con estado Rechazado.");
        //cambiamos el estado del atuando a rechazado para intentar calificarlo
        this.atuendoVerano.cambiarEstado(new Rechazado(this.atuendoVerano));
        this.atuendoVerano.calificar(4);
    }

    @Test
    public void calificarAtuendoAceptado() {
        this.atuendoVerano.cambiarEstado(new Aceptado(this.atuendoVerano));
        this.atuendoVerano.calificar(4);
        Assert.assertEquals(4, this.atuendoVerano.obtenerCalificacionActual());

    }

    @Test
    public void calificarAtuendoFueraDeRango() {
        exception.expect(RangoDeCalificacionException.class);
        exception.expectMessage("La calificación debe estar entre 1 y 5.");
        this.atuendoVerano.cambiarEstado(new Aceptado(this.atuendoVerano));
        this.atuendoVerano.calificar(7);
    }

    @Test
    public void reCalificarAtuendo() {
        this.atuendoVerano.cambiarEstado(new Aceptado(this.atuendoVerano));
        this.atuendoVerano.calificar(4);
        this.atuendoVerano.calificar(5);
        Assert.assertEquals(4, this.atuendoVerano.obtenerCalificacionAnterior());
    }

    @Test
    public void aceptarAtuendoRechazado() {
        exception.expect(NoSePuedeAceptarException.class);
        exception.expectMessage("No se puede aceptar un atuendo con estado Rechazado");
        this.atuendoVerano.cambiarEstado(new Rechazado(this.atuendoVerano));
        this.atuendoVerano.aceptar();
    }

    @Test
    public void aceptarAtuendoAceptado() {
        exception.expect(NoSePuedeAceptarException.class);
        exception.expectMessage("El atuendo ya está con estado Aceptado");
        this.atuendoVerano.cambiarEstado(new Aceptado(this.atuendoVerano));
        this.atuendoVerano.aceptar();
    }

    @Test
    public void aceptarAtuendoCalificado() {
        exception.expect(NoSePuedeAceptarException.class);
        exception.expectMessage("No se puede aceptar un atuendo con estado Calificado");
        this.atuendoVerano.aceptar();
        this.atuendoVerano.calificar(5);
        this.atuendoVerano.aceptar();
    }

    @Test
    public void rechachazarAtuendoAceptado() {
        exception.expect(NoSePuedeRechazarException.class);
        exception.expectMessage("No se puede rechazar un atuendo con estado Aceptado");
        this.atuendoVerano.cambiarEstado(new Aceptado(this.atuendoVerano));
        this.atuendoVerano.rechazar();
    }

    @Test
    public void rechachazarAtuendoCalificado() {
        exception.expect(NoSePuedeRechazarException.class);
        exception.expectMessage("No se puede rechazar un atuendo con estado Calificado");
        this.atuendoVerano.aceptar();
        this.atuendoVerano.calificar(5);
        this.atuendoVerano.rechazar();
    }

    @Test
    public void rechachazarAtuendoRechazado() {
        exception.expect(NoSePuedeRechazarException.class);
        exception.expectMessage("El atuendo ya está con estado Rechazado");
        this.atuendoVerano.cambiarEstado(new Rechazado(this.atuendoVerano));
        this.atuendoVerano.rechazar();
    }

    @Test
    public void validarAtuendo() {
        exception.expect(PrendaInvalidaException.class);
        exception.expectMessage("Una/s de las guardarropas superiores no es válida. La prenda inferior no es válida. La prenda de tipo calzado no es válida. La prenda accesorio no es válida. ");
        Set<Prenda> partesInferiores = new HashSet<>();
        partesInferiores.add(pollera);

        this.atuendoInvalido = new Atuendo(partesInferiores, zapatos, anteojos, musculosa, bufandaRoja, guantesCuero); //con sus partes fuera de orden.
    }

    @Test
    public void validarAccesorioAtuendo() {
        Assert.assertEquals(anteojos, this.atuendoVerano.obtenerAccesorio());
    }

    @Test
    public void validarPrendaSuperiorAtuendo() {
        Assert.assertTrue(this.atuendoVerano.obtenerPrendasSuperiores().contains(this.musculosa));
    }

    @Test
    public void validarPrendaInferiorAtuendo() {
        Assert.assertTrue(this.atuendoVerano.obtenerPrendaInferior().equals(shortDeJean));
    }

    @Test
    public void validarCalzadoAtuendo() {
        Assert.assertTrue(this.atuendoVerano.obtenerCalzado().equals(crocs));
    }

    @Test
    public void validaEstadoAtuendo() {
        Assert.assertEquals("domain.estadoAtuendo.Nuevo", this.atuendoVerano.obtenerEstadoAtuendo().getClass().getName());
    }

}

