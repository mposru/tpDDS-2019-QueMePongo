package domain;

import org.junit.*;
import exceptions.*;
import org.junit.rules.ExpectedException;

public class BorradorTest {

    private Prenda prenda;
    private TipoDePrenda tipoDePrenda;
    private Material material;
    private Color colorPrimario;
    private Borrador borradorZapatillas;
    private Trama trama;
    private Guardarropa guardarropa;
    private Material materialInvalido;

    @Before
    public void iniciarTest() {
        this.tipoDePrenda = TipoDePrenda.ZAPATO;
        this.material = Material.CUERO;
        this.colorPrimario = new Color(20, 20, 30);
        this.trama = Trama.CUADROS;
        this.guardarropa = new Guardarropa();
        //creamos un borrador sin definirle el tipoDePrenda, material, trama y guardarropa
        this.borradorZapatillas = new Borrador();
        this.materialInvalido = Material.JEAN;
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void definirTipoDePrendaVacio() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar un tipo de prenda");
        this.borradorZapatillas.definirTipo(null);
    }

    @Test
    public void definirMaterialVacio() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar un material");
        this.borradorZapatillas.definirMaterial(null);
    }

    @Test
    public void definirMaterialSinTipoDePrenda() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe definir el tipo de prenda antes de definir el material");
        this.borradorZapatillas.definirMaterial(this.material);
    }

    @Test
    public void definirMaterialInvalido() {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda);
        exception.expect(MaterialInvalidoException.class);
        exception.expectMessage("El material no es permitido en el tipo de prenda");
        this.borradorZapatillas.definirMaterial(this.materialInvalido);
    }

    @Test
    public void definirTramaVacia() {
        borradorZapatillas.definirTipo(this.tipoDePrenda);
        borradorZapatillas.definirMaterial(this.material);
        borradorZapatillas.definirColorPrimario(this.colorPrimario);
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar una trama");
        borradorZapatillas.definirTrama(null);
    }

    @Test
    public void definirColorPrimarioVacio() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar un color primario");
        this.borradorZapatillas.definirColorPrimario(null);
    }

    @Test
    public void definirColorSecundarioIgualAlPrimario() {
        this.borradorZapatillas.definirColorPrimario(this.colorPrimario);
        this.exception.expect(ColorSecundarioException.class);
        this.exception.expectMessage("El color secundario debe ser distinto al color primario");
        this.borradorZapatillas.definirColorSecundario(this.colorPrimario);
    }

    @Test
    public void crearPrendaSinTipoDePrenda() throws Exception {
        exception.expect(NullPointerException.class);
        exception.expectMessage("El tipo de prenda es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaSinColorPrimario() throws Exception {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda);
        this.borradorZapatillas.definirMaterial(this.material);
        this.exception.expect(NullPointerException.class);
        this.exception.expectMessage("El color es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaSinMaterial() throws Exception {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda);
        this.borradorZapatillas.definirColorPrimario(this.colorPrimario);
        this.borradorZapatillas.definirGuardarropa(this.guardarropa);
        exception.expect(NullPointerException.class);
        exception.expectMessage("El material es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaSinGuardarropa() throws Exception {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda);
        this.borradorZapatillas.definirMaterial(this.material);
        this.borradorZapatillas.definirColorPrimario(this.colorPrimario);
        exception.expect(NullPointerException.class);
        exception.expectMessage("El guardarropa es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaConExito() {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda);
        this.borradorZapatillas.definirMaterial(this.material);
        this.borradorZapatillas.definirColorPrimario(this.colorPrimario);
        this.borradorZapatillas.definirGuardarropa(this.guardarropa);
    }
}

