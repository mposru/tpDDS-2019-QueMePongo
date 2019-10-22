package domain.usuario.transiciones;

import domain.*;

import javax.persistence.*;

// convertimos interfaz en clase abstracta para poder persistirla

@DiscriminatorColumn(name = "tipo_decision")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Decision {
    @GeneratedValue
    @Id
    long id;

    @ManyToOne
    @JoinColumn(name = "atuendo_id")
    public Atuendo atuendo;

    public abstract void deshacer(Usuario usuario);
}
