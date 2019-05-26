package domain;

public class Recalificar implements Decision {
    private Integer calificacionAnterior;
    public Recalificar(Atuendo atuendo) {
        this.atuendo = atuendo;
    }
    public void deshacer() {
        if (atuendo.obtenerCalificacionAnterior()== 0) {
            this.atuendo.cambiarEstado(new Aceptado());

        }
        else {
            this.atuendo.calificar(atuendo.obtenerCalificacionAnterior());
        }
    }
}
