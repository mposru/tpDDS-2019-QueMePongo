package domain.EstadoAtuendo;

import domain.Atuendo;
import domain.EstadoAtuendo.EstadoAtuendo;
import exceptions.NoSePuedeAceptarException;
import exceptions.NoSePuedeRechazarException;
import exceptions.RangoDeCalificacionException;

public class Calificado implements EstadoAtuendo {
    private Atuendo atuendo;
    private int calificacionActual;
    private int calificacionAnterior;
    public Calificado (Atuendo atuendo,int nuevaCalificacion) {
        this.atuendo = atuendo;
        this.calificacionActual = nuevaCalificacion;
        this.calificacionAnterior = 0; //nace con una calificación fuera de rango, es decir que no está recalificado
    }
    public int obtenerCalificacionActual() { return this.calificacionActual;}
    public int obtenerCalificacionAnterior() { return this.calificacionAnterior;}
    public void aceptar(){
        throw new NoSePuedeAceptarException("No se puede aceptar un atuendo con estado Calificado");
    }
    public void rechazar(){
        throw new NoSePuedeRechazarException("No se puede rechazar un atuendo con estado Calificado");
    }
    public void calificar(int nuevaCalificacion) {
        this.validarRangoCalificacion(nuevaCalificacion);
        this.calificacionAnterior = this.calificacionActual;
        this.calificacionActual = nuevaCalificacion;

        }
    public void validarRangoCalificacion(int calificacion)  {
        if (calificacion < 1 && calificacion > 5) {
            throw new RangoDeCalificacionException("La calificación debe estar entre 1 y 5.");
        }
    }
}

