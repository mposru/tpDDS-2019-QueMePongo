package domain;

public class Aceptar implements Decision {
    private Atuendo atuendo;

    public Aceptar (Atuendo atuendoAceptado) {
        this.atuendo = atuendoAceptado;
    }
    public void deshacer() {
        this.atuendo.cambiarEstado(new Nuevo());
    }
}

