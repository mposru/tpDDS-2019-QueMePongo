package domain.estadoAtuendo;

import domain.Atuendo;

import javax.persistence.*;

/*@DiscriminatorColumn(name = "estado")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "estado_atuendo")*/
public abstract class EstadoAtuendo {
   /* @GeneratedValue
    @Id
    @Column(name = "id",columnDefinition = "int(11) NOT NULL")*/
    long id;
/*    @ManyToOne
    @JoinColumn(name = "atuendo_id")*/
    @Transient
    public Atuendo atuendo;
    public abstract void aceptar();
    public abstract void rechazar();
    public abstract void calificar(int calificacion);
    public abstract int obtenerCalificacionAnterior();
    public abstract int obtenerCalificacionActual();
}
