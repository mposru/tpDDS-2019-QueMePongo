package domain;
import domain.GeneraJson;
import domain.Atuendo;
import domain.Guardarropa;
import domain.Prenda;
import domain.Usuario;
import domain.usuario.Calendario;
import domain.usuario.Evento;
import domain.usuario.Periodo;
import domain.usuario.Sensibilidad;
import domain.usuario.tipoDeUsuario.*;
import domain.usuario.tipoDeUsuario.Premium;
import domain.clima.AccuWeather;
import domain.prenda.Color;
import domain.prenda.Material;
import domain.prenda.TipoDePrenda;
import domain.prenda.Trama;
import exceptions.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.doReturn;

public class GuardarropaTest {
    private Guardarropa guardarropa;
    private Prenda musculosa;
    private Prenda blusa;
    private Prenda campera;
    private Prenda buzo;
    private Prenda crocs;
    private Prenda pantalon;
    private Prenda pantalonPolar;
    private Prenda botasDeNieve;
    private Prenda zapatos;
    private Prenda shortDeJean;
    private Prenda pollera;
    private Prenda ojotas;
    private Prenda pañuelo;
    private Prenda bandana;
    private Prenda bufanda;
    private Prenda anteojos;
    private Prenda prendaVacia;
    private Prenda otraPrendaVacia;
    private Color color;
    private Usuario marta;
    private Usuario flor;
    private Usuario pepita;
    private Guardarropa guardarropaLimitado;
    private Guardarropa guardarropaCompartido;
    //prendas de mi guardarropa limitado
    private Prenda remeraFutbol;
    private Prenda camperaDeportiva;
    private Prenda botines;
    private Prenda zapatillas;
    private Prenda shortDeFutbol;
    private Prenda mediasDeFutbol;
    private Prenda canillera;
    private Calendario calendarioMarta;
    private Calendario calendarioFlor;
    private Calendario calendarioPepita;
    private AccuWeather accuWeather;
    private Evento eventoX;
    private LocalDate dia;
    private Sensibilidad sensibilidad;
    private String jsonClimaAbrigoBasico = GeneraJson.getInstance().dameJSONClimaAbrigoBasico();
    private String jsonClimaAbrigoMediano = GeneraJson.getInstance().dameJSONClimaAbrigoMediano();
    private String jsonClimaAbrigoAlto = GeneraJson.getInstance().dameJSONClimaAbrigoAlto();


    @Before
    public void iniciarTest() {

        dia = Instant.ofEpochMilli(1559188800).atZone(ZoneId.systemDefault()).toLocalDate();
        this.marta = new Usuario(Gratuito.getInstance(),"", calendarioMarta);
        this.flor = new Usuario(Premium.getInstance(),"",calendarioFlor);
        this.pepita = new Usuario(Premium.getInstance(),"", calendarioPepita);
        Set<Usuario> usuariosConFlor = new HashSet<>();
        usuariosConFlor.add(flor);
        Set<Usuario> usuariosConMarta = new HashSet<>();
        usuariosConMarta.add(marta);
        this.guardarropa = new Guardarropa(usuariosConFlor);
        this.guardarropaLimitado = new Guardarropa(usuariosConMarta);
        Set<Usuario> usuarios = new HashSet<>();
        usuarios.add(flor);
        usuarios.add(pepita);
        this.guardarropaCompartido = new Guardarropa(usuarios);

        this.color = new Color(1, 2, 3);
        this.pantalonPolar = new Prenda(TipoDePrenda.PANTALON, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false);
        this.botasDeNieve = new Prenda(TipoDePrenda.BOTAS_NIEVE, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false);
        this.musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.LISA, guardarropa,  false);
        this.blusa = new Prenda(TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false);
        this.campera = new Prenda(TipoDePrenda.CAMPERA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false);
        this.buzo = new Prenda(TipoDePrenda.BUZO, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false);
        this.zapatillas = new Prenda(TipoDePrenda.ZAPATILLAS, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false);
        this.crocs = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropa, true);
        this.ojotas = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropa, true);
        this.pantalon = new Prenda(TipoDePrenda.PANTALON, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, true);
        this.zapatos = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropa, true);
        this.shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropa, false);
        this.pollera = new Prenda(TipoDePrenda.POLLERA, Material.ALGODON, color, null, Trama.LISA, guardarropa, false);
        this.pañuelo = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropa, false);
        this.bandana = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropa, false);
        this.bufanda = new Prenda(TipoDePrenda.BUFANDA, Material.LANA, color, null, Trama.LISA, guardarropa, false);
        this.anteojos = new Prenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropa, false);
        this.prendaVacia = new Prenda(TipoDePrenda.NINGUNO_SUPERIOR, Material.NINGUNO, new Color(0, 0, 0), null, Trama.NINGUNO, guardarropa, false);
        this.otraPrendaVacia = new Prenda(TipoDePrenda.NINGUNO_SUPERIOR, Material.NINGUNO, new Color(0, 0, 0), null, Trama.NINGUNO, guardarropa, false);
        //De guardarropa limitado
        this.remeraFutbol = new Prenda(TipoDePrenda.REMERA, Material.ALGODON, color, null, Trama.ESTAMPADO, guardarropaLimitado, false);
        this.camperaDeportiva = new Prenda(TipoDePrenda.CAMPERA, Material.ALGODON, color, null, Trama.LISA, guardarropaLimitado, false);
        this.botines = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropaLimitado, false);
        this.shortDeFutbol = new Prenda(TipoDePrenda.SHORT, Material.POLYESTER, color, null, Trama.RAYADA, guardarropaLimitado, false);
        this.mediasDeFutbol = new Prenda(TipoDePrenda.MEDIAS, Material.POLYESTER, color, null, Trama.CUADROS, guardarropaLimitado, false);
        this.canillera = new Prenda(TipoDePrenda.CANILLERA, Material.PLASTICO, color, null, Trama.LISA, guardarropaLimitado, false);
        // mockeo clima
        this.accuWeather = Mockito.spy(new AccuWeather());
        doReturn(jsonClimaAbrigoBasico).when(this.accuWeather).getJsonClima();

        doReturn(dia).when(accuWeather).puntoDeReferencia();
        this.eventoX = new Evento("Prueba", "UTN", LocalDateTime.now(), Periodo.NINGUNO,0);

        this.eventoX = new Evento("Prueba", "UTN", LocalDateTime.now(), Periodo.NINGUNO, 0);
        this.sensibilidad = new Sensibilidad();

    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void sugerirAtuendosConAbrigoBasico() {
        doReturn(jsonClimaAbrigoBasico).when(accuWeather).getJsonClima();
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.shortDeJean);
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.pañuelo);
        this.guardarropa.guardarPrenda(this.anteojos);

        List<Atuendo> sugerencias = this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);

        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(this.musculosa);
        prendasSuperiores.add(this.prendaVacia);
        prendasSuperiores.add(this.otraPrendaVacia);
        Set<Prenda> prendasInferiores = new HashSet<>();
        prendasInferiores.add(shortDeJean);
        Set<Prenda> prendasInferiores2 = new HashSet<>();
        prendasInferiores2.add(pollera);
        Set<Prenda> calzados = new HashSet<>();
        calzados.add(crocs);
        Set<Prenda> accesorios = new HashSet<>();
        accesorios.add(anteojos);

        Atuendo primerAtuendo = new Atuendo(prendasSuperiores, prendasInferiores, calzados, accesorios);
        Atuendo segundoAtuendo = new Atuendo(prendasSuperiores, prendasInferiores2, calzados, accesorios);
        List<Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo, segundoAtuendo);
        sugerencias.forEach(sugerencia -> {
            boolean coincide = sugerenciasEsperadas.stream().anyMatch(sugerenciaEsperada ->
                    sugerenciaEsperada.obtenerAccesorios() == sugerencia.obtenerAccesorios() &&
                            sugerenciaEsperada.obtenerCalzados() == sugerencia.obtenerCalzados() &&
                            sugerenciaEsperada.obtenerPrendasInferiores() == sugerencia.obtenerPrendasInferiores()
            );
            Assert.assertTrue(coincide);
            //prendasSuperiores.retainAll(sugerencia.obtenerPrendasSuperiores());
            Assert.assertTrue(sugerencia.obtenerPrendasSuperiores().size() == 3);
        });
        Assert.assertEquals(sugerenciasEsperadas.size(), sugerencias.size());
    }

    @Test
    public void sugerirAtuendosConAbrigoMediano() {
        doReturn(jsonClimaAbrigoMediano).when(accuWeather).getJsonClima();
        this.guardarropa.guardarPrenda(this.musculosa); // valido
        this.guardarropa.guardarPrenda(this.buzo); // valido
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.zapatillas); // valido
        this.guardarropa.guardarPrenda(this.pantalon); // valido
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.pañuelo); // valido

        List<Atuendo> sugerencias = this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);

        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(this.musculosa);
        prendasSuperiores.add(this.buzo);
        prendasSuperiores.add(this.prendaVacia);
        Set<Prenda> prendasInferiores = new HashSet<>();
        prendasInferiores.add(pantalon);
        Set<Prenda> calzados = new HashSet<>();
        calzados.add(zapatillas);
        Set<Prenda> accesorios = new HashSet<>();
        accesorios.add(pañuelo);
        Atuendo primerAtuendo = new Atuendo(prendasSuperiores, prendasInferiores, calzados, accesorios);
        List<Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo);
        sugerencias.forEach(sugerencia -> {
            boolean coincide = sugerenciasEsperadas.stream().anyMatch(sugerenciaEsperada ->
                    sugerenciaEsperada.obtenerAccesorios() == sugerencia.obtenerAccesorios() &&
                            sugerenciaEsperada.obtenerCalzados() == sugerencia.obtenerCalzados() &&
                            sugerenciaEsperada.obtenerPrendasInferiores() == sugerencia.obtenerPrendasInferiores()
            );
            //prendasSuperiores.retainAll(sugerencia.obtenerPrendasSuperiores());
            //Assert.assertTrue(prendasSuperiores.size() == 3);
            Assert.assertTrue(coincide);
            Assert.assertTrue(sugerencia.obtenerPrendasSuperiores().size() == 3);
        });
        Assert.assertEquals(sugerenciasEsperadas.size(), sugerencias.size());
    }

    @Test
    public void sugerirAtuendosConAbrigoAlto() {
        doReturn(jsonClimaAbrigoAlto).when(accuWeather).getJsonClima();
        this.guardarropa.guardarPrenda(this.musculosa); // valido
        this.guardarropa.guardarPrenda(this.buzo); // valido
        this.guardarropa.guardarPrenda(this.campera); // valido
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.botasDeNieve); // valido
        this.guardarropa.guardarPrenda(this.pantalonPolar); // valido
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.bufanda); // valido

        List<Atuendo> sugerencias = this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);

        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(this.musculosa);
        prendasSuperiores.add(this.buzo);
        prendasSuperiores.add(this.campera);
        Set<Prenda> prendasInferiores = new HashSet<>();
        prendasInferiores.add(pantalonPolar);
        Set<Prenda> calzados = new HashSet<>();
        calzados.add(botasDeNieve);
        Set<Prenda> accesorios = new HashSet<>();
        accesorios.add(bufanda);
        Atuendo primerAtuendo = new Atuendo(prendasSuperiores, prendasInferiores, calzados, accesorios);
        List<Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo);
        sugerencias.forEach(sugerencia -> {
            boolean coincide = sugerenciasEsperadas.stream().anyMatch(sugerenciaEsperada ->
                    sugerenciaEsperada.obtenerAccesorios() == sugerencia.obtenerAccesorios() &&
                            sugerenciaEsperada.obtenerCalzados() == sugerencia.obtenerCalzados() &&
                            sugerenciaEsperada.obtenerPrendasInferiores() == sugerencia.obtenerPrendasInferiores()
            );
            //prendasSuperiores.retainAll(sugerencia.obtenerPrendasSuperiores());
            //Assert.assertTrue(prendasSuperiores.size() == 3);
            Assert.assertTrue(coincide);
            Assert.assertTrue(sugerencia.obtenerPrendasSuperiores().size() == 3);
        });
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
        this.guardarropa.guardarPrenda(this.anteojos);
        this.guardarropa.guardarPrenda(this.pollera);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan prendas superiores adecuadas para el clima del evento. ");

        this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);
    }

    @Test
    public void noSePuedeSugerirSinCalzado() {
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.anteojos);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan zapatos adecuados para el clima del evento. ");

        this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);
    }

    @Test
    public void noSePuedeSugerirSinAccesorio() {
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.crocs);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan accesorios adecuados para el clima del evento. ");
        this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);
    }

    @Test
    public void noSePuedeSugerirSinParteInferior() {
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.anteojos);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan prendas inferiores adecuadas para el clima del evento. ");

        this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);
    }

    @Test
    public void superarLimiteDePrendas() {
        exception.expect(SuperaLimiteDePrendasException.class);
        exception.expectMessage("Se supera el límite de " + guardarropaLimitado.limiteDePrendas() + " prendas definido para el tipo de usuario del guardarropa");
        this.guardarropaLimitado.guardarPrenda(this.remeraFutbol);
        this.guardarropaLimitado.guardarPrenda(this.camperaDeportiva);
        this.guardarropaLimitado.guardarPrenda(this.botines);
        this.guardarropaLimitado.guardarPrenda(this.shortDeFutbol);
        this.guardarropaLimitado.guardarPrenda(this.mediasDeFutbol);
        this.guardarropaLimitado.guardarPrenda(this.canillera);
        this.guardarropaLimitado.guardarPrenda(this.anteojos);
    }

    @Test
    public void noPuedoUsarPrendaQueFueAceptada() {
        doReturn(jsonClimaAbrigoBasico).when(accuWeather).getJsonClima();
        this.guardarropaCompartido.guardarPrenda(this.remeraFutbol);
        this.guardarropaCompartido.guardarPrenda(this.musculosa);
        this.guardarropaCompartido.guardarPrenda(this.crocs);
        this.guardarropaCompartido.guardarPrenda(this.ojotas);
        this.guardarropaCompartido.guardarPrenda(this.shortDeJean);
        this.guardarropaCompartido.guardarPrenda(this.pollera);
        this.guardarropaCompartido.guardarPrenda(this.bandana);
        this.guardarropaCompartido.guardarPrenda(this.anteojos);
        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(this.musculosa);
        prendasSuperiores.add(this.prendaVacia);
        prendasSuperiores.add(this.otraPrendaVacia);
        Set<Prenda> prendasInferiores = new HashSet<>();
        prendasInferiores.add(shortDeJean);
        Set<Prenda> calzados = new HashSet<>();
        calzados.add(crocs);
        Set<Prenda> accesorios = new HashSet<>();
        accesorios.add(anteojos);
        Atuendo primerAtuendo = new Atuendo(prendasSuperiores, prendasInferiores, calzados, accesorios);
        flor.aceptarAtuendo(primerAtuendo);
        // cuando pepita pide las sugerencias, no puede tener la ropa del primer atuendo

        List<Atuendo> sugerencias = this.guardarropaCompartido.generarSugerencia(this.eventoX, this.sensibilidad);
        Set<Prenda> prendasSuperiores2 = new HashSet<>();
        prendasSuperiores2.add(this.remeraFutbol);
        prendasSuperiores2.add(this.buzo);
        prendasSuperiores2.add(this.campera);
        Set<Prenda> prendasInferiores2 = new HashSet<>();
        prendasInferiores2.add(pollera);
        Set<Prenda> calzados2 = new HashSet<>();
        calzados.add(ojotas);
        Set<Prenda> accesorios2 = new HashSet<>();
        accesorios2.add(bandana);
        Atuendo primerAtuendo2 = new Atuendo(prendasSuperiores2, prendasInferiores2, calzados2, accesorios2);
        List<Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo2);
        sugerencias.forEach(sugerencia -> {
            boolean coincide = sugerenciasEsperadas.stream().anyMatch(sugerenciaEsperada ->
                    sugerenciaEsperada.obtenerAccesorios() == sugerencia.obtenerAccesorios() &&
                            sugerenciaEsperada.obtenerCalzados() == sugerencia.obtenerCalzados() &&
                            sugerenciaEsperada.obtenerPrendasInferiores() == sugerencia.obtenerPrendasInferiores()
            );
            //prendasSuperiores.retainAll(sugerencia.obtenerPrendasSuperiores());
            //Assert.assertTrue(prendasSuperiores.size() == 3);
            Assert.assertTrue(coincide);
            Assert.assertTrue(sugerencia.obtenerPrendasSuperiores().size() == 3);
        });
        Assert.assertEquals(sugerenciasEsperadas.size(), sugerencias.size());
    }

}
