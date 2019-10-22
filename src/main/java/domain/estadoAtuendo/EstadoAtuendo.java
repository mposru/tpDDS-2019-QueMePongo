package domain.estadoAtuendo;

import domain.Atuendo;

import javax.persistence.*;

/*@DiscriminatorColumn(name = "estado")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity*/
public abstract class EstadoAtuendo {
   /* @GeneratedValue
    @Id*/
    long id;
 /*   @ManyToOne
    @JoinColumn(name = "atuendo_id")*/
    public Atuendo atuendo;
    public abstract void aceptar();
    public abstract void rechazar();
    public abstract void calificar(int calificacion);
    public abstract int obtenerCalificacionAnterior();
    public abstract int obtenerCalificacionActual();
}
