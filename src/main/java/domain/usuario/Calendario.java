package domain.usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
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

    public Evento obtenerProximoEvento() {
        if(!eventos.isEmpty()) {
            eventos.sort(Comparator.comparing(Evento::getFecha));
            return eventos.get(0);
        } else {
            return new Evento("","", LocalDateTime.now());
        }
    }
}
