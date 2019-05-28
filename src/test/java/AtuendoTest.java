package domain;

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
    private int calificacion = 0;
    private int calificacionAnterior = 0;
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

    @Before
    public void iniciarTest() {
        this.carlos = new Usuario();
        this.guardarropa = new Guardarropa(carlos);
        this.color = new Color(1, 2, 3);
        this.musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa,28,40,false);
        this.blusa = new Prenda(TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa,25,30,false);
        this.crocs = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropa,0,40,true);
        this.zapatos = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropa,0,20,true);
        this.shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropa,25,40,false);
        this.pollera = new Prenda(TipoDePrenda.POLLERA, Material.JEAN, color, null, Trama.LISA, guardarropa,25,40,false);
        this.pañuelo = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropa,10,25,false);
        this.anteojos = new Prenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropa,0,100,false);
        this.atuendoVerano = new Atuendo(musculosa,shortDeJean,crocs,anteojos);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void calificarAtuendoNuevo() {
        exception.expect(NoSePuedeCalificarException.class);
        exception.expectMessage("Sólo se puede calificar un atuendo aceptado");
        this.atuendoVerano.calificar(4);
    }


}

