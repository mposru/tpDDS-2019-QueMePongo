package domain;

public class Atuendo {

    private Prenda accesorio;
    private Prenda prendaSuperior;
    private Prenda prendaInferior;
    private Prenda calzado;
    private EstadoAtuendo estado;
    private Integer calificacion;

    public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado, Prenda accesorio) {
        this.accesorio = accesorio;
        this.prendaSuperior = prendaSuperior;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
        this.estado = new Nuevo(); //toda prenda nace en estado nuevo.
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

    public void calificar(Integer calificacion) {
       // hay que agregar la lógica para que se pueda calificar si está en estado aceptado solamento, sino tiramos excepción
        this.calificacion = calificacion;
    }

}