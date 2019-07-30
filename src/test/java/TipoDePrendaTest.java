package domain;

import domain.prenda.Categoria;
import domain.prenda.Material;
import domain.prenda.TipoDePrenda;
import exceptions.MaterialInvalidoException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class TipoDePrendaTest {

    private Categoria categoria;
    private List<Material> materiales = new ArrayList<Material>();
    private TipoDePrenda tipoDePrenda;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deberiaHaberCategoriaAlCrearTipoDePrenda() {
        this.materiales.add(Material.ORO);
        this.categoria = null;
        exception.expect(NullPointerException.class);
        exception.expectMessage("La categoría es obligatoria");
        this.tipoDePrenda = new TipoDePrenda(this.categoria, this.materiales);
    }

    @Test
    public void deberiaHaberMaterialesAsociadosAlCrearTipoDePrenda() {
        this.materiales = null;
        this.categoria = Categoria.CALZADO;
        exception.expect(NullPointerException.class);
        exception.expectMessage("Los materiales son obligatorios");
        this.tipoDePrenda = new TipoDePrenda(this.categoria, this.materiales);
    }

    @Test
    public void deberiaSerMaterialValidoParaTipo() {
        this.materiales.add(Material.ORO);
        this.categoria = Categoria.CALZADO;
        this.tipoDePrenda = new TipoDePrenda(this.categoria, this.materiales);
        exception.expect(MaterialInvalidoException.class);
        exception.expectMessage("El material no es permitido en el tipo de prenda");
        this.tipoDePrenda.validarMaterial(Material.ALGODON);
    }

    @Test
    public void tipoDePrendaConExito() {
        this.materiales.add(Material.ORO);
        this.categoria = Categoria.ACCESORIO;
        this.tipoDePrenda = new TipoDePrenda(this.categoria, this.materiales);
    }

}
