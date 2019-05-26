package domain;

public class Gratuito implements TipoUsuario {
    private int limiteDePrendas=20;

    public int limiteDePrendas() {
        return this.limiteDePrendas;
    }
}
