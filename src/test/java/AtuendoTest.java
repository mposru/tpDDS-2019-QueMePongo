package domain;

import domain.EstadoAtuendo.*;
import domain.TipoDeUsuario.*;
import org.junit.*;
import exceptions.*;
import org.junit.rules.ExpectedException;

public class AtuendoTest {
    private Guardarropa guardarropa;
    private Prenda accesorio;
    private Prenda prendaSuperior;
    private Prenda prendaInferior;
    private Prenda calzado;
    private EstadoAtuendo estado;
    private Atuendo atuendoVerano;
    private Prenda musculosa;
    private Prenda blusa;
    private Prenda crocs;
    private Prenda zapatos;
    private Prenda shortDeJean;
    private Prenda pollera;
    private Prenda pañuelo;
    private Prenda anteojos;
    private Color color;
    private Usuario carlos;
    private Atuendo atuendoInvalido;



    @Before
    public void iniciarTest() {
        this.carlos = new Usuario(Premium.getInstance());
        this.guardarropa = new Guardarropa(carlos);
        this.color = new Color(1, 2, 3);
        this.musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa);
        this.blusa = new Prenda(TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa);
        this.crocs = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropa);
        this.zapatos = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropa);
        this.shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropa);
        this.pollera = new Prenda(TipoDePrenda.POLLERA, Material.JEAN, color, null, Trama.LISA, guardarropa);
        this.pañuelo = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropa);
        this.anteojos = new Prenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropa);
        this.atuendoVerano = new Atuendo(musculosa,shortDeJean,crocs,anteojos);

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
        exception.expectMessage("La prenda superior no es válida. La prenda inferior no es válida. El calzado no es válido. El accesorio no es válido. ");
        this.atuendoInvalido = new Atuendo(pollera,zapatos,anteojos,blusa); //con sus partes fuera de orden.
    }
    @Test
    public void validarAccesorioAtuendo() {
        Assert.assertEquals(anteojos, this.atuendoVerano.obtenerAccesorio());
    }
    @Test
    public void validarPrendaSuperiorAtuendo() {
        Assert.assertEquals(musculosa, this.atuendoVerano.obtenerPrendaSuperior());
    }
    @Test
    public void validarPrendaInferiorAtuendo() {
        Assert.assertEquals(shortDeJean, this.atuendoVerano.obtenerPrendaInferior());
    }
    @Test
    public void validarCalzadoAtuendo() {
        Assert.assertEquals(crocs, this.atuendoVerano.obtenerCalzado());
    }
    @Test
    public void validaEstadoAtuendo() {
        Assert.assertEquals("domain.EstadoAtuendo.Nuevo", this.atuendoVerano.obtenerEstadoAtuendo().getClass().getName());
    }


}

