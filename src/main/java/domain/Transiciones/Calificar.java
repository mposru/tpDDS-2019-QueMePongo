package domain.Transiciones;

import domain.Atuendo;
import domain.EstadoAtuendo.Aceptado;
import domain.Transiciones.Decision;

public class Calificar implements Decision {
    private Atuendo atuendo;

    public Calificar (Atuendo atuendoCalificado) {
        this.atuendo = atuendoCalificado;
    }
    public void deshacer() {
        this.atuendo.cambiarEstado(new Aceptado(this.atuendo));
    }
}
