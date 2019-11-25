
package domain;

import domain.guardarropa.Premium;
import domain.usuario.Calendario;
import domain.prenda.*;
import org.junit.*;
import exceptions.*;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Set;

public class BorradorTest {

    private TipoDePrenda tipoDePrenda;
    private Material material;
    private Color colorPrimario;
    private Borrador borradorZapatillas;
    private Trama trama;
    private Guardarropa guardarropa;
    private boolean esParaLluvia;
    private Material materialInvalido;
    private Usuario magdalena;
    private Calendario calendario;
    private String nombre;

    @Before
    public void iniciarTest() {
        this.magdalena = new Usuario("", calendario, "contrasenialoca","","",null);
        this.tipoDePrenda = TipoDePrenda.ZAPATO;
        this.material = Material.CUERO;
        this.colorPrimario = new Color(20, 20, 30);
        this.trama = Trama.LISA;
        this.esParaLluvia = true;
        this.nombre = "zapatillas converse";

        Set<Usuario> magdalenaLista = new HashSet<>();
        magdalenaLista.add(magdalena);
        this.guardarropa = new Guardarropa("GuardarropaMagdalena",0);
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
    public void definirNombreDePrenda() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar un tipo de prenda");
        this.borradorZapatillas.definirTipo(null);
    }
    @Test
    public void definirNombreDePrendaVacio() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar el nombre de la prenda");
        this.borradorZapatillas.definirNombre(null);
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
        this.borradorZapatillas.definirNombre("zapatillas");
        exception.expect(NullPointerException.class);
        exception.expectMessage("El tipo de prenda es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaSinColorPrimario() {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda).definirMaterial(this.material).definirNombre("zapatillas");
        this.exception.expect(NullPointerException.class);
        this.exception.expectMessage("El color es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaSinMaterial() {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda).definirColorPrimario(this.colorPrimario).definirGuardarropa(this.guardarropa).definirNombre("zapatillas");
        exception.expect(NullPointerException.class);
        exception.expectMessage("El material es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaSinGuardarropa() {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda).definirMaterial(this.material).definirColorPrimario(this.colorPrimario).definirNombre("zapatillas");
        exception.expect(NullPointerException.class);
        exception.expectMessage("El guardarropa es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaConExito() {
        this.borradorZapatillas.definirTipo(this.tipoDePrenda).definirMaterial(this.material).definirColorPrimario(this.colorPrimario).definirColorPrimario(this.colorPrimario).definirGuardarropa(this.guardarropa);
        Prenda prendaEsperada = new Prenda("PrendaEsperada",this.tipoDePrenda, this.material, this.colorPrimario, null, this.trama, this.guardarropa, this.esParaLluvia);
        Assert.assertEquals(prendaEsperada.obtenerTipoDePrenda(),this.tipoDePrenda);
        Assert.assertEquals(prendaEsperada.obtenerColorPrimario(),this.colorPrimario);
        Assert.assertNull(prendaEsperada.obtenerColorSecundario());
        Assert.assertEquals(prendaEsperada.obtenerTrama(),this.trama);
        Assert.assertEquals(prendaEsperada.obtenerGuardarropa(),this.guardarropa);
        Assert.assertEquals(prendaEsperada.obtenerMaterial(), this.material);
    }
}

