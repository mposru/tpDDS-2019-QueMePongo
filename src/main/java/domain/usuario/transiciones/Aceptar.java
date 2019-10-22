package domain.usuario.transiciones;

import domain.Atuendo;
import domain.estadoAtuendo.Nuevo;
import domain.Usuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/*@DiscriminatorValue("Aceptar")
@Entity*/
public class Aceptar extends Decision {
    public Aceptar (Atuendo atuendoAceptado) {
        this.atuendo = atuendoAceptado;
    }

    public void deshacer(Usuario usuario) {
        this.atuendo.cambiarEstado(new Nuevo(this.atuendo));
        usuario.obtenerAtuendosAceptados().remove(atuendo);

    }
}

