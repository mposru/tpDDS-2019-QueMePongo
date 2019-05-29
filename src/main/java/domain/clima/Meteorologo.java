package domain.clima;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public abstract class Meteorologo {

    public abstract Clima obtenerClima();

    public String getJsonClima(Client client, String api){
        WebResource recurso = client.resource(api);
        WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);
        ClientResponse response = builder.get(ClientResponse.class);
        String json = response.toString();
        return json;
    }

}
