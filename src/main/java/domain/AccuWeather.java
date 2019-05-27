package domain;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;

public class AccuWeather extends Meteorologo {
    private Client client;
    private static final String API_ACCUWEATHER = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/7894?apikey=QZYTHbRTv93BEQmByL07F0ssLgYyNhYH&language=es-ar&details=true&metric=true";

    //Inicializacion del cliente
    public AccuWeather() {
        this.client = Client.create();
    }

    public Clima obtenerClima() {
        String jsonClima = this.getJsonClima().toString();
        JSONObject accuWeather = new JSONObject(jsonClima);

        JSONObject dailyForecasts = accuWeather.getJSONArray("DailyForecasts").getJSONObject(0);
        long epochDate = dailyForecasts.getLong("EpochDate");
        JSONObject temperature = dailyForecasts.getJSONObject("Temperature");
        JSONObject day = dailyForecasts.getJSONObject("Day");
        double precipitationProbabilityDay = day.getDouble("PrecipitationProbability");
        JSONObject night = dailyForecasts.getJSONObject("Night");
        double precipitationProbabilityNight = day.getDouble("PrecipitationProbability");

        JSONObject category = accuWeather.getJSONObject("Headline").getJSONObject("Category");
        JSONObject maximum = temperature.getJSONObject("Maximum");
        double valorMaximoTemperatura = maximum.getInt("Value");
        JSONObject minimum = temperature.getJSONObject("Minimum");
        double valorMinimoTemperatura = minimum.getInt("Value");

        return new Clima(epochDate, valorMaximoTemperatura, valorMinimoTemperatura, precipitationProbabilityDay, precipitationProbabilityNight);
    }

    public ClientResponse getJsonClima(){
        WebResource recurso = this.client.resource(API_ACCUWEATHER);
        WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);
        ClientResponse response = builder.get(ClientResponse.class);
        return response;
    }
}
