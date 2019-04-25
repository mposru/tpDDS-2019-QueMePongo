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
        materiales.add(Material.ORO);
        categoria = null;
    }

    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deberiaHaberCategoriaAlCrearTipoDePrenda() {
        try {
            tipoDePrenda = new TipoDePrenda(categoria, materiales);
        } catch (Exception e) {
            exception.expect(NullPointerException.class);
            exception.expectMessage("La categoría es obligatoria");
        }
    }

    @Before
    public void iniciarParaMaterialesAsociados() {
        materiales.remove(Material.ORO);
        categoria = Categoria.CALZADO;
    }

    @Test
    public void deberiaHaberMaterialesAsociadosAlCrearTipoDePrenda() {
        try {
            tipoDePrenda = new TipoDePrenda(categoria, materiales);
        } catch (Exception e) {
            exception.expect(NullPointerException.class);
            exception.expectMessage("Los materiales son obligatorios");
        }
    }

    @Before
    public void iniciarParaVerSiMaterialEsValido() {
        tipoDePrenda = new TipoDePrenda(categoria, materiales);
    }

    @Test
    public void deberiaSerMaterialValidoParaTipo() {
        try {
            tipoDePrenda.validarMaterial(Material.ALGODON);
        } catch (MaterialInvalidoException excepcion) {
            System.out.println(excepcion.getMessage());
        }
    }
}
