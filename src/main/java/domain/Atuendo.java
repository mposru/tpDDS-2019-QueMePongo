package domain;
import exceptions.*;


public class Atuendo {

    private Prenda accesorio;
    private Prenda prendaSuperior;
    private Prenda prendaInferior;
    private Prenda calzado;
    private EstadoAtuendo estado;
    private int calificacion = 0;
    private int calificacionAnterior = 0;

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

    public String obtenerEstadoAtuendo() { return estado.nombre(); }

    public int obtenerCalificacionActual() { return this.calificacion;}
    public int obtenerCalificacionAnterior() { return this.calificacionAnterior;}

    public void aceptar() {
        if(!this.obtenerEstadoAtuendo().equals("Nuevo")) {
            throw new NoSePuedeAceptarException("Sólo se puede aceptar un atuendo con estado = Nuevo");
        }
        this.estado = new Aceptado(); //quedaría mejor usar singleton. Hay que cambiarlo
    }

    public void calificar(int nuevaCalificacion) {
        if (nuevaCalificacion < 1 && nuevaCalificacion > 5) {
            throw new RangoDeCalificacionException("La calificación del atuendo debe estar entre 1 y 5");
        }
        if (this.obtenerEstadoAtuendo().equals("Calificado")) {
            this.calificacionAnterior = this.calificacion;
        }
        if (!this.obtenerEstadoAtuendo().equals("Aceptado")) {
            throw new NoSePuedeCalificarException("Sólo se puede calificar un atuendo aceptado");
        }
        this.calificacion = nuevaCalificacion;
        this.estado = new Calificado();
    }

    public void rechazar() {
        if(!this.obtenerEstadoAtuendo().equals("Nuevo")) {
            throw new NoSePuedeRechazarException("Solo se puede rechazar un atuendo con estado = Nuevo");
        }
        this.estado = new Rechazado();
    }


    public void cambiarEstado(EstadoAtuendo estado) {
        this.estado = estado;
    }


}