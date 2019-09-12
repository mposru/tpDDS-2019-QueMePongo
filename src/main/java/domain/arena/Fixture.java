package domain.arena;

import domain.usuario.Evento;
import domain.usuario.Periodo;

import java.time.LocalDateTime;

public class Fixture {

    public static void initialize() {
        Evento finalSO = new Evento("FinalSO", "UTN", LocalDateTime.of(2019, 12, 15, 19, 00, 00), Periodo.DIARIO, 1);
        Evento inscripcion2doCuatri = new Evento("Inscripcion2doCuatri", "UTN", LocalDateTime.of(2019, 07, 3, 14, 00, 00), Periodo.NINGUNO, 1);
        Evento vacaciones = new Evento("Vacaciones", "Mendoza", LocalDateTime.of(2019, 07, 4, 14, 00, 00), Periodo.NINGUNO, 1);

        /*
        Calendario calendarioDaiu = new Calendario();
        Calendario calendarioDiego = new Calendario();
        calendarioDaiu.agregarEvento(vacaciones);
        calendarioDaiu.agregarEvento(inscripcion2doCuatri);
        calendarioDaiu.agregarEvento(finalSO);
        calendarioDiego.agregarEvento(inscripcion2doCuatri);
        calendarioDiego.agregarEvento(finalSO);

        Usuario daiu = new Usuario(Gratuito.getInstance(), "1122334455", calendarioDaiu);
        Usuario diego = new Usuario(Gratuito.getInstance(), "1199887766", calendarioDiego);

        RepositorioDeUsuarios.getInstance().agregarUsuario(daiu);
        RepositorioDeUsuarios.getInstance().agregarUsuario(diego);
        */
    }
}

