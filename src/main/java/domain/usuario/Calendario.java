package domain.usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Calendario {
    List<Evento> eventos = new ArrayList<>();

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }

    public List<Evento> obtenerEventosPorFecha(LocalDate fecha) {
        return eventos.stream().filter(evento -> evento.getFecha().toLocalDate() == fecha).collect(Collectors.toList());
    }
}
