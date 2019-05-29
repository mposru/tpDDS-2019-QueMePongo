package domain.EstadoAtuendo;

import domain.Atuendo;
import exceptions.NoSePuedeCalificarException;

public class Nuevo implements EstadoAtuendo {
    private Atuendo atuendo;
    public Nuevo (Atuendo atuendo) {
        this.atuendo = atuendo;
    }
    public void aceptar() {
        this.atuendo.cambiarEstado(new Aceptado(this.atuendo));
    }
    public void rechazar() {
        this.atuendo.cambiarEstado(new Rechazado(this.atuendo));

    }
    public void calificar(int nuevaCalificacion) {
        throw new NoSePuedeCalificarException("No se puede calificar un atuendo con estado Nuevo.");
    }

    public int obtenerCalificacionAnterior() {return 0;}
    public int obtenerCalificacionActual() {return 0;}

}
