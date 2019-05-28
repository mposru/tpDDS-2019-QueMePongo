package domain.TipoDeUsuario;

import domain.TipoDeUsuario.TipoUsuario;

public class Gratuito implements TipoUsuario {
    private int limiteDePrendas=20;

    public int limiteDePrendas() {
        return this.limiteDePrendas;
    }
    public boolean tieneLimiteDePrendas() {return true;}
}
