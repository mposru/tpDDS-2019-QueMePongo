package domain;

public class Atuendo {

    private Prenda accesorio;
    private Prenda prendaSuperior;
    private Prenda prendaInferior;
    private Prenda calzado;

    public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado, Prenda accesorio) {
        this.accesorio = accesorio;
        this.prendaSuperior = prendaSuperior;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
    }

    public Prenda obtenerPrendaSuperior() {
        return prendaSuperior;
    }

    public Prenda obtenerPrendaInferior() {
        return prendaInferior;
    }

    public Prenda obtenerAccesorio() {
        return accesorio;
    }

    public Prenda obtenerCalzado() {
        return calzado;
    }

}