package domain.usuario.tipoDeUsuario;

// convertimos interfaz en clase abstracta para poder persistirla

public abstract class TipoUsuario {
    public abstract int limiteDePrendas();
    public abstract boolean tieneLimiteDePrendas();
}
