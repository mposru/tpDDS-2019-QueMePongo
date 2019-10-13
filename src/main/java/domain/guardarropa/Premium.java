package domain.guardarropa;

public class Premium extends TipoDeGuardarropa {

    private int limiteDePrendas=0;

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
    }
}