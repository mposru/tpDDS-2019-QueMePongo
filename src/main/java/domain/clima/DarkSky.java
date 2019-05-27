package domain.clima;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import domain.Clima;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;

public class DarkSky extends Meteorologo {
    private Client client;
    private static final String API_DARKSKY = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/7894?apikey=QZYTHbRTv93BEQmByL07F0ssLgYyNhYH&language=es-ar&details=true&metric=true";

    //Inicializacion del cliente
    public DarkSky() {
        this.client = Client.create();
    }

    public Clima obtenerClima() {
        String jsonClima = this.getJsonClima().toString();
        JSONObject darksky = new JSONObject(jsonClima);

        JSONObject daily = darksky.getJSONObject("daily");
        JSONObject data = daily.getJSONArray("data").getJSONObject(0);

        long epochDate = data.getLong("time");
        double precipitationProbabilityDay = data.getDouble("precipProbability");
        double precipitationProbabilityNight = data.getDouble("precipProbability");
        double valorMaximoTemperatura = data.getDouble("temperatureMax");
        double valorMinimoTemperatura = data.getDouble("temperatureMin");

        return new Clima(epochDate, valorMaximoTemperatura, valorMinimoTemperatura, precipitationProbabilityDay, precipitationProbabilityNight);
    }

    public String getJsonClima(){
        WebResource recurso = this.client.resource(API_DARKSKY);
        WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);
        ClientResponse response = builder.get(ClientResponse.class);
        String json = response.toString();
        return json;
    }

}
