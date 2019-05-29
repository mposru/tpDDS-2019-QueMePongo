package domain.clima;

import com.sun.jersey.api.client.Client;
import org.json.JSONObject;

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
        return super.getJsonClima(this.client, API_DARKSKY);
    }

}
