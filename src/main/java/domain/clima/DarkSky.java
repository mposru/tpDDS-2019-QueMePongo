package domain.clima;

import com.sun.jersey.api.client.Client;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DarkSky extends Meteorologo {
    private Client client;
    private static final String API_DARKSKY = "https://api.darksky.net/forecast/d443d875fed330c9e41fe374130c3e1e/-34.36,-58.22?units=si&lang=es&exclude=currently,hourly";

    public DarkSky() {
        this.client = Client.create();
    }

    public Clima obtenerClima(LocalDate dia) {
        this.validarQuePuedaObtenerElClima(dia, 8, "DarkSky");
        Clima climaDelDia = this.obtenerClimaDelDiaSiLoTengo(dia);
        if(climaDelDia != null) {
            return climaDelDia;
        } else {
            String jsonClima = this.getJsonClima();
            JSONObject darksky = new JSONObject(jsonClima);
            JSONObject daily = darksky.getJSONObject("daily");

            for(int i = 0; i<daily.getJSONArray("data").length() ; i++) {
                JSONObject data = daily.getJSONArray("data").getJSONObject(i);

                long epochDate = data.getLong("time");
                double precipitationProbabilityDay = data.getDouble("precipProbability");
                double precipitationProbabilityNight = data.getDouble("precipProbability");
                double valorMaximoTemperatura = data.getDouble("temperatureMax");
                double valorMinimoTemperatura = data.getDouble("temperatureMin");
                this.agregarClima(new Clima(epochDate, valorMaximoTemperatura, valorMinimoTemperatura, precipitationProbabilityDay, precipitationProbabilityNight));
            }
            return climas.get((int) ChronoUnit.DAYS.between(this.puntoDeReferencia(), dia));
        }
    }

    public String getJsonClima(){
        return getJson(this.client, API_DARKSKY);
    }

    public List<Alerta> obtenerAlertas() {
        return new ArrayList<>();
    }
}
