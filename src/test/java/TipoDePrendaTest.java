package domain;

import org.junit.*;
import exceptions.*;
import org.junit.rules.ExpectedException;

import java.util.*;

public class TipoDePrendaTest {

    private Categoria categoria;
    private List<Material> materiales = new ArrayList<Material>();
    private TipoDePrenda tipoDePrenda;

    @Before
    public void iniciarParaCategoria() {
        this.materiales.add(Material.ORO);
        this.categoria = null;
    }

    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deberiaHaberCategoriaAlCrearTipoDePrenda() {
        try {
            this.tipoDePrenda = new TipoDePrenda(categoria, materiales);
        } catch (Exception e) {
            exception.expect(NullPointerException.class);
            exception.expectMessage("La categoría es obligatoria");
        }
    }

    @Before
    public void iniciarParaMaterialesAsociados() {
        this.materiales.remove(Material.ORO);
        this.categoria = Categoria.CALZADO;
    }

    @Test
    public void deberiaHaberMaterialesAsociadosAlCrearTipoDePrenda() {
        try {
            this.tipoDePrenda = new TipoDePrenda(categoria, materiales);
        } catch (Exception e) {
            exception.expect(NullPointerException.class);
            exception.expectMessage("Los materiales son obligatorios");
        }
    }

    @Before
    public void iniciarParaVerSiMaterialEsValido() {
        this.tipoDePrenda = new TipoDePrenda(categoria, materiales);
    }

    @Test
    public void deberiaSerMaterialValidoParaTipo() {
        try {
            this.tipoDePrenda.validarMaterial(Material.ALGODON);
        } catch (MaterialInvalidoException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
