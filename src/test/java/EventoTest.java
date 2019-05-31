import domain.Evento;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;

public class EventoTest {
    private LocalDateTime fecha;
    private String nombre;
    private String ubicacion;
    private Evento evento;

    @Before
    public void iniciarTest() {
        this.fecha = LocalDateTime.of(2019, 5, 29, 17, 50, 30);
        this.nombre = "Ir a caminar";
        this.ubicacion = "UTN";
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deberiaHaberNombreAlDefinirEvento() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Usted no ingreso un nombre");
        this.evento = new Evento(null, this.ubicacion, this.fecha);
    }

    @Test
    public void deberiaHaberUbicacionAlDefinirEvento() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar una ubicación para el evento");
        this.evento = new Evento(this.nombre, null, this.fecha);
    }

    @Test
    public void crearEventoSinFecha() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Usted no ingreso una fecha para evento");
        Evento evento = new Evento("a", "b", null);
    }

    @Test
    public void noOcurreHoy() {
        this.evento = new Evento(this.nombre, this.ubicacion, this.fecha);
        Assert.assertFalse(this.evento.esHoy());
    }

    @Test
    public void ocurreHoy() {
        this.evento = new Evento(this.nombre, this.ubicacion, LocalDateTime.now());
        Assert.assertTrue(this.evento.esHoy());
    }

}
