package domain.clima;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import domain.RepositorioDeUsuarios;
import domain.Usuario;

import javax.ws.rs.core.MediaType;
import java.util.List;

public abstract class Meteorologo {

    public abstract Clima obtenerClima(int dia);
    public abstract List<Alerta> obtenerAlertas();

    public String getJson(Client client, String api){
        WebResource recurso = client.resource(api);
        WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);
        ClientResponse response = builder.get(ClientResponse.class);
        String json = response.toString();
        return json;
    }

}
