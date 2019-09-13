package domain.usuario.tipoDeUsuario;

// convertimos interfaz en clase abstracta para poder persistirla

import javax.persistence.*;

@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class TipoUsuario {
    @GeneratedValue
    @Id
    long id;
    public abstract int limiteDePrendas();
    public abstract boolean tieneLimiteDePrendas();
}
