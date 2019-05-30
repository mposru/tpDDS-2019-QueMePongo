package domain;

import domain.TipoDeUsuario.*;
import exceptions.*;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

public class GuardarropaTest {
    private Guardarropa guardarropa;
    private Prenda musculosa;
    private Prenda blusa;
    private Prenda crocs;
    private Prenda zapatos;
    private Prenda shortDeJean;
    private Prenda pollera;
    private Prenda pañuelo;
    private Prenda anteojos;
    private Color color;
    private Usuario marta;
    private Usuario flor;
    private Guardarropa guardarropaLimitado;
    //prendas de mi guardarropa limitado
    private Prenda remeraFutbol;
    private Prenda camperaDeportiva;
    private Prenda botines;
    private Prenda zapatillas;
    private Prenda shortDeFutbol;
    private Prenda mediasDeFutbol;
    private Prenda canillera;

    @Before
    public void iniciarTest() {
        this.color = new Color(1, 2, 3);
        this.musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa);
        this.blusa = new Prenda(TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa);
        this.crocs = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropa);
        this.zapatos = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropa);
        this.shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropa);
        this.pollera = new Prenda(TipoDePrenda.POLLERA, Material.JEAN, color, null, Trama.LISA, guardarropa);
        this.pañuelo = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropa);
        this.anteojos = new Prenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropa);
        this.marta = new Usuario(Gratuito.getInstance());
        this.flor = new Usuario(Premium.getInstance());
        this.guardarropa = new Guardarropa(flor);
        this.guardarropaLimitado = new Guardarropa(marta);
        //De guardarropa limitado
        this.remeraFutbol= new Prenda(TipoDePrenda.REMERA, Material.ALGODON, color, null, Trama.ESTAMPADO, guardarropaLimitado);;
        this.camperaDeportiva= new Prenda(TipoDePrenda.CAMPERA,Material.ALGODON,color,null,Trama.LISA,guardarropaLimitado);
        this.botines= new Prenda(TipoDePrenda.ZAPATO,Material.CUERO,color,null,Trama.LISA,guardarropaLimitado);
        this.shortDeFutbol=new Prenda(TipoDePrenda.SHORT,Material.POLYESTER,color,null,Trama.RAYADA,guardarropaLimitado);
        this.mediasDeFutbol=new Prenda(TipoDePrenda.MEDIAS,Material.POLYESTER,color,null,Trama.CUADROS,guardarropaLimitado);
        this.canillera=new Prenda(TipoDePrenda.CANILLERA,Material.PLASTICO,color,null,Trama.LISA,guardarropaLimitado);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void sugerirAtuendos() {
        //todo: fixear test, creo que se esta guardando la data que se setea en los otros tests
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.zapatos);
        this.guardarropa.guardarPrenda(this.shortDeJean);
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.pañuelo);
        this.guardarropa.guardarPrenda(this.anteojos);
        List<Atuendo> sugerencias = this.guardarropa.generarSugerencia();
        Atuendo primerAtuendo = new Atuendo(musculosa, shortDeJean, crocs, pañuelo);
        Atuendo segundoAtuendo = new Atuendo(musculosa, shortDeJean, crocs, anteojos);
        Atuendo tercerAtuendo = new Atuendo(musculosa, shortDeJean, zapatos, pañuelo);
        Atuendo cuartoAtuendo = new Atuendo(musculosa, shortDeJean, zapatos, anteojos);
        Atuendo quintoAtuendo = new Atuendo(musculosa, pollera, crocs, pañuelo);
        Atuendo sextoAtuendo = new Atuendo(musculosa, pollera, crocs, anteojos);
        Atuendo septimoAtuendo = new Atuendo(musculosa, pollera, zapatos, pañuelo);
        Atuendo octavoAtuendo = new Atuendo(musculosa, pollera, zapatos, anteojos);
        List <Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo, segundoAtuendo, tercerAtuendo, cuartoAtuendo, quintoAtuendo,
                sextoAtuendo, septimoAtuendo, octavoAtuendo);
        sugerenciasEsperadas.forEach(sugerencia -> Assert.assertTrue(sugerencias.contains(sugerencia)));
        Assert.assertEquals(sugerenciasEsperadas.size(), sugerencias.size());
    }

    @Test
    public void guardarPartesSuperiores() {
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.blusa);
        Assert.assertTrue(this.guardarropa.obtenerPrendasSuperiores().contains(this.musculosa));
        Assert.assertTrue(this.guardarropa.obtenerPrendasSuperiores().contains(this.blusa));
        Assert.assertEquals(2, this.guardarropa.obtenerPrendasSuperiores().size());
    }

    @Test
    public void guardarPartesInferiores() {
        this.guardarropa.guardarPrenda(this.shortDeJean);
        this.guardarropa.guardarPrenda(this.pollera);
        Assert.assertTrue(this.guardarropa.obtenerPrendasInferiores().contains(this.shortDeJean));
        Assert.assertTrue(this.guardarropa.obtenerPrendasInferiores().contains(this.pollera));
        Assert.assertEquals(2, this.guardarropa.obtenerPrendasInferiores().size());
    }

    @Test
    public void guardarCalzados() {
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.zapatos);
        Assert.assertTrue(this.guardarropa.obtenerCalzados().contains(this.crocs));
        Assert.assertTrue(this.guardarropa.obtenerCalzados().contains(this.zapatos));
        Assert.assertEquals(2, this.guardarropa.obtenerCalzados().size());
    }


    @Test
    public void guardarAccesorios() {
        this.guardarropa.guardarPrenda(this.pañuelo);
        this.guardarropa.guardarPrenda(this.anteojos);
        Assert.assertTrue(this.guardarropa.obtenerAccesorios().contains(this.pañuelo));
        Assert.assertTrue(this.guardarropa.obtenerAccesorios().contains(this.anteojos));
        Assert.assertEquals(2, this.guardarropa.obtenerAccesorios().size());
    }

    @Test
    public void noSePuedeSugerirSinParteSuperior() {
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.pañuelo);
        this.guardarropa.guardarPrenda(this.pollera);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan prendas superiores. ");
        this.guardarropa.generarSugerencia();
    }

    @Test
    public void noSePuedeSugerirSinCalzado() {
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.blusa);
        this.guardarropa.guardarPrenda(this.pañuelo);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan zapatos. ");
        this.guardarropa.generarSugerencia();
    }

    @Test
    public void noSePuedeSugerirSinAccesorio() {
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.blusa);
        this.guardarropa.guardarPrenda(this.crocs);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan accesorios. ");
        this.guardarropa.generarSugerencia();
    }

    @Test
    public void noSePuedeSugerirSinParteInferior() {
        this.guardarropa.guardarPrenda(this.blusa);
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.pañuelo);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan prendas inferiores. ");
        this.guardarropa.generarSugerencia();
    }
    @Test
    public void superarLimiteDePrendas() {
        exception.expect(SuperaLimiteDePrendasException.class);
        exception.expectMessage("Se supera el límite de "+guardarropaLimitado.limiteDePrendas() + " prendas definido para el tipo de usuario del guardarropa");
        this.guardarropaLimitado.guardarPrenda(this.remeraFutbol);
        this.guardarropaLimitado.guardarPrenda(this.camperaDeportiva);
        this.guardarropaLimitado.guardarPrenda(this.botines);
        this.guardarropaLimitado.guardarPrenda(this.shortDeFutbol);
        this.guardarropaLimitado.guardarPrenda(this.mediasDeFutbol);
        this.guardarropaLimitado.guardarPrenda(this.canillera);
    }

}
