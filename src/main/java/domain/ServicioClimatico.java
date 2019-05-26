package domain;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class ServicioClimatico {
    private String urlClima;
    private Client client = ClientBuilder.newClient();

    public Map<LocalDate,Integer> obtenerPronostico() {
        WebTarget webTarget = client.target(urlClima);
    }
}
