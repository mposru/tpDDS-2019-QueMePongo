package domain.estadoAtuendo;

import domain.Atuendo;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@DiscriminatorColumn(name = "estado")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class EstadoAtuendo {
    public Atuendo atuendo;
    public abstract void aceptar();
    public abstract void rechazar();
    public abstract void calificar(int calificacion);
    public abstract int obtenerCalificacionAnterior();
    public abstract int obtenerCalificacionActual();
}
