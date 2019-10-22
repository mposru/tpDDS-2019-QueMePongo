package domain.usuario.transiciones;

import domain.*;

import javax.persistence.*;

// convertimos interfaz en clase abstracta para poder persistirla
 /*
@DiscriminatorColumn(name = "tipo_decision")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
*/
public abstract class Decision {
  /*  @GeneratedValue
    @Id
    @Column (name = "idDecision")*/
    long id;

  /*  @ManyToOne
    @JoinColumn(name = "idAtuendo")*/
    public Atuendo atuendo;

    public abstract void deshacer(Usuario usuario);
}
