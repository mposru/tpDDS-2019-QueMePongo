package domain.usuario.transiciones;

import domain.*;

public interface Decision {
    void deshacer(Usuario usuario);
}
