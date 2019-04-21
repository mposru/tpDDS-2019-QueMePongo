import java.util.ArrayList;
import java.util.List;

public class Atuendo {

    private List<Prenda> prendas = new ArrayList();

    public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado, Prenda accesorios) {
        prendas.add(prendaSuperior);
        prendas.add(prendaInferior);
        prendas.add(calzado);
        prendas.add(accesorios);
    }

}
