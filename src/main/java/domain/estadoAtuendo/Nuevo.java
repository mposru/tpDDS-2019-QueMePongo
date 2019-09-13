package domain.estadoAtuendo;

import domain.Atuendo;
import exceptions.NoSePuedeCalificarException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@DiscriminatorValue("N")
@Entity
public class Nuevo extends EstadoAtuendo {
    public Nuevo (Atuendo atuendo) {
        this.atuendo = atuendo;
    }
    public void aceptar() {
        this.atuendo.cambiarEstado(new Aceptado(this.atuendo));
    }
    public void rechazar() {
        this.atuendo.cambiarEstado(new Rechazado(this.atuendo));

    }
    public void calificar(int nuevaCalificacion) {
        throw new NoSePuedeCalificarException("No se puede calificar un atuendo con estado Nuevo.");
    }

    public int obtenerCalificacionAnterior() {return 0;}
    public int obtenerCalificacionActual() {return 0;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nuevo estado = (Nuevo) o;
        return Objects.equals(Nuevo.class, estado.getClass());
    }
}
