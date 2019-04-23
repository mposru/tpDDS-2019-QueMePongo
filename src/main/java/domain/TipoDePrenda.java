package domain;

import exceptions.MaterialInvalidoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.Objects.requireNonNull;

public class TipoDePrenda {

    private Categoria categoria;
    private List<Material> materialesValidos;

    static final TipoDePrenda ZAPATO = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.CUERO));
    static final TipoDePrenda REMERA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.SEDA, Material.ALGODON));
    static final TipoDePrenda PANTALON = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.JEAN, Material.ALGODON));
    static final TipoDePrenda COLLAR = new TipoDePrenda(Categoria.ACCESORIO, Arrays.asList(Material.ACERO));
    public static final TipoDePrenda CROCS = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.GOMA));
    public static final TipoDePrenda BOTAS = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.CUERO));
    public static final TipoDePrenda POLLERA = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.JEAN, Material.ALGODON));
    public static final TipoDePrenda SHORT = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.JEAN, Material.ALGODON));
    public static final TipoDePrenda BLUSA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.SEDA, Material.ALGODON));
    public static final TipoDePrenda MUSCULOSA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.ALGODON));
    public static final TipoDePrenda REMERA_MANGA_LARGA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.SEDA, Material.ALGODON));
    public static final TipoDePrenda ANTEOJOS = new TipoDePrenda(Categoria.ACCESORIO, Arrays.asList(Material.PLASTICO));
    public static final TipoDePrenda PANUELO = new TipoDePrenda(Categoria.ACCESORIO, Arrays.asList(Material.ALGODON));


    public TipoDePrenda(Categoria categoria, List<Material> materialesValidos) {
        this.categoria = requireNonNull(categoria, "La categoría es obligatoria");
        this.materialesValidos = requireNonNull(materialesValidos, "Los materiales son obligatorios");
    }

    public Categoria obtenerCategoria() {
        return categoria;
    }

    public void validarMaterial(Material material)  {
        if (!materialesValidos.contains(material)) {
            throw new MaterialInvalidoException("El material no es permitido en el tipo de prenda");
        }
    }

}
