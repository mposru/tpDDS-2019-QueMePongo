package domain.guardarropa;

import javax.persistence.DiscriminatorValue;

import static java.util.Objects.requireNonNull;

@DiscriminatorValue("Gratuito")
public class Gratuito extends TipoDeGuardarropa {

    private int limiteDePrendas;

    public Gratuito(int limiteDePrendas) {
        this.limiteDePrendas = requireNonNull(limiteDePrendas, "Debe ingresar la cantidad límite de prendas");
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
