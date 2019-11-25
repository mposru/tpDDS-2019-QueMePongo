
import domain.*;

import domain.clima.AccuWeather;
import domain.clima.Alerta;
import domain.guardarropa.Gratuito;
import domain.guardarropa.Premium;
import domain.usuario.Calendario;
import domain.usuario.Evento;
import domain.usuario.Periodo;
import domain.prenda.Color;
import domain.prenda.Material;
import domain.prenda.TipoDePrenda;
import domain.prenda.Trama;
import exceptions.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.doReturn;

public class UsuarioTest {

    private Guardarropa guardarropaDeMerlin;
    private Guardarropa guardarropaDeMaria;
    private Guardarropa guardarropaDeMarina;
    private Usuario merlin;
    private Usuario maria;
    private Atuendo atuendoVerano;
    private Prenda musculosa;
    private Prenda blusa;
    private Prenda crocs;
    private Prenda zapatos;
    private Prenda shortDeJean;
    private Prenda pollera;
    private Prenda pañuelo;
    private Prenda anteojos;
    private Prenda ningunAccesorioManos;
    private Prenda chalina;
    private Color color;
    private Usuario nana;
    private Calendario calendarioMaria;
    private Calendario calendarioMerlin;
    private Calendario calendarioNana;

    private AccuWeather accuWeather;
    private String jsonClima = "{\n" +
            "    \"Headline\": {\n" +
            "        \"EffectiveDate\": \"2019-05-30T01:00:00-03:00\",\n" +
            "        \"EffectiveEpochDate\": 1559188800,\n" +
            "        \"Severity\": 5,\n" +
            "        \"Text\": \"Previsión de chubascos el miércoles a última hora de la noche\",\n" +
            "        \"Category\": \"rain\",\n" +
            "        \"EndDate\": \"2019-05-30T07:00:00-03:00\",\n" +
            "        \"EndEpochDate\": 1559210400,\n" +
            "        \"MobileLink\": \"http://m.accuweather.com/es/ar/buenos-aires/7894/extended-weather-forecast/7894?unit=c&lang=es-ar\",\n" +
            "        \"Link\": \"http://www.accuweather.com/es/ar/buenos-aires/7894/daily-weather-forecast/7894?unit=c&lang=es-ar\"\n" +
            "    },\n" +
            "    \"DailyForecasts\": [\n" +
            "        {\n" +
            "            \"Date\": \"2019-05-26T07:00:00-03:00\",\n" +
            "            \"EpochDate\": 1558864800,\n" +
            "            \"Sun\": {\n" +
            "                \"Rise\": \"2019-05-26T07:48:00-03:00\",\n" +
            "                \"EpochRise\": 1558867680,\n" +
            "                \"Set\": \"2019-05-26T17:53:00-03:00\",\n" +
            "                \"EpochSet\": 1558903980\n" +
            "            },\n" +
            "            \"Moon\": {\n" +
            "                \"Rise\": \"2019-05-26T00:05:00-03:00\",\n" +
            "                \"EpochRise\": 1558839900,\n" +
            "                \"Set\": \"2019-05-26T13:48:00-03:00\",\n" +
            "                \"EpochSet\": 1558889280,\n" +
            "                \"Phase\": \"Last\",\n" +
            "                \"Age\": 22\n" +
            "            },\n" +
            "            \"Temperature\": {\n" +
            "                \"Minimum\": {\n" +
            "                    \"Value\": 21,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                },\n" +
            "                \"Maximum\": {\n" +
            "                    \"Value\": 30,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                }\n" +
            "            },\n" +
            "            \"RealFeelTemperature\": {\n" +
            "                \"Minimum\": {\n" +
            "                    \"Value\": 21,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                },\n" +
            "                \"Maximum\": {\n" +
            "                    \"Value\": 30,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                }\n" +
            "            },\n" +
            "            \"RealFeelTemperatureShade\": {\n" +
            "                \"Minimum\": {\n" +
            "                    \"Value\": 15.0,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                },\n" +
            "                \"Maximum\": {\n" +
            "                    \"Value\": 18.3,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                }\n" +
            "            },\n" +
            "            \"HoursOfSun\": 3.9,\n" +
            "            \"DegreeDaySummary\": {\n" +
            "                \"Heating\": {\n" +
            "                    \"Value\": 2.0,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                },\n" +
            "                \"Cooling\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                }\n" +
            "            },\n" +
            "            \"AirAndPollen\": [\n" +
            "                {\n" +
            "                    \"Name\": \"AirQuality\",\n" +
            "                    \"Value\": 0,\n" +
            "                    \"Category\": \"Bueno\",\n" +
            "                    \"CategoryValue\": 1,\n" +
            "                    \"Type\": \"Contaminación por partículas\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Name\": \"Grass\",\n" +
            "                    \"Value\": 0,\n" +
            "                    \"Category\": \"Bajo\",\n" +
            "                    \"CategoryValue\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Name\": \"Mold\",\n" +
            "                    \"Value\": 0,\n" +
            "                    \"Category\": \"Bajo\",\n" +
            "                    \"CategoryValue\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Name\": \"Tree\",\n" +
            "                    \"Value\": 0,\n" +
            "                    \"Category\": \"Bajo\",\n" +
            "                    \"CategoryValue\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Name\": \"Ragweed\",\n" +
            "                    \"Value\": 0,\n" +
            "                    \"Category\": \"Bajo\",\n" +
            "                    \"CategoryValue\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Name\": \"UVIndex\",\n" +
            "                    \"Value\": 2,\n" +
            "                    \"Category\": \"Bajo\",\n" +
            "                    \"CategoryValue\": 1\n" +
            "                }\n" +
            "            ],\n" +
            "            \"Day\": {\n" +
            "                \"Icon\": 6,\n" +
            "                \"IconPhrase\": \"Mayormente nublado\",\n" +
            "                \"ShortPhrase\": \"Tornándose nublado\",\n" +
            "                \"LongPhrase\": \"Tornándose nublado\",\n" +
            "                \"PrecipitationProbability\": 0,\n" +
            "                \"ThunderstormProbability\": 0,\n" +
            "                \"RainProbability\": 0,\n" +
            "                \"SnowProbability\": 0,\n" +
            "                \"IceProbability\": 0,\n" +
            "                \"Wind\": {\n" +
            "                    \"Speed\": {\n" +
            "                        \"Value\": 11.3,\n" +
            "                        \"Unit\": \"km/h\",\n" +
            "                        \"UnitType\": 7\n" +
            "                    },\n" +
            "                    \"Direction\": {\n" +
            "                        \"Degrees\": 327,\n" +
            "                        \"Localized\": \"NNO\",\n" +
            "                        \"English\": \"NNW\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"WindGust\": {\n" +
            "                    \"Speed\": {\n" +
            "                        \"Value\": 22.5,\n" +
            "                        \"Unit\": \"km/h\",\n" +
            "                        \"UnitType\": 7\n" +
            "                    },\n" +
            "                    \"Direction\": {\n" +
            "                        \"Degrees\": 327,\n" +
            "                        \"Localized\": \"NNO\",\n" +
            "                        \"English\": \"NNW\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"TotalLiquid\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"mm\",\n" +
            "                    \"UnitType\": 3\n" +
            "                },\n" +
            "                \"Rain\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"mm\",\n" +
            "                    \"UnitType\": 3\n" +
            "                },\n" +
            "                \"Snow\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"cm\",\n" +
            "                    \"UnitType\": 4\n" +
            "                },\n" +
            "                \"Ice\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"mm\",\n" +
            "                    \"UnitType\": 3\n" +
            "                },\n" +
            "                \"HoursOfPrecipitation\": 0.0,\n" +
            "                \"HoursOfRain\": 0.0,\n" +
            "                \"HoursOfSnow\": 0.0,\n" +
            "                \"HoursOfIce\": 0.0,\n" +
            "                \"CloudCover\": 86\n" +
            "            },\n" +
            "            \"Night\": {\n" +
            "                \"Icon\": 36,\n" +
            "                \"IconPhrase\": \"Nubes intermitentes\",\n" +
            "                \"ShortPhrase\": \"Nubosidad variable\",\n" +
            "                \"LongPhrase\": \"Nubosidad variable\",\n" +
            "                \"PrecipitationProbability\": 0,\n" +
            "                \"ThunderstormProbability\": 0,\n" +
            "                \"RainProbability\": 0,\n" +
            "                \"SnowProbability\": 0,\n" +
            "                \"IceProbability\": 0,\n" +
            "                \"Wind\": {\n" +
            "                    \"Speed\": {\n" +
            "                        \"Value\": 3.2,\n" +
            "                        \"Unit\": \"km/h\",\n" +
            "                        \"UnitType\": 7\n" +
            "                    },\n" +
            "                    \"Direction\": {\n" +
            "                        \"Degrees\": 307,\n" +
            "                        \"Localized\": \"NO\",\n" +
            "                        \"English\": \"NW\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"WindGust\": {\n" +
            "                    \"Speed\": {\n" +
            "                        \"Value\": 12.9,\n" +
            "                        \"Unit\": \"km/h\",\n" +
            "                        \"UnitType\": 7\n" +
            "                    },\n" +
            "                    \"Direction\": {\n" +
            "                        \"Degrees\": 307,\n" +
            "                        \"Localized\": \"NO\",\n" +
            "                        \"English\": \"NW\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"TotalLiquid\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"mm\",\n" +
            "                    \"UnitType\": 3\n" +
            "                },\n" +
            "                \"Rain\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"mm\",\n" +
            "                    \"UnitType\": 3\n" +
            "                },\n" +
            "                \"Snow\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"cm\",\n" +
            "                    \"UnitType\": 4\n" +
            "                },\n" +
            "                \"Ice\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"mm\",\n" +
            "                    \"UnitType\": 3\n" +
            "                },\n" +
            "                \"HoursOfPrecipitation\": 0.0,\n" +
            "                \"HoursOfRain\": 0.0,\n" +
            "                \"HoursOfSnow\": 0.0,\n" +
            "                \"HoursOfIce\": 0.0,\n" +
            "                \"CloudCover\": 69\n" +
            "            },\n" +
            "            \"Sources\": [\n" +
            "                \"AccuWeather\"\n" +
            "            ],\n" +
            "            \"MobileLink\": \"http://m.accuweather.com/es/ar/buenos-aires/7894/daily-weather-forecast/7894?day=1&unit=c&lang=es-ar\",\n" +
            "            \"Link\": \"http://www.accuweather.com/es/ar/buenos-aires/7894/daily-weather-forecast/7894?day=1&unit=c&lang=es-ar\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Before
    public void iniciarTest() {
        this.calendarioMaria = new Calendario();
        this.calendarioNana = new Calendario();
        this.calendarioMerlin = new Calendario();
        this.accuWeather = Mockito.spy(new AccuWeather());
        this.merlin = new Usuario( "1543333322", calendarioMerlin,"contraseniaLoca","","",null);
        this.maria = new Usuario("1543333322",calendarioMaria, "contraseniaLoca","","",null);
        Set<Usuario> merlinLista = new HashSet<>();
        merlinLista.add(merlin);
        Set<Usuario> mariaLista = new HashSet<>();
        mariaLista.add(maria);
        this.guardarropaDeMerlin = new Guardarropa("GuardarropaMerlin",5);
        this.guardarropaDeMaria = new Guardarropa("GuardarropaMaria",0);
        this.color = new Color(1, 2, 3);
        this.musculosa = new Prenda("Musculosa",TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropaDeMerlin,false);
        this.blusa = new Prenda("Blusa",TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropaDeMerlin,false);
        this.crocs = new Prenda("Crocs",TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropaDeMerlin,false);
        this.zapatos = new Prenda("Zapatos",TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropaDeMerlin,false);
        this.shortDeJean = new Prenda("ShortDeJean",TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropaDeMerlin,false);
        this.pollera = new Prenda("Pollera",TipoDePrenda.POLLERA, Material.JEAN, color, null, Trama.LISA, guardarropaDeMerlin,false);
        this.pañuelo = new Prenda("Pañuelo",TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropaDeMerlin,false);
        this.anteojos = new Prenda("Anteojos",TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropaDeMerlin,false);
        this.chalina = new Prenda("Chalina",TipoDePrenda.CHALINA, Material.SEDA, color, null, Trama.LISA, guardarropaDeMerlin, true);
        this.ningunAccesorioManos = new Prenda("Nada",TipoDePrenda.ACCESORIO_VACIO_MANOS, Material.NINGUNO, color, null, Trama.LISA, guardarropaDeMerlin, true);
        Set<Prenda> superiores = new HashSet<>();
        superiores.add(musculosa);
        this.atuendoVerano = new Atuendo(superiores, shortDeJean, crocs, anteojos, chalina, ningunAccesorioManos);
        this.nana = new Usuario( "1534433333",calendarioNana,"Contrasenia123","","",null);
        doReturn(jsonClima).when(accuWeather).getJsonClima();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void guardarropaGratuitoTieneLimiteDePrendas(){
        Assert.assertTrue(this.guardarropaDeMerlin.tieneLimiteDePrendas());
    }
    @Test
    public void guardarropaPremiumNoTieneLimiteDePrendas(){
        Assert.assertFalse(this.guardarropaDeMaria.tieneLimiteDePrendas());
    }

    @Test
    public void usuarioAceptaAtuendo(){
        merlin.aceptarAtuendo(atuendoVerano);
        Assert.assertTrue(merlin.obtenerAtuendosAceptados().contains(atuendoVerano));
    }
    @Test
    public void usuarioRechazaAtuendo(){
        merlin.rechazarAtuendo(atuendoVerano);
        Assert.assertTrue(merlin.obtenerAtuendosRechazados().contains(atuendoVerano));
    }
    @Test
    public void usuarioDeshaceUltimaDecisionVacia(){
        exception.expect(PilaVaciaException.class);
        exception.expectMessage("No hay decisiones por deshacer");
        merlin.deshacer();

    }
    @Test
    public void usuarioDeshaceUltimaDecision(){
        merlin.aceptarAtuendo(atuendoVerano);
        merlin.calificarAtuendo(atuendoVerano,5);
        merlin.deshacer();
        Assert.assertEquals("domain.estadoAtuendo.Aceptado", this.atuendoVerano.obtenerEstadoAtuendo().getClass().getName());
    }
    @Test
    public void cantidadDeOperaciones(){
        merlin.aceptarAtuendo(atuendoVerano);
        merlin.calificarAtuendo(atuendoVerano,5);
        merlin.calificarAtuendo(atuendoVerano,4);
        merlin.calificarAtuendo(atuendoVerano,3);
        Assert.assertEquals(4, merlin.obtenerDecisiones().size());
    }
    @Test
    public void usuarioDeshaceUltimaDecisionAceptada(){
        merlin.aceptarAtuendo(atuendoVerano);
        merlin.deshacer();
        Assert.assertEquals(0, merlin.obtenerAtuendosAceptados().size());
    }

    @Test
    public void usuarioDeshaceUltimaDecisionRechazada(){
        merlin.rechazarAtuendo(atuendoVerano);
        merlin.deshacer();
        Assert.assertEquals(0, merlin.obtenerAtuendosRechazados().size());
    }
 // Arreglar este test porque usa meteorologo
    /*
    @Test
    public void obtenerSugerenciasParaProximoEvento() {
        nana.getCalendario().agregarEvento(new Evento("Evento 1", "Palermo", LocalDateTime.now().plusDays(1), Periodo.NINGUNO,0));
        nana.getCalendario().agregarEvento(new Evento("Evento 2", "Recoleta", LocalDateTime.now(),Periodo.NINGUNO,0));
        nana.getCalendario().agregarEvento(new Evento("Evento 3", "Villa Ballester", LocalDateTime.now().plusDays(3),Periodo.NINGUNO,0));
        Set<Usuario> usuarios = new HashSet<>();
        usuarios.add(nana);
        this.guardarropaDeMarina = new Guardarropa(usuarios);

        Color color = new Color(1, 2, 3);
        Prenda musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.LISA, guardarropaDeMarina,false);
        Prenda crocs = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropaDeMarina,true);
        Prenda shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropaDeMarina,false);
        Prenda pollera = new Prenda(TipoDePrenda.POLLERA, Material.ALGODON, color, null, Trama.LISA, guardarropaDeMarina,false);
        Prenda pañuelo = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropaDeMarina,false);
        Prenda anteojos = new Prenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropaDeMarina,false);
        Prenda prendaVacia = new Prenda(TipoDePrenda.NINGUNO_SUPERIOR, Material.NINGUNO, new Color(0,0, 0), null, Trama.NINGUNO, guardarropaDeMarina,  false);
        Prenda otraPrendaVacia = new Prenda(TipoDePrenda.NINGUNO_SUPERIOR, Material.NINGUNO, new Color(0,0, 0), null, Trama.NINGUNO, guardarropaDeMarina,  false);

        nana.agregarGuardarropa(guardarropaDeMarina);
        nana.getGuardarropas().forEach(guardarropa -> guardarropa.guardarPrenda(musculosa));
        nana.getGuardarropas().forEach(guardarropa -> guardarropa.guardarPrenda(crocs));
        nana.getGuardarropas().forEach(guardarropa -> guardarropa.guardarPrenda(shortDeJean));
        nana.getGuardarropas().forEach(guardarropa -> guardarropa.guardarPrenda(pollera));
        nana.getGuardarropas().forEach(guardarropa -> guardarropa.guardarPrenda(pañuelo));
        nana.getGuardarropas().forEach(guardarropa -> guardarropa.guardarPrenda(anteojos));

        nana.getGuardarropas().forEach(guardarropa -> guardarropa.definirMeteorologo(accuWeather));
        nana.generarSugerenciasParaProximoEvento();
        AtuendosSugeridosPorEvento sugerenciasProximoEvento = nana.obtenerAtuendosSugeridosProximoEvento();
        List<Atuendo> sugerencias = sugerenciasProximoEvento.getAtuendosSugeridos();

        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(musculosa);
        prendasSuperiores.add(prendaVacia);
        prendasSuperiores.add(otraPrendaVacia);
        Atuendo primerAtuendo = new Atuendo(prendasSuperiores, shortDeJean, crocs, anteojos);
        Atuendo segundoAtuendo = new Atuendo(prendasSuperiores, pollera, crocs, anteojos);
        List <Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo, segundoAtuendo);
        sugerencias.forEach(sugerencia -> {
            boolean coincide = sugerenciasEsperadas.stream().anyMatch( sugerenciaEsperada ->
                    sugerenciaEsperada.obtenerAccesorio() == sugerencia.obtenerAccesorio() &&
                            sugerenciaEsperada.obtenerCalzado() == sugerencia.obtenerCalzado() &&
                            sugerenciaEsperada.obtenerPrendaInferior() == sugerencia.obtenerPrendaInferior()
            );
            Assert.assertTrue(coincide);
            Assert.assertTrue(sugerencia.getPrendasSuperiores().size() == 3);
        });
        Assert.assertEquals(sugerenciasEsperadas.size(), sugerencias.size());
        Assert.assertEquals("Evento 2", nana.getCalendario().obtenerProximoEvento().getNombre());
    }
*/
    @Test
    public void quiereSerNotificado() {
        nana.getCalendario().agregarEvento(new Evento("Comilona", "Villa Ballester", LocalDateTime.now().plusDays(2),Periodo.NINGUNO,0));
        nana.setTiempoDeAnticipacion(49);
        nana.quiereSerNotificado();
        Assert.assertTrue(nana.quiereSerNotificado());
    }

    @Test
    public void noQuiereSerNotificado() {
        nana.getCalendario().agregarEvento(new Evento("Comilona", "Villa Ballester", LocalDateTime.now().plusDays(2),Periodo.NINGUNO,0));
        nana.setTiempoDeAnticipacion(47);
        nana.quiereSerNotificado();
        Assert.assertFalse(nana.quiereSerNotificado());
    }
// corregir los test de abajo comentados
    /*
    @Test
    public void sugerenciasListasParaProximoEvento() {
        this.obtenerSugerenciasParaProximoEvento();
        Assert.assertTrue(nana.sugerenciasListasParaProximoEvento());
    }

    @Test
    public void sugerenciasNoListasParaProximoEvento() {
        this.obtenerSugerenciasParaProximoEvento();
        nana.obtenerAtuendosSugeridosProximoEvento().setEvento(new Evento("Cena", "Villa Soldati", LocalDateTime.now().plusDays(1),Periodo.NINGUNO,0));
        Assert.assertFalse(nana.sugerenciasListasParaProximoEvento());
    }

    @Test
    public void seDebeResugerirPorqueAnuncianLLuvia() {
        this.obtenerSugerenciasParaProximoEvento();
        Assert.assertTrue(nana.seDebeResugerir(Alerta.LLUVIA));
    }

    @Test
    public void seDebeResugerirPorqueAnuncianGranizo() {
        this.obtenerSugerenciasParaProximoEvento();
        Assert.assertTrue(nana.seDebeResugerir(Alerta.GRANIZO));
    }
*/
    @Test
    public void noSeDebeResugerirPorqueTieneParaguasYAnuncianLLuvia(){
        Prenda musculosa = new Prenda("Musculosa",TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.LISA, guardarropaDeMarina,false);
        Prenda crocs = new Prenda("Crocs",TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropaDeMarina,true);
        Prenda shortDeJean = new Prenda("ShortDeJean",TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropaDeMarina,false);
        Prenda paraguas = new Prenda("Paraguas",TipoDePrenda.PARAGUAS, Material.PLASTICO, color, null, Trama.LISA, guardarropaDeMarina,true);
        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(musculosa);
        nana.obtenerAtuendosSugeridosProximoEvento().agregarAtuendo(new Atuendo(prendasSuperiores, shortDeJean, crocs, paraguas, this.chalina, this.ningunAccesorioManos));
        Assert.assertFalse(nana.seDebeResugerir(Alerta.LLUVIA));
    }

    @Test
    public void noSeDebeResugerirPorqueTieneRompeVientosYBotas() {
        Prenda remera = new Prenda("Remera",TipoDePrenda.REMERA, Material.ALGODON, color, null, Trama.LISA, guardarropaDeMarina,false);
        Prenda rompevientos = new Prenda("Rompevientos",TipoDePrenda.CAMPERA, Material.NINGUNO, color, null, Trama.LISA, guardarropaDeMarina,true);
        Prenda botas = new Prenda("Botas",TipoDePrenda.BOTAS, Material.GOMA, color, null, Trama.CUADROS, guardarropaDeMarina,true);
        Prenda jean = new Prenda("Jean",TipoDePrenda.PANTALON, Material.JEAN, color, null, Trama.LISA, guardarropaDeMarina,true);
        Prenda pañuelo = new Prenda("Pañuelo",TipoDePrenda.PANUELO, Material.PLASTICO, color, null, Trama.LISA, guardarropaDeMarina,false);
        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(remera);
        prendasSuperiores.add(rompevientos);
        nana.obtenerAtuendosSugeridosProximoEvento().agregarAtuendo(new Atuendo(prendasSuperiores, jean, botas, pañuelo, this.chalina, this.ningunAccesorioManos));
        Assert.assertFalse(nana.seDebeResugerir(Alerta.LLUVIA));
    }

    @Test
    public void noSeDebeResugerirPorqueTieneCasco() {
        Prenda musculosa = new Prenda("Musculosa",TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.LISA, guardarropaDeMarina,false);
        Prenda crocs = new Prenda("Crocs",TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropaDeMarina,true);
        Prenda shortDeJean = new Prenda("Short",TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropaDeMarina,false);
        Prenda casco = new Prenda("Casco",TipoDePrenda.CASCO, Material.PLASTICO, color, null, Trama.LISA, guardarropaDeMarina,true);
        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(musculosa);
        nana.obtenerAtuendosSugeridosProximoEvento().agregarAtuendo(new Atuendo(prendasSuperiores, shortDeJean, crocs, casco, this.chalina, this.ningunAccesorioManos));
        Assert.assertFalse(nana.seDebeResugerir(Alerta.GRANIZO));
    }

}
