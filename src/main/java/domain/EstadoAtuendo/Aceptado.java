package domain.EstadoAtuendo;

import domain.Atuendo;
import exceptions.*;

public class Aceptado implements EstadoAtuendo {
    private Atuendo atuendo;
    public Aceptado(Atuendo atuendo) {
        this.atuendo = atuendo;
    }
    public void aceptar(){
        throw new NoSePuedeAceptarException("El atuendo ya está con estado Aceptado");
    }
    public void rechazar() {
        throw new NoSePuedeRechazarException("No se puede rechazar un atuendo con estado Aceptado");
    }
    public void calificar(int nuevaCalificacion) {
        this.validarRangoCalificacion(nuevaCalificacion);
        this.atuendo.cambiarEstado(new Calificado(this.atuendo,nuevaCalificacion));
    }
    public int obtenerCalificacionAnterior() {return 0;}
    public int obtenerCalificacionActual() {return 0;}

    public void validarRangoCalificacion(int calificacion) {
        if (calificacion < 1 || calificacion > 5) {
            throw new RangoDeCalificacionException("La calificación debe estar entre 1 y 5.");
        }
    }

}
