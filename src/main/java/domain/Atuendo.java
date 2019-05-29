package domain;
import domain.EstadoAtuendo.EstadoAtuendo;


import domain.EstadoAtuendo.Nuevo;
import exceptions.PrendaInvalidaException;

import java.util.Objects;

public class Atuendo {

    private Prenda accesorio;
    private Prenda prendaSuperior;
    private Prenda prendaInferior;
    private Prenda calzado;
    private EstadoAtuendo estado;


    public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado, Prenda accesorio) {
        this.validarPrendas(prendaSuperior, prendaInferior, calzado, accesorio);
        this.accesorio = accesorio;
        this.prendaSuperior = prendaSuperior;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
        this.estado = new Nuevo(this); //todo atuendo nace en estado nuevo.
    }

    private void validarPrendas(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado, Prenda accesorio) {
        String mensajeDeError = "";
        if (prendaSuperior.obtenerCategoria() != Categoria.PARTE_SUPERIOR) {
            mensajeDeError = mensajeDeError.concat("La prenda superior no es válida. ");
        }
        if (prendaInferior.obtenerCategoria() != Categoria.PARTE_INFERIOR) {
            mensajeDeError = mensajeDeError.concat("La prenda inferior no es válida. ");
        }
        if (calzado.obtenerCategoria() != Categoria.CALZADO) {
            mensajeDeError = mensajeDeError.concat("El calzado no es válido. ");
        }
        if (accesorio.obtenerCategoria() != Categoria.ACCESORIO) {
            mensajeDeError = mensajeDeError.concat("El accesorio no es válido. ");
        }
        if (mensajeDeError != "") {
            throw new PrendaInvalidaException(mensajeDeError);
        }
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

    public EstadoAtuendo obtenerEstadoAtuendo() { return this.estado; }

    public int obtenerCalificacionAnterior() {return this.estado.obtenerCalificacionAnterior();}
    public int obtenerCalificacionActual() {return this.estado.obtenerCalificacionActual();}


    public void aceptar() {
        this.estado.aceptar();
    }

    public void calificar(int nuevaCalificacion) {
        this.estado.calificar(nuevaCalificacion);
    }

    public void rechazar() {
        this.estado.rechazar();

    }
    public void cambiarEstado(EstadoAtuendo estado) {
        this.estado = estado;
    }
    public boolean estaCalificado() {
        return this.obtenerCalificacionActual()>0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atuendo atuendo = (Atuendo) o;
        return Objects.equals(accesorio, atuendo.obtenerAccesorio()) &&
                Objects.equals(prendaSuperior, atuendo.obtenerPrendaSuperior()) &&
                Objects.equals(prendaInferior, atuendo.obtenerPrendaInferior()) &&
                Objects.equals(calzado, atuendo.obtenerCalzado());
    }






}