import static java.util.Objects.requireNonNull;

public class Prenda {

    private TipoDePrenda tipoDePrenda;
    private Material material;
    private Color colorPrimario;
    private Color colorSecundario;
    private Trama trama;

    //falta esSuperior, etc para el filter o map o eso

    public Prenda(TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama) {
        tipoDePrenda.validarMaterial(material);
        this.tipoDePrenda = tipoDePrenda;
        this.material = material;
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.trama = trama;
    }

    public Categoria categoria() {
        return tipoDePrenda.categoria();
    }

}
