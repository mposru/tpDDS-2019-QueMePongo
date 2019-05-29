package domain.Transiciones;

import domain.Atuendo;
import domain.EstadoAtuendo.Aceptado;
import domain.Transiciones.Decision;

public class Recalificar implements Decision {
    private Atuendo atuendo;
    private int calificacionAnterior;

    public Recalificar(Atuendo atuendoRecalificado) {

        this.atuendo = atuendoRecalificado;
    }
    public void deshacer() {
        if (this.atuendo.obtenerCalificacionAnterior()==0) {
            this.atuendo.cambiarEstado(new Aceptado(this.atuendo));

        }
        else {
            this.atuendo.calificar(this.atuendo.obtenerCalificacionAnterior());
        }
    }
}
