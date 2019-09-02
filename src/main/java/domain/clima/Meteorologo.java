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
import java.util.stream.Collectors;

public abstract class Meteorologo {
    public List<Clima> climas = new ArrayList<>();
    public abstract Clima obtenerClima(LocalDate dia);
    public abstract List<Alerta> obtenerAlertas();

    public void validarQuePuedaObtenerElClima(LocalDate dia, int limiteDeDias, String nombreApi) {
        LocalDate ahora = this.puntoDeReferencia();
        if(!dia.isAfter(ahora.plusDays(-1)) || !dia.isBefore(ahora.plusDays(limiteDeDias))) {
            throw new RuntimeException("No se puede obtener el pronostico de ese dia. La API " + nombreApi + " no lo provee");
        }
    }

    public Clima obtenerClimaDelDiaSiLoTengo(LocalDate dia) {
        if(climas == null) {
            return null;
        }
        List<Clima> climasDelDia = climas.stream().filter(climaDia -> climaDia.esDelDia(dia)).collect(Collectors.toList());
        if(climasDelDia.isEmpty()) {
            return null;
        }
        return climasDelDia.get(0);
    }

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
