package domain;

public class Rechazar implements Decision {
    public Rechazar (Atuendo atuendo) {
        this.atuendo = atuendo;
    }
    public void deshacer() {

        this.atuendo.cambiarEstado(new Nuevo());
    }
}
