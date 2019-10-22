package domain;

import domain.usuario.Evento;
import domain.usuario.Periodo;

import java.time.LocalDateTime;

public class RepositorioEventos {
    private static RepositorioEventos instanceOfRepositorioEventos;

    public static RepositorioEventos getInstance() {
        if(instanceOfRepositorioEventos==null) {
            instanceOfRepositorioEventos = new RepositorioEventos();
        }
        return instanceOfRepositorioEventos;
    }

    public Evento findById(String id) {
        // hack temporario
        // todo: hacerlo bien
        return new Evento("UPD", "UTN", LocalDateTime.now(), Periodo.ANUAL, 456);
    }

}
