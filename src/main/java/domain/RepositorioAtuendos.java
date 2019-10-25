package domain;

import domain.usuario.Evento;

public class RepositorioAtuendos {
    private static RepositorioAtuendos instanceOfRepositorioAtuendos;

    public static RepositorioAtuendos getInstance() {
        if(instanceOfRepositorioAtuendos==null) {
            instanceOfRepositorioAtuendos = new RepositorioAtuendos();
        }
        return instanceOfRepositorioAtuendos;
    }

    public Atuendo findById(String id) {
        // hack temporario
        // todo: hacerlo bien
        //return new Evento("UPD", "UTN", LocalDateTime.now(), Periodo.ANUAL, 456);
        Evento evento = RepositorioEventos.getInstance().findById("");

        Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuarioPorId(4);

        return usuario.obtenerSugerenciasDeEvento(evento).get(0);
    }
}
