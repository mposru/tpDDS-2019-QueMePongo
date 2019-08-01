package domain;

import domain.usuario.Evento;

import java.util.List;

public class AtuendosSugeridosPorEvento {
    List<Atuendo> atuendosSugeridos;
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
