import domain.Clima;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;


public class ClimaTest {

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
        //clima = jsonFactory.fromJson(json,Clima.class);
    }


    @Test
    public void climaDailyForcastsMapperTest() {


    }

}