package domain;

public class Aceptar implements Decision {
    public Aceptar (Atuendo atuendo) {
        this.atuendo = atuendo;
    }
    public void deshacer() {
        this.atuendo.cambiarEstado(new Nuevo());
    }
}

