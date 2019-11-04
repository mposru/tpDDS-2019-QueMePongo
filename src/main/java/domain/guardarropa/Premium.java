package domain.guardarropa;

import javax.persistence.*;


@Embeddable
public class Premium extends TipoDeGuardarropa {
    @Column(name = "tipo_guardarropa")
    private String tipoGuardarropa;
    @Column(name = "limite_prendas")
    private int limiteDePrendas;


    public Premium() {
        this.tipoGuardarropa = "Premium";
        this.limiteDePrendas = 0;

    }

    @Override
    public boolean tieneLimiteDePrendas() {
        return false;
    }
    @Override
    public int getLimiteDePrendas() {
        return this.limiteDePrendas;
    }

    @Override
    public void setLimiteDePrendas(int limiteDePrendas) {
        this.limiteDePrendas = 0;
    }
}
