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
    private Usuario lioMessi;

    @Before
    public void iniciar() {
        this.tipoDePrenda = TipoDePrenda.ZAPATO;
        this.colorPrimario = new Color(20,20,30);
        this.colorSecundario = new Color(20,20,30);
        this.otroColorSecundario = new Color(25,40,88);
        this.trama = Trama.CUADROS;
        this.lioMessi = new Usuario("Lionel","Messi","lio.messi@est.utn.frba.edu.ar")
        this.guardarropa = new Guardarropa(lioMessi);
        //creamos un borrador sin definirle el tipoDePrenda, material, trama y guardarropa
        this.borradorZapatillas = new Borrador();
        this.borradorZapatillas.definirColorPrimario(colorPrimario);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void setearColorSecundarioIgualAlPrimario() throws Exception {
        try {
            this.borradorZapatillas.definirColorSecundario(colorSecundarioIgualPrimario);
        }
        catch (ColorSecundarioException exception){
            System.out.print(exception.getMessage());
        }
    }
    @Test
    public void deberiaHaberColorAlCrearPrenda() throws Exception {
        borradorZapatillas.definirTipo(new TipoDePrenda(Categoria.CALZADO,Arrays.asList(Material.CUERO)));
        borradorZapatillas.definirMaterial(Material.CUERO);
       // borradorZapatillas.definirColorPrimario(new Color(29,29,29));
        borradorZapatillas.definirTrama(Trama.LISA);
        borradorZapatillas.definirGuardarropa(guardarropa);
        exception.expect(NullPointerException.class);
        exception.expectMessage("El color es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }
    @Test
    public void deberiaHaberTipoDePrendaAlCrearPrenda() throws Exception {
     //   borradorZapatillas.definirTipo(new TipoDePrenda(Categoria.CALZADO,Arrays.asList(Material.CUERO)));
        borradorZapatillas.definirMaterial(Material.CUERO);
        borradorZapatillas.definirColorPrimario(new Color(29,29,29));
        borradorZapatillas.definirTrama(Trama.LISA);
        borradorZapatillas.definirGuardarropa(guardarropa);

        exception.expect(NullPointerException.class);
        exception.expectMessage("El tipo de prenda es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }
    @Test
    public void crearPrendaSinMaterial() throws Exception {
        borradorZapatillas.definirTipo(new TipoDePrenda(Categoria.CALZADO,Arrays.asList(Material.CUERO)));
     //   borradorZapatillas.definirMaterial(Material.CUERO);
        borradorZapatillas.definirColorPrimario(new Color(29,29,29));
        borradorZapatillas.definirTrama(Trama.LISA);
        borradorZapatillas.definirGuardarropa(guardarropa);
        exception.expect(NullPointerException.class);
        exception.expectMessage("El material es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }
    @Test
    public void crearPrendaSinTrama() throws Exception {
        borradorZapatillas.definirTipo(new TipoDePrenda(Categoria.CALZADO,Arrays.asList(Material.CUERO)));
         borradorZapatillas.definirMaterial(Material.CUERO);
        borradorZapatillas.definirColorPrimario(new Color(29,29,29));
     //   borradorZapatillas.definirTrama(Trama.LISA);
        borradorZapatillas.definirGuardarropa(guardarropa);

        exception.expect(NullPointerException.class);
        exception.expectMessage("La trama es obligatoria");
        this.borradorZapatillas.crearPrenda();
    }
    @Test
    public void crearPrendaSinGuardarropa() throws Exception {
        borradorZapatillas.definirTipo(new TipoDePrenda(Categoria.CALZADO,Arrays.asList(Material.CUERO)));
        borradorZapatillas.definirMaterial(Material.CUERO);
        borradorZapatillas.definirColorPrimario(new Color(29,29,29));
        borradorZapatillas.definirTrama(Trama.LISA);
     //   borradorZapatillas.definirGuardarropa(guardarropa);
        exception.expect(NullPointerException.class);
        exception.expectMessage("El guardarropa es obligatorio");
        this.borradorZapatillas.crearPrenda();
    }

}