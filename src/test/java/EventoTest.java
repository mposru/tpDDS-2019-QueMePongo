import domain.Evento;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventoTest {
    private LocalDateTime fecha;
    private LocalDate dia;
    private LocalDate diaDistinto;
    private LocalDateTime fechaDistinta;
    private LocalDateTime fechaParaComparar;
    private String nombre;
    private Evento evento;

    @Before
    public void iniciarTest() {
        this.fecha = LocalDateTime.of(2019, 5, 29, 17, 50, 30);
        this.dia = LocalDate.of(2019, 5, 29);
        this.diaDistinto = this.dia.plusDays(1);
        this.fechaDistinta = this.fecha.plusDays(1);
        this.nombre = "Ir a caminar";
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deberiHaberNombreAlDefinirEvento() {
        this.nombre = null;
        exception.expect(NullPointerException.class);
        exception.expectMessage("Usted no ingreso un nombre");
        this.evento = new Evento(nombre);
    }

    @Before
    public void definoEvento() {
        this.evento = new Evento(this.nombre);
    }

    @Test
    public void indicarFechaSinDecirCualEsLaFecha() {
        this.fecha = null;
        exception.expect(NullPointerException.class);
        exception.expectMessage("Usted no ingreso una fecha para evento");
        this.evento.indicarFecha(fecha);
    }

    @Before
    public void indicarFechaAlEvento() {
        this.evento.indicarFecha(fecha);
    }

    @Test
    public void verSiEsProximoSinIndicarQueFecha() {
        this.fechaParaComparar = null;
        exception.expect(NullPointerException.class);
        exception.expectMessage("Usted no ingreso una fecha para comparar");
        this.evento.verSiEsProximo(fechaParaComparar);
    }

    @Test
    public void verSiEsProximoConUnaFechaDistinta() {
        Assert.assertFalse(this.evento.verSiEsProximo(this.fechaDistinta));
    }

    @Test
    public void verSiEsProximoConLaFechaEstipulada() {
        Assert.assertTrue(this.evento.verSiEsProximo(this.fecha));
    }
}
