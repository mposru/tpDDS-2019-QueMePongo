package domain;

import domain.TipoDeUsuario.Premium;
import domain.clima.AccuWeather;
import domain.clima.Clima;
import exceptions.*;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    private Prenda prendaVacia;
    private Prenda otraPrendaVacia;
    private Color color;
    private Usuario marta;
    private Usuario flor;

    @Before
    public void iniciarTest() {
        this.color = new Color(1, 2, 3);
        this.musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa,21,30,false);
        this.blusa = new Prenda(TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa,21,30,false);
        this.crocs = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropa,21,30,true);
        this.zapatos = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropa,21,30,true);
        this.shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropa,21,30,false);
        this.pollera = new Prenda(TipoDePrenda.POLLERA, Material.JEAN, color, null, Trama.LISA, guardarropa,21,30,false);
        this.pañuelo = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropa,21,30,false);
        this.anteojos = new Prenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropa,21, 30,false);
        this.prendaVacia = new Prenda(TipoDePrenda.NINGUNO_SUPERIOR, Material.NINGUNO, new Color(0,0, 0), null, Trama.NINGUNO, guardarropa, 0, 0, false);
        this.otraPrendaVacia = new Prenda(TipoDePrenda.NINGUNO_SUPERIOR, Material.NINGUNO, new Color(0,0, 0), null, Trama.NINGUNO, guardarropa, 0, 0, false);
        this.marta = new Usuario(new Premium());
        this.flor = new Usuario(new Premium());
        this.guardarropa = new Guardarropa(marta);
        // mockeo clima
        AccuWeather accuWeather = mock(AccuWeather.class);
        this.guardarropa.definirMeteorologo(accuWeather);
        Clima clima = new Clima(1558917066, 30, 21, 0, 0);
        when(accuWeather.obtenerClima()).thenReturn(clima);
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
    public void definirMasDeUnUsuario() {
        this.guardarropa.definirUsuario(this.flor);
        exception.expect(GuardarropaOcupadoException.class);
        exception.expectMessage("Ya tengo dueño/a, no me podes asignar a otra/o");
        this.guardarropa.definirUsuario(this.marta);
    }

    @Test
    public void sugerirAtuendos() {
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.zapatos);
        this.guardarropa.guardarPrenda(this.shortDeJean);
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.pañuelo);
        this.guardarropa.guardarPrenda(this.anteojos);
        List<Atuendo> sugerencias = this.guardarropa.generarSugerencia();

        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(this.musculosa);
        prendasSuperiores.add(this.prendaVacia);
        prendasSuperiores.add(this.otraPrendaVacia);
        Atuendo primerAtuendo = new Atuendo(prendasSuperiores, shortDeJean, crocs, pañuelo);
        Atuendo segundoAtuendo = new Atuendo(prendasSuperiores, shortDeJean, crocs, anteojos);
        Atuendo tercerAtuendo = new Atuendo(prendasSuperiores, shortDeJean, zapatos, pañuelo);
        Atuendo cuartoAtuendo = new Atuendo(prendasSuperiores, shortDeJean, zapatos, anteojos);
        Atuendo quintoAtuendo = new Atuendo(prendasSuperiores, pollera, crocs, pañuelo);
        Atuendo sextoAtuendo = new Atuendo(prendasSuperiores, pollera, crocs, anteojos);
        Atuendo septimoAtuendo = new Atuendo(prendasSuperiores, pollera, zapatos, pañuelo);
        Atuendo octavoAtuendo = new Atuendo(prendasSuperiores, pollera, zapatos, anteojos);
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
    
}
