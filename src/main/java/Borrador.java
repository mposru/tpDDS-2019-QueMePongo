import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class Borrador  {
    private TipoDePrenda tipoDePrenda;
    private Trama trama = Trama.LISA;
    private Color colorPrimario;
    private Color colorSecundario;
    private Material material;
    private Guardarropa guardarropa;

    // definirTipo(TipoPrenda)
    // definirColorPrimario(Color)
    // definirColorSecundario diferente del colorPrimario
    // definirTrama(Trama)
    // definirMaterial(Material) que tire la excepcion
    // crearPrenda() valido los requireNonNull aca
    // trama = liso


    public void definirTipo(TipoDePrenda tipoDePrenda) {
        this.tipoDePrenda = requireNonNull(tipoDePrenda, "Debe ingresar un tipo de prenda");
    }

    public void definirMaterial(Material material) throws Exception {
        requireNonNull(tipoDePrenda, "Debe definir el tipo de prenda antes de definir el material");
        requireNonNull(material, "Debe ingresar un material");
        tipoDePrenda.validarMaterial(material);
        this.material = material;
    }

    public void definirColorPrimario(Color colorPrimario) {
        this.colorPrimario = requireNonNull(colorPrimario, "Debe ingresar un color");
    }

    public void definirColorSecundario(Color colorSecundario) throws Exception {
        if (isNull(colorPrimario)) {
            //esta bien esto?
            throw new Exception("Antes de definir el color primario debe definir el secundario");
        }
        requireNonNull(colorSecundario, "Debe ingresar un color secundario");
        if (colorPrimario.equals(colorSecundario)) {
            throw new Exception("El color secundario debe ser distinto al color primario");
        }
        this.colorSecundario = colorSecundario;
    }

    public void definirTrama(Trama trama) {
        this.trama = requireNonNull(trama, "Debe ingresar una trama");
    }

    public void definirGuardarropa(Guardarropa guardarropa) {
        this.guardarropa = requireNonNull(guardarropa, "Debe asignarle un guardarropa a la prenda");;
    }

    public Prenda crearPrenda() throws Exception {
        requireNonNull(tipoDePrenda, "El tipo de prenda es obligatorio");
        requireNonNull(material, "El material es obligatorio");
        requireNonNull(colorPrimario, "El color es obligatorio");
        requireNonNull(guardarropa, "El guardarropa es obligatorio");
        Prenda prenda = new Prenda(tipoDePrenda, material, colorPrimario, colorSecundario, trama, guardarropa);
        guardarropa.guardarPrenda(prenda);
        return prenda;
    }
}
