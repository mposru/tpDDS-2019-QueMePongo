/*
package clima;

import domain.usuario.tipoDeUsuario.Premium;
import domain.Usuario;
import domain.clima.AccuWeather;
import domain.clima.Clima;
import domain.Guardarropa;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockAccuWeatherTest {
    private AccuWeather accuWeather;
    private Guardarropa guardarropa;
    private Clima clima;
    private Usuario marta;

    @Before
    public void iniciarTest() {
        accuWeather = mock(AccuWeather.class);
        marta = new Usuario(Premium.getInstance(), "1534444444");
        Set<Usuario> martaLista = new HashSet<>();
        martaLista.add(marta);
        guardarropa = new Guardarropa(martaLista);
        guardarropa.definirMeteorologo(accuWeather);
        clima = new Clima(1558917066, 30, 20, 0.5, 1.0);
    }

    @Test
    public void obtenerClima() {
        when(accuWeather.obtenerClima(0)).thenReturn(clima);
        assertEquals(accuWeather.obtenerClima(0), clima);
        verify(accuWeather).obtenerClima(0);
    }
}
*/