package domain;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class GuardarropaTest {
    private Set<Prenda> prendasSuperiores;
    private Set<Prenda> prendasInferiores;
    private Set<Prenda> calzados;
    private Set<Prenda> accesorios;
    private Usuario usuario;
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

    @Before
    public void iniciarTest() {
        this.color = new Color(1, 2, 3);
        this.guardarropa = new Guardarropa();
        this.musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa);
        this.blusa = new Prenda(TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa);
        this.crocs = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropa);
        this.zapatos = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropa);
        this.shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropa);
        this.pollera = new Prenda(TipoDePrenda.POLLERA, Material.JEAN, color, null, Trama.LISA, guardarropa);
        this.pañuelo = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropa);
        this.anteojos = new Prenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropa);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void definirUsuarioVacio() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("El usuario no puede ser vacio");
        this.guardarropa.definirUsuario(null);
    }

    @Test
    public void sugerirAtuendos() throws Exception {
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.zapatos);
        this.guardarropa.guardarPrenda(this.shortDeJean);
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.pañuelo);
        this.guardarropa.guardarPrenda(this.anteojos);
        List<Atuendo> sugerencias = this.guardarropa.generarSugerencia();
        //Atuendo primerAtuendo = new Atuendo(musculosa, shortDeJean, crocs, pañuelo);
        //Atuendo segundoAtuendo = new Atuendo(musculosa, shortDeJean, crocs, anteojos);
        //Atuendo tercerAtuendo = new Atuendo(musculosa, shortDeJean, zapatos, pañuelo);
        //Atuendo cuartoAtuendo = new Atuendo(musculosa, shortDeJean, zapatos, anteojos);
        //Atuendo quintoAtuendo = new Atuendo(musculosa, pollera, crocs, pañuelo);
        //Atuendo sextoAtuendo = new Atuendo(musculosa, pollera, crocs, anteojos);
        //Atuendo septimoAtuendo = new Atuendo(musculosa, pollera, zapatos, pañuelo);
        //Atuendo octavoAtuendo = new Atuendo(musculosa, pollera, zapatos, anteojos);
        //List <Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo, segundoAtuendo, tercerAtuendo, cuartoAtuendo, quintoAtuendo,
        //    sextoAtuendo, septimoAtuendo, octavoAtuendo);
        Assert.assertEquals(8, sugerencias.size());
        sugerencias.forEach(atuendo -> {
            Assert.assertEquals(Categoria.PARTE_SUPERIOR, atuendo.obtenerPrendaSuperior().obtenerCategoria());
            Assert.assertEquals(Categoria.PARTE_INFERIOR, atuendo.obtenerPrendaInferior().obtenerCategoria());
            Assert.assertEquals(Categoria.CALZADO, atuendo.obtenerCalzado().obtenerCategoria());
            Assert.assertEquals(Categoria.ACCESORIO, atuendo.obtenerAccesorio().obtenerCategoria());
        });
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
}
