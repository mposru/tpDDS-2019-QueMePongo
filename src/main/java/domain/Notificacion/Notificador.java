package domain.Notificacion;

import domain.Usuario;

public interface Notificador {
    void notificar(Usuario usuario, String mensaje);
}
