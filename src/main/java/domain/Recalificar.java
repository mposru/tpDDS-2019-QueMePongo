package domain;

public class Recalificar implements Decision {
    private Atuendo atuendo;
    private Integer calificacionAnterior;

    public  Recalificar(Atuendo atuendoRecalificado) {

        this.atuendo = atuendoRecalificado;
    }
    public void deshacer() {
        if (this.atuendo.obtenerCalificacionAnterior()== 0) {
            this.atuendo.cambiarEstado(new Aceptado());

        }
        else {
            this.atuendo.calificar(this.atuendo.obtenerCalificacionAnterior());
        }
    }
}
