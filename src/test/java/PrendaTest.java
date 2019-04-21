import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PrendaTest {

    private Prenda prenda;
    private TipoDePrenda tipoDePrenda;
    private Material material;
    private Color color;

    // Hay que agregar mas Test y retocar los que estan

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deberiaHaberTipoDePrendaAlCrearPrenda() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("el tipo de prenda es obligatorio");

        material = Material.ALGODON;
        color = new Color(100,100,100);
        prenda = new Prenda(tipoDePrenda, material, color);
    }

    @Test
    public void deberiaHaberMaterialAlCrearPrenda() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("el material es obligatorio");

        tipoDePrenda = TipoDePrenda.PANTALON;
        color = new Color(100,100,100);
        prenda = new Prenda(tipoDePrenda, material, color);
    }

    @Test
    public void deberiaHaberColorPrimarioAlCrearPrenda() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("el color primario es obligatorio");

        tipoDePrenda = TipoDePrenda.PANTALON;
        material = Material.ALGODON;
        prenda = new Prenda(tipoDePrenda, material, color);
    }
}
