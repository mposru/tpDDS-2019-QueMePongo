package domain.guardarropa;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

import static java.util.Objects.requireNonNull;

@Embeddable
public class Gratuito extends TipoDeGuardarropa {
    @Column(name = "tipo_guardarropa")
    public String tipoGuardarropa;
    @Column(name = "limite_prendas")
    public int limiteDePrendas;

    public Gratuito() {
        this.tipoGuardarropa = "Gratuito";
     }

    public Gratuito(int limiteDePrendas) {
       this.limiteDePrendas = requireNonNull(limiteDePrendas, "Debe ingresar la cantidad límite de prendas");
        this.tipoGuardarropa = "Gratuito";
    }
    @Override
    public boolean tieneLimiteDePrendas() {
        return this.limiteDePrendas>0;
    }
    @Override
    public int getLimiteDePrendas() {
        return limiteDePrendas;
    }
    @Override
    public void setLimiteDePrendas(int limiteDePrendas) {
        this.limiteDePrendas = limiteDePrendas;
    }
}
