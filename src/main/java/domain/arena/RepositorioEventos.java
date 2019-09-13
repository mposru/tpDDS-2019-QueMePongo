package domain.arena;

import domain.usuario.Evento;
import domain.usuario.Periodo;
import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.model.annotations.Observable;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Observable
public class RepositorioEventos extends CollectionBasedRepo<Evento> {
    private static RepositorioEventos instance = new RepositorioEventos();

    public static RepositorioEventos repositorioEventos() {
        return instance;
    }

    public RepositorioEventos() {
        this.init();
    }

    public void init() {
        this.create("FinalSO", "UTN", LocalDateTime.of(2019, 12, 15, 19, 00, 00), Periodo.DIARIO, 1);
        this.create("Inscripcion2doCuatri", "UTN", LocalDateTime.of(2019, 07, 3, 14, 00, 00), Periodo.NINGUNO, 1);
        this.create("Vacaciones", "Mendoza", LocalDateTime.of(2019, 07, 4, 14, 00, 00), Periodo.NINGUNO, 1);
    }


    public void create (String nombre, String ubicacion, LocalDateTime fecha, Periodo tipoDeActualizacion, Integer antelacionEnHoras) {
        this.create(new Evento( nombre,  ubicacion,  fecha, tipoDeActualizacion, antelacionEnHoras));
    }

    public List<Evento> search(LocalDateTime fechaCompletaDesde, LocalDateTime fechaCompletaHasta) {
        return this.allInstances().stream().filter((evento) ->
                evento.getFecha().toLocalDate().isAfter(ChronoLocalDate.from(fechaCompletaDesde)) && evento.getFecha().toLocalDate().isBefore(ChronoLocalDate.from(fechaCompletaHasta)))
                .collect(Collectors.toList());
    }

    public Class<Evento> getEntityType() {
        return Evento.class;
    }

    public Evento createExample() {
        return new Evento();
    }

    protected Predicate<Evento> getCriterio(Evento example) {
        return null;
    }
}
