package domain;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class AccuWeather {
    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target("http://localhost:8082/spring-jersey");
}
