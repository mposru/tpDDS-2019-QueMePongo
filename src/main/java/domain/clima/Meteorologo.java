package domain.clima;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import domain.RepositorioDeUsuarios;
import domain.Usuario;

import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Meteorologo {
    public List<Clima> climas = new ArrayList<>();
    public abstract Clima obtenerClima(LocalDate dia);
    public abstract List<Alerta> obtenerAlertas();
    public abstract void validarQuePuedaObtenerElClima(LocalDate dia);
    public abstract Clima obtenerClimaDelDiaSiLoTengo(LocalDate dia);

    public LocalDate puntoDeReferencia() { return LocalDate.now(); }

    public void agregarClima(Clima clima) {
        climas.add(clima);
    }

    public String getJson(Client client, String api){
        WebResource recurso = client.resource(api);
        WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);
        ClientResponse response = builder.get(ClientResponse.class);
        return response.toString();
    }

}
