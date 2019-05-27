package domain;

public class Calificar implements Decision {
    private Atuendo atuendo;

    public Calificar (Atuendo atuendoCalificado) {
        this.atuendo = atuendoCalificado;
    }
    public void deshacer() {
        this.atuendo.cambiarEstado(new Aceptado());
    }
}
