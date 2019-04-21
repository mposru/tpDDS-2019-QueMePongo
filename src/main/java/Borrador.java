import static java.util.Objects.requireNonNull;

public class Borrador  {
    private TipoDePrenda tipoDePrenda;
    private Trama trama = Trama.LISA;
    private Color colorPrimario;
    private Color colorSecundario;
    private Material material;

    // definirTipo(TipoPrenda)
    // definirColorPrimario(Color)
    // definirColorSecundario diferente del colorPrimario
    // definirTrama(Trama)
    // definirMaterial(Material) que tire la excepcion
    // crearPrenda() valido los requireNonNull aca
    // trama = liso

    public void definirTipo(TipoDePrenda tipoDePrenda) {
        this.tipoDePrenda = tipoDePrenda;
    }

    public void definirColorPrimario(Color colorPrimario) {
        this.colorPrimario = colorPrimario;
    }

    public void definirColorSecundario(Color colorSecundario) throws Exception {
        if(colorPrimario == colorSecundario) {
            throw new Exception("El color secundario no puede ser igual al primario");
        }
        else {
            this.colorSecundario = colorSecundario;
        }
    }

    public void definirTrama(Trama trama) {
        this.trama = trama;
    }

    public void definirMaterial(Material material) {
        this.material = material;
    }

    public Prenda crearPrenda() {
        this.tipoDePrenda = requireNonNull(tipoDePrenda, "el tipo de prenda es obligatorio");
        this.material = requireNonNull(material, "el material es obligatorio");
        this.colorPrimario = requireNonNull(colorPrimario, "el color primario es obligatorio");
        return new Prenda(tipoDePrenda, material, colorPrimario, colorSecundario, trama);
    }
}
