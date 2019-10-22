package domain.usuario.transiciones;

import domain.Atuendo;
import domain.estadoAtuendo.Aceptado;
import domain.Usuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/*@DiscriminatorValue("Calificar")
@Entity*/
public class Calificar extends Decision {
    public Calificar (Atuendo atuendoCalificado) {
        this.atuendo = atuendoCalificado;
    }
    public void deshacer(Usuario usuario) {
        this.atuendo.cambiarEstado(new Aceptado(this.atuendo));
    }
}
