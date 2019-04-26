package domain;

public class Prenda {

    private TipoDePrenda tipoDePrenda;
    private Material material;
    private Color colorPrimario;
    private Color colorSecundario;
    private Trama trama;
    private Guardarropa guardarropa;

    //falta esSuperior, etc para el filter o map o eso

    public Prenda(TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama, Guardarropa guardarropa) {
        this.tipoDePrenda = tipoDePrenda;
        this.material = material;
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.trama = trama;
        this.guardarropa = guardarropa;
    }

    public Trama obtenerTrama() {
        return this.trama;
    }

    public Color obtenerColorPrimario() {
        return this.colorPrimario;
    }

    public Color obtenerColorSecundario() {
        return this.colorSecundario;
    }

    public Material obtenerMaterial() {
        return this.material;
    }

    public TipoDePrenda obtenerTipoDePrenda() {
        return this.tipoDePrenda;
    }

    public Categoria obtenerCategoria() { return this.tipoDePrenda.obtenerCategoria(); }

    public Guardarropa obtenerGuardarropa() {return this.guardarropa; }

}

