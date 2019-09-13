package domain.usuario.tipoDeUsuario;

// convertimos interfaz en clase abstracta para poder persistirla

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class TipoUsuario {
    public abstract int limiteDePrendas();
    public abstract boolean tieneLimiteDePrendas();
}
