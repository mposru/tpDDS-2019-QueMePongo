package domain.estadoAtuendo;

import domain.Atuendo;
import exceptions.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rechazado estado = (Rechazado) o;
        return Objects.equals(Rechazado.class, estado.getClass());
    }
}
