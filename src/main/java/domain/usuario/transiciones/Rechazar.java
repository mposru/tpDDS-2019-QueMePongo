package domain.usuario.transiciones;

import domain.Atuendo;
import domain.estadoAtuendo.Nuevo;
import domain.Usuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/*
@DiscriminatorValue("Rechazar")
@Entity*/
public class Rechazar extends Decision {
    public Rechazar(Atuendo atuendoRechazado) {
        this.atuendo = atuendoRechazado;
    }
    public void deshacer(Usuario usuario) {
        this.atuendo.cambiarEstado(new Nuevo(this.atuendo));
        usuario.obtenerAtuendosRechazados().remove(atuendo);
    }
}
