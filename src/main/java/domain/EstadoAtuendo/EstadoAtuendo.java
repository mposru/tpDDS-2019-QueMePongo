package domain.EstadoAtuendo;

public interface EstadoAtuendo {
    void aceptar();
    void rechazar();
    void calificar(int calificacion);
    int obtenerCalificacionAnterior();
    int obtenerCalificacionActual();
}
