import domain.AccuWeather;
import domain.Clima;
import domain.Guardarropa;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockAccuWeatherTest {
    private AccuWeather accuWeather;
    private Guardarropa guardarropa;
    private Clima clima;

    @Before
    public void iniciarTest() {
        accuWeather = mock(AccuWeather.class);
        guardarropa = new Guardarropa();
        guardarropa.setMeteorologo(accuWeather);
        clima = new Clima(1558917066, 30, 20, 0.5, 1.0);
    }

    @Test
    public void obtenerClima() {
        when(accuWeather.obtenerClima()).thenReturn(clima);
        assertEquals(accuWeather.obtenerClima(), clima);
        verify(accuWeather).obtenerClima();
    }
}
