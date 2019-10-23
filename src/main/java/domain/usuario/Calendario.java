package domain.usuario;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Calendario {


    @GeneratedValue
    @Id
    @Column(name = "idCalendario")
    long id;

    String nombre;
    
    @OneToMany
    @JoinColumn(name = "idEvento")
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
            return new Evento("","", LocalDateTime.now(),Periodo.NINGUNO,0);
        }
    }

    public List<Evento> eventosProximos(){
        return eventos.stream().filter(evento -> evento.esProximo()).collect(Collectors.toList());
    }

    public List<Evento> obtenerEventosEntreFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
        return eventos.stream().filter((evento) -> evento.getFecha().toLocalDate().isAfter(fechaDesde) && evento.getFecha().toLocalDate().isBefore(fechaHasta)).collect(Collectors.toList());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
