package domain;


import org.junit.*;
import exceptions.*;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class BorradorTest {

    private Prenda prenda;
    private TipoDePrenda tipoDePrenda;
    private Material material;
    private Color colorPrimario;
    private Color colorSecundario;
    private Color otroColorSecundario;
    private Borrador borradorZapatillas;
    private Trama trama;
    private Guardarropa guardarropa;
    private Usuario lioMessi;

    @Before
    public void iniciar() {
        this.tipoDePrenda = TipoDePrenda.ZAPATO;
        this.colorPrimario = new Color(20, 20, 30);
        this.colorSecundario = new Color(20, 20, 30);
        this.otroColorSecundario = new Color(25, 40, 88);
        this.trama = Trama.CUADROS;
        this.lioMessi = new Usuario("Lionel", "Messi", "lio.messi@est.utn.frba.edu.ar");
        this.guardarropa = new Guardarropa(lioMessi);
        //creamos un borrador sin definirle el tipoDePrenda, material, trama y guardarropa
        this.borradorZapatillas = new Borrador();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void deberiaHaberColorPrimarioAlCrearPrenda() throws Exception {
        this.borradorZapatillas.definirTipo(new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.CUERO)));
        this.borradorZapatillas.definirMaterial(Material.CUERO);
        //  this.borradorZapatillas.definirColorPrimario(new Color(29,29,29));
        this.borradorZapatillas.definirGuardarropa(guardarropa);
        this.exception.expect(NullPointerException.class);
        this.exception.expectMessage("El color es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }
    @Test
    public void setearColorSecundarioIgualAlPrimario() throws Exception {
        try {
            this.borradorZapatillas.definirColorPrimario(colorPrimario);
            this.borradorZapatillas.definirColorSecundario(colorPrimario);
        } catch (ColorSecundarioException exception) {
            System.out.print(exception.getMessage());
        }
    }


    @Test
    public void deberiaHaberTipoDePrendaAlCrearPrenda() throws Exception {
        //    this.borradorZapatillas.definirTipo(new TipoDePrenda(Categoria.CALZADO,Arrays.asList(Material.CUERO)));
       //  this.borradorZapatillas.definirMaterial(Material.CUERO);
        this.borradorZapatillas.definirColorPrimario(new Color(29, 29, 29));
        this.borradorZapatillas.definirGuardarropa(guardarropa);
        exception.expect(NullPointerException.class);
        exception.expectMessage("El tipo de prenda es obligatorio");
     //   exception.expectMessage("Debe definir el tipo de prenda antes de definir el material");
        this.borradorZapatillas.crearPrenda();
    }

    @Test
    public void crearPrendaSinMaterial() throws Exception {
        this.borradorZapatillas.definirTipo(new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.CUERO)));
        //    this.borradorZapatillas.definirMaterial(Material.CUERO);
        this.borradorZapatillas.definirColorPrimario(new Color(29, 29, 29));
      //   this.borradorZapatillas.definirTrama(Trama.LISA);
        this.borradorZapatillas.definirGuardarropa(guardarropa);
        exception.expect(NullPointerException.class);
        exception.expectMessage("El material es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

   /* El test de la trama lo sacamos, porque por defecto viene LISA y no tiene sentido probar si creo una prenda sin trama
        porque no lo valida

    @Test
    public void crearPrendaSinTrama() throws Exception {
        borradorZapatillas.definirTipo(new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.CUERO)));
        borradorZapatillas.definirMaterial(Material.CUERO);
        borradorZapatillas.definirColorPrimario(new Color(29, 29, 29));
       // borradorZapatillas.definirGuardarropa(guardarropa);

        exception.expect(NullPointerException.class);
        exception.expectMessage("Debe ingresar una trama");
        borradorZapatillas.definirTrama(null);
    }
*/
    @Test
    public void crearPrendaSinGuardarropa() throws Exception {
        this.borradorZapatillas.definirTipo(new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.CUERO)));
        this.borradorZapatillas.definirMaterial(Material.CUERO);
        this.borradorZapatillas.definirColorPrimario(new Color(29, 29, 29));
      //   this.borradorZapatillas.definirTrama(Trama.LISA);
        //    this.borradorZapatillas.definirGuardarropa(guardarropa);
        exception.expect(NullPointerException.class);
        exception.expectMessage("El guardarropa es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }
}

