package domain.estadoAtuendo;

import domain.Atuendo;

import javax.persistence.*;

public abstract class EstadoAtuendo {
    @ManyToOne
    @JoinTable(name = "atuendo_estado",joinColumns = @JoinColumn(name="estado_id"),inverseJoinColumns = @JoinColumn(name = "atuendo_id"))
    public Atuendo atuendo;
    public abstract void aceptar();
    public abstract void rechazar();
    public abstract void calificar(int calificacion);
    public abstract int obtenerCalificacionAnterior();
    public abstract int obtenerCalificacionActual();
    public boolean estaAceptado() {
        return false;
    }
    public boolean estaCalificado() {
        return false;
    }
}
