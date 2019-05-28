package domain.TipoDeUsuario;

import domain.TipoDeUsuario.TipoUsuario;

public class Premium implements TipoUsuario {

    public int limiteDePrendas() {
        return 0;
    }
    public boolean tieneLimiteDePrendas() {return false;}
}
