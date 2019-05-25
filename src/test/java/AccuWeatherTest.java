
import com.sun.jersey.api.client.ClientResponse;
import domain.AccuWeather;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

    public class AccuWeatherTest {

        AccuWeather proveedor;

        @Before
        public void iniciarTest() {
            this.proveedor = new AccuWeather();
        }

        @Test
        public void obtenerJsonClima(){
            ClientResponse response = this.proveedor.getJsonClima();
            String json = response.getEntity(String.class);
            assertTrue(json.contains("Chaparrones"));
        }
}
