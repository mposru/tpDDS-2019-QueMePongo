package domain.usuario.transiciones;

import domain.*;

// convertimos interfaz en clase abstracta para poder persistirla

public abstract class Decision {
    public Atuendo atuendo;

    public abstract void deshacer(Usuario usuario);
}
