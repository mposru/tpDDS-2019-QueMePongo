package domain.usuario.transiciones;

import domain.Atuendo;
import domain.estadoAtuendo.Nuevo;
import domain.Usuario;

public class Rechazar implements Decision {
    private Atuendo atuendo;

    public Rechazar(Atuendo atuendoRechazado) {
        this.atuendo = atuendoRechazado;
    }
    public void deshacer(Usuario usuario) {
        this.atuendo.cambiarEstado(new Nuevo(this.atuendo));
        usuario.obtenerAtuendosRechazados().remove(atuendo);
    }
}
