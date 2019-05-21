package domain;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;


public class AccuWeather {
    private Client client;
    private static final String API_ACCUWEATHER = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/7894?apikey=QZYTHbRTv93BEQmByL07F0ssLgYyNhYH&language=es-ar";

    //Inicializacion del cliente
    public AccuWeather() {
        this.client = Client.create();
    }

    public ClientResponse getJsonClima(){
        WebResource recurso = this.client.resource(API_ACCUWEATHER);
        WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);
        ClientResponse response = builder.get(ClientResponse.class);
        return response;
    }
}
