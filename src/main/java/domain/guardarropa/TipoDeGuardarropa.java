package domain.guardarropa;

import javax.persistence.*;

@MappedSuperclass
@Embeddable
public abstract class TipoDeGuardarropa {

     public abstract int getLimiteDePrendas();
     public abstract void setLimiteDePrendas(int limiteDePrendas);
     public abstract boolean tieneLimiteDePrendas();
}
