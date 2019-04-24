package domain;


import org.junit.*;
import exceptions.*;
import org.junit.rules.ExpectedException;

public class BorradorTest {

    private Prenda prenda;
    private TipoDePrenda tipoDePrenda;
    private Material material;
    private Color colorPrimario;
    private Color colorSecundario;
    private Color colorSecundarioIgualPrimario;
    private Color otroColorSecundario;
    private Borrador borradorZapatillas;
    private Trama trama;
    private Guardarropa guardarropa;

    @Before
    public void iniciar() {
        this.tipoDePrenda = TipoDePrenda.ZAPATO;
        this.colorPrimario = new Color(20,20,30);
        this.colorSecundario = new Color(20,20,30);
        this.otroColorSecundario = new Color(25,40,88);
        this.trama = Trama.CUADROS;
        this.guardarropa = new Guardarropa();
        this.borradorZapatillas = new Borrador();
        this.borradorZapatillas.definirColorPrimario(colorPrimario);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void setearColorSecundarioIgualAlPrimario(){
        try {
            this.borradorZapatillas.definirColorSecundario(colorSecundarioIgualPrimario);
        }
        catch (ColorSecundarioException exception){
            System.out.print(exception.getMessage());
        }
    }

    @Test
    public void deberiaHaberTipoDePrendaAlCrearPrenda() throws Exception {
        exception.expect(NullPointerException.class);
        exception.expectMessage("El tipo de prenda es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

}