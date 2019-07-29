package domain;

import domain.usuario.tipoDeUsuario.*;
import domain.prenda.*;
import org.junit.*;
import exceptions.*;
import org.junit.rules.ExpectedException;

public class BorradorTest {

    private TipoDePrenda tipoDePrenda;
    private Material material;
    private Color colorPrimario;
    private Borrador borradorZapatillas;
    private Trama trama;
    private Guardarropa guardarropa;
    private double temperaturaMin;
    private double temperaturaMax;
    private boolean esParaLluvia;
    private Material materialInvalido;
    private Usuario magdalena;

    @Before
    public void iniciarTest() {
        this.magdalena = new Usuario(Premium.getInstance());
        this.tipoDePrenda = TipoDePrenda.ZAPATO;
        this.material = Material.CUERO;
        this.colorPrimario = new Color(20, 20, 30);
        this.trama = Trama.LISA;
        this.temperaturaMin = 0;
        this.temperaturaMax = 20;
        this.esParaLluvia = true;
        this.guardarropa = new Guardarropa(magdalena);
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
        borradorZapatillas.definirTipo(this.tipoDePrenda).definirMaterial(this.material).definirColorPrimario(this.colorPrimario);
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
    public void crearPrendaSinTipoDePrenda() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("El tipo de prenda es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaSinColorPrimario() {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda).definirMaterial(this.material);
        this.exception.expect(NullPointerException.class);
        this.exception.expectMessage("El color es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaSinMaterial() {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda).definirColorPrimario(this.colorPrimario).definirGuardarropa(this.guardarropa);
        exception.expect(NullPointerException.class);
        exception.expectMessage("El material es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaSinGuardarropa() {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda).definirMaterial(this.material).definirColorPrimario(this.colorPrimario);
        exception.expect(NullPointerException.class);
        exception.expectMessage("El guardarropa es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaConExito() {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda).definirMaterial(this.material).definirColorPrimario(this.colorPrimario).definirColorPrimario(this.colorPrimario).definirGuardarropa(this.guardarropa);
        Prenda prendaEsperada = new Prenda(this.tipoDePrenda, this.material, this.colorPrimario, null, this.trama, this.guardarropa, this.temperaturaMin, this.temperaturaMax, this.esParaLluvia);
        Assert.assertEquals(prendaEsperada.obtenerTipoDePrenda(),this.tipoDePrenda);
        Assert.assertEquals(prendaEsperada.obtenerColorPrimario(),this.colorPrimario);
        Assert.assertNull(prendaEsperada.obtenerColorSecundario());
        Assert.assertEquals(prendaEsperada.obtenerTrama(),this.trama);
        Assert.assertEquals(prendaEsperada.obtenerGuardarropa(),this.guardarropa);
        Assert.assertEquals(prendaEsperada.obtenerMaterial(), this.material);
    }
}

