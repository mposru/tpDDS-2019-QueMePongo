package domain;

import domain.usuario.Evento;

import javax.persistence.*;
import java.util.List;

@Entity
public class AtuendosSugeridosPorEvento {

    @GeneratedValue
    @Id
    long id;

    @OneToMany
    @JoinColumn(name = "atuendos_sugeridos_id")
    List<Atuendo> atuendosSugeridos;

    @OneToOne
    Evento evento;

    public AtuendosSugeridosPorEvento(List<Atuendo> atuendosSugeridos, Evento evento) {
        this.atuendosSugeridos = atuendosSugeridos;
        this.evento = evento;
    }

    public List<Atuendo> getAtuendosSugeridos() {
        return atuendosSugeridos;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void agregarAtuendo(Atuendo atuendoSugerido) {
        this.atuendosSugeridos.add(atuendoSugerido);
    }
}
