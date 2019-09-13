package domain.estadoAtuendo;

import domain.Atuendo;

public abstract class EstadoAtuendo {
    public Atuendo atuendo;
    public abstract void aceptar();
    public abstract void rechazar();
    public abstract void calificar(int calificacion);
    public abstract int obtenerCalificacionAnterior();
    public abstract int obtenerCalificacionActual();
}
