package domain.EstadoAtuendo;

import domain.Atuendo;
import domain.EstadoAtuendo.EstadoAtuendo;
import exceptions.*;

public class Rechazado implements EstadoAtuendo {
    private Atuendo atuendo;
    public Rechazado (Atuendo atuendo) {
        this.atuendo = atuendo;
    }
    public void aceptar() {
        throw new NoSePuedeAceptarException("No se puede aceptar un atuendo con estado Rechazado");

    }
    public void rechazar() {
        throw new NoSePuedeRechazarException("El atuendo ya está con estado Rechazado");


    }
    public void calificar(int calificacion) {
        throw new NoSePuedeCalificarException("No se puede calificar un atuendo con estado Rechazado.");
    }
    public int obtenerCalificacionAnterior() {return 0;}
    public int obtenerCalificacionActual() {return 0;}

}
