package domain;

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

import java.time.LocalDateTime;
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
    private Prenda pañuelo;
    private Prenda bufanda;
    private Prenda anteojos;
    private Prenda prendaVacia;
    private Prenda otraPrendaVacia;
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
    private AccuWeather accuWeather;
    private String jsonClimaAbrigoBasico = "{\n" +
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

    private String jsonClimaAbrigoMediano = "{\n" +
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
            "                    \"Value\": 10,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                },\n" +
            "                \"Maximum\": {\n" +
            "                    \"Value\": 20,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                }\n" +
            "            },\n" +
            "            \"RealFeelTemperature\": {\n" +
            "                \"Minimum\": {\n" +
            "                    \"Value\": 10,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                },\n" +
            "                \"Maximum\": {\n" +
            "                    \"Value\": 20,\n" +
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

    private String jsonClimaAbrigoAlto = "{\n" +
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
            "                    \"Value\": 5,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                },\n" +
            "                \"Maximum\": {\n" +
            "                    \"Value\": 9,\n" +
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
        this.marta = new Usuario(Gratuito.getInstance(),"");
        this.flor = new Usuario(Premium.getInstance(),"");
        flor.agregarEvento("Prueba", "UTN", LocalDateTime.now());
        this.guardarropa = new Guardarropa(flor);
        this.guardarropaLimitado = new Guardarropa(marta);

        this.color = new Color(1, 2, 3);
        this.pantalonPolar = new Prenda(TipoDePrenda.PANTALON, Material.ALGODON, color, null, Trama.CUADROS, guardarropa,6,9,false);;
        this.botasDeNieve = new Prenda(TipoDePrenda.BOTAS_NIEVE, Material.ALGODON, color, null, Trama.CUADROS, guardarropa,5,9,false);;
        this.musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.LISA, guardarropa,21,30,false);
        this.blusa = new Prenda(TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa,0,9,false);
        this.campera = new Prenda(TipoDePrenda.CAMPERA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa,5,9,false);
        this.buzo = new Prenda(TipoDePrenda.BUZO, Material.ALGODON, color, null, Trama.CUADROS, guardarropa,10,20,false);
        this.zapatillas = new Prenda(TipoDePrenda.ZAPATILLAS, Material.ALGODON, color, null, Trama.CUADROS, guardarropa,10,20,false);
        this.crocs = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropa,21,30,true);
        this.pantalon = new Prenda(TipoDePrenda.PANTALON, Material.ALGODON, color, null, Trama.CUADROS, guardarropa,10,20,true);
        this.zapatos = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropa,21,30,true);
        this.shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropa,21,30,false);
        this.pollera = new Prenda(TipoDePrenda.POLLERA, Material.ALGODON, color, null, Trama.LISA, guardarropa,21,30,false);
        this.pañuelo = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropa,10,20,false);
        this.bufanda = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropa,5,9,false);
        this.anteojos = new Prenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropa,21, 30,false);
        this.prendaVacia = new Prenda(TipoDePrenda.NINGUNO_SUPERIOR, Material.NINGUNO, new Color(0,0, 0), null, Trama.NINGUNO, guardarropa, 0, 0, false);
        this.otraPrendaVacia = new Prenda(TipoDePrenda.NINGUNO_SUPERIOR, Material.NINGUNO, new Color(0,0, 0), null, Trama.NINGUNO, guardarropa, 0, 0, false);
        //De guardarropa limitado
        this.remeraFutbol= new Prenda(TipoDePrenda.REMERA, Material.ALGODON, color, null, Trama.ESTAMPADO, guardarropaLimitado, 30, 30, false);;
        this.camperaDeportiva= new Prenda(TipoDePrenda.CAMPERA,Material.ALGODON,color,null,Trama.LISA,guardarropaLimitado, 30, 30, false);
        this.botines= new Prenda(TipoDePrenda.ZAPATO,Material.CUERO,color,null,Trama.LISA,guardarropaLimitado, 30, 30, false);
        this.shortDeFutbol=new Prenda(TipoDePrenda.SHORT,Material.POLYESTER,color,null,Trama.RAYADA,guardarropaLimitado, 30, 30, false);
        this.mediasDeFutbol=new Prenda(TipoDePrenda.MEDIAS,Material.POLYESTER,color,null,Trama.CUADROS,guardarropaLimitado, 30, 30, false);
        this.canillera=new Prenda(TipoDePrenda.CANILLERA,Material.PLASTICO,color,null,Trama.LISA,guardarropaLimitado, 30, 30, false);
        // mockeo clima
        this.accuWeather = Mockito.spy(new AccuWeather());
        this.guardarropa.definirMeteorologo(this.accuWeather);
        doReturn(jsonClimaAbrigoBasico).when(this.accuWeather).getJsonClima();
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
        List<Atuendo> sugerencias = this.guardarropa.generarSugerencia();

        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(this.musculosa);
        prendasSuperiores.add(this.prendaVacia);
        prendasSuperiores.add(this.otraPrendaVacia);
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
        List<Atuendo> sugerencias = this.guardarropa.generarSugerencia();

        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(this.musculosa);
        prendasSuperiores.add(this.buzo);
        prendasSuperiores.add(this.prendaVacia);
        Atuendo primerAtuendo = new Atuendo(prendasSuperiores, pantalon, zapatillas, pañuelo);
        List <Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo);
        sugerencias.forEach(sugerencia -> {
            boolean coincide = sugerenciasEsperadas.stream().anyMatch( sugerenciaEsperada ->
                    sugerenciaEsperada.obtenerAccesorio() == sugerencia.obtenerAccesorio() &&
                            sugerenciaEsperada.obtenerCalzado() == sugerencia.obtenerCalzado() &&
                            sugerenciaEsperada.obtenerPrendaInferior() == sugerencia.obtenerPrendaInferior()
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
        List<Atuendo> sugerencias = this.guardarropa.generarSugerencia();

        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(this.musculosa);
        prendasSuperiores.add(this.buzo);
        prendasSuperiores.add(this.campera);
        Atuendo primerAtuendo = new Atuendo(prendasSuperiores, pantalonPolar, botasDeNieve, bufanda);
        List <Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo);
        sugerencias.forEach(sugerencia -> {
            boolean coincide = sugerenciasEsperadas.stream().anyMatch( sugerenciaEsperada ->
                    sugerenciaEsperada.obtenerAccesorio() == sugerencia.obtenerAccesorio() &&
                            sugerenciaEsperada.obtenerCalzado() == sugerencia.obtenerCalzado() &&
                            sugerenciaEsperada.obtenerPrendaInferior() == sugerencia.obtenerPrendaInferior()
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
        this.guardarropa.generarSugerencia();
    }

    @Test
    public void noSePuedeSugerirSinCalzado() {
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.anteojos);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan zapatos adecuados para el clima del evento. ");
        this.guardarropa.generarSugerencia();
    }

    @Test
    public void noSePuedeSugerirSinAccesorio() {
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.crocs);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan accesorios adecuados para el clima del evento. ");
        this.guardarropa.generarSugerencia();
    }

    @Test
    public void noSePuedeSugerirSinParteInferior() {
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.anteojos);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan prendas inferiores adecuadas para el clima del evento. ");
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
