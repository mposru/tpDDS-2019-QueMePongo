package domain.Transiciones;

import domain.Atuendo;
import domain.EstadoAtuendo.Nuevo;
import domain.Transiciones.Decision;
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
