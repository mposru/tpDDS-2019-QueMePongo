package domain;

public class Rechazar implements Decision {
    private Atuendo atuendo;

    public Rechazar(Atuendo atuendoRechazado) {
        this.atuendo = atuendoRechazado;
    }
    public void deshacer() {
        this.atuendo.cambiarEstado(new Nuevo());
    }
}
