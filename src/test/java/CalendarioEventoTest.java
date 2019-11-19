import domain.Usuario;
import domain.usuario.Calendario;
import domain.usuario.Evento;
import domain.usuario.Periodo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CalendarioEventoTest {
    private LocalDateTime fecha;
    private String nombre;
    private String ubicacion;
    private Evento evento;
    private Usuario nana;
    private Calendario calendario;

    @Before
    public void iniciarTest() {
        this.calendario = new Calendario();
        this.nana = new Usuario( "1534433333", calendario, "nana123","","",null);
        this.fecha = LocalDateTime.of(2019, 5, 29, 17, 50, 30);
        this.nombre = "Ir a caminar";
        this.ubicacion = "UTN";
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deberiaHaberNombreAlDefinirEvento() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar un nombre para el evento");
        this.evento = new Evento(null, this.ubicacion, this.fecha, Periodo.NINGUNO, 0);
    }

    @Test
    public void deberiaHaberUbicacionAlDefinirEvento() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar una ubicación para el evento");
        this.evento = new Evento(this.nombre, null, this.fecha, Periodo.NINGUNO, 0);
    }

    @Test
    public void crearEventoSinFecha() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar una fecha para el evento");
        Evento evento = new Evento("a", "b", null,Periodo.NINGUNO,0);
    }

    @Test
    public void crearEventoAntelacion() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar la antelacion del evento");
        Evento evento = new Evento("a", "b", LocalDateTime.now(), Periodo.NINGUNO, null);
    }

    @Test
    public void crearEventoSinPeriodicidad() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar el tipo de periodicidad");
        Evento evento = new Evento("a","b",LocalDateTime.now(),null,1);
    }

    @Test
    public void noOcurreHoy() {
        this.evento = new Evento(this.nombre, this.ubicacion, this.fecha, Periodo.NINGUNO,0);
        Assert.assertFalse(this.evento.esHoy());
    }

    @Test
    public void ocurreHoy() {
        this.evento = new Evento(this.nombre, this.ubicacion, LocalDateTime.now(), Periodo.NINGUNO,0);
        Assert.assertTrue(this.evento.esHoy());
    }

    @Test
    public void verificarQueNoHayEventosHoy() {
        Assert.assertEquals(new ArrayList<Evento>(), nana.getCalendario().obtenerEventosPorFecha(LocalDate.now()));
    }

    @Test
    public void validoObtenerProximoEvento() {
        nana.getCalendario().agregarEvento(new Evento("Evento 1", this.ubicacion, LocalDateTime.now().plusDays(1),Periodo.NINGUNO,0));
        nana.getCalendario().agregarEvento(new Evento("Evento 2", this.ubicacion, LocalDateTime.now(),Periodo.NINGUNO,0));
        nana.getCalendario().agregarEvento(new Evento("Evento 3", this.ubicacion, LocalDateTime.now().plusDays(3),Periodo.NINGUNO,0));
        Assert.assertEquals("Evento 2", nana.getCalendario().obtenerProximoEvento().getNombre());
    }

    @Test
    public void hayEventoProximo(){
        Usuario usuario=new Usuario("011145454545", calendario,"nana123","","",null);
        Evento evento=new Evento("Robar","BA", LocalDateTime.now().plusHours(1), Periodo.DIARIO,1);
        usuario.getCalendario().agregarEvento(evento);
        Assert.assertEquals(true,usuario.getCalendario().eventosProximos().size()>0);
    }

    @Test
    public void noHayEventoProximo(){
        Usuario usuario=new Usuario("01154545412", calendario, "usuario123","","",null);
        this.evento=new Evento(this.nombre,this.ubicacion,LocalDateTime.now().plusDays(1),Periodo.NINGUNO,10);
        usuario.getCalendario().agregarEvento(evento);
        Assert.assertEquals(false,usuario.getCalendario().eventosProximos().size()>0);
    }

    @Test
    public void hayQueActualizarEvento(){
        Evento evento = new Evento("a","CABA",LocalDateTime.now().plusHours(1),Periodo.DIARIO,1);
        evento.esProximo();
        Assert.assertEquals(true, LocalDateTime.now().plusDays(1).getDayOfWeek()==evento.getFecha().getDayOfWeek());
    }
}
