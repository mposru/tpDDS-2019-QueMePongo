package domain;

public class Calificar implements Decision {

    public Calificar (Atuendo atuendo) {
        this.atuendo = atuendo;
    }
    public void deshacer() {
        this.atuendo.cambiarEstado(new Aceptado());
       // this.atuendo.calificar(null); //le borro la calificación
    }
}
