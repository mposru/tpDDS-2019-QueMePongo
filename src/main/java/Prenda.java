import static java.util.Objects.requireNonNull;

public class Prenda {

    private TipoDePrenda tipoDePrenda;
    private Material material;
    private Color colorPrimario;
    private Color colorSecundario;
    private Trama trama;
    private Guardarropa guardarropa;

    //falta esSuperior, etc para el filter o map o eso

    public Prenda(TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama, Guardarropa guardarropa) throws Exception {
        tipoDePrenda.validarMaterial(material);
        this.tipoDePrenda = tipoDePrenda;
        this.material = material;
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.trama = trama;
        this.guardarropa = guardarropa;
    }

    public Trama obtenerTrama() {
        return trama;
    }

    public Color obtenerColorPrimario() {
        return colorPrimario;
    }

    public Color obtenerColorSecundario() {
        return colorSecundario;
    }

    public Material obtenerMaterial() {
        return material;
    }

    public TipoDePrenda obtenerTipoDePrenda() {
        return tipoDePrenda;
    }

    public Categoria obtenerCategoria() { return tipoDePrenda.obtenerCategoria(); }
}
