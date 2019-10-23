package domain.guardarropa;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TipoDeGuardarropa {



     public abstract int getLimiteDePrendas();
     public abstract void setLimiteDePrendas(int limiteDePrendas);
     public abstract boolean tieneLimiteDePrendas();
}
