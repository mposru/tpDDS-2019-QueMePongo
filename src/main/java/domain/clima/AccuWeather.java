package domain.clima;

import com.sun.jersey.api.client.Client;
import org.json.JSONObject;

import java.util.*;

public class AccuWeather extends Meteorologo {
    private Client client;
    private static final String API_ACCUWEATHER = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/7894?apikey=QZYTHbRTv93BEQmByL07F0ssLgYyNhYH&language=es-ar&details=true&metric=true";

    //Inicializacion del cliente
    public AccuWeather() {
        this.client = Client.create();
    }

    public Clima obtenerClima() {
        String jsonClima = this.getJsonClima();
        JSONObject accuWeather = new JSONObject(jsonClima);

        JSONObject dailyForecasts = accuWeather.getJSONArray("DailyForecasts").getJSONObject(0);
        long epochDate = dailyForecasts.getLong("EpochDate");
        JSONObject temperature = dailyForecasts.getJSONObject("Temperature");
        JSONObject day = dailyForecasts.getJSONObject("Day");
        double precipitationProbabilityDay = day.getDouble("PrecipitationProbability");
        JSONObject night = dailyForecasts.getJSONObject("Night");
        double precipitationProbabilityNight = day.getDouble("PrecipitationProbability");

        JSONObject maximum = temperature.getJSONObject("Maximum");
        double valorMaximoTemperatura = maximum.getDouble("Value");
        JSONObject minimum = temperature.getJSONObject("Minimum");
        double valorMinimoTemperatura = minimum.getDouble("Value");

        return new Clima(epochDate, valorMaximoTemperatura, valorMinimoTemperatura, precipitationProbabilityDay, precipitationProbabilityNight);
    }

    public String getJsonClima(){
        return getJson(this.client, API_ACCUWEATHER);
    }

    public List<Alerta> obtenerAlertas() {
        return new ArrayList<>();
    }
}
