package clima;

import domain.clima.Clima;
import domain.clima.DarkSky;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


public class DarkSkyJsonTest {
    private Clima clima;
    private DarkSky darkSky;
    private LocalDate dia;
    private String jsonClima = "{\n" +
            "    \"latitude\": -34.36,\n" +
            "    \"longitude\": -58.22,\n" +
            "    \"timezone\": \"America/Argentina/Buenos_Aires\",\n" +
            "    \"daily\": {\n" +
            "        \"summary\": \"Lluvia ligera hoy y mañana, con temperaturas cayendo a 15°C el Jueves.\",\n" +
            "        \"icon\": \"rain\",\n" +
            "        \"data\": [\n" +
            "            {\n" +
            "                \"time\": 1558839600,\n" +
            "                \"summary\": \"Mayormente nublado durante el día.\",\n" +
            "                \"icon\": \"partly-cloudy-day\",\n" +
            "                \"sunriseTime\": 1558867697,\n" +
            "                \"sunsetTime\": 1558904074,\n" +
            "                \"moonPhase\": 0.75,\n" +
            "                \"precipIntensity\": 0.0533,\n" +
            "                \"precipIntensityMax\": 0.447,\n" +
            "                \"precipIntensityMaxTime\": 1558918800,\n" +
            "                \"precipProbability\": 0.26,\n" +
            "                \"precipType\": \"rain\",\n" +
            "                \"temperatureHigh\": 17.9,\n" +
            "                \"temperatureHighTime\": 1558897200,\n" +
            "                \"temperatureLow\": 15.66,\n" +
            "                \"temperatureLowTime\": 1558954800,\n" +
            "                \"apparentTemperatureHigh\": 17.97,\n" +
            "                \"apparentTemperatureHighTime\": 1558897200,\n" +
            "                \"apparentTemperatureLow\": 15.66,\n" +
            "                \"apparentTemperatureLowTime\": 1558954800,\n" +
            "                \"dewPoint\": 13.62,\n" +
            "                \"humidity\": 0.87,\n" +
            "                \"pressure\": 1012.42,\n" +
            "                \"windSpeed\": 3.59,\n" +
            "                \"windGust\": 11.59,\n" +
            "                \"windGustTime\": 1558843200,\n" +
            "                \"windBearing\": 343,\n" +
            "                \"cloudCover\": 0.88,\n" +
            "                \"uvIndex\": 3,\n" +
            "                \"uvIndexTime\": 1558882800,\n" +
            "                \"visibility\": 10.32,\n" +
            "                \"ozone\": 262.94,\n" +
            "                \"temperatureMin\": 13.96,\n" +
            "                \"temperatureMinTime\": 1558861200,\n" +
            "                \"temperatureMax\": 17.9,\n" +
            "                \"temperatureMaxTime\": 1558897200,\n" +
            "                \"apparentTemperatureMin\": 13.96,\n" +
            "                \"apparentTemperatureMinTime\": 1558861200,\n" +
            "                \"apparentTemperatureMax\": 17.97,\n" +
            "                \"apparentTemperatureMaxTime\": 1558897200\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": 1558926000,\n" +
            "                \"summary\": \"Mayormente nublado durante el día.\",\n" +
            "                \"icon\": \"partly-cloudy-day\",\n" +
            "                \"sunriseTime\": 1558954136,\n" +
            "                \"sunsetTime\": 1558990448,\n" +
            "                \"moonPhase\": 0.78,\n" +
            "                \"precipIntensity\": 0.1295,\n" +
            "                \"precipIntensityMax\": 0.6833,\n" +
            "                \"precipIntensityMaxTime\": 1558947600,\n" +
            "                \"precipProbability\": 0.5,\n" +
            "                \"precipType\": \"rain\",\n" +
            "                \"temperatureHigh\": 17.14,\n" +
            "                \"temperatureHighTime\": 1558980000,\n" +
            "                \"temperatureLow\": 14.04,\n" +
            "                \"temperatureLowTime\": 1559041200,\n" +
            "                \"apparentTemperatureHigh\": 17.14,\n" +
            "                \"apparentTemperatureHighTime\": 1558980000,\n" +
            "                \"apparentTemperatureLow\": 14.04,\n" +
            "                \"apparentTemperatureLowTime\": 1559041200,\n" +
            "                \"dewPoint\": 13.59,\n" +
            "                \"humidity\": 0.84,\n" +
            "                \"pressure\": 1012.61,\n" +
            "                \"windSpeed\": 1.9,\n" +
            "                \"windGust\": 5.43,\n" +
            "                \"windGustTime\": 1558958400,\n" +
            "                \"windBearing\": 141,\n" +
            "                \"cloudCover\": 0.89,\n" +
            "                \"uvIndex\": 2,\n" +
            "                \"uvIndexTime\": 1558965600,\n" +
            "                \"visibility\": 16.09,\n" +
            "                \"ozone\": 268.65,\n" +
            "                \"temperatureMin\": 15.65,\n" +
            "                \"temperatureMinTime\": 1558958400,\n" +
            "                \"temperatureMax\": 17.14,\n" +
            "                \"temperatureMaxTime\": 1558980000,\n" +
            "                \"apparentTemperatureMin\": 15.65,\n" +
            "                \"apparentTemperatureMinTime\": 1558958400,\n" +
            "                \"apparentTemperatureMax\": 17.14,\n" +
            "                \"apparentTemperatureMaxTime\": 1558980000\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": 1559012400,\n" +
            "                \"summary\": \"Mayormente nublado hasta por la tarde.\",\n" +
            "                \"icon\": \"partly-cloudy-day\",\n" +
            "                \"sunriseTime\": 1559040575,\n" +
            "                \"sunsetTime\": 1559076824,\n" +
            "                \"moonPhase\": 0.81,\n" +
            "                \"precipIntensity\": 0,\n" +
            "                \"precipIntensityMax\": 0.0025,\n" +
            "                \"precipIntensityMaxTime\": 1559062800,\n" +
            "                \"precipProbability\": 0,\n" +
            "                \"temperatureHigh\": 17.21,\n" +
            "                \"temperatureHighTime\": 1559066400,\n" +
            "                \"temperatureLow\": 13.09,\n" +
            "                \"temperatureLowTime\": 1559127600,\n" +
            "                \"apparentTemperatureHigh\": 17.21,\n" +
            "                \"apparentTemperatureHighTime\": 1559066400,\n" +
            "                \"apparentTemperatureLow\": 13.09,\n" +
            "                \"apparentTemperatureLowTime\": 1559127600,\n" +
            "                \"dewPoint\": 11.04,\n" +
            "                \"humidity\": 0.76,\n" +
            "                \"pressure\": 1015.62,\n" +
            "                \"windSpeed\": 2.14,\n" +
            "                \"windGust\": 4.74,\n" +
            "                \"windGustTime\": 1559012400,\n" +
            "                \"windBearing\": 131,\n" +
            "                \"cloudCover\": 0.51,\n" +
            "                \"uvIndex\": 3,\n" +
            "                \"uvIndexTime\": 1559055600,\n" +
            "                \"visibility\": 16.09,\n" +
            "                \"ozone\": 283.21,\n" +
            "                \"temperatureMin\": 14.04,\n" +
            "                \"temperatureMinTime\": 1559041200,\n" +
            "                \"temperatureMax\": 17.21,\n" +
            "                \"temperatureMaxTime\": 1559066400,\n" +
            "                \"apparentTemperatureMin\": 14.04,\n" +
            "                \"apparentTemperatureMinTime\": 1559041200,\n" +
            "                \"apparentTemperatureMax\": 17.21,\n" +
            "                \"apparentTemperatureMaxTime\": 1559066400\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": 1559098800,\n" +
            "                \"summary\": \"Mayormente nublado durante el día y vientos suaves comenzando por la noche.\",\n" +
            "                \"icon\": \"wind\",\n" +
            "                \"sunriseTime\": 1559127014,\n" +
            "                \"sunsetTime\": 1559163201,\n" +
            "                \"moonPhase\": 0.84,\n" +
            "                \"precipIntensity\": 0,\n" +
            "                \"precipIntensityMax\": 0,\n" +
            "                \"precipProbability\": 0,\n" +
            "                \"temperatureHigh\": 15.58,\n" +
            "                \"temperatureHighTime\": 1559156400,\n" +
            "                \"temperatureLow\": 13.11,\n" +
            "                \"temperatureLowTime\": 1559206800,\n" +
            "                \"apparentTemperatureHigh\": 15.58,\n" +
            "                \"apparentTemperatureHighTime\": 1559156400,\n" +
            "                \"apparentTemperatureLow\": 13.11,\n" +
            "                \"apparentTemperatureLowTime\": 1559206800,\n" +
            "                \"dewPoint\": 7.89,\n" +
            "                \"humidity\": 0.65,\n" +
            "                \"pressure\": 1018.2,\n" +
            "                \"windSpeed\": 4.62,\n" +
            "                \"windGust\": 8.44,\n" +
            "                \"windGustTime\": 1559181600,\n" +
            "                \"windBearing\": 131,\n" +
            "                \"cloudCover\": 0.79,\n" +
            "                \"uvIndex\": 2,\n" +
            "                \"uvIndexTime\": 1559138400,\n" +
            "                \"visibility\": 16.09,\n" +
            "                \"ozone\": 271.01,\n" +
            "                \"temperatureMin\": 13.09,\n" +
            "                \"temperatureMinTime\": 1559127600,\n" +
            "                \"temperatureMax\": 15.58,\n" +
            "                \"temperatureMaxTime\": 1559156400,\n" +
            "                \"apparentTemperatureMin\": 13.09,\n" +
            "                \"apparentTemperatureMinTime\": 1559127600,\n" +
            "                \"apparentTemperatureMax\": 15.58,\n" +
            "                \"apparentTemperatureMaxTime\": 1559156400\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": 1559185200,\n" +
            "                \"summary\": \"Vientos suaves y nublado durante el día.\",\n" +
            "                \"icon\": \"wind\",\n" +
            "                \"sunriseTime\": 1559213451,\n" +
            "                \"sunsetTime\": 1559249580,\n" +
            "                \"moonPhase\": 0.87,\n" +
            "                \"precipIntensity\": 0.0559,\n" +
            "                \"precipIntensityMax\": 0.1295,\n" +
            "                \"precipIntensityMaxTime\": 1559214000,\n" +
            "                \"precipProbability\": 0.63,\n" +
            "                \"precipType\": \"rain\",\n" +
            "                \"temperatureHigh\": 14.86,\n" +
            "                \"temperatureHighTime\": 1559235600,\n" +
            "                \"temperatureLow\": 12.09,\n" +
            "                \"temperatureLowTime\": 1559293200,\n" +
            "                \"apparentTemperatureHigh\": 14.86,\n" +
            "                \"apparentTemperatureHighTime\": 1559235600,\n" +
            "                \"apparentTemperatureLow\": 12.09,\n" +
            "                \"apparentTemperatureLowTime\": 1559293200,\n" +
            "                \"dewPoint\": 8.65,\n" +
            "                \"humidity\": 0.7,\n" +
            "                \"pressure\": 1018.11,\n" +
            "                \"windSpeed\": 8.2,\n" +
            "                \"windGust\": 9.8,\n" +
            "                \"windGustTime\": 1559228400,\n" +
            "                \"windBearing\": 102,\n" +
            "                \"cloudCover\": 1,\n" +
            "                \"uvIndex\": 2,\n" +
            "                \"uvIndexTime\": 1559224800,\n" +
            "                \"visibility\": 15.9,\n" +
            "                \"ozone\": 262.84,\n" +
            "                \"temperatureMin\": 13.11,\n" +
            "                \"temperatureMinTime\": 1559206800,\n" +
            "                \"temperatureMax\": 14.86,\n" +
            "                \"temperatureMaxTime\": 1559235600,\n" +
            "                \"apparentTemperatureMin\": 13.11,\n" +
            "                \"apparentTemperatureMinTime\": 1559206800,\n" +
            "                \"apparentTemperatureMax\": 14.86,\n" +
            "                \"apparentTemperatureMaxTime\": 1559235600\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": 1559271600,\n" +
            "                \"summary\": \"Parcialmente nublado por la mañana.\",\n" +
            "                \"icon\": \"partly-cloudy-night\",\n" +
            "                \"sunriseTime\": 1559299888,\n" +
            "                \"sunsetTime\": 1559335961,\n" +
            "                \"moonPhase\": 0.9,\n" +
            "                \"precipIntensity\": 0,\n" +
            "                \"precipIntensityMax\": 0.0051,\n" +
            "                \"precipIntensityMaxTime\": 1559325600,\n" +
            "                \"precipProbability\": 0,\n" +
            "                \"temperatureHigh\": 15.91,\n" +
            "                \"temperatureHighTime\": 1559332800,\n" +
            "                \"temperatureLow\": 13.14,\n" +
            "                \"temperatureLowTime\": 1559376000,\n" +
            "                \"apparentTemperatureHigh\": 15.91,\n" +
            "                \"apparentTemperatureHighTime\": 1559332800,\n" +
            "                \"apparentTemperatureLow\": 13.14,\n" +
            "                \"apparentTemperatureLowTime\": 1559376000,\n" +
            "                \"dewPoint\": 7.38,\n" +
            "                \"humidity\": 0.65,\n" +
            "                \"pressure\": 1016.88,\n" +
            "                \"windSpeed\": 4.56,\n" +
            "                \"windGust\": 7.4,\n" +
            "                \"windGustTime\": 1559354400,\n" +
            "                \"windBearing\": 71,\n" +
            "                \"cloudCover\": 0.28,\n" +
            "                \"uvIndex\": 3,\n" +
            "                \"uvIndexTime\": 1559314800,\n" +
            "                \"visibility\": 16.09,\n" +
            "                \"ozone\": 264.06,\n" +
            "                \"temperatureMin\": 12.09,\n" +
            "                \"temperatureMinTime\": 1559293200,\n" +
            "                \"temperatureMax\": 15.91,\n" +
            "                \"temperatureMaxTime\": 1559332800,\n" +
            "                \"apparentTemperatureMin\": 12.09,\n" +
            "                \"apparentTemperatureMinTime\": 1559293200,\n" +
            "                \"apparentTemperatureMax\": 15.91,\n" +
            "                \"apparentTemperatureMaxTime\": 1559332800\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": 1559358000,\n" +
            "                \"summary\": \"Mayormente nublado durante el día.\",\n" +
            "                \"icon\": \"partly-cloudy-day\",\n" +
            "                \"sunriseTime\": 1559386324,\n" +
            "                \"sunsetTime\": 1559422342,\n" +
            "                \"moonPhase\": 0.94,\n" +
            "                \"precipIntensity\": 0.0254,\n" +
            "                \"precipIntensityMax\": 0.1219,\n" +
            "                \"precipIntensityMaxTime\": 1559401200,\n" +
            "                \"precipProbability\": 0.26,\n" +
            "                \"precipType\": \"rain\",\n" +
            "                \"temperatureHigh\": 16.48,\n" +
            "                \"temperatureHighTime\": 1559419200,\n" +
            "                \"temperatureLow\": 13.73,\n" +
            "                \"temperatureLowTime\": 1559473200,\n" +
            "                \"apparentTemperatureHigh\": 16.48,\n" +
            "                \"apparentTemperatureHighTime\": 1559419200,\n" +
            "                \"apparentTemperatureLow\": 13.73,\n" +
            "                \"apparentTemperatureLowTime\": 1559473200,\n" +
            "                \"dewPoint\": 11.07,\n" +
            "                \"humidity\": 0.79,\n" +
            "                \"pressure\": 1015.16,\n" +
            "                \"windSpeed\": 3.76,\n" +
            "                \"windGust\": 7.68,\n" +
            "                \"windGustTime\": 1559361600,\n" +
            "                \"windBearing\": 32,\n" +
            "                \"cloudCover\": 0.71,\n" +
            "                \"uvIndex\": 2,\n" +
            "                \"uvIndexTime\": 1559397600,\n" +
            "                \"visibility\": 16.09,\n" +
            "                \"ozone\": 280.81,\n" +
            "                \"temperatureMin\": 13.14,\n" +
            "                \"temperatureMinTime\": 1559376000,\n" +
            "                \"temperatureMax\": 16.48,\n" +
            "                \"temperatureMaxTime\": 1559419200,\n" +
            "                \"apparentTemperatureMin\": 13.14,\n" +
            "                \"apparentTemperatureMinTime\": 1559376000,\n" +
            "                \"apparentTemperatureMax\": 16.48,\n" +
            "                \"apparentTemperatureMaxTime\": 1559419200\n" +
            "            },\n" +
            "            {\n" +
            "                \"time\": 1559444400,\n" +
            "                \"summary\": \"Despejado durante el día.\",\n" +
            "                \"icon\": \"clear-day\",\n" +
            "                \"sunriseTime\": 1559472759,\n" +
            "                \"sunsetTime\": 1559508726,\n" +
            "                \"moonPhase\": 0.97,\n" +
            "                \"precipIntensity\": 0.0102,\n" +
            "                \"precipIntensityMax\": 0.0483,\n" +
            "                \"precipIntensityMaxTime\": 1559509200,\n" +
            "                \"precipProbability\": 0.19,\n" +
            "                \"precipType\": \"rain\",\n" +
            "                \"temperatureHigh\": 17.82,\n" +
            "                \"temperatureHighTime\": 1559505600,\n" +
            "                \"temperatureLow\": 14.72,\n" +
            "                \"temperatureLowTime\": 1559548800,\n" +
            "                \"apparentTemperatureHigh\": 17.82,\n" +
            "                \"apparentTemperatureHighTime\": 1559505600,\n" +
            "                \"apparentTemperatureLow\": 14.72,\n" +
            "                \"apparentTemperatureLowTime\": 1559548800,\n" +
            "                \"dewPoint\": 10.61,\n" +
            "                \"humidity\": 0.73,\n" +
            "                \"pressure\": 1021.24,\n" +
            "                \"windSpeed\": 3.5,\n" +
            "                \"windGust\": 11.84,\n" +
            "                \"windGustTime\": 1559466000,\n" +
            "                \"windBearing\": 355,\n" +
            "                \"cloudCover\": 0.14,\n" +
            "                \"uvIndex\": 3,\n" +
            "                \"uvIndexTime\": 1559487600,\n" +
            "                \"visibility\": 16.09,\n" +
            "                \"ozone\": 286.5,\n" +
            "                \"temperatureMin\": 13.73,\n" +
            "                \"temperatureMinTime\": 1559473200,\n" +
            "                \"temperatureMax\": 17.82,\n" +
            "                \"temperatureMaxTime\": 1559505600,\n" +
            "                \"apparentTemperatureMin\": 13.73,\n" +
            "                \"apparentTemperatureMinTime\": 1559473200,\n" +
            "                \"apparentTemperatureMax\": 17.82,\n" +
            "                \"apparentTemperatureMaxTime\": 1559505600\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    \"flags\": {\n" +
            "        \"sources\": [\n" +
            "            \"cmc\",\n" +
            "            \"gfs\",\n" +
            "            \"icon\",\n" +
            "            \"isd\",\n" +
            "            \"madis\"\n" +
            "        ],\n" +
            "        \"nearest-station\": 28.5,\n" +
            "        \"units\": \"si\"\n" +
            "    },\n" +
            "    \"offset\": -3\n" +
            "}";

    @Before
    public void iniciarTest(){
        dia = LocalDate.of(2019, 5, 26);
        darkSky = Mockito.spy(new DarkSky());
        doReturn(jsonClima).when(darkSky).getJsonClima();
        doReturn(dia).when(darkSky).puntoDeReferencia();
        clima = new Clima(1558839600,17.9,13.96,0.26,0.26);
    }

    @Test
    public void obtenerFechaDelJson() {
        assertEquals(clima.getFecha(), darkSky.obtenerClima(dia).getFecha());
    }

    @Test
    public void obtenerMaximaDelJson() {
        assertEquals(clima.getTemperaturaMaxima(), darkSky.obtenerClima(dia).getTemperaturaMaxima(),0);
    }

    @Test
    public void obtenerMinimaDelJson() {
        assertEquals(clima.getTemperaturaMinima(), darkSky.obtenerClima(dia).getTemperaturaMinima(),0);
    }

    @Test
    public void obtenerProbaPrecipitacionDiaDelJson() {
        assertEquals(clima.getPrecipitacionDia(), darkSky.obtenerClima(dia).getPrecipitacionDia(),0);
    }

    @Test
    public void obtenerProbaPrecipitacionNocheDelJson() {
        assertEquals(clima.getPrecipitacionNoche(), darkSky.obtenerClima(dia).getPrecipitacionNoche(),0);
    }
}
