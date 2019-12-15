package domain.clima;

import com.sun.jersey.api.client.Client;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static domain.clima.Alerta.*;

public class AccuWeather extends Meteorologo {
    private Client client;
    private static final String CLIMA_ACCUWEATHER = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/7894?apikey=QZYTHbRTv93BEQmByL07F0ssLgYyNhYH&language=es-ar&details=true&metric=true";
    private static final String ALERTAS_ACCUWEATHER = "http://dataservice.accuweather.com/alarms/v1/1day/78947894?apikey=QZYTHbRTv93BEQmByL07F0ssLgYyNhYH";

    public AccuWeather() {
        //jedis = new Jedis();
        this.client = Client.create();
    }

    private static AccuWeather instanceOfAccuWeather;

    public static AccuWeather getInstance() {
        if(instanceOfAccuWeather==null) {
            instanceOfAccuWeather = new AccuWeather();
        }
        return instanceOfAccuWeather;
    }

    public Clima obtenerClima(LocalDate dia) {
        this.validarQuePuedaObtenerElClima(dia, 5, "AccuWeather");
        Clima climaDelDia = this.obtenerClimaDelDiaSiLoTengo(dia);
        if(climaDelDia != null) {
            return climaDelDia;
        } else {
            String jsonClima = this.getJsonClima();
            JSONObject accuWeather = new JSONObject(jsonClima);

            for(int i = 0; i<accuWeather.getJSONArray("DailyForecasts").length() ; i++) {
                JSONObject dailyForecasts = accuWeather.getJSONArray("DailyForecasts").getJSONObject(i);
                long epochDate = dailyForecasts.getLong("EpochDate");
                JSONObject temperature = dailyForecasts.getJSONObject("Temperature");
                JSONObject day = dailyForecasts.getJSONObject("Day");
                double precipitationProbabilityDay = day.getDouble("PrecipitationProbability");
                JSONObject night = dailyForecasts.getJSONObject("Night");
                double precipitationProbabilityNight = night.getDouble("PrecipitationProbability");

                JSONObject maximum = temperature.getJSONObject("Maximum");
                double valorMaximoTemperatura = maximum.getDouble("Value");
                JSONObject minimum = temperature.getJSONObject("Minimum");
                double valorMinimoTemperatura = minimum.getDouble("Value");
                this.agregarClima(
                        new Clima(epochDate, valorMaximoTemperatura, valorMinimoTemperatura,
                                precipitationProbabilityDay, precipitationProbabilityNight)); // esto no iria
                /*jedis.hset("climaAccuWeather#" + i, "fecha", epochDate);
                * jedis.hset("climaAccuWeather#" + i, "maxima", valorMaximoTemperatura);
                * jedis.hset("climaAccuWeather#" + i, "minima", valorMinimoTemperatura);
                * jedis.hset("climaAccuWeather#" + i, "probabilidadDeLLuviaEnElDia", precipitationProbabilityDay);
                * jedis.hset("climaAccuWeather#" + i, "probabilidadDeLLuviaEnLaNoche", precipitationProbabilityNight);*/
            }
            return climas.get((int) ChronoUnit.DAYS.between(this.puntoDeReferencia(), dia)); // esto no iria
        }
    }

    public String getJsonClima() {
        return getJson(this.client, CLIMA_ACCUWEATHER);
    }

    public String getJsonAlertas() {
        return getJson(this.client, ALERTAS_ACCUWEATHER);
    }

    public List<Alerta> obtenerAlertas() {
        List<Alerta> alertas = new ArrayList<>();
        String jsonAlertas = this.getJsonAlertas();
        JSONObject accuWeather = new JSONArray(jsonAlertas).getJSONObject(0);
        JSONArray alertasJson = accuWeather.getJSONArray("Alarms");
        if(alertasJson.isEmpty()) {
            return alertas;
        } else {
            for(int i=0;i<alertasJson.length();i++) {
                String tipoDeAlarma = alertasJson.getJSONObject(i).getString("AlarmType");
                if(tipoDeAlarma.equals("Rain")) {
                    alertas.add(LLUVIA);
                }
                if(tipoDeAlarma.equals("Ice")) {
                    alertas.add(GRANIZO);
                }
            }
            return alertas;
        }
    }
}
