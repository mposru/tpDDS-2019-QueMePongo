package domain.prenda;

import exceptions.MaterialInvalidoException;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class TipoDePrenda {

    private Categoria categoria;
    private List<Material> materialesValidos;

    public static final TipoDePrenda ZAPATO = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.CUERO));
    public static final TipoDePrenda REMERA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.SEDA, Material.ALGODON));
    public static final TipoDePrenda BUZO = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.ALGODON));
    public static final TipoDePrenda PANTALON = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.JEAN, Material.ALGODON));
    public static final TipoDePrenda COLLAR = new TipoDePrenda(Categoria.ACCESORIO, Arrays.asList(Material.ACERO));
    public static final TipoDePrenda CROCS = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.GOMA));
    public static final TipoDePrenda BOTAS = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.CUERO));
    public static final TipoDePrenda POLLERA = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.JEAN, Material.ALGODON));
    public static final TipoDePrenda SHORT = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.JEAN, Material.ALGODON));
    public static final TipoDePrenda BLUSA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.SEDA, Material.ALGODON));
    public static final TipoDePrenda MUSCULOSA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.ALGODON));
    public static final TipoDePrenda REMERA_MANGA_LARGA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.SEDA, Material.ALGODON));
    public static final TipoDePrenda ANTEOJOS = new TipoDePrenda(Categoria.ACCESORIO, Arrays.asList(Material.PLASTICO));
    public static final TipoDePrenda PANUELO = new TipoDePrenda(Categoria.ACCESORIO, Arrays.asList(Material.ALGODON));
    public static final TipoDePrenda SIN_ACCESORIO = new TipoDePrenda(Categoria.ACCESORIO, Arrays.asList(Material.NINGUNO));
    public static final TipoDePrenda MEDIAS= new TipoDePrenda(Categoria.ACCESORIO, Arrays.asList(Material.POLYESTER,Material.ALGODON));
    public static final TipoDePrenda CAMPERA = new TipoDePrenda(Categoria.PARTE_SUPERIOR,Arrays.asList(Material.POLYESTER,Material.ALGODON));
    public static final TipoDePrenda CANILLERA = new TipoDePrenda(Categoria.ACCESORIO,Arrays.asList(Material.PLASTICO,Material.GOMA));
    public static final TipoDePrenda BOTAS_NIEVE = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.ALGODON));
    public static final TipoDePrenda ZAPATILLAS = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.ALGODON));
    public static final TipoDePrenda NINGUNO_SUPERIOR = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.NINGUNO));
    public static final TipoDePrenda GUANTES = new TipoDePrenda(Categoria.ACCESORIO,Arrays.asList(Material.LANA));
    public static final TipoDePrenda GORROABRIGO = new TipoDePrenda(Categoria.ACCESORIO,Arrays.asList(Material.LANA));
    public static final TipoDePrenda PARAGUAS = new TipoDePrenda(Categoria.ACCESORIO,Arrays.asList(Material.PLASTICO));
    public static final TipoDePrenda CASCO = new TipoDePrenda(Categoria.ACCESORIO,Arrays.asList(Material.PLASTICO));
    public static final TipoDePrenda BUFANDA = new TipoDePrenda(Categoria.ACCESORIO_CUELLO,Arrays.asList(Material.LANA));


    public TipoDePrenda(Categoria categoria, List<Material> materialesValidos) {
        this.categoria = requireNonNull(categoria, "La categoría es obligatoria");
        this.materialesValidos = requireNonNull(materialesValidos, "Los materiales son obligatorios");
    }

    public Categoria obtenerCategoria() {
        return this.categoria;
    }

    public void validarMaterial(Material material)  {
        if (!materialesValidos.contains(material)) {
            throw new MaterialInvalidoException("El material no es permitido en el tipo de prenda");
        }
    }

    public List<Material> obtenerMaterialesValidos() {
        return materialesValidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoDePrenda tipoDePrenda = (TipoDePrenda) o;
        return categoria == tipoDePrenda.obtenerCategoria() &&
                materialesValidos.equals(tipoDePrenda.obtenerMaterialesValidos());
    }

}
