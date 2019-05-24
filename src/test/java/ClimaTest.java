import domain.JsonFactory;
import domain.clima.Clima;
import domain.clima.DailyForecasts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class ClimaTest {

    private JsonFactory jsonFactory = new JsonFactory();
    private Clima clima; //Student student;
    private String jsonClima = "{\n" +
            "  \"Headline\": {\n" +
            "    \"EffectiveDate\": \"2019-05-25T07:00:00-03:00\",\n" +
            "    \"EffectiveEpochDate\": 1558778400,\n" +
            "    \"Severity\": 7,\n" +
            "    \"Text\": \"Mayormente nublado este fin de semana\",\n" +
            "    \"Category\": null,\n" +
            "    \"EndDate\": null,\n" +
            "    \"EndEpochDate\": null,\n" +
            "    \"MobileLink\": \"http://m.accuweather.com/es/ar/buenos-aires/7894/extended-weather-forecast/7894?lang=es-ar\",\n" +
            "    \"Link\": \"http://www.accuweather.com/es/ar/buenos-aires/7894/daily-weather-forecast/7894?lang=es-ar\"\n" +
            "  },\n" +
            "  \"DailyForecasts\": [\n" +
            "    {\n" +
            "      \"Date\": \"2019-05-23T07:00:00-03:00\",\n" +
            "      \"EpochDate\": 1558605600,\n" +
            "      \"Temperature\": {\n" +
            "        \"Minimum\": {\n" +
            "          \"Value\": 47,\n" +
            "          \"Unit\": \"F\",\n" +
            "          \"UnitType\": 18\n" +
            "        },\n" +
            "        \"Maximum\": {\n" +
            "          \"Value\": 63,\n" +
            "          \"Unit\": \"F\",\n" +
            "          \"UnitType\": 18\n" +
            "        }\n" +
            "      },\n" +
            "      \"Day\": {\n" +
            "        \"Icon\": 1,\n" +
            "        \"IconPhrase\": \"Soleado\"\n" +
            "      },\n" +
            "      \"Night\": {\n" +
            "        \"Icon\": 33,\n" +
            "        \"IconPhrase\": \"Despejado\"\n" +
            "      },\n" +
            "      \"Sources\": [\n" +
            "        \"AccuWeather\"\n" +
            "      ],\n" +
            "      \"MobileLink\": \"http://m.accuweather.com/es/ar/buenos-aires/7894/daily-weather-forecast/7894?day=1&lang=es-ar\",\n" +
            "      \"Link\": \"http://www.accuweather.com/es/ar/buenos-aires/7894/daily-weather-forecast/7894?day=1&lang=es-ar\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Before
    public void setUp() {
        clima = jsonFactory.fromJson(jsonClima,Clima.class);
    }


    @Test
    public void climaDailyForcastsMapperTest() {
        List<DailyForecasts> forecasts = this.clima.getDailyForecasts();
        //assertEquals(forecasts.size(), 1);

        DailyForecasts tiempoHoy = forecasts.get(0);

        //Integer fechaHoy = tiempoHoy.getEpochDate();

        /*Temperature temperature = forecasts.get(0).getTemperature();
        Maximum maximum = temperature.getMaximum();
        Integer tempMax = maximum.getValue();

        assertEquals(java.util.Optional.ofNullable(tempMax),63);
*/
        Assert.assertEquals(1558605600,tiempoHoy.getEpochDate());
    }

}