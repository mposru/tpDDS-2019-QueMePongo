package domain.Transiciones;

import domain.Atuendo;
import domain.EstadoAtuendo.Nuevo;
import domain.Usuario;

public class Aceptar implements Decision {
    private Atuendo atuendo;

    public Aceptar (Atuendo atuendoAceptado) {
        this.atuendo = atuendoAceptado;
    }

    public void deshacer(Usuario usuario) {
        this.atuendo.cambiarEstado(new Nuevo(this.atuendo));
        usuario.obtenerAtuendosAceptados().remove(atuendo);

    }
}

