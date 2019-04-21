import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class TipoDePrenda {

    private Categoria categoria;
    private List<Material> materialesValidos = new ArrayList();
    static final TipoDePrenda ZAPATO = new TipoDePrenda(Categoria.CALZADO);
    static final TipoDePrenda REMERA = new TipoDePrenda(Categoria.PARTE_SUPERIOR);
    static final TipoDePrenda PANTALON = new TipoDePrenda(Categoria.PARTE_INFERIOR);
    static final TipoDePrenda COLLAR = new TipoDePrenda(Categoria.ACCESORIOS);

    public TipoDePrenda(Categoria categoria) {
        this.categoria = requireNonNull(categoria, "la categoria es obligatoria");;
    }

    public Categoria categoria() {
        return this.categoria;
    }

    public void validarMaterial(Material material) {
        //validar listaDeMateriales segun tipoDePrenda
    }

}
