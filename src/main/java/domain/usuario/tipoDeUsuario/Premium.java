package domain.usuario.tipoDeUsuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("P")
@Entity
public class Premium extends TipoUsuario {
    private static Premium instanceOfPremium;
    private Premium(){}
    public static Premium getInstance() {
        if(instanceOfPremium==null) {
            instanceOfPremium = new Premium();
        }
        return instanceOfPremium;

    }
    public int limiteDePrendas() {
        return 0;
    }
    public boolean tieneLimiteDePrendas() {return false;}
}
