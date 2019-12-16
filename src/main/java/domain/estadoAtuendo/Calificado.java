package domain.estadoAtuendo;

import domain.Atuendo;
import exceptions.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/*@DiscriminatorValue("Calificado")
@Entity*/
public class Calificado extends EstadoAtuendo {
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
        if (calificacion < 1 || calificacion > 10) {
            throw new RangoDeCalificacionException("La calificación debe estar entre 1 y 10.");
        }
    }

    @Override
    public boolean estaAceptado() {
        return true;
    }
    @Override
    public boolean estaCalificado() {
        return true;
    }
}

